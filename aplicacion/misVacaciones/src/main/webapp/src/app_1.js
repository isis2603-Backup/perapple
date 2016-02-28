(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "viajeroModule",
        "itinerarioModule",
        "moduloCiudad",
        "viajeroMock",
        "itinerarioMock",
        "ciudadMock",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

       mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/viajero");
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        controller: "itinerarioCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "./modules/itinerario/itinerario.tpl.html"
                    })
                    .state('viajero', {
                        url: '/viajero',
                        controller: "viajeroCtrl",
                        controllerAs: "ctrl",
                        templateUrl: "./modules/viajero/viajero.tpl.html"
                    });

        }]);
})(window.angular);