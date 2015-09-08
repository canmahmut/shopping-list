'use strict';


angular.module('app')


    .factory('ShoppingList', ['$resource', function ($resource) {
        return $resource('/api/shoppingList/:id', null,
            {
            });
    }]);