'use strict';


angular
    .module('certiApp')
    .run(function ($rootScope, $location, $window, $http, $state, $translate, Auth, Principal) {

        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;
            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }else{
            }
            //// Update the language
            //Language.getCurrent().then(function (language) {
            //    $translate.use(language);
            //});
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {

            var titleKey = 'global.title';


            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $translate(titleKey).then(function (title) {
                // Change window title with translated one
                $window.document.title = title;
            });
        });

        $rootScope.back = function () {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('app.private.dashboard');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
    });

angular.module('certiApp')

    .config(function ($stateProvider, $urlRouterProvider) {


        $stateProvider


            .state('app', {
                abstract: true,
                template: '<ui-view></ui-view>',
                resolve: {
                    authorize: function (Auth) {
                        return Auth.authorize();
                    }
                }
            }).state('app.public', {
                abstract: true,
                parent: 'app',
                url: '/public',
                templateUrl: 'views/public/main.html',
                data: {
                    roles: [],
                    pageTitle: 'login.title'
                }
            }).state('app.private', {
                abstract: true,
                url: '/private',
                parent: 'app',
                templateUrl: 'scripts/main.html',
                controller: 'NavbarController',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'configuration.title'
                }
            });

        $urlRouterProvider.otherwise('/public/login');
    });
