(function () {
    var app = angular.module("letter", ["ngRoute"])
        .config(function ($routeProvider, $httpProvider) {
            $routeProvider.when("/", {
                templateUrl: "home.html",
                controller: "hello"
                }).when("/login", {
                    templateUrl: "login.html",
                    controller: "navigation"
                }).otherwise("/");

            $httpProvider.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
        })
        .controller("hello", helloController)
        .controller("navigation", function () {
        });
})();
