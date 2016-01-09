l4fApp.controller('letterController', ['$rootScope', '$scope', '$http', '$location',
    function ($rootScope, $scope, $http, $location) {

        $scope.letter = {};
        $scope.letter.user = $rootScope.currentUser;

        $scope.submitLetter = function () {
            $http.post('/letter', $scope.letter).success(function () {
                $location.path('/');
            })
        };
    }]);