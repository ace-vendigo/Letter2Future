'use strict';
(function() {
  angular.module('L2F.Common').controller('MainController', MainController);

  function MainController() {
      var auth = this;
      auth.authenticated = false;

      auth.logout = function() {
          console.log("Logout");
      }
  }
})();