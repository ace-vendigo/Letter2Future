var l4fApp = angular.module("letter2future", ['ngRoute', 'ngStorage']);

l4fApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider.when('/', {
        templateUrl: 'partials/home.html',
        controller: 'navigationController'
    }).when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'navigationController'
    }).when("/register", {
        templateUrl: 'partials/register.html',
        controller: 'registrationController'
    }).when("/letter/new", {
        templateUrl: 'partials/new-letter.html',
        controller: 'letterController'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]);