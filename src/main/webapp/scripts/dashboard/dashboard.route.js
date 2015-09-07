'use strict';


angular.module('certiApp')
    .config(
    function ($stateProvider) {
        $stateProvider
            .state('app.private.dashboard1', {
                url: "/dashboard1",
                templateUrl: "views/main.html",
                controller: 'MainCtrl'
            })
    });


angular.module('certiApp')
    .config(
    function ($stateProvider) {
        $stateProvider
            .state('app.private.dashboard', {
                url: "/dashboard",

                templateUrl: "scripts/main.html",
                views: {
                    'breadcrumb': {
                        templateUrl: "scripts/dashboard/breadcrumb.html"
                    },
                    'content': {
                        controller: 'MainCtrl',
                        templateUrl: "scripts/dashboard/dashboard.html",
                        controllerAs: 'ct',
                        resolve: {
                            statistics: function (StatikService, $q) {
                                var deferred = $q.defer();
                                StatikService.get().$promise.then(
                                    function (data) {
                                        deferred.resolve(data);
                                    },
                                    function () {
                                        deferred.reject();
                                    }
                                );
                                return deferred.promise;
                            }
                        }
                    }
                }
            })


    })
;