package com.capacitor.plugins.securepsw;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;

@CapacitorPlugin(name = "SecurePassword")
public class SecurePasswordPlugin extends Plugin {

    private SecurePassword securePassword;

    @Override
    public void load() {
        securePassword = new SecurePassword(getContext());
    }

    @PluginMethod
    public void save(PluginCall call) {
        String key = call.getString("key");
        String data = call.getString("data");

        if (key == null || data == null) {
        call.reject("Please provide a key and data.");
        return;
        }

        try {
        securePassword.savePassword(key, data);
        JSObject ret = new JSObject();
        ret.put("success", true);
        call.resolve(ret);
        } catch (Exception e) {
        call.reject("Error saving password: " + e.getLocalizedMessage(), e);
        }
    }

    @PluginMethod
    public void read(PluginCall call) {
        String key = call.getString("key");

        if (key == null) {
        call.reject("Please provide a key.");
        return;
        }

        try {
        String data = securePassword.getPassword(key);
        if (data != null) {
            JSObject ret = new JSObject();
            ret.put("value", data);
            call.resolve(ret);
        } else {
            call.reject("No data found for the given key");
        }
        } catch (Exception e) {
        call.reject("Error reading password: " + e.getLocalizedMessage(), e);
        }
    }
}
