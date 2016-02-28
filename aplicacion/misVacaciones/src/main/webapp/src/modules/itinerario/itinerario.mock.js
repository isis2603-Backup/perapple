/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('itinerarioMock', ['ngMockE2E']);


    mod.run(['$httpBackend', function ($httpBackend) {
        var ignore_regexp = new RegExp('^((?!api).)*$');
        /*
         * @type RegExp
         * recordUrl acepta cualquier url con el formato
         * api/(cualquierpalabra)/(numero)
         * ej: api/authors/1
         */
        var recordUrl = new RegExp('api/itinerario/([0-9]+)');

        /*
         * @type Array
         * records: Array con un Author por defecto
         */
        var records = [

                {   id:1,
                    viajero: 'perapple',
                    nombre: 'Verano 2016',
                    fechaInicio: '12-05-2016',
                    fechaFin: '13-06-2016',
                    ciudades: [
                            {
                            id: 1,
                            nombre: 'Cali',
                            descripcion: 'Sucursal del cielo',
                            foto: 'link_foto_cali.png',
                            fInicio: '12-05-2016',
                            fFin: '23-05-2016',
                            sitios: [
                                {id: 1,nombre:'Zoologico de Cali', descripcion:'Muchos animales'},
                                {id: 2,nombre:'Zoologico de Cali', descripcion:'Muchos animales'}
                            ],
                            eventos: [
                                {id: 1,nombre:'Partido America vs Nacional', descripcion: 'Partido de la copa Postobon', fecha: '13-05-2016'},
                                {id: 2,nombre:'Partido America vs Nacional', descripcion: 'Partido de la copa Postobon', fecha: '13-05-2016'}
                            ]
                        }
                    ]
                },

                  { id: 2,
                    viajero: 'perapple',
                    nombre: 'Verano 2016-2',
                    fechaInicio: '12-07-2016',
                    fechaFin: '13-08-2016',
                    ciudades: [
                             {
                            id:1,
                            nombre: 'Bogotá',
                            descripcion: '2600 metros más cerca de las estrellas',
                            foto: 'http://4.bp.blogspot.com/-XcTA8HOervg/U1jgFFAEFWI/AAAAAAAAWKI/o0epuGtwH2I/s1600/Plaza+de+Bolivar+en+Bogot%C3%A1.jpg',
                            fInicio: '12-05-2016',
                            fFin: '23-05-2016',
                            sitios: [
                                {id:1,nombre:'PLaza de Bolivar', descripcion:'Muchas palomas'},
                                 {id:2,nombre:'Catedral Primada', descripcion:'Mucha historia'}
                            ],
                            eventos: [
                                 {id:1,nombre:'Partido Millonarios vs Santa fe', descripcion: 'Partido de la copa Postobon', fecha: '13-05-2016'},
                                {id:2,nombre:'Partido Milonarios vs Nacional', descripcion: 'Partido de la copa Postobon', fecha: '20-05-2016'}
                            ]
                        }
                    ]
                }

            ];


        function getQueryParams(url) {
            var vars = {}, hash;
            var hashes = url.slice(url.indexOf('?') + 1).split('&');
            for (var i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars[hash[0]] = hash[1];
            }
            return vars;
        }

        /*
         * Ignora las peticiones GET, no contempladas en la Exp regular ignore_regexp
         */
        $httpBackend.whenGET(ignore_regexp).passThrough();

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/itinerarios"
         * Obtiene los parámetros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de itinerarios y los headers.
         */
        $httpBackend.whenGET('api/itinerario').respond(function (method, url) {
            var queryParams = getQueryParams(url);
            var responseObj = [];
            var page = queryParams.page;
            var maxRecords = queryParams.maxRecords;
            var headers = {};
            if (page && maxRecords) {
                var start_index = (page - 1) * maxRecords;
                var end_index = start_index + maxRecords;
                responseObj = records.slice(start_index, end_index);
                headers = {"X-Total-Count": records.length};
            } else {
                responseObj = records;
            }
            return [200, responseObj, headers];
        });
        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/itinerarios/[numero]"
         * Obtiene el id de la url y el registro asociado dentro del array records.
         * Response: 200 -> Status ok, record -> libro y ningún header.
         */
        $httpBackend.whenGET(recordUrl).respond(function (method, url) {
            var id = parseInt(url.split('/').pop());
            var record;
            ng.forEach(records, function (value) {
                if (value.id === id) {
                    record = ng.copy(value);
                }
            });
            return [200, record, {}];
        });
        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/authors"
         * Obtiene el record de libro desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al record de libro y lo guarda en el
         * array de records.
         * Response: 201 -> Status created, record -> libro y ningún header.
         */
        $httpBackend.whenPOST('api/itinerario').respond(function (method, url, data) {
            var record = ng.fromJson(data);
            record.id = Math.floor(Math.random() * 10000);
            records.push(record);
            return [201, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/itinerarios/[numero]"
         * Obtiene el id del la url y el registro asociado dentro del array records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */

        $httpBackend.whenDELETE(recordUrl).respond(function (method, url) {
            var id = parseInt(url.split('/').pop());
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1);
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/itinerarios/[numero]"
         * Obtiene el id del la url y el record de libro desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         *
         */
        $httpBackend.whenPUT(recordUrl).respond(function (method, url, data) {
            var id = parseInt(url.split('/').pop());
            var record = ng.fromJson(data);
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1, record);
                }
            });
            return [204, null, {}];
        });

    }]);
})(window.angular);


