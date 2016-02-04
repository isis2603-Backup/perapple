(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);


        mod.controller("controlador", function($scope, sharedProperties){

            $scope.usuario= "Daniel";
            $scope.correo = "correo@gmail.com";
            $scope.objectValue = sharedProperties.getObject();
            $scope.setString = function(newValue) {
                $scope.objectValue.data = newValue;
                sharedProperties.setString(newValue);
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
                    });
        }]);
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
})(window.angular);


var app= angular.module("myApp",[]);


app.controller('MainController', ['$scope', function($scope){


}]);


