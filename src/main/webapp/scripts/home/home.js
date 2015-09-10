'use strict';

angular.module('app')


    .factory('Product', ['$resource', function ($resource) {
        return $resource('/api/product/:id', null,
            {
                'update':   {method:'PUT'}
            });
    }])
    ;