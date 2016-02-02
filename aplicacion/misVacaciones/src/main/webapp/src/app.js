(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/login");
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        templateUrl: "src/modules/itinerario/itinerario.html"
                    })
                    .state('home', {
                        url: '/home',
                        templateUrl: "src/modules/home/home.html"
                    })
                    .state('login', {
                        url: '/login',
                        templateUrl: "src/loginPage.html"
                    });
        }]);
})(window.angular);

var app= angular.module("myApp",[]);


app.controller('MainController', ['$scope', function($scope){


}]);

