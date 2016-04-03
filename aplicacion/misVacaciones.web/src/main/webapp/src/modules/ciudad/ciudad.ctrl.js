(function (ng) {

    var mod = ng.module("moduloCiudad");

    mod.controller("ciudadCtrl", ["$scope", "ciudadService", function ($scope, svc) {

        //Ciudad actual
        $scope.currentCiudad = {};
        //Ciudades
        $scope.records = [];

        //Evento actual
        $scope.currentEvento = {};
        //Eventos
        $scope.eventos = [];

        //Sitio actual
        $scope.currentSitio = {};
        //Sitios
        $scope.sitios = [];

        //Hotel actual
        $scope.currentHotel = {};
        //Hoteles
        $scope.hoteles = [];

        //viajero actual
        $scope.currentViajero = {};

        //Viajeros
        $scope.viajeros = [];

        //Alertas
        $scope.alerts = [];

        $scope.clear = function () {
            $scope.value = null;
        };

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.opened = true;
        };

        //Variables para el controlador
        this.readOnly = false;
        this.editMode = false;

        //Self
        var self = this;

        //Mensajes
        function showMessage(msg, type) {
            var types = ["info", "danger", "warning", "success"];
            if (types.some(function (rc) {
                return type === rc;
            })) {
                $scope.alerts.push({type: type, msg: msg});
            }
        }

        //Response Error
        function responseError(response) {
            self.showError(response.data);
        }

        //Close Alert
        this.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        //Show Error
        this.showError = function (msg) {
            showMessage(msg, "danger");
        };

        //Change Tab
        this.changeTab = function (tab) {
            $scope.tab = tab;
        };

        this.createCiudad = function () {
            $scope.$broadcast("pre-create", $scope.currentCiudad);
            this.editMode = true;
            $scope.currentCiudad = {};
            $scope.$broadcast("post-create", $scope.currentCiudad);
        };

        this.editCiudad = function (ciudad) {
            $scope.$broadcast("pre-edit", $scope.currentCiudad);
            return svc.fetchCiudad(ciudad.id).then(function (response) {
                $scope.currentCiudad = response.data;
                self.editMode = true;
                $scope.$broadcast("post-edit", $scope.currentCiudad);
                return response;
            }, responseError);
        };

        this.fetchCiudad = function (idCiudad) {
            return svc.fetchCiudades(idCiudad).then(function (response) {
                $scope.currentCiudad = response.data;
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchCiudades = function () {
            return svc.fetchCiudades().then(function (response) {
                $scope.ciudades = response.data;
                $scope.currentCiudad = $scope.ciudades[0];
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchSitios = function (ciudad) {
            return svc.fetchSitios(ciudad.id).then(function (response) {
                $scope.sitios = response.data;
                $scope.currentSitio = {};
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchEventos = function (ciudad) {
            return svc.fetchEventos(ciudad.id).then(function (response) {
                $scope.eventos = response.data;
                $scope.currentEvento = {};
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchHoteles = function (ciudad) {
            return svc.fetchHoteles(ciudad.id).then(function (response) {
                $scope.hoteles = response.data;
                $scope.currentHotel = {};
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.saveCiudad = function () {
            return svc.saveCiudad($scope.currentCiudad).then(function () {
                self.fetchCiudades();
            }, responseError);
        };

        this.saveSitio = function (sitio) {
            return svc.saveSitio($scope.currentCiudad, sitio.id).then(function () {
                self.fetchSitios();
            }, responseError);
        };

        this.saveEvento = function (evento) {
            return svc.saveSitio($scope.currentCiudad, evento.id).then(function () {
                self.fetchEventos();
            }, responseError);
        };

        this.saveHotel = function (hotel) {
            return svc.saveHotel($scope.currentCiudad, hotel.id).then(function () {
                self.fetchHoteles();
            }, responseError);
        };

        this.deleteCiudad = function (ciudad) {
            return svc.deleteCiudad(ciudad.id).then(function () {
                self.fetchCiudades();
            }, responseError);
        };

        this.deleteSitio = function (ciudad, sitio) {
            return svc.deleteSitio(ciudad.id, sitio.id).then(function () {
                self.fetchSitios();
            }, responseError);
        };

        this.deleteEvento = function (ciudad, evento) {
            return svc.deleteEvento(ciudad.id, evento.id).then(function () {
                self.fetchEventos();
            }, responseError);
        };

        this.deleteHotel = function ($event) {
            var info = $event.currentTarget.name.split("-");
            var ciudadid = parseInt(info[0]);
            var hotelid = parseInt(info[1]);
            console.log("ciudad id:  "+ciudadid+" hotelid "+ hotelid);
            return svc.deleteHotel(ciudadid, hotelid).then(function () {
                self.fetchHoteles(ciudadid);
            }, responseError);
        };

        this.agregarCiudad= function (nombre, detalles, imagen){

            $scope.currentRecord = {

                                    nombre: nombre ,
                                   detalles: detalles ,
                                    imagen: imagen,
                                    sitios:[],
                                    eventos: []
                                   };

            return svc.saveCiudad($scope.currentRecord)
                    .then(function () {
                        self.fetchCiudades();
                    }, responseError);
        };


        this.createViajero = function () {
            $scope.$broadcast("pre-create", $scope.currentViajero);
                this.editMode = true;
                $scope.currentRecord = {};
                $scope.$broadcast("post-create", $scope.currentViajero);
            };

             this.editViajero = function (viajero) {
                $scope.$broadcast("pre-edit", $scope.currentViajero);
                return svc.fetchRecord(viajero.id).then(function (response) {
                    $scope.currentViajero = response.data;
                    self.editMode = true;
                    $scope.$broadcast("post-edit", $scope.currentViajero);
                    return response;
                }, responseError);
            };
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
             this.saveViajero = function () {
                    return svc.saveViajero($scope.currentViajero).then(function () {
                        self.fetchViajeros();
                    }, responseError);
            };
            this.deleteViajero = function (viajero) {
                return svc.deleteViajero(viajero.id).then(function () {
                    self.fetchViajeros();
                }, responseError);
            };
            this.agregarViajero = function(viajero){
            $scope.viajeros.push(viajero);
             $scope.viajero = "";};

        this.fetchCiudades(1);

    }]);

})(window.angular);
