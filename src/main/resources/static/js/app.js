var l4fApp = angular.module("letter2future", ['ngRoute']);

l4fApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'navigationController'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'navigationController'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}]);