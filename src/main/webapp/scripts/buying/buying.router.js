'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('buying', {
            url: '/buying',
            templateUrl: 'scripts/buying/shoppingItems.html',
            controller: 'BuyingController',
            controllerAs: 'ctrl',
            resolve: {
                listItem: function (ShoppingItem, $q) {
                    var deferred = $q.defer();
                    ShoppingItem.query().$promise.then(function (data) {
                        deferred.resolve(data);
                    }, function (data) {
                        console.log(data);
                        deferred.reject();
                    });
                    return deferred.promise;
                }
            },
            reloadOnSearch: false
        });
    });
