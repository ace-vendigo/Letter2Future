var app = angular.module("letter2future", []);

app.config(['$routeProvider', 'httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider.when('/', {
        templateUrl: 'home.html'
    }).when('/sign-up', {
        templateUrl : 'sign-up.html'
    }).otherwise('/');
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);