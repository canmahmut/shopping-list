'use strict';

angular
    .module('app')

    .controller('BuyingController', function ($scope, BucketData) {

        var self = this;


        self.data = BucketData.selected();

    });