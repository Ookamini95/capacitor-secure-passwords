import { WebPlugin } from '@capacitor/core';
export class SecurePasswordWeb extends WebPlugin {
    async save(options) {
        options;
        throw this.unimplemented('Not implemented on web');
    }
    async read(options) {
        options;
        throw this.unimplemented('Not implemented on web');
    }
}
//# sourceMappingURL=web.js.map