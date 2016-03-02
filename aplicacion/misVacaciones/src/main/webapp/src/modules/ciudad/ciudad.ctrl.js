    (function (ng) {
    var mod = ng.module("moduloCiudad");

    mod.controller("ciudadCtrl", ["$scope", "ciudadService", function ($scope, svc) {
            $scope.currentRecord = {};
            $scope.records = [];
            $scope.eventos = [];
            $scope.alerts = [];

            $scope.currentSitio={}; //sitio seleccionado para agregar
            $scope.fechaSitio= new Date(); //fecha visita del sitio
            $scope.sitioElegido=""; //sitio elegido por el usurario
            $scope.sitios=[]; //sitios a mostrar según la ciudad elegida



            $scope.grabar = function () {
                svc.agregarEvento();
                svc.refrescar();
            };

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

            this.fetchCiudades = function () {
                return svc.fetchCiudades().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.editMode = false;
                    return response;
                }, responseError);
            };

             this.fetchSitios = function () {
                return svc.fetchSitios.then(function (response) {
                    $scope.sitios = response.data;
                    $scope.currentSitio = {};
                    self.editMode = false;
                    return response;
                }, responseError);
            };

            this.fetchEventos = function () {
                return svc.fetchSitios.then(function (response) {
                    $scope.sitios = response.data;
                    $scope.currentEvento = {};
                    self.editMode = false;
                    return response;
                }, responseError);
            };
            this.saveCiudad = function () {
                return svc.saveCiudad($scope.currentRecord).then(function () {
                    self.fetchCiudades();
                }, responseError);
            };
            this.saveEvento = function () {
                return svc.saveEvento($scope.currentRecord).then(function () {
                    self.fetchEventos();
                }, responseError);
            };
            this.deleteCiudad = function (record) {
                return svc.deleteCiudad(record.id).then(function () {
                    self.fetchCiudades();
                }, responseError);
            };
            this.deleteEvento = function (record) {
                return svc.deleteEvento(record.id).then(function () {
                    self.fetchEventos();
                }, responseError);
            };

            this.fetchCiudades();
        }]);

})(window.angular);
