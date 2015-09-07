'use strict';
angular.module('certiApp')
    .run(function (bootstrap3ElementModifier) {
        bootstrap3ElementModifier.enableValidationStateIcons(true);
    });

angular.module('certiApp').config(function (uiDateConfig) {
    uiDateConfig.dateFormat = 'dd.mm.yy';
});
angular.module('certiApp').config(function ($translateProvider) {
    $translateProvider.translations('de', {
        action: {
            delete: 'Löschen'
        },
        login: {
            form: {
                rememberme: 'Auto Login'
            },
            messages: {
                error: {
                    authentication: 'Benutzername oder Passwort falsch!'
                }
            }
        },
        HOURLY: 'Stunden Lohn',
        DAILY: 'Tageslohn',
        TITLE: 'Hello',
        client: 'Ansprechspartner',
        city: 'Stadt',
        country: 'Land',
        houseNumber: 'Haus Nummer',
        plz: 'PLZ',
        street: 'Strasse'
    });

    $translateProvider.preferredLanguage('de');
    $translateProvider.useSanitizeValueStrategy('escaped');
});


angular.module('certiApp')
    .config(function (uiSelectConfig) {
        uiSelectConfig.theme = 'bootstrap';
    })
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, httpRequestInterceptorCacheBusterProvider) {

        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/, /.*scripts.*/], true);
        //$urlRouterProvider.otherwise('/');
        //$stateProvider.state('site', {
        //    'abstract': true,
        //    views: {
        //        'navbar@': {
        //            templateUrl: 'scripts/components/navbar/navbar.html',
        //            controller: 'NavbarController'
        //        }
        //    },
        //    resolve: {
        //        authorize: ['Auth',
        //            function (Auth) {
        //                return Auth.authorize();
        //            }
        //        ]
        //        //,
        //        //translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
        //        //    $translatePartialLoader.addPart('global');
        //        //    $translatePartialLoader.addPart('language');
        //        //}]
        //    }
        //});

        //
        //// Initialize angular-translate
        //$translateProvider.useLoader('$translatePartialLoader', {
        //    urlTemplate: 'i18n/{lang}/{part}.json'
        //});
        //
        //$translateProvider.preferredLanguage('en');
        //$translateProvider.useCookieStorage();
        //$translateProvider.useSanitizeValueStrategy('escaped');

        //tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
        //tmhDynamicLocaleProvider.useCookieStorage();
        //tmhDynamicLocaleProvider.storageKey('NG_TRANSLATE_LANG_KEY');
    });