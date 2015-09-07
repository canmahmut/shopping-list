'use strict';

angular.module('ZettelApp.controllers.Main')

    .controller('AtHomeCtrl', function ($scope, BucketData) {

        var self = this;

        self.products = BucketData.all();

        self.decreaseStock = function (item) {
            if (item.stock >= 1) {
                var byId = getById(self.products, item.id);
                byId.stock--;
                if (byId.stock == 0) {
                    BucketData.delete(byId);
                }
            }
        };

        self.addProduct = function () {
            if (self.name != null) {
                BucketData.putToList({
                        id: Math.random(), name: self.name, stock: 0
                    }
                );
                self.name = '';
            }
        };


        self.increaseStock = function (item) {
            var byId = getById(self.products, item.id);
            BucketData.delete(byId);
            BucketData.put(item);
            byId.stock++;
        };

        function getById(results, id) {
            for (var i = 0; i < results.length; i += 1) {
                var result = results[i];
                if (result.id === id) {
                    return result;
                }
            }
        }

    })
;