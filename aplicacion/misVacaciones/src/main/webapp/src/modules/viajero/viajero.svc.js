/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){

    var mod = ng.module("viajeroModule");

     mod.service("viajeroService", ["$http", "viajeroContext", function ($http, context) {
        /**
         * GET
         * Obtener la lista de viajero.
         * Hace una petición GET con $http a /viajeros para obtener la lista
         * de viajeros
         * @returns {promise} promise para leer la respuesta del servidor}
         * Devuelve una lista de objetos de viajeros con sus atributos 
         */
        this.fetchRecords = function () {
            return $http.get(context);
        };

        /**
         * GET
         * Obtener un registro de viajero.
         * Hace una petición GET a /viajeros/:viajeroid para obtener
         * los datos de un registro específico de viajero
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de viajero con sus atributos 
         */
        this.fetchRecord = function (id) {
            return $http.get(context + "/" + id);
        };

        /**
         * POST
         * Guardar un registro de viajero.
         * Si currentRecord tiene la propiedad id, hace un PUT a /viajeros/:viajeroid con los
         * nuevos datos de la instancia de viajero.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /viajeros
         * para crear el nuevo registro de viajero
         * @param {object} currentRecord instancia de viajero a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de viajero con sus datos incluyendo el id si es creado
         */
        this.saveRecord = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

        /**
         * DELETE
         * Hace una petición DELETE a /viajeros/:viajerosid para eliminar un viajero
         * @param {number} id identificador de la instancia de viajero a eliminar
         * @returns {promise} promise para leer la respuesta del servidor
         * No devuelve datos.
         */
        this.deleteRecord = function (id) {
            return $http.delete(context + "/" + id);
        };
    }]);


})(window.angular);

