'use strict';


angular.module('app')


    .factory('ShoppingList', ['$resource', function ($resource) {
        return $resource('/api/shoppingList/:id', null,
            {
                'update':   {method:'PUT'}
            });
    }])

    .factory('ShoppingListItem', ['$resource', function ($resource) {
        return $resource('/api/shoppingList/:shoppingListId/:id', null,
            {
                'update':   {method:'PUT'}
            });
    }])
;