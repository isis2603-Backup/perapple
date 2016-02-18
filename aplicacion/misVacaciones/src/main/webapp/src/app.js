(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);


        mod.controller("controlador", function($scope, sharedProperties){

            $scope.usuario= "Perapple";
            $scope.contrasena = "Perapple";
            $scope.correo = "perapple@gmail.com";
            $scope.itinerario = "";
            $scope.itinerarios = [];

            $scope.agregar = function(itinerario){
            $scope.itinerarios.push(itinerario);
             $scope.itinerario = "";
        };

            $scope.objectValue = sharedProperties.getObject();
            $scope.setString = function(newValue) {
                $scope.objectValue.data = newValue;
                sharedProperties.setString(newValue);
               };

        });

         mod.controller("ctrl", function($scope, sharedProperties){
            
        $scope.nombre_usuario="";
        $scope.contrasena_usuario="";
        $scope.email_usuario="@";
        
        $scope.nueva_ciudad = "";
        $scope.ciudades = ["Bogotá","Bucaramanga", "Cali" ];
        
        $scope.nuevo_evento="";
        $scope.eventos=[ "Festival Estereo Picnic", "Festival Internacional de Teatro" , "Fiesta Andres" ];
        
        $scope.sitios = ["Andrés Carne de Res", "Museo del Oro", "Monserrate"];
        
        $scope.stringValue = sharedProperties.getString();
        $scope.objectValue = sharedProperties.getObject().data;
        $scope.comentario = "";
        
        $scope.calificacion = 0;
        $scope.calificacionesEvento = [];
        $scope.calificacionesSitio = [];
        
        $scope.agregar_ciudad = function(){

          $scope.ciudades.push(prompt("¿Cuál es la nueva ciudad que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
          $scope.nueva_ciudad = "";
        };
        
        $scope.agregar_evento= function(){
            $scope.eventos.push(prompt("¿Cuál es el nuevo evento/sitio que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
            $scope.nuevo_evento="";
        };

        $scope.agregar_sitio= function(){
            $scope.sitios.push(prompt("¿Cuál es el nuevo evento/sitio que desea agregar...?(por ahora luego se cambia a pop-Up decente)"));
           };
        
        $scope.agegar_usuario=function (){
       // <!--- por desarrollar--->
        };

        $scope.detalles = function(ciudad){
          window.alert("Actualizacion de eventos y sitios de interes segun ciudad...");
        };

        $scope.detalles_evento = function(evento){
          window.alert("Detalles del evento con pop-Up...");
        };

        $scope.calificar_evento = function(evento, calificacion, comentario){
          $scope.calificacionesEvento.push({evento:evento,calificacion:calificacion,comentario:comentario});
          $scope.calificacion = 0;
          $scope.comentario ="";
        };

        $scope.detalles_sitio = function(sitio){
          window.alert("Detalles del sitio con pop-Up...");
        };

        $scope.calificar_sitio = function(sitio,calificacion,comentario){
          $scope.calificacionesSitio.push({sitio:sitio,calificacion:calificacion,comentario:comentario});
          $scope.calificacion = 0;
          $scope.comentario ="";
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
            
            $stateProvider
                    .state('itinerario', {
                        url: '/itinerario',
                        templateUrl: "./modules/itinerario/itinerario.tpl.html"
                    })
                    .state('viajero', {
                        url: '/viajero',
                        templateUrl: "./modules/viajero/viajero.tpl.html"
                    })
                    .state('ciudad', {
                        url: '/ciudad',
                        templateUrl: "./modules/ciudad/ciudad.tpl.html"
                    });
        }]);

})(window.angular);