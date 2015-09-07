'use strict';

/**
 * @ngdoc overview
 * @name certiApp
 * @description
 * # certiApp
 *
 * Main module of the application.
 */
angular
    .module('certiApp', [
        'ngAnimate',
        'jcs-autoValidate',
        'ui.router',
        'ngCookies',
        'ngCacheBuster',
        'ngResource',
        'ngSanitize',
        'ngTable',
        'toaster',
        'ngTouch',
        'ui.date',
        'checklist-model',
        'ui.bootstrap',
        'angularFileUpload',
        'ui.select',
        'ng-currency',
        'authModule',
        'pascalprecht.translate',
        'LocalStorageModule',
        'validation.match'
    ]);

angular.module('certiApp').controller('NavController', function ($rootScope, $scope) {
    $rootScope.$on('EMPLOYEE_NAV_EVENT', function (event, data) {
        $scope.employee = data;
    });
});

angular.module('certiApp')
    .constant('REST_URL', '/api')
    .constant('REST_URL_SERVER', './api')
    .filter('cnDate', function ($filter) {
        return function (input) {
            if (input == null) {
                return "";
            }
            var _date = $filter('date')(new Date(input), 'dd.MM.yyyy');
            return _date.toUpperCase();
        };
    });

;


angular.module('certiApp')
    .controller('MasterCtrl', ['$scope', '$cookieStore', function ($scope, $cookieStore) {
        /**
         * Sidebar Toggle & Cookie Control
         */
        var mobileView = 992;

        $scope.getWidth = function () {
            return window.innerWidth;
        };

        $scope.$watch($scope.getWidth, function (newValue, oldValue) {
            if (newValue >= mobileView) {
                if (angular.isDefined($cookieStore.get('toggle'))) {
                    $scope.toggle = !$cookieStore.get('toggle') ? false : true;
                } else {
                    $scope.toggle = true;
                }
            } else {
                $scope.toggle = false;
            }

        });

        $scope.toggleSidebar = function () {
            $scope.toggle = !$scope.toggle;
            $cookieStore.put('toggle', $scope.toggle);
        };

        window.onresize = function () {
            $scope.$apply();
        };
    }]);

angular.module('certiApp')

    .controller('NavbarController', function ($scope, $location, $state) {
        ////$scope.isAuthenticated = Principal.isAuthenticated;
        //$scope.$state = $state;
        //
        //$scope.logout = function () {
        //    Auth.logout();
        //    $state.go('app.public.login');
        //};
    })

;