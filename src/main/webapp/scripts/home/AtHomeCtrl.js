'use strict';

angular
    .module('app')

    .controller('AtHomeCtrl', function ($scope, products,selectedProducts) {






        var self = this;

        self.products = products;

        self.decreaseStock = function (item) {
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
            var byId = getById(self.products, item.id);
            //BucketData.delete(byId);
            //BucketData.put(item);
            byId.stock++;
        };



    })
;