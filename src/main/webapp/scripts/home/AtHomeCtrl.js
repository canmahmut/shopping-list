'use strict';

angular
    .module('app')

    .controller('AtHomeCtrl', function ($scope, products, underscore, ShoppingItem) {


        var self = this;


        self.products = products;


        //ShoppingItem.query().$promise.then(function (data) {
        //    self.shoppingList = data;
        //});


        var ax = function (item) {
            return underscore.findWhere(self.products, {id: item.id});
        };

        self.decreaseStock = function (item) {
            if (ax(item) == 'undefined') {
                self.shoppingList.selectedItems.push(item);
            }
            //if (item.stock >= 1) {
            //    var byId = getById(self.products, item.id);
            //    byId.stock--;
            //    if (byId.stock == 0) {
            //        //BucketData.delete(byId);
            //    }
            //}
        };

        self.addProduct = function () {
            if (self.name != null) {
                //BucketData.putToList({
                //        id: Math.random(), name: self.name, stock: 0
                //    }
                //);
                self.name = '';
            }
        };


        self.increaseStock = function (item) {
            console.log('increase' + JSON.stringify(item));

            if (item.id != null) {
                console.log('updating');
                item.stock = item.stock + 1;
                ShoppingItem.update({id: item.id}, item).$promise.then(function (data) {
                    //var find = ax(data);
                    //console.log('find' + JSON.stringify(find));
                    //find.stock = data.stock;
                });
            } else {
                console.log('saving');
                ShoppingItem.save({}, item
                ).$promise.then(function (data) {
                        self.products.push(data);
                    });
            }
        };


    })
;