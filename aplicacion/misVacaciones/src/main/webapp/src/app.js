(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);


        mod.controller("controlador", function($scope){

            $scope.usuario= "Daniel";
            $scope.correo = "correo@gmail.com";
            $scope.nameItinerario = "Itinerario 1";

        });

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/home");
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        templateUrl: "./modules/itinerario/itinerario.html"
                    })
                    .state('home', {
                        url: '/home',
                        templateUrl: "./modules/home/home.html"
                    });
        }]);



})(window.angular);


var app= angular.module("myApp",[]);


app.controller('MainController', ['$scope', function($scope){


}]);

