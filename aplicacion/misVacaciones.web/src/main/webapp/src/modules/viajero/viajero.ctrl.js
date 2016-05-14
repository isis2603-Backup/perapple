

(function (ng) {

    var mod = ng.module("viajeroModule");

    mod.controller("viajeroCtrl", ["$scope", "viajeroService", function ($scope, svc,svcViajero) {

            //viajero actual
            $scope.currentViajero = {};

            //Viajeros
            $scope.viajeros = [];
            $scope.currentViajeroMostrar= {};
            //Se almacenan todas las alertas
            $scope.alerts = [];
            $scope.currentRecord = {};
            $scope.records = [];

            //fecha de hoy
            $scope.today = function () {
                $scope.value = new Date();
            };
            //vaciar scope
            $scope.clear = function () {
                $scope.value = null;
            };

            $scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope.opened = true;
            };

            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage:
            //              Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
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

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
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
            showMessage("Bienvenido!, Esto es un ejemplo para mostrar un mensaje de información","info");


            /*
             * Funcion createRecord emite un evento a los $scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion. El template de la lista cambia por el formulario.
             *
             */

            this.createViajero = function () {
                $scope.$broadcast("pre-create", $scope.currentViajero);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentViajero);
            };

            /*
             * Funcion editRecord emite el evento ("pre-edit") a los $Scope hijos del controlador por medio de la
             * sentencia &broadcast ("nombre del evento", record), esto con el fin cargar la información de modulos hijos
             * al actual modulo.
             * Habilita el modo de edicion.  Carga el template de formulario con los datos del record a editar.
             *
             */

            this.editViajero = function (viajero) {
                $scope.$broadcast("pre-edit", $scope.currentViajero);
                return svc.fetchRecord(viajero.id).then(function (response) {
                    $scope.currentViajero = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentViajero);
                    return response;
                }, responseError);
            };

            /*
             * Funcion fetchRecords consulta el servicio svc.fetchRecords con el fin de consultar
             * todos los registros del modulo book.
             * Guarda los registros en la variable $scope.records
             * Muestra el template de la lista de records.
             */

             this.fetchViajero = function (idViajero) {
                return svc.fetchViajeros(idViajero).then(function (response) {
                    $scope.currentViajero = response.data;
                    self.editMode = false;
                    return response;
                }, responseError);
            };
            this.fetchViajeros = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                    return response;
                }, responseError);
            };

            this.fetchViajerosBD = function (idViajero) {
            return svcViajero.fetchEventos(idViajero).then(function (response) {
                $scope.viajerosBD = response.data;
                $scope.currentViajero = {};
                self.editMode = false;
                return response;
            }, responseError);
        };

            /*
             * Funcion saveRecord hace un llamado al servicio svc.saveRecord con el fin de
             * persistirlo en base de datos.
             * Muestra el template de la lista de records al finalizar la operación saveRecord
             */
            this.saveViajero = function () {
                    return svc.saveViajero($scope.currentViajero).then(function () {
                        self.fetchViajeros();
                    }, responseError);
            };
            this.agregarEvento = function($event){

            var idEvento = parseInt($event.currentTarget.name);
            console.log("ctrl agregar evento idEve: "+idEvento);
            var eventoBD = {};

            return svcViajero.fetchViajero($scope.currentViajeroMostrar.id, idViajero)
                    .then(function (response) {
                        var viajeroBD = response.data;
                        console.log("ctrl agregar viajero viajeroBD: "+viajeroBD.id);
                        return response;
                    }, responseError)
                    .then(function () {
                        var nViajero = {
                            id:viajeroBD.id,
                            nombre:viajeroBD.nombre,
                            email:viajeroBD.email,
                            contrasena:viajeroBD.contrasena
                          };
                        svc.saveViajero($scope.currentRecord.id, nViajero);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrentEventos();
                    }, responseError);
        };
            /*
             * Funcion deleteRecord hace un llamado al servicio svc.deleteRecord con el fin
             * de eliminar el registro asociado.
             * Muestra el template de la lista de records al finalizar el borrado del registro.
             */
            this.deleteViajero = function (viajero) {
                return svc.deleteViajero(viajero.id).then(function () {
                    self.fetchViajeros();
                }, responseError);
            };
            this.borrarViajero = function ($event){

            var idViajero = parseInt($event.currentTarget.id);

            return svc.deleteViajero(idViajero)
                    .then(function (){
                        self.fetchCurrentViajeros();
                    });
        };

            /*
             * Funcion fetchRecords consulta todos los registros del módulo book en base de datos
             * para desplegarlo en el template de la lista.
             */
            $scope.viajero= "Perapple";
            $scope.contrasena = "Perapple";
            $scope.correo = "perapple@gmail.com";
            $scope.itinerario = "";
            $scope.itinerarios = [];

            this.agregarViajero = function(viajero){
            $scope.viajeros.push(viajero);
             $scope.viajero = "";};

          

        }]);

})(window.angular);
