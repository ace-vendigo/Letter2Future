function helloController($scope, $http) {
    $scope.message = "Hello from Ctrl";
    $http.get("/user/info").success(function (data) {
        $scope.greeting = data;
    })
}