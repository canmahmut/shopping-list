'use strict';
angular.module('app')
    .config(function ($stateProvider) {

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'scripts/home/home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl',
            resolve: {
                products: function (Product, ShoppingItem, $q, underscore) {
                    var deferred = $q.defer();


                    //* var users = [
                    //        *   { 'user': 'barney', 'active': true },
                    //    *   { 'user': 'fred',   'active': false }
                    //* ];
                    //*
                    //* // using the `_.matches` callback shorthand
                    //* _.some(users, { 'user': 'barney', 'active': false });
                    //* // => false
                    //*
                    //* // using the `_.matchesProperty` callback shorthand
                    //* _.some(users, 'active', false);
                    //* // => true
                    //*
                    //* // using the `_.property` callback shorthand
                    //* _.some(users, 'active');
                    //* // => true

                    var arr = [];
                    $q.all([Product.query({}).$promise, ShoppingItem.query({}).$promise]).then(function (data) {

                        //var arr1 = underscore.filter(data[1], function(obj) {
                        //    console.log(obj);
                        //    var some = underscore.find(obj.id, 1);
                        //    console.log(some);
                        //    return obj.id == 1;
                        //});
                        //
                        // console.log(arr1);

                        underscore.each(data[0], function (p) {


                            var b =underscore.chain(data[1]).filter(function (obj) {
                                return p.id == obj.id;
                            }).first().value();
                            console.log('b is ' + JSON.stringify(b));

                            if (b) {
                                console.log('1');
                                arr.push({
                                    stock: b.stock,
                                    product: b.product,
                                    done: b.done,
                                    id: b.id
                                });
                            } else {
                                console.log('2');
                                arr.push({
                                    stock: 0,
                                    product: p,
                                    done: false
                                });
                            }





                        });
                        console.log('arr is ' + JSON.stringify(arr));
                        deferred.resolve(arr);
                    });


                    //Product.query({}).$promise.then(function (data) {
                    //
                    //
                    //    var arr = [];
                    //    underscore.each(data, function (item) {
                    //        arr.push({
                    //                stock: 0,
                    //                product: item,
                    //                done: item.done,
                    //                id: item.id
                    //            }
                    //        )
                    //    });
                    //
                    //
                    //    deferred.resolve(arr);
                    //
                    //
                    //}, function (data) {
                    //    deferred.reject();
                    //});
                    return deferred.promise;
                }
            }
        });


    });
