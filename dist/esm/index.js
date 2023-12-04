import { registerPlugin } from '@capacitor/core';
const SecurePassword = registerPlugin('SecurePassword', {
    web: () => import('./web').then(m => new m.SecurePasswordWeb()),
});
export * from './definitions';
export { SecurePassword };
//# sourceMappingURL=index.js.map