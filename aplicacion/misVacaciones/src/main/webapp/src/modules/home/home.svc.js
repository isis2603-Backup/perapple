/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module("bookModule");
    
     mod.service("bookService", ["$http", "bookContext", function ($http, context) {
        /**
         * Obtener la lista de books.
         * Hace una petición GET con $http a /books para obtener la lista
         * de books
         * @returns {promise} promise para leer la respuesta del servidor}
         * Devuelve una lista de objetos de books con sus atributos y reviews
         */
        this.fetchRecords = function () {
            return $http.get(context);
        };

        /**
         * Obtener un registro de books.
         * Hace una petición GET a /books/:id para obtener
         * los datos de un registro específico de books
         * @param {number} id del registro a obtener
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de books con sus atributos y reviews
         */
        this.fetchRecord = function (id) {
            return $http.get(context + "/" + id);
        };

        /**
         * Guardar un registro de books.
         * Si currentRecord tiene la propiedad id, hace un PUT a /books/:id con los
         * nuevos datos de la instancia de books.
         * Si currentRecord no tiene la propiedad id, se hace un POST a /books
         * para crear el nuevo registro de books
         * @param {object} currentRecord instancia de book a guardar/actualizar
         * @returns {promise} promise para leer la respuesta del servidor
         * Devuelve un objeto de books con sus datos incluyendo el id
         */
        this.saveRecord = function (currentRecord) {
            if (currentRecord.id) {
                return $http.put(context + "/" + currentRecord.id, currentRecord);
            } else {
                return $http.post(context, currentRecord);
            }
        };

        /**
         * Hace una petición DELETE a /books/:id para eliminar un book
         * @param {number} id identificador de la instancia de book a eliminar
         * @returns {promise} promise para leer la respuesta del servidor
         * No devuelve datos.
         */
        this.deleteRecord = function (id) {
            return $http.delete(context + "/" + id);
        };
    }]);
    
    
})(window.angular);

