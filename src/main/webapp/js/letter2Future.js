'use strict';
(function () {

    var l2FApp = angular.module('L2F', [
        'ngRoute',
        'L2F.Common'
    ]);

    l2FApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'partials/public/home.html'
        }).when('/login', {
            templateUrl: 'partials/public/login.html'
        }).when("/register", {
            templateUrl: 'partials/public/register.html',
            controller: 'registrationController'
        }).when("/letter/new", {
            templateUrl: 'partials/new-letter.html',
            controller: 'letterController'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    }]);

})();
