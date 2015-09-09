'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'scripts/home/home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl',
            resolve: {
                products: function (Product, $q, underscore) {
                    var deferred = $q.defer();
                    Product.query({}).$promise.then(function (data) {


                        var arr = [];
                        underscore.each(data, function (item) {
                            arr.push({

                                    stock: 0,
                                    product: item
                                }
                            )
                        });


                        deferred.resolve(arr);


                    }, function (data) {
                        deferred.reject();
                    });
                    return deferred.promise;
                }
            }
        });


    });
