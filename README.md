# capacitor-secure-passwords

Short plugin that exposes native secure layer APIs

## Install

```bash
npm install capacitor-secure-passwords
npx cap sync
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
save(options: { key: string; data: string; }) => Promise<{ success: boolean; }>
```

Saves a password securely.

This method encrypts and stores a password using the specified key.
The encryption is handled natively using the platform's secure storage.

| Param         | Type                                        | Description                                             |
| ------------- | ------------------------------------------- | ------------------------------------------------------- |
| **`options`** | <code>{ key: string; data: string; }</code> | The options including the key and the password to save. |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

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
