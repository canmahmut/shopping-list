'use strict';

angular
    .module('app')

    .controller('AtHomeCtrl', function ($scope, products, underscore, ShoppingList, ShoppingListItem) {


        var self = this;


        self.products = products;


        ShoppingList.get({id: 1}).$promise.then(function (data) {
            self.shoppingList = data;
        });

        //self.shoppingList = {
        //    id: 1,
        //    selectedItems: [],
        //    creationDate: new Date(),
        //    archived: false
        //};


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
                item.stock = item.stock +1;
                ShoppingListItem.update({
                    shoppingListId: self.shoppingList.id,
                    id: item.id
                }, item).$promise.then(function (data) {
                       var find = ax(data);
                        console.log('find' + JSON.stringify(find));
                        find.stock = data.stock;
                    });
            } else {
                console.log('saving');
                ShoppingListItem.save({shoppingListId: self.shoppingList.id},
                    item.product
                ).$promise.then(function (data) {
                        self.products.push(data);
                    });
            }

            if (item.id == 1) {
                ShoppingListItem.update({shoppingListId: self.shoppingList.id, id: 1},

                    {"id": 1, "product": {"id": 1, "name": "Salz"}, "stock": 20, "done": true}
                ).$promise.then(function (data) {
                        self.products.push(data);
                    });
            }

            //if (item.id == 3) {
            //    ShoppingListItem.save({shoppingListId: self.shoppingList.id},
            //
            //         {"id": 1, "name": "Salz"}
            //    ).$promise.then(function (data) {
            //            console.log(data);
            //        });
            //}


            //if (ax(item) == 'undefined') {
            //    // call to server
            //    // update shopppingList
            //
            //    //self.shoppingList.selectedItems.push(item);
            //
            //}
        };


    })
;