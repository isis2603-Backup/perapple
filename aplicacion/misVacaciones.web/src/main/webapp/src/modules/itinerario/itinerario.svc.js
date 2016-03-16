(function (ng) {
    var mod = ng.module("itinerarioModule");

    mod.service("itinerarioService", ["$http", "itinerarioContext", function ($http, context) {

        //Context:
        //context = api/itinerarios

        //Variables:
        var self = this;


        //FUNCIONES PARA OBTENER LISTAS (GET)

        /**
         * Obtener la lista de itinerarios.
         * Hace una petición GET con $http a /itinerario para obtener la lista
         * de objetos de la entidad itinerario
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de itinerario(todos los itinerarios de todos los usuarios).
         */
        this.fetchItinerarios = function () {
            return $http.get(context);
        };

        /**
         * Obtener la lista de itinerarios asociados a cierto viajero.
         * Hace una petición GET con $http a /itinerario/viajero/:idViajero
         * para obtener la lista de objetos de la entidad itinerario asociados a ese viajero
         * @param {string} emailViajero del viajero de quien se quieren los itinerarios
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de itinerario (solo los itinerarios de ese usuario).
         */
        this.fetchItinerariosViajero = function (emailViajero) {
            //console.log("url:"+context + "/viajero/" + emailViajero);
            return $http.get(context + "/viajero/" + emailViajero);
        };

        /**
         * Obtener la lista de ciudades de un itinerario especifico.
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades para obtener la lista
         * de objetos de la entidad ciudad
         * @param {string} idItinerario del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de ciudades
         */
        this.fetchCiudades = function (idItinerario) {
            //console.log("svc.fetchCiudades: " + context + "/" + idItinerario + "/ciudades");
            if(idItinerario)
            return $http.get(context + "/" + idItinerario + "/ciudades");
        };

        /**
         * Obtener la lista de sitios de una ciudad de un itinerario especifico.
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades/:idCiudad/sitios
         * para obtener la lista de objetos de la entidad sitio
         * @param {string} idItinerario al que pertenece la ciudad
         * @param {string} idCiudad a la que pertenecen los sitios a consultar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de sitios
         */
        this.fetchSitios = function (idItinerario, idCiudad) {
            if(idCiudad && idItinerario)
            return $http.get(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/sitios");
        };

        /**
         * Obtener la lista de eventos de una ciudad de un itinerario especifico.
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades/:idCiudad/eventos
         * para obtener la lista de objetos de la entidad sitio
         * @param {string} idItinerario al que pertenece la ciudad
         * @param {string} idCiudad a la que pertenecen los sitios a consultar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de eventos
         */
        this.fetchEventos = function (idItinerario, idCiudad) {
            if(idCiudad && idItinerario)
            return $http.get(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/eventos");
        };


        //FUNCIONES PARA OBTENER OBJETOS (GET)

        /**
         * Obtener el itinerario actual.
         * Hace una petición GET con $http a /itinerario/current para obtener el itinerario actual
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de itinerario
         */
        this.fetchCurrentItinerario = function () {
            return $http.get(context+"/current");
        };

        /**
         * Obtener un registro de itinerario especifico.
         * Hace una petición GET a /itinerario/:idItinerario para obtener
         * el objeto de un registro especifico de itinerarios
         * @param {string} idItinerario del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de itinerario.
         */
        this.fetchItinerario = function (idItinerario) {
            return $http.get(context + "/" + idItinerario);
        };

        /**
         * Obtener un registro de ciudad especifico
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades/:idCiudad
         * el objeto de un registro especifico de ciudad de ese itinerario
         * @param {string} idItinerario al que pertenece la ciudad a obtener
         * @param {string} idCiudad de la ciudad a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de ciudad.
         */
        this.fetchCiudad= function (idItinerario, idCiudad) {
            if(idCiudad && idItinerario)
            return $http.get(context + "/" + idItinerario + "/ciudades/" + idCiudad);
        };

        /**
         * Obtener un registro de sitio especifico
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades/:idCiudad/sitios/:idSitio
         * el objeto de un registro especifico de sitio de esas ciudad de ese itinerario
         * @param {string} idItinerario al que pertenece la ciudad a obtener
         * @param {string} idCiudad de la ciudad a la que perteneces el sitio a obtener
         * @param {string} idSitio del sitio a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de sitio.
         */
        this.fetchSitio = function (idItinerario, idCiudad, idSitio) {
            return $http.get(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/sitios/" + idSitio);
        };

        /**
         * Obtener un registro de evento especifico
         * Hace una petición GET con $http a /itinerario/:idItinerario/ciudades/:idCiudad/eventos/:idEvento
         * el objeto de un registro especifico de sitio de esas ciudad de ese itinerario
         * @param {string} idItinerario al que pertenece la ciudad a obtener
         * @param {string} idCiudad de la ciudad a la que perteneces el evento a obtener
         * @param {string} idEvento del evento a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de evento.
         */
        this.fetchEvento = function (idItinerario, idCiudad, idEvento) {
            return $http.get(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/eventos/" + idEvento);
        };


        //FUNCIONES PARA AGREGAR/ACTUALIZAR (POST/PUT)

        /**
         * Guardar un registro de itinerario.
         * Se hace un PUT a /itinerario/current/ con los
         * nuevos datos de la instancia de itinerario.
         * @param {object} nItinerario instancia de itineraio a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de itinerario con su nuevo viajero
         */
        this.saveCurrentItinerario = function (nItinerario) {
            return $http.put(context + "/current", nItinerario);
        };

        /**
         * Guardar un registro de itinerario.
         * Si nItinerario existe en la lista de itinerarios se hace un PUT a /itinerarios/:nItinerario.id
         * con los nuevos datos de la instancia de itinerario.
         * Si nItinerario no existe en la lista de itinerarios se hace un POST a /itinerarios
         * para crear el nuevo registro de itinerario
         * @param {object} nItinerario instancia de itineraio a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         */
        this.saveItinerario = function (nItinerario) {

            var encontro = false;
            var itinerarios;

            self.fetchItinerarios()
                    .then(function (response) {
                        itinerarios = response.data;
                        for(var i = 0; i<itinerarios.length && !encontro;i++)
                        {
                            if(nItinerario.id === itinerarios[i].id)
                            {
                                encontro = true;
                            }
                        }

                        if (encontro) {
                            return $http.put(context + "/" + nItinerario.id, nItinerario);
                        }
                        else {
                            return $http.post(context, nItinerario);
                        }
                    })
                    //.then(function () {
                      //  for(var i = 0; i<itinerarios.length && !encontro;i++)
                       // {
                         //   if(nItinerario.id === itinerarios[i].id)
                           // {
                             //   encontro = true;
                           // }
                        //}

                        //if (encontro) {
                          //  return $http.put(context + "/" + nItinerario.id, nItinerario);
                        //}
                        //else {
                          //  return $http.post(context, nItinerario);
                        //}
                    //})
                    ;
        };

        /**
         * Guardar un registro de ciudad.
         * Si nCiudad existe en la lista de ciudades se hace un PUT a /itinerarios/:idItinerario/ciudades/:nCiudad.id
         * con los nuevos datos de la instancia de ciudad.
         * Si nCiudad no existe en la lista de ciudades se hace un POST a /itinerarios/:idItinerario/ciudades/
         * para crear el nuevo registro de ciudad
         * @param {object} idItinerario del itinerario al que se le quiere hacer saveCiudad
         * @param {object} nCiudad ciudad a agregar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         */
        this.saveCiudad = function (idItinerario, nCiudad) {

            var ciudades;
            var encontro = false;

            self.fetchCiudades(idItinerario)
                    .then(function(response){
                        ciudades = response.data;
                    })
                    .then(function(){
                        for(var i = 0; i<ciudades.length && !encontro;i++)
                        {
                            if(nCiudad.id === ciudades[i].id)
                            {
                                encontro = true;
                            }
                        }

                        if (encontro) {
                            return $http.put(context + "/" + idItinerario + "/ciudades/" + nCiudad.id, nCiudad);
                        }
                        else {
                            return $http.post(context + "/" + idItinerario + "/ciudades/", nCiudad);
                        }
                    });
        };

        /**
         * Guardar un registro de sitio.
         * Si nSitio existe en la lista de sitios se hace un PUT a /itinerarios/:idItinerario/ciudades/:idCiudad/sitios/:nSitio.id
         * con los nuevos datos de la instancia de sitio.
         * Si nCiudad no existe en la lista de sitios se hace un POST a /itinerarios/:idItinerario/ciudades/:idCiudad/sitios/
         * para crear el nuevo registro de sitio
         * @param {object} idItinerario del itinerario al que se le quiere hacer saveCiudad
         * @param {object} idCiudad de la ciudad a la que se le quiere hacer saveSitio
         * @param {object} nSitio sitio a agregar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         */
        this.saveSitio = function (idItinerario, idCiudad, nSitio) {

            var sitios;
            var encontro = false;

            self.fetchSitios(idItinerario, idCiudad)
                    .then( function(response) {
                        sitios=response.data;
                    })
                    .then( function() {
                        for(var i = 0; i<sitios.length && !encontro;i++)
                        {
                            if(nSitio.id === sitios[i].id)
                            {
                                encontro = true;
                            }
                        }

                        if (encontro) {
                            return $http.put(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/sitios/" + nSitio.id, nSitio);
                        }
                        else {
                            return $http.post(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/sitios/", nSitio);
                        }
                    });
        };

        /**
         * Guardar un registro de evento.
         * Si nSitio existe en la lista de eventos se hace un PUT a /itinerarios/:idItinerario/ciudades/:idCiudad/eventos/:nEvento.id
         * con los nuevos datos de la instancia de evento.
         * Si nCiudad no existe en la lista de sitios se hace un POST a /itinerarios/:idItinerario/ciudades/:idCiudad/eventos/
         * para crear el nuevo registro de evento
         * @param {object} idItinerario del itinerario al que se le quiere hacer saveCiudad
         * @param {object} idCiudad de la ciudad a la que se le quiere hacer saveEvento
         * @param {object} nEvento evento a agregar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         */
        this.saveEvento = function (idItinerario, idCiudad, nEvento) {

            var eventos;
            var encontro = false;

            self.fetchEventos(idItinerario, idCiudad)
                    .then( function(response) {
                        eventos=response.data;
                    })
                    .then( function() {
                        for(var i = 0; i<eventos.length && !encontro;i++)
                        {
                            if(nEvento.id === eventos[i].id)
                            {
                                encontro = true;
                            }
                        }

                        if (encontro) {
                            return $http.put(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/eventos/" + nEvento.id, nSitio);
                        }
                        else {
                            return $http.post(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/eventos/", nEvento);
                        }
                    });
        };


        //FUNCIONES PARA BORRAR (DELETE)

        /**
         * Hace una petición DELETE a /itineraro/:idItinerario para eliminar un itinerario
         * @param {string} idItinerario identificador del itinerario a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteItinerario = function (idItinerario) {
            return $http.delete(context + "/" + idItinerario );
        };

        /**
         * Hace una petición DELETE a /itineraro/:idItinerario/ciudades/:idCiudad
         * para eliminar una ciudad de ese itinerario
         * @param {string} idItinerario identificador del itinerario que tiene esa ciudad
         * @param {string} idCiudad identificador de la ciudad a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteCiudad = function (idItinerario, idCiudad) {
            return $http.delete(context + "/" + idItinerario + "/ciudades/" + idCiudad );
        };

        /**
         * Hace una petición DELETE a /itineraro/:idItinerario/ciudades/:idCiudad/sitios/:idSitio
         * para eliminar un sitio de una ciudad dada de un itinerario dado
         * @param {string} idItinerario identificador del itinerario del que se quiere eliminar sitio
         * @param {string} idCiudad identificador de la ciudad de la que se quiere eliminar el sitio
         * @param {string} idSitio identificador del sitio a eliminar
         * @returns {promise} promise para leer respuesta del servidor
         * No se recibe cuerpo en la respuesta
         */
        this.deleteSitio = function (idItinerario, idCiudad, idSitio) {
            return $http.delete(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/sitios/" + idSitio);
        };

        /**
         * Hace una petición DELETE a /itineraro/:idItinerario/ciudades/:idCiudad/eventos/:idEvento
         * para eliminar un evento de una ciudad dada de un itinerario dado
         * @param {string} idItinerario identificador del itinerario del que se quiere eliminar sitio
         * @param {string} idCiudad identificador de la ciudad de la que se quiere eliminar el sitio
         * @param {string} idEvento identificador del evento a eliminar
         * @returns {promise} promise para leer respuesta del servidor
         * No se recibe cuerpo en la respuesta
         */
        this.deleteEvento = function (idItinerario, idCiudad, idEvento) {
            return $http.delete(context + "/" + idItinerario + "/ciudades/" + idCiudad + "/eventos/" + idEvento);
        };

    }]);
})(window.angular);
