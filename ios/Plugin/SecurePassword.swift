import Foundation

@objc public class SecurePassword: NSObject {
       
    private var query: [String: Any] = [:]
    
    @objc public func save(data: String, key: String) throws {
        let encoded = data.data(using: .utf8) ?? Data()
        
        self.query = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: key,
            kSecValueData as String: encoded
        ]

        let status = SecItemUpdate(query as CFDictionary, [kSecValueData as String: encoded] as CFDictionary)

        if status == errSecItemNotFound {
            try addItem(data: encoded, key: key)
        } else if status != errSecSuccess {
            throw StoreError.keychainError(status: status, operation: "update")
        }
    }
    
    private func addItem(data: Data, key: String) throws {
        let status = SecItemAdd(query as CFDictionary, nil)
        if status != errSecSuccess {
            throw StoreError.keychainError(status: status, operation: "add")
        }
    }
    
    @objc public func read(key: String) throws -> String {
        let query: [String: Any] = [
            kSecClass as String: kSecClassGenericPassword,
            kSecAttrAccount as String: key,
            kSecMatchLimit as String: kSecMatchLimitOne,
            kSecReturnAttributes as String: true,
            kSecReturnData as String: true
        ]

        var item: CFTypeRef?
        let status = SecItemCopyMatching(query as CFDictionary, &item)

        if status != errSecSuccess {
            throw StoreError.keychainError(status: status, operation: "read")
        }

        guard let foundItem = item as? [String: Any] else {
                    throw StoreError.unexpectedItemFormat
                }
        
        return try password(from: foundItem)
    }

    private func password(from keychainItem: [String: Any]) throws -> String {
        guard let passwordData = keychainItem[kSecValueData as String] as? Data,
              let password = String(data: passwordData, encoding: .utf8) else {
            throw StoreError.dataConversionError
        }
        return password
    }
}

enum StoreError: Error {
    case keychainError(status: OSStatus, operation: String)
    case unexpectedItemFormat
    case dataConversionError

    var localizedDescription: String {
        switch self {
        case .keychainError(let status, let operation):
            return "Keychain error during \(operation) with status: \(status)"
        case .unexpectedItemFormat:
            return "Unexpected format of the keychain item."
        case .dataConversionError:
            return "Failed to convert keychain data."
        }
    }
}
