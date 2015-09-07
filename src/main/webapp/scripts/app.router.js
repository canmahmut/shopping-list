'use strict';


angular.module('app')

    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/");

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl'
        });
        $stateProvider.state('buying', {
            url: '/einkaufen',
            templateUrl: 'einkaufen.html',
            controller: 'BuyingController as ctrl',
            reloadOnSearch: false
        });
    })


    .controller('MainController', function (){})
;


/*
 $stateProvider
 .state('state1', {
 url: "/state1",
 templateUrl: "partials/state1.html"
 })
 */
