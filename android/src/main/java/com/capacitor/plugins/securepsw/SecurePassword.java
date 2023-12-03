package com.capacitor.plugins.securepsw;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class SecurePassword {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String ENCRYPTION_BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM;
    private static final String ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_NONE;
    private static final String ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES;
    private static final String KEY_ALIAS = "MyKeyAlias";
    private static final String SHARED_PREFERENCES_FILE = "SecurePreferences";

    private Context context;

    public SecurePassword(Context context) {
        this.context = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            createKeyIfNotExists();
        }
    }

    public void savePassword(String key, String password) {
        String encryptedPassword = encrypt(password);
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        .edit()
        .putString(key, encryptedPassword)
        .apply();
    }

    public String getPassword(String key) {
        String encryptedPassword = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        .getString(key, null);
        return encryptedPassword != null ? decrypt(encryptedPassword) : null;
    }

    private String encrypt(String data) {
        try {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = getKeyFromKeystore();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] ivBytes = cipher.getIV();
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        byte[] ivAndEncryptedData = new byte[ivBytes.length + encryptedData.length];
        System.arraycopy(ivBytes, 0, ivAndEncryptedData, 0, ivBytes.length);
        System.arraycopy(encryptedData, 0, ivAndEncryptedData, ivBytes.length, encryptedData.length);
        return Base64.encodeToString(ivAndEncryptedData, Base64.DEFAULT);
        } catch (Exception e) {
        throw new RuntimeException("Encrypt exception", e);
        }
    }

    private String decrypt(String encryptedData) {
        try {
        byte[] ivAndEncryptedData = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] ivBytes = new byte[12]; // GCM IV length is 12 bytes
        System.arraycopy(ivAndEncryptedData, 0, ivBytes, 0, ivBytes.length);
        byte[] dataBytes = new byte[ivAndEncryptedData.length - ivBytes.length];
        System.arraycopy(ivAndEncryptedData, ivBytes.length, dataBytes, 0, dataBytes.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = getKeyFromKeystore();
        GCMParameterSpec spec = new GCMParameterSpec(128, ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] decryptedData = cipher.doFinal(dataBytes);
        return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
        throw new RuntimeException("Decrypt exception", e);
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private void createKeyIfNotExists() {
        try {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM, ANDROID_KEYSTORE);
            KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(ENCRYPTION_BLOCK_MODE)
            .setEncryptionPaddings(ENCRYPTION_PADDING)
            .setRandomizedEncryptionRequired(true)
            .build();
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        }
        } catch (Exception e) {
        throw new RuntimeException("Key generation exception", e);
        }
    }

    private SecretKey getKeyFromKeystore() {
        try {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        KeyStore.SecretKeyEntry keyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
        return keyEntry.getSecretKey();
        } catch (Exception e) {
        throw new RuntimeException("Keystore access exception", e);
        }
    }
}
