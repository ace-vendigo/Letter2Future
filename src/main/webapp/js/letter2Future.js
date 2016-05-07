'use strict';
(function () {

    var l2FApp = angular.module('L2F', [
        'ngRoute',
        'L2F.Common',
        'L2F.User'
    ]);

    l2FApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'partials/public/home.html',
            controller: 'HomeController',
            controllerAs: 'home'
        }).when('/login', {
            templateUrl: 'partials/public/login.html',
            controller: 'AuthController',
            controllerAs: 'auth'
        }).when('/register', {
            templateUrl: 'partials/public/register.html',
            controller: 'UserController',
            controllerAs: 'user'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    }]);

    l2FApp.constant('URLs', {
        home: '/',
        currentUser: 'user',
        logout: 'logout',
        login: 'login'
    });

})();
