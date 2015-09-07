'use strict';


angular.module('certiApp')

    .config(
    function ($stateProvider) {
        $stateProvider


            .state('app.private.user', {
                url: '/user',
                parent: 'app.private',
                abstract: true
            })

            .state('app.private.user.detail', {
                url: '',
                parent: 'app.private.user',

                views: {
                    'breadcrumb@app.private': {
                        templateUrl: "scripts/user/user.menu.html"
                    },
                    'content@app.private': {
                        templateUrl: 'scripts/user/detail.html',
                        controller: 'UserController',
                        resolve: {
                            account: function ($q, Principal) {
                                var deferred = $q.defer();
                                Principal.identity().then(
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


            .state('app.private.user.detail.passwortChange', {
                url: "/passwort",

                onEnter: ['$stateParams', '$state', '$modal', '$rootScope', function ($stateParams, $state, $modal, $rootScope) {
                    var a = $modal.open({
                        templateUrl: "/scripts/user/passwortChange.html",
                        controller: 'UserPasswortController',
                        resolve: {
                            account: function ($q, Principal) {
                                var deferred = $q.defer();
                                Principal.identity().then(
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

                    });
                }
                ]

            })
    })


;

