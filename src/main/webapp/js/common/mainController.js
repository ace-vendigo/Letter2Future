'use strict';
(function() {
  angular.module('L2F.Common').controller('MainController', ['AuthService', MainController]);

  function MainController(AuthService) {
      var main = this;

      AuthService.authenticate();

      main.credentials = {};

      main.login = function() {
          AuthService.login(main.credentials);
      };

      main.logout = function() {
          AuthService.logout();
      };
  }
})();