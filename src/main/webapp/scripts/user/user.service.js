'use strict';

angular.module('certiApp')
    .factory('Account1', function ($resource, REST_URL_SERVER) {
        return $resource(REST_URL_SERVER + '/account', null,
            {

                'update': {
                    method: 'PUT'
                }
            }
        )
            ;
    });