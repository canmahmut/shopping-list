'use strict';

angular
    .module('app')

    .controller('BuyingController', function ($scope, listItem, ShoppingItem) {

        var self = this;


        self.data = listItem;


        self.toggle = function (item) {
            console.log('toggle' + JSON.stringify(item));
            if (item.id != null) {
                item.done = !item.done;
                ShoppingItem.update({id: item.id}, item).$promise.then(function (data) {
                });
            }

        };

    })

;
