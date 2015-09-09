'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'scripts/home/home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl',
            resolve: {
                products: function (Product, $q) {
                    var deferred = $q.defer();
                    Product.query({}).$promise.then(function (data) {
                        deferred.resolve(data);
                    }, function (data) {
                        console.log(data);
                        deferred.reject();
                    });
                    return deferred.promise;
                }
            }
        });


    });
