'use strict';

angular
    .module('app')

    .controller('AtHomeCtrl', function ($scope, products, underscore, ShoppingItem, Product) {


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

        self.edit = function (item) {
            self.selectedId = item.product.id;
        };

        self.updateProduct = function (item) {

            Product.update({id: item.product.id}, item.product).$promise.then(function (data) {
                self.selectedId = null;
            }, function () {
                self.globalError = 'Produkt exitiert bereits';
            });
        };


        self.addProduct = function () {
            self.error = '';
            if (self.name != null) {
                Product.save({}, self.name).$promise.then(function (data) {
                    self.name = '';
                    self.products.push({
                        stock: 0,
                        product: data,
                        done: false
                    });
                }, function () {
                    self.error = 'Produkt exitiert bereits';
                });
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