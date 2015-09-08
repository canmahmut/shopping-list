'use strict';

angular
    .module('app')

    .controller('BuyingController', function ($scope, listItem) {

        var self = this;


        self.data = listItem;

    })
    .controller('ShoppingListItemController', function ($scope, listItemById) {

        console.log('active');
        var self = this;
        self.data = listItemById;
    })


;
