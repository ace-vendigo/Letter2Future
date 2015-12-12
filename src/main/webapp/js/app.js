var app = angular.module("letter2future", ["ngRoute"]);

app.config(function ($routeProvider, $httpProvider) {
    $routeProvider.when("/", {
        templateUrl: "home.html",
        controller: "hello"
    }).when("/login", {
        templateUrl: "login.html",
        controller: "navigation"
    }).otherwise("/");

    $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
});

app.controller("navigation", function () {
});

