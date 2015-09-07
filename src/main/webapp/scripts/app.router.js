'use strict';



angular.module('certiApp')

    .config(function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl'
        });
        $routeProvider.when('/einkaufen', {
            templateUrl: 'einkaufen.html',
            controller: 'BuyingController as ctrl',
            reloadOnSearch: false
        });
    });
