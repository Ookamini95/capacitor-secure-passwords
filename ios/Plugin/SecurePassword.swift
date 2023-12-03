import Foundation

@objc public class SecurePassword: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
