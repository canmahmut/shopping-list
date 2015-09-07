'use strict';

angular.module('ZettelApp.controllers.Main')

    .factory('BucketData', function (underscore) {
        var data = [];
        var selected = [];
        return {
            put: function (item) {
                selected.push(item);
            },
            putToList: function (item) {
                data.push(item);
            },
            clear: function () {
                selected = [];
            },
            all: function(){
                return data;
            },
            selected: function(){
                return selected;
            },
            delete: function (item) {
                selected = underscore.reject(selected, function (d) {
                    return d.id === item.id;
                });
            }

        }
    });