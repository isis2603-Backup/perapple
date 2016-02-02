(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/evento");
            $stateProvider
                    .state('evento', {
                        url: '/evento',
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('home', {
                        url: '/home',
                        templateUrl: "src/modules/home/home.tpl.html"
                    });
        }]);
})(window.angular);

var app= angular.module("myApp",[]);


app.controller('MainController', ['$scope', function($scope){
        
        
}]);

