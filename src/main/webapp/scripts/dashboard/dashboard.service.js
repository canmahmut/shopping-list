'use strict';


angular.module('certiApp')
    .factory('StatikService',
    ['$resource', 'REST_URL_SERVER',
        function ($resource, REST_URL_SERVER) {
            return $resource(REST_URL_SERVER + '/statistic', null
            );
        }
    ]
)

;