l4fApp.controller('registrationController', ['$rootScope', '$scope', '$http', '$location',
    function ($rootScope, $scope, $http, $location) {

        $scope.isUsernameValid = function () {
            var usernameField = $scope.registrationForm.username;
            var username = $scope.credentials.username;

            if (!usernameField.$dirty) {
                return true;
            }

            if (usernameField.$error.required) {
                $scope.username.errorMessage = 'Username should be not empty';
                return false;
            }

            var usernameRegex = /\w{2,20}/i;

            if (!usernameRegex.test(username)) {
                $scope.username.errorMessage = 'Username should consist of letters, numbers or _ and has length 2-20';
                return false;
            }

            return true;
        };

        $scope.isEmailValid = function() {
            var emailField = $scope.registrationForm.email;

            if (!emailField.$dirty) {
                return true;
            }

            if (emailField.$error.required) {
                $scope.email.errorMessage = 'Email should be not empty';
                return false;
            }

            if (emailField.$invalid) {
                $scope.email.errorMessage = 'Invalid email';
                return false;
            }

            return true;
        };

        $scope.isFormValid = function() {
          return $scope.isUsernameValid() && $scope.isEmailValid();
        };

        $scope.register = function () {
            $http.post('/user/register', $scope.credentials).success(function (data) {
                if (data.error) {
                    $scope.registrationError = data.error;
                }
                else {
                    $location.path('/login');
                }
            })
        };
    }]);