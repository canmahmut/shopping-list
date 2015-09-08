'use strict';


angular.module('app')

    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/");

        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'scripts/home/home.html',
            reloadOnSearch: false,
            controller: 'AtHomeCtrl as ctrl'
        });
;
    })
;
