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
    .module('certiApp', [
        'ui.router',
        'mobile-angular-ui',
        'ZettelApp.controllers.Main',
        'ngUnderscore'
    ]);


angular.module('certiApp')
    .constant('REST_URL', '/api')
    .constant('REST_URL_SERVER', './api');
