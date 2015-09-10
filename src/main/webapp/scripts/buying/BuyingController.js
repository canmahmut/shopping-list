'use strict';

angular
    .module('app')

    .controller('BuyingController', function ($scope, listItem, ShoppingItem, underscore, $q) {

        var self = this;


        self.data = listItem;


        self.toggle = function (item) {
            if (item.id != null) {
                item.done = !item.done;
                ShoppingItem.update({id: item.id}, item).$promise.then(function (data) {
                });
            }

        };


        self.closeBuying = function () {
            var promises = [];
            underscore.each(underscore.where(self.data, {done: true}), function (item) {
                promises.push(ShoppingItem.delete({id: item.id}, item).$promise);
            });
            $q.all(promises).then(function (data) {
                ShoppingItem.query().$promise.then(function (data) {
                    self.data = data;
                });
            });
        }

    })

;
