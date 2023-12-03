import { WebPlugin } from '@capacitor/core';

import type { SecurePasswordPlugin } from './definitions';

export class SecurePasswordWeb
  extends WebPlugin
  implements SecurePasswordPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
