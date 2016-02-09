(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);


        mod.controller("controlador", function($scope, $rootScope,sharedProperties){

            $rootScope.usuario= "Daniel";

            $scope.contrasena = "jojojojo";
            $scope.correo = "correo@gmail.com";
            $scope.itinerario = "Nombre Itinerario";
            $scope.itinerarios = [];
            $scope.agregar = function(itinerario){
            $scope.itinerarios.push(itinerario);
             $scope.itinerario = "Nombre Itinerario";
        };

            $scope.objectValue = sharedProperties.getObject();
            $scope.setString = function(newValue) {
                $scope.objectValue.data = newValue;
                sharedProperties.setString(newValue);
               };

        });

         mod.controller("ctrl", function($scope, sharedProperties){
        $scope.nueva_ciudad = "";
        $scope.ciudades = [];
        $scope.nuevo_evento="";
        $scope.eventos=[];
        $scope.stringValue = sharedProperties.getString();
        $scope.objectValue = sharedProperties.getObject().data;

        $scope.agregar = function(nueva_ciudad){
          $scope.ciudades.push(nueva_ciudad);
          $scope.nueva_ciudad = "";
        };
        $scope.add= function(nuevo_evento){
            $scope.eventos.push(nuevo_evento);
            $scope.nuevo_evento="";
        };

        $scope.detalles = function(nc){
          window.alert("se muestran abajo en la parte que esta haciendo nicolas");
        };
  });

  mod.service('sharedProperties', function() {
    var stringValue = 'Itinerario';
    var objectValue = {
        data: 'test object value'
    };

    return {
        getString: function() {
            return stringValue;
        },
        setString: function(value) {
            stringValue = value;
        },
        getObject: function() {
            return objectValue;
        }
    };
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
                    })
                    .state('ciudad', {
                        url: '/ciudad',
                        templateUrl: "./modules/ciudad/ciudad.html"
                    });
        }]);

})(window.angular);