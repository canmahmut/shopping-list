'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('buying', {
            url: '/einkaufen',
            templateUrl: 'scripts/buying/einkaufen.html',
            controller: 'BuyingController',
            controllerAs: 'ctrl',
            resolve: {
                listItem: function (ShoppingList, $q) {
                    var deferred = $q.defer();
                    ShoppingList.query({}).$promise.then(function (data) {
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

        $stateProvider.state('buyingId', {
            url: '/einkaufen/{id:int}',
            templateUrl: 'scripts/buying/shoppingItems.html',
            controller: 'ShoppingListItemController',
            controllerAs: 'ctrl',
            resolve: {
                listItemById: function (ShoppingList, $q, $stateParams) {
                    var deferred = $q.defer();
                    ShoppingList.get({id: $stateParams.id}).$promise.then(function (data) {
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
