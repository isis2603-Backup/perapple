(function (ng) {
    var mod = ng.module("itinerarioModule");

     mod.controller("itinerarioCtrl", ["$scope","itinerarioService", "ciudadService",function ($scope, svc, svcCiudad) {
            $scope.currentUser = "";
            $scope.currentRecord = {};
            $scope.records = []; // Itinerarios
            $scope.currentCiudad = {};
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
                    $scope.currentRecord = $scope.records[0];
                    self.editMode = false;
                    return response;
                }, responseError);
            };
            this.saveRecord = function () {
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchRecords();
                }, responseError);
            };

            this.borrarItinerario = function (viajero, itinerario){
                return svc.borrarItinerario(viajero,itinerario).then(function () {
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

            this.fetchCiudades = function ()
            {
                 return svcCiudad.fetchCiudades().then(function (response) {
                    $scope.ciudades = response.data;
                    $scope.currentCiudad = {};
                    return response;
                }, responseError);
            };
            this.fetchRecords();
        }]);

})(window.angular);
