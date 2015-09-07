'use strict';

angular.module('authModule')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


