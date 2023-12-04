import { WebPlugin } from '@capacitor/core';

import type { SecurePasswordPlugin } from './definitions';

export class SecurePasswordWeb
  extends WebPlugin
  implements SecurePasswordPlugin
{
  async save(options: { key: string, data: string }): Promise<void> {
    options
    throw this.unimplemented('Not implemented on web')
  }

  async read(options: { key: string; }): Promise<{ value: string; }> {
    options
    throw this.unimplemented('Not implemented on web')
  }
}
