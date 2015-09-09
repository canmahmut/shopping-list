'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'scripts/home/home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl',
            resolve: {
                products: function (Product, ShoppingItem, $q, underscore) {
                    var deferred = $q.defer();
                    var arr = [];
                    $q.all([Product.query({}).$promise, ShoppingItem.query({}).$promise]).then(function (data) {
                        underscore.each(data[0], function (p) {
                            var b =underscore.chain(data[1]).filter(function (obj) {
                                return p.id == obj.product.id;
                            }).first().value();
                            if (b) {
                                arr.push({
                                    stock: b.stock,
                                    product: b.product,
                                    done: b.done,
                                    id: b.id
                                });
                            } else {
                                arr.push({
                                    stock: 0,
                                    product: p,
                                    done: false
                                });
                            }
                        });
                        deferred.resolve(arr);
                    });


                    return deferred.promise;
                }
            }
        });


    });
