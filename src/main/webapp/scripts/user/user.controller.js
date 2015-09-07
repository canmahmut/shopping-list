'use strict';


angular.module('certiApp')
    .controller('UserController', function ($scope, account, toaster, Principal, Auth) {
        $scope.account = account;
        $scope.submit = function () {
            Auth.updateAccount($scope.account).then(function () {
                $scope.error = null;
                $scope.success = 'OK';
                Principal.identity().then(function (account) {
                    $scope.account = account;
                });
                toaster.pop('success', 'Benutzerdaten Ändern', 'Die Benutzerdaten wurden erfolgreich verändert.');
            }).catch(function () {
                $scope.success = null;
                $scope.error = 'ERROR';
                toaster.pop('error', 'Benutzerdaten Ändern', 'Die Benutzerdaten wurden nicht verändert.');
            });
        };


    })

    .controller('LoggedUserController', function ($scope, Principal) {
        Principal.identity().then(function (account) {
            $scope.login = account;
        });
    })


    .controller('UserPasswortController', function ($scope, Auth, Principal, toaster, $state) {
        Principal.identity().then(function (account) {
            $scope.account = account;
        });
        $scope.passwordModel = {};
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.cancel = function () {
            $scope.$dismiss();
            $state.go('app.private.user.detail');
        };
        $scope.submit = function () {
            if ($scope.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.doNotMatch = null;
                Auth.changePassword($scope.passwordModel.password).then(function () {
                    $scope.error = null;
                    $scope.success = 'OK';
                    toaster.pop('success', 'Passwort Ändern', 'Das Passwort wurde verändert.');
                    $state.go('app.private.user.detail');
                    $scope.$close(true);
                }).catch(function () {
                    $scope.success = null;
                    $scope.error = 'ERROR';
                    $state.go('app.private.user.detail');
                    toaster.pop('error', 'Passwort Ändern', 'Das Passwort wurde nicht verändert.');
                });
            }
        };
    })
;
