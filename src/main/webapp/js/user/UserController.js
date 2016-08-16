'use strict';
(function () {
    angular.module('L2F.User').controller('UserController', ['UserService', UserController]);

    function UserController(UserService) {
        var user = this;
    }
})();