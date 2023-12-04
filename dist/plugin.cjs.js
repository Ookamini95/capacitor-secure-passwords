'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

core.registerPlugin('SecurePassword');

const SecurePassword = core.registerPlugin('SecurePassword', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.SecurePasswordWeb()),
});

class SecurePasswordWeb extends core.WebPlugin {
    async save(options) {
        throw this.unimplemented('Not implemented on web');
    }
    async read(options) {
        throw this.unimplemented('Not implemented on web');
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    SecurePasswordWeb: SecurePasswordWeb
});

exports.SecurePassword = SecurePassword;
//# sourceMappingURL=plugin.cjs.js.map
