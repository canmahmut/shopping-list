'use strict';

angular
    .module('app')

    .controller('AtHomeCtrl', function ($scope, products, underscore, ShoppingItem) {


        var self = this;


        self.products = products;


        var updateProduct = function (p) {
            var match = underscore.find(self.products, function (item) {
                return item.product.id === p.product.id
            });
            if (match) {
                match.stock = p.stock;
                match.id = p.id;
                match.done = p.done;
                console.log(match);
            }
        };

        self.decreaseStock = function (item) {
            if (item.id != null) {
                item.stock = item.stock - 1;
                item.done = false;
                ShoppingItem.update({id: item.id}, item).$promise.then(function (data) {
                });
            } else {
                ShoppingItem.save({}, item
                ).$promise.then(function (data) {
                        updateProduct(data);
                    });
            }

        };

        self.addProduct = function () {
            if (self.name != null) {
                self.name = '';
            }
        };


        self.increaseStock = function (item) {
            if (item.id != null) {
                item.stock = item.stock + 1;
                item.done = false;
                ShoppingItem.update({id: item.id}, item).$promise.then(function (data) {
                });
            } else {
                ShoppingItem.save({}, item
                ).$promise.then(function (data) {
                        updateProduct(data);
                    });
            }
        };


    })
;