'use strict';
(function () {
    angular.module('L2F.Common').controller('HomeController', ['$http', HomeController]);

    function HomeController($http) {
        var home = this;

        $http.get('news').success(function (response) {
            home.news = response.news;
        });

    }
})();