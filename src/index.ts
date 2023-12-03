import { registerPlugin } from '@capacitor/core';

import type { SecurePasswordPlugin } from './definitions';

const SecurePassword = registerPlugin<SecurePasswordPlugin>('SecurePassword', {
  web: () => import('./web').then(m => new m.SecurePasswordWeb()),
});

export * from './definitions';
export { SecurePassword };
