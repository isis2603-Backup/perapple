(function (ng) {

    var mod = ng.module("itinerarioModule");

    mod.controller("itinerarioCtrl", ["$scope","itinerarioService","ciudadService",function ($scope, svc, svcCiudad) {

        //Variables para el controlador
        var self = this;
        this.readOnly = false;
        this.editMode = false;

        $scope.events = [{
    badgeClass: 'info',
    badgeIconClass: 'glyphicon-check',
    title: 'First heading',
    content: 'Some awesome content.'
  }, {
    badgeClass: 'warning',
    badgeIconClass: 'glyphicon-credit-card',
    title: 'Second heading',
    content: 'More awesome content.'
  }];
        //Variables current
        $scope.mostrarDetallesCiudad = false;
        //id del viajero actual (ojo esto se obtiene de cuando el viajero inicia sesión
        $scope.currentUser = {
                                email: "p@erapple",
                                id: 1,
                                image: "https://s-media-cache-ak0.pinimg.com/736x/01/c1/3e/01c13e690b87d69a89fe6633c21cd7a5.jpg",
                                name: "perapple",
                                password: "1234"
                              };
        //itinerarios a mostrar según el currentUser
        $scope.records=[];
        //itinerario del que se muestra todo
        $scope.currentRecord = {};
        //ciudades que se muestran en la lista
        $scope.currentCiudadesMostrar = [];
        //ciudad actual de la que se muestran detalles
        $scope.currentCiudadMostrar = {};
        //sitios a mostrar según la ciudad mostrada en detalles
        $scope.currentSitiosMostrar = [];
        //Sitio del cual se muestran detalles
        $scope.currentSitioMostrar={};
        //eventos a mostrar según la ciudad mostrada en detalles
        $scope.currentEventosMostrar = [];
        //Sitio del cual se muestran detalles
        $scope.currentEventoMostrar={};

        //Variables para agregar ciudad
        $scope.ciudadesBD = [];
        $scope.fechaIni = new Date ();
        $scope.fechaFin = new Date ();

        //Variables para agregar sitio
        //sitios disponibles para agregar
        $scope.sitiosBD = [];
        //fecha visita del sitio
        $scope.fechaSitioIni = new Date();
        $scope.fechaSitioFin = new Date ();

        //Variables para agregar evento
        //eventos disponibles para agregar
        $scope.eventosBD = [];
        //fecha evento del sitio
        $scope.fechaEventoIni = new Date();
        $scope.fechaEventoFin = new Date();

        //Otras variables
        $scope.nombreNuevoItinerario = "Verano 2017";
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

        $scope.$on('fetchRecordsItinerario', function() {
            var idviajero = $scope.currentUser.id;
            self.fetchRecordsViajero(idviajero);
        });

        //Message/Error/Alerts
        function showMessage(msg, type) {
            var types = ["info", "danger", "warning", "success"];
            if (types.some(function (rc) {
                return type === rc;
            })) {
                $scope.alerts.push({type: type, msg: msg});
            }
        }

        function responseError(response) {
            self.showError(response.data);
        }

        this.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

        this.showError = function (msg) {
            showMessage(msg, "danger");
        };

        this.changeTab = function (tab) {
            $scope.tab = tab;
        };

        this.fetchRecordsViajero = function (idViajero) {
            return svc.fetchItinerariosViajero(idViajero).then(function (response) {
                $scope.records = response.data;
                $scope.currentUser = {
                                        email: "p@erapple",
                                        id: 1,
                                        image: "https://s-media-cache-ak0.pinimg.com/736x/01/c1/3e/01c13e690b87d69a89fe6633c21cd7a5.jpg",
                                        name: "perapple",
                                        password: "1234"
                                      };
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchCurrentRecord = function () {
            $scope.currentRecord = svc.fetchCurrentItinerario();
        };

        this.itinerarioActual = function($event){

            var idItinerario = parseInt($event.currentTarget.name);

            return svc.fetchItinerario(idItinerario)
                    .then(function(response){
                        $scope.currentRecord = response.data;
                    }, responseError)
                    .then(function(){
                        svc.saveCurrentItinerario($scope.currentRecord);
                        self.fetchCurrents();
                    }, responseError);
        };

        this.agregarItinerario = function (nombre, fechaI, fechaF){

            $scope.currentRecord = {
                                    viajero: $scope.currentUser,
                                    nombre: nombre ,
                                    fechaInicio: fechaI ,
                                    fechaFin: fechaF,
                                    ciudades: []
                                   };

            return svc.saveItinerario($scope.currentRecord)
                    .then(function () {
                        self.fetchRecordsViajero($scope.currentUser.id);
                    }, responseError);
        };

        this.borrarItinerario = function ($event){
            var idItinerario = parseInt($event.currentTarget.name);
            return svc.deleteItinerario(idItinerario)
                    .then(function () {
                        self.fetchRecordsViajero($scope.currentUser.id);
                    }, responseError);
        };


        //Funciones para obtener (FETCH)

        this.fetchCurrentCiudades = function (){
            return svc.fetchCiudades($scope.currentRecord.id)
                    .then(function (response) {
                        $scope.currentCiudadesMostrar = response.data;
                    }, responseError)
                    .then(function () {
                        self.fetchCiudadesBD();
                    }, responseError);
        };

        this.fetchCurrentSitios = function (){
            return svc.fetchSitios($scope.currentRecord.id, $scope.currentCiudadMostrar.id)
                    .then(function (response) {
                        $scope.currentSitiosMostrar = response.data;
                    }, responseError);
        };

        this.fetchCurrentEventos = function (){
            return svc.fetchEventos($scope.currentRecord.id, $scope.currentCiudadMostrar.id)
                    .then(function (response) {
                        $scope.currentEventosMostrar = response.data;
                    }, responseError);
        };

        this.fetchCurrents = function (){

            self.fetchCurrentRecord();

            if($scope.currentRecord.id){
                self.fetchCurrentCiudades()
                .then(function(){
                            if($scope.currentCiudadMostrar.id){
                                $scope.mostrarDetallesCiudad = true;
                                self.fetchCurrentSitios();
                                self.fetchCurrentEventos();
                            }
                        }, responseError
                    );
            }
        };

        this.fetchCiudadesBD = function (){
            return svcCiudad.fetchCiudades().then(function (response) {
                $scope.ciudadesBD = response.data;
                return response;
            }, responseError);
        };

        this.fetchSitiosBD = function (idCiudad) {
            return svcCiudad.fetchSitios(idCiudad).then(function (response) {
                $scope.sitiosBD = response.data;
                $scope.currentSitio = {};
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchEventosBD = function (idCiudad) {
            return svcCiudad.fetchEventos(idCiudad).then(function (response) {
                $scope.eventosBD = response.data;
                $scope.currentEvento = {};
                self.editMode = false;
                return response;
            }, responseError);
        };


        //Funciones para agregar (AGREGAR)

        this.agregarCiudad=function($event){

            var ciudadBD;
            var nCiudad;
            var idCiudad = parseInt($event.currentTarget.name);

            return svcCiudad.fetchCiudad(idCiudad)
                    .then(function (response) {
                        ciudadBD = response.data;
                        return response;
                    }, responseError)
                    .then(function () {
                        nCiudad = {
                            ciudad: {id:ciudadBD.id,
                            nombre:ciudadBD.nombre,
                            detalles:ciudadBD.detalles,
                            imagen:ciudadBD.imagen},
                            fechaIni: $scope.fechaIni,
                            fechaFin: $scope.fechaFin,
                            sitios: [],
                            eventos: [],
                            itinerario: $scope.currentRecord
                          };
                    }, responseError)
                    .then(function () {
                        svc.saveCiudad($scope.currentRecord.id, nCiudad);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrents();
                    }, responseError);
        };

        this.agregarSitio = function($event){

            var sitioBD;
            var nSitio;
            var idSitio = parseInt($event.currentTarget.name);

            return svcCiudad.fetchSitio($scope.currentCiudadMostrar.ciudad.id, idSitio)
                    .then(function (response) {
                        sitioBD = response.data;
                        return response;
                    }, responseError)
                    .then(function () {
                        nSitio = {
                            //podria ser sitio : sitioBD ?
                            sitio:{id:sitioBD.id,
                            nombre:sitioBD.nombre,
                            detalles:sitioBD.detalles,
                            imagen:sitioBD.imagen},
                            fechaIni:$scope.fechaSitioIni,
                            fechaFin:$scope.fechaSitioFin,
                            ciudad: $scope.currentCiudadMostrar
                        };
                        svc.saveSitio($scope.currentRecord.id, $scope.currentCiudadMostrar.id, nSitio);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrents();
                    }, responseError);
        };

        this.agregarEvento = function($event){

            var eventoBD;
            var nEvento;
            var idEvento = parseInt($event.currentTarget.name);

            return svcCiudad.fetchEvento($scope.currentCiudadMostrar.ciudad.id, idEvento)
                    .then(function (response) {
                        eventoBD = response.data;
                    }, responseError)
                    .then(function () {
                        nEvento = {
                           evento:{ id:eventoBD.id,
                            nombre:eventoBD.nombre,
                            detalles:eventoBD.detalles,
                            imagen:eventoBD.imagen,
                            fechaEvento:eventoBD.fechaEvento},
                            fechaIni:$scope.fechaEventoIni,
                            fechaFin:$scope.fechaEventoFin,
                            ciudad: $scope.currentCiudadMostrar
                        };
                        svc.saveEvento($scope.currentRecord.id, $scope.currentCiudadMostrar.id, nEvento);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrents();
                    }, responseError);
        };


        //Funciones para borrar (BORRAR)

        this.borrarCiudad = function ($event){

            var idCiudad = parseInt($event.currentTarget.name);

            if (idCiudad === $scope.currentCiudadMostrar.id){
                $scope.currentCiudadMostrar = {};
            }

            return svc.deleteCiudad($scope.currentRecord.id, idCiudad)
                    .then(function (){
                        self.fetchCurrents();
                    });
        };

        this.borrarSitio = function ($event){

            var idSitio = parseInt($event.currentTarget.name);

            return svc.deleteSitio($scope.currentRecord.id, $scope.currentCiudadMostrar.id, idSitio)
                    .then(function (){
                        self.fetchCurrentSitios();
                    });
        };

        this.borrarEvento = function ($event){

            var idEvento = parseInt($event.currentTarget.name);

            return svc.deleteEvento($scope.currentRecord.id, $scope.currentCiudadMostrar.id, idEvento)
                    .then(function (){
                        self.fetchCurrentEventos();
                    });
        };


        //Funciones para obtener detalles (DETALLES)

        this.detallesCiudad=function($event){

            var idCiudad = parseInt($event.currentTarget.name);

            return svc.fetchCiudad($scope.currentRecord.id, idCiudad)
                    .then(function (response){
                        $scope.currentCiudadMostrar = response.data;
                    })
                    .then(function (){
                        self.fetchCurrentSitios();
                    })
                    .then(function (){
                        self.fetchCurrentEventos();
                    })
                    .then(function (){
                        self.fetchSitiosBD($scope.currentCiudadMostrar.ciudad.id);
                    })
                    .then(function (){
                        self.fetchCurrents();
                    });
        };

        this.detallesSitio=function($event){

            var idSitio = parseInt($event.currentTarget.name);

            return svc.fetchSitio($scope.currentRecord.id, $scope.currentCiudadMostrar.id, idSitio)
                    .then(function (response){
                        $scope.currentSitioMostrar = response.data;
                    });
        };

        this.detallesEvento=function($event){

            var idEvento = parseInt($event.currentTarget.name);

            return svc.fetchEvento($scope.currentRecord.id, $scope.currentCiudadMostrar.id, idEvento)
                    .then(function (response){
                        $scope.currentEventoMostrar = response.data;
                    });
        };

        // al cargar cualquiera de las plantillas se
        // ejecutan estos
        this.fetchRecordsViajero($scope.currentUser.id)
                .then(function(){
                    self.fetchCurrents();
                });
    }]);

})(window.angular);
