(function (ng) {
    var mod = ng.module("moduloCiudad");
    
    mod.service("ciudadService", ["$http", "ciudadContext", function ($http, context) {
        /**
         * Obtener la lista de ciudades.
         * Hace una petición GET con $http a /ciudades para obtener la lista
         * de objetos de la entidad ciudades
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de ciudades.
         */
        this.fetchCiudades = function () {
            return $http.get(context);
        };

        /**
         * Obtener lista de sitios de ciudad con idCiudad
         * Hace una petición GET con $http a /ciudades/idciudad/sitios
         * @returns {promise} promise para leer la respuesta del servidor.
         * @param {number} idCiudad de la ciudad a la que pertenecen los sitios
         * Se recibe un array de objetos de sitios.
         */
        this.fetchSitios= function(idCiudad)
        {
            return $http.get(context+ "/" + idCiudad + "/"+ "sitios");
        };

        /**
         * Obtener lista de eventos de ciudad con idCiudad
         * Hace una petición GET con $http a /ciudades/idciudad/eventos
         * @returns {promise} promise para leer la respuesta del servidor.
         * @param {number} idCiudad de la ciudad a la que pertenecen los eventos
         * Se recibe un array de objetos de eventos.
         */
        this.fetchEventos=function(idCiudad)
        {
            return $http.get(context+ "/" + idCiudad + "/"+ "eventos");
        };

        /**
         * Obtener lista de hoteles de ciudad con idCiudad
         * Hace una petición GET con $http a /ciudades/idciudad/hoteles
         * @returns {promise} promise para leer la respuesta del servidor.
         * @param {number} idCiudad de la ciudad a la que pertenecen los sitios
         * Se recibe un array de objetos de hoteles.
         */
        this.fetchHoteles=function(idCiudad)
        {
            return $http.get(context+ "/" + idCiudad + "/"+ "hoteles");
        };

        /**
         * Obtener un registro de ciudad.
         * Hace una petición GET a /ciudades/:id para obtener
         * el objeto de un registro específico de ciudades
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de ciudad.
         */
        this.fetchCiudad= function (id) {
            return $http.get(context + "/" + id);
        };

        /**
         * Obtener un registro de sitio con idSitio de una ciudad con idCiudad.
         * Hace una petición GET a /ciudades/:idCiudad/sitios/:idSitio para obtener
         * el objeto de un registro específico de sitios
         * @param {number} idCiudad del registro a obtener
         * @param {number} idSitio del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de sitio.
         */
        this.fetchSitio = function(idCiudad,idSitio)
        {
            return $http.get(context+"/"+ idCiudad +"/"+ "sitios"+ "/" + idSitio);
        };

        /**
         * Obtener un registro de evento con idEvento de una ciudad con idCiudad.
         * Hace una petición GET a /ciudades/:idCiudad/sitios/:idSitio para obtener
         * el objeto de un registro específico de eventos
         * @param {number} idCiudad del registro a obtener
         * @param {number} idEvento del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de evento.
         */
        this.fetchEvento = function(idCiudad,idEvento)
        {
            return $http.get(context+"/"+ idCiudad +"/eventos/" + idEvento);
        };

        /**
         * Obtener un registro de evento con idEvento de una ciudad con idCiudad.
         * Hace una petición GET a /ciudades/:idCiudad/sitios/:idSitio para obtener
         * el objeto de un registro específico de eventos
         * @param {number} idCiudad del registro a obtener
         * @param {number} idHotel del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de evento.
         */
        this.fetchHotel = function(idCiudad,idHotel)
        {
            return $http.get(context+"/"+ idCiudad +"/"+ "hoteles/" + idHotel);
        };

        /**
         * Guardar un registro de ciudades.
         * Si currentRecord tiene la propiedad id, hace un PUT a /ciudades/:id con los
         * nuevos datos de la instancia de authors.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /ciudades
         * para crear el nuevo registro de ciudades
         * @param {object} currentRecord instancia de ciudades a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de ciudades con su nuevo id
         */
        this.saveCiudad = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

         /** REVISAR DE ACA*/

        this.saveSitio = function (currentRecord, idSitio) {
            if (currentRecord.id.sitios.id) {
                return $http.put(context + "/" + currentRecord.id+ "/"+ "sitios"+ idSitio, currentRecord);
            } else {
                return $http.post(context+ "/"+currentRecord.id+"/"+"sitios", currentRecord);
            }
        };

        this.saveEvento = function (currentRecord, idEvento) {
            if (currentRecord.id.eventos.id) {
                return $http.put(context + "/" + currentRecord.id+ "/"+ "eventos"+ idEvento, currentRecord);
            } else {
                return $http.post(context+ "/"+currentRecord.id+"/"+"eventos", currentRecord);
            }
        };

        this.saveHotel = function (currentRecord, idHotel) {
            if (currentRecord.id.hoteles.id) {
                return $http.put(context + "/" + currentRecord.id+ "/"+ "eventos"+ idHotel, currentRecord);
            } else {
                return $http.post(context+ "/"+currentRecord.id+"/"+"eventos", currentRecord);
            }
        };

        /** REVISAR HASTA ACA*/

        /**
         * Hace una petición DELETE a /ciudades/:id para eliminar una ciudad
         * @param {number} id identificador de la instancia de ciudad a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteCiudad = function (id) {
            return $http.delete(context + "/" + id);
        };

        /**
         * Hace una petición DELETE a /ciudades/:idCiudad/sitios/idSitio para
         * eliminar un sitio de esa ciudad
         * @param {number} idCiudad identificador de la instancia de ciudad
         * @param {number} idSitio identificador de la instancia sitio a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteSitio = function (idCiudad, idSitio) {
            return $http.delete(context+"/"+ idCiudad + "/"+ "sitios"+"/"+ idSitio);
        };

        /**
         * Hace una petición DELETE a /ciudades/:idCiudad/eventos/idEvento para
         * eliminar un evento de esa ciudad
         * @param {number} idCiudad identificador de la instancia de ciudad
         * @param {number} idEvento identificador de la instancia evento a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteEvento = function (idCiudad, idEvento) {
            return $http.delete(context+"/"+ idCiudad + "/"+ "eventos"+"/"+ idEvento);
        };

        /**
         * Hace una petición DELETE a /ciudades/:idCiudad/hoteles/idHotel para
         * eliminar un hotel de esa ciudad
         * @param {number} idCiudad identificador de la instancia de ciudad
         * @param {number} idHotel identificador de la instancia hotel a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteHotel = function (idCiudad, idHotel) {
            return $http.delete(context+"/"+ idCiudad + "/"+ "hoteles"+"/"+ idHotel);
        };

    }]);
})(window.angular);