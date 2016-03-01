(function (ng) {
    var mod = ng.module("itinerarioModule");

     mod.controller("itinerarioCtrl", ["$scope","itinerarioService", "ciudadService",function ($scope, svc, svcCiudad) {

            $scope.currentUser = "";
            $scope.nombreItinerario = "nombre";
            $scope.currentRecord = {}; //itinerario actual
            $scope.currentCiudadItinerario = {}; //ciudad actual de la que se muestran detalles

            $scope.records = []; // Itinerarios del usuario

            $scope.currentCiudad = {}; //ciudad seleccionada para agregar
            $scope.fechaIni = new Date ();
            $scope.fechaFin = new Date ();
            $scope.ciudadBuscada = ""; //nombre de ciudad ingresada por el usuario
             $scope.ciudades = []; //ciudades a mostrar para elección
            $scope.alerts = [];
            $scope.today = function () {
                $scope.value = new Date();
            };

            $scope.clear = function () {
                $scope.value = null;
            };

            $scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };
            $scope.$on('fecthRecordsItinerario', function() { fetchRecords(); });
            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            var self = this;
            function responseError(response) {
                self.showError(response.data);
            }

            //Variables para el controlador
            this.readOnly = false;
            this.editMode = false;

            this.changeTab = function (tab) {
                $scope.tab = tab;
            };

            //Ejemplo alerta
            showMessage("Bienvenido!, Esto es un ejemplo para mostrar un mensaje de atención", "warning");


            this.createRecord = function () {
                $scope.$broadcast("pre-create", $scope.currentRecord);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentRecord);
            };

            this.editRecord = function (record) {
                $scope.$broadcast("pre-edit", $scope.currentRecord);
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentRecord);
                    return response;
                }, responseError);
            };

            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    
                    $scope.currentUser = $scope.currentRecord.viajero;
                 //   if($scope.currentRecord.ciudades.length >0){
                   // $scope.currentCiudadItinerario = $scope.currentRecord.ciudades[0];
                //} console.log($scope.currentRecord);
                console.log($scope.currentRecord);
                    self.editMode = false;
                    return response;
                }, responseError);
            };
            
             this.fetchCurrentRecord = function () {
                return svc.fetchCurrentRecord().then(function (response) {
                    $scope.currentRecord = response.data;
                    
                console.log($scope.currentRecord);
                    return response;
                }, responseError);
            };
           
            /**
             * para crear un nuevo itinerario
             * @returns {unresolved}
             */
            this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };
            
             this.saveCurrentRecord = function () {
                return svc.saveCurrentRecord($scope.currentRecord).then(function () {
                    self.fetchCurrentRecord();
                }, responseError);
            };
            
            this.itinerarioActual = function($event)
            {
                var id = parseInt($event.currentTarget.name);
              
                for (var i = 0; i<$scope.records.length;i++)
                {
                    if($scope.records[i].id === id)
                    {
                       
                        $scope.currentRecord = $scope.records[i];
                           console.log($scope.currentRecord);
                     
                    }
                }
                return svc.saveCurrentRecord($scope.currentRecord).then(function () {
                    console.log("record actual itinerario"+$scope.currentRecord.name);
                    self.fetchCurrentRecord();
                }, responseError);
            };
            this.agregarItinerario = function (nombre, fechaI, fechaF){
                
                console.log("nombre: "+nombre+ " fecha:"+fechaI);
                
                $scope.currentRecord = {id:'',
                                   viajero: 'perapple',
                                   nombre: nombre ,
                                   fechaInicio: fechaI ,
                                   fechaFin: fechaF,
                                   ciudades: [],
                                   eventos: []                           
                };
                
                return svc.saveRecord($scope.currentRecord).then(function () {
                    console.log("agregando... "+$scope.nombreItinerario);
                    self.fetchRecords();
                }, responseError);
            
            };
            
            this.borrarItinerario = function ($event){
                
                var iditinerario = $event.currentTarget.name;
                console.log($event.currentTarget.name);
                return svc.borrarItinerario(iditinerario).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.borrarCiudad = function (viajero, iditinerario, idciudad){
                return svc.borrarCiudad(viajero,iditinerario,idciudad).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.borrarEvento = function (viajero, iditinerario, idciudad, idevento){
                return svc.borrarEvento(viajero,iditinerario,idciudad, idevento).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.borrarSitio = function (viajero, iditinerario, idciudad, idsitio){
                return svc.borrarSitio(viajero,iditinerario, idciudad, idsitio).then(function () {
                    self.fetchRecords();
                }, responseError);
            };
               /**
                *
                * @returns {String} respuesta
                */
            this.fetchCiudades = function ()
            {
                 return svcCiudad.fetchCiudades().then(function (response) {
                    $scope.ciudades = response.data;
                    $scope.currentCiudad = {};
                    return response;
                }, responseError);
            };

            this.detallesCiudad=function($event)
            {

                var pId = $event.currentTarget.name;
                ng.forEach($scope.currentRecord.ciudades, function (value) {
                    console.log("value.id:"+value.id+" pId:"+pId);
                if (value.id == pId) {

                    $scope.currentCiudadItinerario = value;
                    console.log($scope.currentCiudadItinerario.nombre);
                }
            });
            };

            /**
             * REVISAR
             * @returns {unresolved}
             */
            this.ciudadBuscada = function()
            {   self.fetchCiudades();
                var tam = $scope.ciudades.length;
                for(var i = 0; i<tam;i++){
               var ciudad = svcCiudad.fetchCiudad(i).then(function (response){
                   if(response.data.nombre === $scope.ciudadBuscada)
                   {
                       $scope.ciudades = response.data;
                       return response;
                   }
                    }, responseError);
               }
               return ciudad;
            };

            this.agregarCiudad=function($event){
                console.log($event)
                    var pId = $event.currentTarget.name;
                    var encontro = false;
                for (var i = 0; i<$scope.ciudades.length && !encontro;i++)
                {
                    var value = $scope.ciudades[i];
                    console.log("value.id:"+value.id+" pId:"+pId);
                if (value.id == pId) {

                    $scope.currentCiudad = {id:value.id,
                                            nombre:value.nombre,
                                            detalles:value.detalles,
                                            imagen:value.imagen,
                                            fInicio: $scope.fechaIni,
                                            fFin: $scope.fechaFin,
                                            sitios: [ ],
                                            eventos: [ ]};
                    $scope.currentRecord.ciudades.push($scope.currentCiudad);
                    self.saveRecord();
                    encontro = true;
                    console.log($scope.currentCiudad.nombre);
                }
                }

            };

            this.fetchRecords();
            this.fetchCiudades();
            this.fetchCurrentRecord();
        }]);

})(window.angular);
