# capacitor-secure-passwords

Short plugin that exposes native secure layer APIs (KeyChain and KeyStore) to save simple key/value encrypted pairs

## Install

```bash
npm install github:Ookamini95/capacitor-secure-passwords
npx cap sync
```

## How to use

Manually import the plugin like so
```js
    import { SecurePassword } from 'capacitor-secure-passwords';
```

and use it like this

```js
    async saveSecretPassword() {
        try {
            await SecurePassword.save({
                key: "secret-key",
                data: "very-secret" // ... JSON.stringify({accessToken, refreshToken})
                })
        } catch (error) {
            console.error(error)
        }
    }

    async showSecret() {
        try {
            const { value } = await SecurePassword.read({
                key: "secret-key"
            })
            console.log(value)
        } catch (error) {
            console.log(JSON.stringify(error))
        }
    }
```


## API

<docgen-index>

* [`save(...)`](#save)
* [`read(...)`](#read)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### save(...)

```typescript
save(options: { key: string; data: string; }) => Promise<void>
```

Saves a password securely.

This method encrypts and stores a password using the specified key.
The encryption is handled natively using the platform's secure storage.

| Param         | Type                                        | Description                                             |
| ------------- | ------------------------------------------- | ------------------------------------------------------- |
| **`options`** | <code>{ key: string; data: string; }</code> | The options including the key and the password to save. |

--------------------


### read(...)

```typescript
read(options: { key: string; }) => Promise<{ value: string; }>
```

Reads a securely stored password.

This method retrieves and decrypts a password associated with the specified key.
The decryption is handled natively using the platform's secure storage.

| Param         | Type                          | Description                                                 |
| ------------- | ----------------------------- | ----------------------------------------------------------- |
| **`options`** | <code>{ key: string; }</code> | The options including the key to retrieve the password for. |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------

</docgen-api>
