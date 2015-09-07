'use strict';
angular
    .module('certiApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('app.private.about', {
                url: "/about",
                views: {
                    'breadcrumb@app.private': {
                        templateUrl: "scripts/about/breadcrumb.html"
                    },
                    'content@app.private': {
                        templateUrl: 'scripts/about/about.html'
                    }
                }


            }
        );
    });