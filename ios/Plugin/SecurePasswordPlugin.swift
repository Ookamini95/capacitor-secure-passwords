import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(SecurePasswordPlugin)
public class SecurePasswordPlugin: CAPPlugin {
    private let implementation = SecurePassword()

    @objc func save(_ call: CAPPluginCall) {
        guard let data = call.getString("data"), let key = call.getString("key") else {
            call.reject("Missing parameters")
            return
        }

        do {
            try implementation.save(data: data, key: key)
            call.resolve()
        } catch {
            call.reject("Failed to save data: \(error.localizedDescription)")
        }
    }
    
    @objc func read(_ call: CAPPluginCall) {
        guard let key = call.getString("key") else {
            call.reject("Missing key")
            return
        }

        do {
            let data = try implementation.read(key: key)
            call.resolve(["value": data])
        } catch {
            call.reject("Failed to read data: \(error.localizedDescription)")
        }
    }
}
