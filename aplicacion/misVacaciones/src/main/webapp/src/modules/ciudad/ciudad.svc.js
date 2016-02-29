(function (ng) {
    var mod = ng.module("moduloCiudad");

    mod.service("ciudadService", ["$http", "ciudadContext", function ($http, context) {

        /**
         * Obtener la lista de authors.
         * Hace una petición GET con $http a /ciudades para obtener la lista
         * de objetos de la entidad ciudades
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un array de objetos de ciudades.
         */
        this.fetchCiudades = function () {
            return $http.get(context);
        };

        this.fetchSitios= function()
        {
            return $http.get(context = "api/ciudad" + "/"+ "sitios");
        };
        
        /**
         * Obtener un registro de authors.
         * Hace una petición GET a /authors/:id para obtener
         * el objeto de un registro específico de authors
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto instancia de authors.
         */
        this.fetchCiudad= function (id) {
            return $http.get(context + "/" + id);
           
        };


        this.fetchSitio = function(idCiudad, idSitio)
        {
            return $http.get(fetchCiudad(idCiudad)+ "/"+ fetchSitios()+ "/" + idSitio);
        };
        /**
         * Guardar un registro de authors.
         * Si currentRecord tiene la propiedad id, hace un PUT a /authors/:id con los
         * nuevos datos de la instancia de authors.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /authors
         * para crear el nuevo registro de authors
         * @param {object} currentRecord instancia de authors a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor.
         * Se recibe un objeto de authors con su nuevo id
         */
        this.saveCiudad = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

            this.saveSitio = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };


        /**
         * Hace una petición DELETE a /authors/:id para eliminar un author
         * @param {number} id identificador de la instancia de author a eliminar
         * @returns {promise} promise para leer la respuesta del servidor.
         * No se recibe cuerpo en la respuesta.
         */
        this.deleteCiudad = function (id) {
            return $http.delete(context + "/" + id);
        };
        
       
        
         this.deleteSitio = function (idCiudad, idSitio) {
            return $http.delete(context+"/"+ idCiudad + "/"+ "api/ciudad"+"/"+ idSitio);
        };
    }]);
})(window.angular);
