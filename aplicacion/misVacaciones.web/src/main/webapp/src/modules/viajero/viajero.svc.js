/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

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
        this.fetchViajeros = function () {
            return $http.get(context + "/" + "viajeros");
        };

        /**
         * GET
         * Obtener un registro de viajero.
         * Hace una petición GET a /viajeros/:viajeroid para obtener
         * los datos de un registro específico de viajero
         * @param {number} idViajero del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de viajero con sus atributos
         */
        this.fetchViajero = function (idViajero) {
            return $http.get(context + "/" + "viajeros" + "/" + idViajero);
        };

        /**
         * POST
         * Guardar un registro de viajero.
         * Si currentViajero tiene la propiedad id, hace un PUT a /viajeros/:viajeroid con los
         * nuevos datos de la instancia de viajero.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /viajeros
         * para crear el nuevo registro de viajero
         * @param {object} currentViajero instancia de viajero a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de viajero con sus datos incluyendo el id si es creado
         */
        this.saveViajero = function (currentViajero) {
            return $http.put(context + "/" + currentViajero.id, currentViajero);
        };

        /**
         * DELETE
         * Hace una petición DELETE a /viajeros/:viajerosid para eliminar un viajero
         * @param {number} idViajero identificador de la instancia de viajero a eliminar
         * @returns {promise} promise para leer la respuesta del servidor
         * No devuelve datos.
         */
        this.deleteViajero = function (idViajero) {
            return $http.delete(context + "/" + idViajero);
        };
    }]);
})(window.angular);

