/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('itinerarioMock', ['ngMockE2E']);


    mod.run(['$httpBackend', function ($httpBackend) {
            
        /*
         * @type String
         * api/itinerario/
         */
        var context = 'api/itinerario';
        /*
         * @type RegExp
         * recordUrl acepta cualquier url con el formato
         * api/itinerario/(numero)
         * ej: api/itinerario/1
         */
        var urlItinerario = new RegExp('api/itinerario/([0-9]+)');
        /*
         * @type RegExp
         * recordUrl acepta cualquier url con el formato
         * api/itinerario/viajero/(username)
         * ej: api/itinerario/viajero/perapple
         */
        var urlViajero = new RegExp('api/itinerario/viajero/([a-zA-Z0-9._-]+)@([a-zA-Z0-9.-]+)\.([a-zA-Z]{2,4})');
        /*
         * @type Array
         * records: Array con 2 itinerarios por defecto
         */
        var records = [
                {   
                    id:1,
                    viajero: 'p@earpple.com',
                    nombre: 'Verano 2016',
                    fechaInicio: '12-05-2016',
                    fechaFin: '13-06-2016',
                    ciudades: [
                            {
                            id: 2,
                            nombre: 'Cali',
                            detalles: 'Cali, oficialmente Santiago de Cali, es un municipio colombiano, capital del departamento del Valle del Cauca, es la tercera ciudad más poblada de Colombia. \n\
                                        Está situada en la región Sur del Valle del Cauca, entre la cordillera occidental y la cordillera central de los Andes. La ciudad forma parte del Área Metropolitana de Cali, \n\
                                        junto con los municipios aledaños a ésta. Fue fundada el 25 de julio de 1536 por Sebastián de Belalcázar, lo que la convierte en una de las ciudades más antiguas de América.\n\
                                        \n\
                                        Es a su vez conocida como «La capital mundial de la salsa», «La Sucursal del cielo».\n\
                                        Temperatura promedio= 23°C \n\
                                        Altitud= 1018 metros\n\
                                        Población= 2.394.870 habitantes ',
                            imagen: 'http://www.elchontaduro.com/wp-content/uploads/2016/01/pub54007.jpg',
                            fInicio: '12-05-2016',
                            fFin: '23-05-2016',
                            sitios: [
                                {
                                    id:1,
                                    nombre:'Zoologico de Cali',
                                    detalles:'Es innegable que la región ha redescubierto al Zoológico de Cali como epicentro de conservación,\n\
                                              que goza de credibilidad en virtud de su transparencia, honestidad y profesionalismo. \n\
                                              Por más de una década ha ejercido un liderazgo en la comunidad zoológica nacional e internacional,\n\
                                              promoviendo y acompañando el desarrollo de otras instituciones de su misma naturaleza, \n\
                                              y participando activamente en la consolidación de una comunidad más comprometida con la conservación de la biodiversidad. \n\
                                              El Zoológico de Cali ha crecido bajo una forma de organización con objetivos claros y compartidos, \n\
                                              sustentada en principios y valores en el marco de un pensamiento estratégico que propone relatos innovadores en \n\
                                              una institución que contribuye a crear escenarios de bienestar para las comunidades humanas y la vida silvestre. \n\
                                              El Zoológico de Cali es una plataforma que promueve la construcción del compromiso ambiental.',
                                    imagen:'http://www.icesi.edu.co/blogs_estudiantes/zooincali/files/2012/09/mapa_final-1024x6401.jpg',
                                    fecha:'13-05-2016'
                                },
                                { 
                                    id:2,
                                    nombre:'Teatro Municipal Enrique Buenaventura',
                                    detalles:'Anteriormente llamado Teatro Municipal de Cali, es un teatro declarado monumento nacional en 1982 que se encuentra en el centro de la ciudad de Santiago de Cali.\n\
                                              El teatro cuenta con un estilo clásico italiano y se encuentra adornado por múltiples obras de artistas nacionales y extranjeros.\n\
                                              La Sala Principal del teatro tiene una capacidad de 1039 espectadores que se dividen en diferentes localidades, cada uno con un nombre particular, \n\
                                              siendo la Luneta la primera y está situada a un nivel más bajo que el escenario. Le siguen el Primer y Segundo palco respectivamente divididos en cubículos numerados y \n\
                                              en los dos últimos niveles el anfiteatro y la galería.',
                                    imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0b/CONTRASTES.JPG',
                                    fecha:'20-05-2016'
                                },
                                {
                                    id:3,
                                    nombre:'Iglesia de la Ermita',
                                    detalles:'La iglesia la Ermita está ubicada en Santiago de Cali, Colombia. Originalmente fue una construcción pajiza de comienzos del siglo XVII, establecida en las cercanías del río Cali y\n\
                                              dedicada a Nuestra Señora de la Soledad y al Señor de la Caña.\n\
                                              En 1942, se construyó la iglesia que se ve hoy en día, la cual es uno de los referentes del paisaje arquitectónico de la Ciudad de Cali. La actual construcción es una iglesia gótica en miniatura,\n\
                                              y como muchas iglesias góticas en América está inspirada en la Catedral de Ulm, Alemania.\n\
                                              La nueva ermita está dedicada a Nuestra Señora de los Dolores y en su interior conserva la antigua imagen del Señor de la Caña en el altar lateral izquierdo.',
                                    imagen:'https://upload.wikimedia.org/wikipedia/commons/2/2f/Ermita_cali.jpg',
                                    fecha:'22-05-2016'
                                }
                            ],
                            eventos: [
                                {
                                    id: 1,
                                    nombre:'Partido America vs Nacional',
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '13-05-2016'
                                },
                                {
                                    id: 2,
                                    nombre:'Partido America vs Junior', 
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '20-05-2016'
                                }
                            ]
                        },
                        {
                            id:1,
                            nombre: 'Bogotá',
                            detalles: 'Bogotá es la capital de la República de Colombia y del departamento de Cundinamarca.\n\
                                        Es la tercera capital más alta en América del Sur (después de La Paz y Quito), \n\
                                        a un promedio de 2625 metros sobre el nivel del mar. «2600 metros más cerca de las estrellas»\n\
                                        \n\
                                        Temperatura promedio= 14°C\n\
                                        Altitud= 2600 metros\n\
                                        Población= 7.980.001 habitantes',
                            imagen: 'http://4.bp.blogspot.com/-XcTA8HOervg/U1jgFFAEFWI/AAAAAAAAWKI/o0epuGtwH2I/s1600/Plaza+de+Bolivar+en+Bogot%C3%A1.jpg',
                            fInicio: '12-05-2016',
                            fFin: '23-05-2016',
                            sitios: [
                                {
                                    id:1,
                                    nombre:'Museo del oro',
                                    detalles:'El Museo del Oro del Banco de la República de Colombia es una institución abierta al público cuya finalidad es la adquisición, \n\
                                                conservación y exposición de piezas de orfebrería y alfarería de culturas indígenas del periodo precolombino de la actual Colombia. \n\
                                                Está ubicado en el costado oriental del parque Santander, en el centro histórico de Bogotá.\n\
                                                \n\
                                                Posee la colección de orfebrería prehispánica más grande del mundo con aproximadamente treinta y cuatro mil piezas de oro y tumbaga,\n\
                                                cerca de veinticinco mil objetos en cerámica, piedra, concha, hueso y textiles. \n\
                                                Expone piezas de diferentes culturas indígenas asentadas en la actual Colombia antes de la llegada de los europeos, \n\
                                                entre las que destacan la Calima, los muiscas, la Nariño, la quimbaya, la sinú, la tairona, la San Agustín, la Tierradentro, la Tolima, \n\
                                                entre otras cosas.',
                                    imagen:'https://upload.wikimedia.org/wikipedia/commons/d/dc/BOG_Museo_del_Oro.JPG',
                                    fecha:'13-05-2016'
                                },
                                {
                                    id:2,
                                    nombre:'Parque de la 93',
                                    detalles:'El parque de la 93 es un parque turístico en el norte de Bogotá.\n\
                                                Se encuentra ubicado en la localidad de Chapinero, entre las calles 93 A y 93 B y entre las carreras 11 A y 13, \n\
                                                en el sector de El Chicó. Fue inaugurado el 14 de junio de 1995.\n\
                                                En el parque se encuentra la primera tienda de Starbucks y Carls Jr. en toda Colombia.',
                                    imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0d/Bogot%C3%A1barrio_El_Chic%C3%B3_Parque_de_la_93.JPG',
                                    fecha:'18-05-2016'
                                },
                                {
                                    id:3,
                                    nombre:'La Candelaria',
                                    detalles:'La Candelaria es tradicionalmente la localidad más reconocida de la ciudad pues de ella hace parte todo el centro histórico de Bogotá, \n\
                                                desde el sitio original de la fundación de la ciudad (según los historiadores la fecha es el 6 de agosto de 1538) hasta el establecimiento de la primera iglesia y la Plaza Mayor,\n\
                                                plaza que actualmente se conoce como la Plaza de Bolívar y en la que está ubicada, en su costado oriental, la Catedral Primada de Colombia,\n\
                                                nombre que adquirió aquella primera iglesia construida en plena fundación de la ciudad.\n\
                                                \n\
                                                La Candelaria no sólo es el centro histórico de Bogotá, con los años se ha convertido además en un importante centro comercial y de negocios para la ciudad pues ofrece mercados y \n\
                                                oportunidades para todos los habitantes de la ciudad sin importar el origen de su condición social.',
                                    imagen:'http://bogotabureau.com/herramientas/wp-content/uploads/2014/02/La-Candelaria.jpg',
                                    fecha:'22-05-2016'
                                }
                            ],
                            eventos: [
                                {
                                    id:3,
                                    nombre:'Partido Millonarios vs Santa Fe', 
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '13-05-2016'
                                },
                                {
                                    id:4,
                                    nombre:'Partido Milonarios vs Nacional', 
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '20-05-2016'
                                }
                            ]
                        }
                    ]
                },
                { 
                    id: 2,
                    viajero: 'p@earpple.com',
                    nombre: 'Invierno 2016',
                    fechaInicio: '12-07-2016',
                    fechaFin: '13-08-2016',
                    ciudades: [
                        {
                            id:1,
                            nombre: 'Bogotá',
                            detalles: 'Bogotá es la capital de la República de Colombia y del departamento de Cundinamarca.\n\
                                        Es la tercera capital más alta en América del Sur (después de La Paz y Quito), \n\
                                        a un promedio de 2625 metros sobre el nivel del mar. «2600 metros más cerca de las estrellas»\n\
                                        \n\
                                        Temperatura promedio= 14°C\n\
                                        Altitud= 2600 metros\n\
                                        Población= 7.980.001 habitantes',
                            imagen: 'http://4.bp.blogspot.com/-XcTA8HOervg/U1jgFFAEFWI/AAAAAAAAWKI/o0epuGtwH2I/s1600/Plaza+de+Bolivar+en+Bogot%C3%A1.jpg',
                            fInicio: '12-05-2016',
                            fFin: '23-05-2016',
                            sitios: [
                                {
                                    id:4,
                                    nombre:'Plaza de Bolivar', 
                                    detalles:'Muchas palomas',
                                    fecha: '14-05-2016'
                                },
                                {
                                    id:5,
                                    nombre:'Catedral Primada', 
                                    detalles:'Mucha historia',
                                    fecha: '17-05-2016'
                                }
                            ],
                            eventos: [
                                {
                                    id:3,
                                    nombre:'Partido Millonarios vs Santa Fe', 
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '13-05-2016'
                                },
                                {
                                    id:4,
                                    nombre:'Partido Millonarios vs Nacional', 
                                    detalles: 'Partido de la copa Postobon', 
                                    fecha: '20-05-2016'
                                }
                            ]
                        }
                    ]
                }
        ];
        var current = {};

        function getQueryParams(url) {
            var vars = {}, hash;
            var hashes = url.slice(url.indexOf('?') + 1).split('&');
            for (var i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars[hash[0]] = hash[1];
            }
            return vars;
        }


        //GET

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/itinerarios"
         * Obtiene los parámetros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de itinerarios y los headers.
         */
        $httpBackend.whenGET(context).respond(function (method, url) {
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
        $httpBackend.whenGET(urlItinerario).respond(function (method, url) {
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
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/itinerario/viajero/[username]"
         * Obtiene el id de la url y el registro asociado dentro del array records.
         * Response: 200 -> Status ok, record -> libro y ningún header.
         */
        $httpBackend.whenGET(urlViajero).respond(function (method, url) {
            var email_viajero = url.split('/').pop();
            var records_viajero = [];
            ng.forEach(records, function (value) {
                if (value.viajero === email_viajero) {
                    records_viajero.push(value);
                }
            });
            return [200, records_viajero, {}];
        });
        
        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/itinerarios/current"
         * Response: 200 -> Status ok, current itinerario y no headers.
         */
        $httpBackend.whenGET(context+'/current').respond(function (method, url) {
            return [200, current, {}];
        });
    
            
        //POST
            
        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/itinierario"
         * Obtiene el record de libro desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al record de itinerario y lo guarda en el
         * array de records.
         * Response: 201 -> Status created, record -> libro y ningún header.
         */
        $httpBackend.whenPOST(context).respond(function (method, url, data) {
            var record = ng.fromJson(data);
            record.id = Math.floor(Math.random() * 10000);
            records.push(record);
            return [201, record, {}];
        });

        
        //DELETE

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/itinerarios/[numero]"
         * Obtiene el id del la url y el registro asociado dentro del array records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenDELETE(urlItinerario).respond(function (method, url) {
            var id = parseInt(url.split('/').pop());
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1);
                }
            });
            return [204, records, {}];
        });


        //PUT

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/itinerarios/[numero]"
         * Obtiene el id del la url y el record de itinerario desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(urlItinerario).respond(function (method, url, data) {

            var id = parseInt(url.split('/').pop());
            var record = ng.fromJson(data);
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1, record);
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/itinerarios/current"
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(context+'/current').respond(function (method, url, data) {
            current = ng.fromJson(data);
            return [204, null, {}];
        });

    }]);
})(window.angular);


