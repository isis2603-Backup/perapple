(function (ng) {

    var mod = ng.module("itinerarioModule");

    mod.controller("itinerarioCtrl", ["$scope","itinerarioService","ciudadService",function ($scope, svc, svcCiudad) {

        //Variables current
        $scope.currentUser = 'p@earpple.com';
        $scope.records=[]; //itinerarios a mostrar según el currentUser
        $scope.currentRecord = {}; //itinerario del que se muestra todo
        $scope.currentCiudadesMostrar = []; //ciudades que se muestran en la lista
        $scope.currentCiudadMostrar = {}; //ciudad actual de la que se muestran detalles
        $scope.currentSitiosMostrar = []; //sitios a mostrar según la ciudad mostrada en detalles
        $scope.currentSitioMostrar={}; //Sitio del cual se muestran detalles
        $scope.currentEventosMostrar = []; //eventos a mostrar según la ciudad mostrada en detalles
        $scope.currentEventoMostrar={}; //Sitio del cual se muestran detalles

        //Variables para agregar ciudad
        $scope.ciudadesBD = [];
        $scope.fechaIni = new Date ();
        $scope.fechaFin = new Date ();

        //Variables para agregar sitio
        $scope.sitiosBD = []; //sitios disponibles para agregar
        $scope.fechaSitio = new Date(); //fecha visita del sitio

        //Variables para agregar evento
        $scope.eventosBD = []; //eventos disponibles para agregar
        $scope.fechaEvento = new Date(); //fecha evento del sitio

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
            self.fetchRecordsViajero($scope.currentUser);
        });

        //Variables para el controlador
        var self = this;
        this.readOnly = false;
        this.editMode = false;

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

        this.fetchRecordsViajero = function (emailViajero) {
            return svc.fetchItinerariosViajero(emailViajero).then(function (response) {
                $scope.records = response.data;
                $scope.currentUser = emailViajero;
                self.editMode = false;
                return response;
            }, responseError);
        };

        this.fetchCurrentRecord = function () {
            return svc.fetchCurrentItinerario().then(function (response) {
                $scope.currentRecord = response.data;
                return response;
            }, responseError);
        };

        this.itinerarioActual = function($event){

            var idItinerario = parseInt($event.currentTarget.name);

            return svc.fetchItinerario(idItinerario)
                    .then(function(response){
                        $scope.currentRecord = response.data;
                    }, responseError)
                    .then(function(){
                        svc.saveCurrentItinerario($scope.currentRecord);
                        self.fetchCurrentRecord();
                    }, responseError)
                    .then(function(){
                        self.fetchCurrentCiudades();
                    }, responseError);
        };

        this.agregarItinerario = function (nombre, fechaI, fechaF){

            $scope.currentRecord = {
                                    viajero: $scope.currentUser,
                                    nombre: nombre ,
                                    fechaInicio: fechaI ,
                                    fechaFin: fechaF,
                                    ciudades: [],
                                    sitios:[],
                                    eventos: []
                                   };

            return svc.saveItinerario($scope.currentRecord)
                    .then(function () {
                        self.fetchRecordsViajero($scope.currentUser);
                    }, responseError);
        };

        this.borrarItinerario = function ($event){
            var idItinerario = parseInt($event.currentTarget.name);
            return svc.deleteItinerario(idItinerario)
                    .then(function () {
                        self.fetchRecordsViajero($scope.currentUser);
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
            self.fetchCurrentRecord()
                    .then(
                        function(){
                            if($scope.currentRecord.id)
                                self.fetchCurrentCiudades();
                        }, responseError
                    )
                    .then(
                        function(){
                            if($scope.currentCiudadMostrar.id){
                                self.fetchCurrentSitios();
                                self.fetchCurrentEventos();}
                        }, responseError
                    );
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

            var ciudadBD = {};
            var idCiudad = parseInt($event.currentTarget.name);

            return svcCiudad.fetchCiudad(idCiudad)
                    .then(function (response) {
                        ciudadBD = response.data;
                        return response;
                    }, responseError)
                    .then(function () {
                        var nCiudad = {id:ciudadBD.id,
                            nombre:ciudadBD.nombre,
                            detalles:ciudadBD.detalles,
                            imagen:ciudadBD.imagen,
                            fechaInicio: $scope.fechaIni,
                            fechaFin: $scope.fechaFin,
                            sitios: [],
                            eventos: []
                          };
                        svc.saveCiudad($scope.currentRecord.id, nCiudad);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrentCiudades();
                    }, responseError);
        };

        this.agregarSitio = function($event){

            var idSitio = parseInt($event.currentTarget.name);
            var sitioBD = {};

            return svcCiudad.fetchSitio($scope.currentCiudadMostrar.id, idSitio)
                    .then(function (response) {
                        sitioBD = response.data;
                        return response;
                    }, responseError)
                    .then(function () {
                        var nSitio = {id:sitioBD.id,
                                        nombre:sitioBD.nombre,
                                        detalles:sitioBD.detalles,
                                        imagen:sitioBD.imagen,
                                        fecha:$scope.fechaSitio
                                     };
                        svc.saveSitio($scope.currentRecord.id, $scope.currentCiudadMostrar.id, nSitio);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrentSitios();
                    }, responseError);
        };

        this.agregarEvento = function($event){

            var idEvento = parseInt($event.currentTarget.name);
            var eventoBD = {};

            return svcCiudad.fetchEvento($scope.currentCiudadMostrar.id, idEvento)
                    .then(function (response) {
                        eventoBD = response.data;
                        console.log("ctrl agregar evento eventoBD: "+eventoBD.name);
                        return response;
                    }, responseError)
                    .then(function () {
                        var nEvento = {id:eventoBD.id,
                                        nombre:eventoBD.nombre,
                                        detalles:eventoBD.detalles,
                                        fecha:$scope.fechaEvento
                                      };
                        svc.saveEvento($scope.currentRecord.id, $scope.currentCiudadMostrar.id, nEvento);
                    }, responseError)
                    .then(function () {
                        self.fetchCurrentEventos();
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
                        self.fetchSitiosBD(idCiudad);
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
        this.fetchRecordsViajero($scope.currentUser);
        this.fetchCurrentRecord();
        this.fetchCurrents();
    }]);

})(window.angular);
