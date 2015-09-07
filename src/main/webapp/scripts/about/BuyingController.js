'use strict';

angular.module('ZettelApp.controllers.Main')

    .controller('BuyingController', function ($scope, BucketData) {

        var self = this;


        self.data = BucketData.selected();

    });