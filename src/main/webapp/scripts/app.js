'use strict';

/**
 * @ngdoc overview
 * @name certiApp
 * @description
 * # certiApp
 *
 * Main module of the application.
 */
angular
    .module('app', [
        'ui.router',
        'mobile-angular-ui',
        'ngResource',
        'ngUnderscore'
    ]);


angular.module('app')
    .constant('REST_URL', '/api')
    .constant('REST_URL_SERVER', './api');
