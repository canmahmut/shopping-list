'use strict';


angular.module('certiApp')
    .controller('AccountController', function ($scope, Principal, Auth, $state) {
        Principal.identity().then(function (account) {
            $scope.login = account;
        });

        $scope.logout = function () {
            Auth.logout();
            $state.go('app.public.login');
        }
    });

