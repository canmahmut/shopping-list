'use strict';


angular.module('certiApp')
    .controller('MainCtrl', function ($scope, statistics) {
        var self = this;
        self.statistics = statistics;

    })
;
