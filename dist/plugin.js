var capacitorSecurePassword = (function (exports, core) {
    'use strict';

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

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
