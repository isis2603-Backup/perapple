(function (ng) {
    var mod = ng.module("itinerarioModule");

    mod.service("itinerarioService", ["$http", "itinerarioContext", function ($http, context) {


            //FUNCIONES PARA OBTENER/LEER
        /**
         * Obtener la lista de itinerarios.
         * Hace una petición GET con $http a /itinerario para obtener la lista
         * de objetos de la entidad itinerario
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de itinerario(todos los itinerarios de todos los usuarios).
         */
        this.fetchRecords = function () {
            return $http.get(context);
        };

        /**
         * Obtener un registro de itinerarios.
         * Hace una petición GET a /itinerario/:viajero para obtener
         * el objeto de un registro específico de itinerarios
         * @param {string} viajero del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de itinerario.
         */
        this.fetchRecord = function (usuario) {
            return $http.get(context + "/" + usuario);
        };

        //FUNCIONES PARA AGREGAR/GUARDAR
        /**
         * Guardar un registro de itinerario.
         * Si currentRecord tiene la propiedad viajero, hace un PUT a /itinerario/:viajero con los
         * nuevos datos de la instancia de itinerario.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /itinerario
         * para crear el nuevo registro de itinerario
         * @param {object} currentRecord instancia de itineraio a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de itinerario con su nuevo viajero
         */
        this.saveRecord = function (currentRecord) {
            if (currentRecord.usuario) {
                return $http.put(context + "/" + currentRecord.usuario, currentRecord);
            } else {
                //revisar que hacer si usuario no tiene objeto de modulo itinerario asociado que hace el mock
                return $http.post(context, currentRecord);
            }
        };



       //FUNCIONES PARA BORRADO DE ELEMENTOS

        /**
         * Hace una petición DELETE a /itineraro/:viajero/:nItinerario para eliminar un itinerario
         * @param {string} viajero identificador de la instancia de itinerarios de la que se quiere eliminar
         * @param {string} itinerario identificador del itinerario a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.borrarItinerario = function (viajero, itinerario) {
            return $http.delete(context + "/" + viajero + "/" + itinerario);
        };

        /**
         *
         * @param {type} viajero
         * @param {type} itinerario
         * @param {type} ciudad
         * @returns {unresolved}
         */
        this.borrarCiudad = function (viajero, itinerario, ciudad){
            return $http.delete(context + "/" + viajero + "/" + itinerario+"/"+ciudad);
        };

        /**
         * Hace una petición DELETE a /itineraro/:viajero/S/:sitio para eliminar un sitio de un itinerario de un usuario dado
         * @param {string} viajero identificador de la instancia de itinerarios de la que se quiere eliminar
         * @param {string} itinerario identificador del itinerario del que se quiere eliminar sitio
         * @param {string} sitio identificador del sitio a eliminar
         * * @param {string} ciudad identificador de la ciudad
         * @returns {promise} promise para leer respuesta del servidor
         * No se recibe cuerpo en la respuesta
         */

        this.borrarSitio = function (viajero, ciudad,itinerario, sitio) {
            return $http.delete(context + viajero + "/" + itinerario+ "/"+ciudad +"/S/"+sitio);
        };

        /**
         * Hace una petición DELETE a /itineraro/:viajero/E/:evento para eliminar un evento de un itinerario de un usuario dado
         * @param {string} viajero identificador de la instancia de itinerarios de la que se quiere eliminar
         * @param {string} itinerario identificador del itinerario del que se quiere eliminar sitio
         * @param {string} evento identificador del sitio a eliminar
         * @param {string} ciudad identificador de la ciudad
         * @returns {promise} promise para leer respuesta del servidor
         * No se recibe cuerpo en la respuesta
         */

        this.borrarEvento = function (viajero,itinerario, ciudad,evento) {
            return $http.delete(context + "/" + viajero + "/" + itinerario+"/"+ciudad+"/E/"+evento);
        };


    }]);
})(window.angular);
