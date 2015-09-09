'use strict';


angular.module('app')


    .factory('ShoppingList', ['$resource', function ($resource) {
        return $resource('/api/shoppingList/:id', null,
            {
                'update':   {method:'PUT'}
            });
    }])

    .factory('ShoppingItem', ['$resource', function ($resource) {
        return $resource('/api/shoppingItem/:id', null,
            {
                'update':   {method:'PUT'}
            });
    }])
;