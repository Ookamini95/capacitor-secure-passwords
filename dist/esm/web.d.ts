import { WebPlugin } from '@capacitor/core';
import type { SecurePasswordPlugin } from './definitions';
export declare class SecurePasswordWeb extends WebPlugin implements SecurePasswordPlugin {
    save(options: {
        key: string;
        data: string;
    }): Promise<void>;
    read(options: {
        key: string;
    }): Promise<{
        value: string;
    }>;
}
