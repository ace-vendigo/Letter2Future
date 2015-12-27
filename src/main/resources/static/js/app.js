var l2fApp = angular.module("letter2future", ['ngRoute']);

l2fApp.config(['$routeProvider', 'httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider.when('/', {
        templateUrl: 'home.html'
    }).when('/login', {
        templateUrl : 'login.html'
    }).otherwise('/');
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);