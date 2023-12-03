import { registerPlugin } from '@capacitor/core';

export interface SecurePasswordPlugin {
    /**
     * Saves a password securely.
     * 
     * This method encrypts and stores a password using the specified key.
     * The encryption is handled natively using the platform's secure storage.
     * 
     * @param options The options including the key and the password to save.
     * @returns A promise that resolves if the password is saved successfully.
     */
    save(options: { key: string; data: string }): Promise<{ success: boolean }>;

    /**
     * Reads a securely stored password.
     * 
     * This method retrieves and decrypts a password associated with the specified key.
     * The decryption is handled natively using the platform's secure storage.
     * 
     * @param options The options including the key to retrieve the password for.
     * @returns A promise that resolves with the password, or rejects if not found.
     */
    read(options: { key: string }): Promise<{ value: string }>;
}

const SecurePassword = registerPlugin<SecurePasswordPlugin>('SecurePassword');

export default SecurePassword;
