/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('ciudadMock', ['ngMockE2E']);


    mod.run(['$httpBackend', function ($httpBackend) {
        var ignore_regexp = new RegExp('^((?!api).)*$');
        /*
         * @type RegExp
         * recordUrl acepta cualquier url con el formato
         * api/(cualquierpalabra)/(numero)
         * ej: api/authors/1
         */
        var recordUrl = new RegExp('api/ciudad/([0-9]+)');

        /*
         * @type Array
         * records: Array con un Author por defecto
         */
        var record = [{
                
                id: 1,
                nombre: 'Bogotá',
                detalles:'Bogotá es la capital de la República de Colombia y del departamento de Cundinamarca.\n\
                           Es la tercera capital más alta en América del Sur (después de La Paz y Quito), \n\
                            a un promedio de 2625 metros sobre el nivel del mar.\n\
                            \n\
                            Temperatura promedio= 14°C\n\
                            Altitud= 2600 metros\n\
                            Población= 7.980.001 habitantes',
                imagen: 'http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg',
                sitios:[
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
                       imagen:'https://upload.wikimedia.org/wikipedia/commons/d/dc/BOG_Museo_del_Oro.JPG'
                    },
                    {
                        id:2,
                        nombre:'Parque de la 93',
                        detalles:'El parque de la 93 es un parque turístico en el norte de Bogotá.\n\
                                    Se encuentra ubicado en la localidad de Chapinero, entre las calles 93 A y 93 B y entre las carreras 11 A y 13, \n\
                                    en el sector de El Chicó. Fue inaugurado el 14 de junio de 1995.\n\
                                    En el parque se encuentra la primera tienda de Starbucks y Carls Jr. en toda Colombia.',
                        imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0d/Bogot%C3%A1barrio_El_Chic%C3%B3_Parque_de_la_93.JPG'
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
                        imagen:'http://bogotabureau.com/herramientas/wp-content/uploads/2014/02/La-Candelaria.jpg'
                    }
                ],
                eventos:[
                    {
                        id:1,
                         nombre:'Carrera Zombie Survivor Race 5K ',
                         detalles:'El próximo 28 de febrero, zombis y corredores serán protagonistas de una competencia apocalíptica de 5 kilómetros y con más de 10 obstáculos (agua, barro, tierra, entre otros).\n\
                                   Durante la carrera Zombie Survivor Race 5K, los participantes tendrán que sobrevivir a hordas de zombies que vendrán con el único fin de convertirlos en uno más de ellos.\n\
                                    \n\
                                    \n\
                                    Hay tres manera de participar:\n\
                                    \n\
                                    1. Como corredor. Usted llevará un cinturón y banderines y sus deber es escapar de los zombis, mantener los banderines completos, superar obstáculos y llegar a la meta.\n\
                                    2. Como zombi. Debe disfrazarse, asustar a los corredores y robarles los banderines. Cuando se participa como zombi puede moverse libremente por la pista\n\
                                    3. Zombi y corredor. Es posible que usted primero un haga un papel y luego el otro.\n\
                                    \n\
                                    \n\
                                    ¿Qué debo saber?\n\
                                     \n\
                                    El precio de la inscripción está entre $85.000 y $140.000, el valor depende del rol que elija. Se incluye seguro médico\n\
                                     \n\
                                    La inscripción incluye:\n\
                                    \n\
                                    -Cinturón con banderines de vida (entregado el día de la carrera).\n\
                                    \n\
                                    -Camiseta oficial Zombie Survivor Race 5k.\n\
                                    \n\
                                    -Número de sobreviviente.\n\
                                    \n\
                                    -Chip de medición de tiempo.\n\
                                    \n\
                                    -Puntos de hidratación Refrigerio al finalizar (seas Zombie o Survivor).\n\
                                    \n\
                                    -Rifas y sorteos.\n\
                                    \n\
                                    -Atención médica disponible durante todo el evento.\n\
                                    \n\
                                   - Baños VIP.\n\
                                    \n\
                                    -Guardarropas, ambulancia, escuadrones anti zombies en zonas seguras.\n\
                                    \n\
                                    -Vestidores Agua para breve higiene después de la travesía apocalítica.\n\
                                    \n\
                                    -Cupón de descuento del 20% para el merchandising oficial de la Zombie-Survivor Race 5K.\n\
                                    \n\
                                    Se reciben corredores desde los 13 años de edad. Pero para los menores de 18, los papás deben llenar el formulario de “Exoneración de Responsabilidades’’ con firma,\n\
    |                               huella y anexa la copia de la cédula.\n\
                                    \n\
                                    Habrá premios para:\n\
                                    \n\
                                    -Participante más rápido de cada categoría y cada sexo.\n\
                                    \n\
                                    -Zombie con más banderines tomados de los participantes.\n\
                                    \n\
                                    -Mejor disfraz zombie.\n\
                                    \n\
                                   - Mejor disfraz sobreviviente.\n\
                                    \n\
                                   - Mejor disfraz grupo.',
                        imagen:'http://res.cloudinary.com/civico/image/upload/c_fill,f_auto,fl_lossy,h_368,w_1122/v1455293431/entity/event_image/file/bc2/001/56be03f72f41f3c2be001bc2.jpg'
                    },
                    {
                        id:2,
                        nombre:'Bogotá Pub Craw',
                        descripción:'Acerca del tour\n\
                                     Este jueves 18 de febrero, bogotanos y extranjeros podrán disfrutar del Bogota Pub Crawl, evento que promete hacer un tour por los mejores bares de la ciudad.\n\
                                     Todo el recorrido se hace en compañía de un guía que habla español e inglés. A lo largo de la jornada habrá un fotógrafo que estará acompañando a los participantes\n\
                                     del tour y capturará los mejores momentos.\n\
                                     \n\
                                     Qué debo saber\n\
                                        \n\
                                     La fiesta inicia en el Bier Market (carrera 13 No. 83-75 - zona T) a las 8:30 p.m. Allí, todos los participantes recibirán una manilla después de cancelar el precio \n\
                                     del tour que es de $60.000. En esta primera parada podrán tomar cerveza o vino y comer pizza sin límite.\n\
                                     Una hora y media después, el grupo será llevado a un bar donde recibirá un shot de trago gratis y descuentos en la carta del lugar. Habrán precios especiales en cervezas y cocteles.\n\
                                     Tiempo después el grupo irá a un segundo bar.\n\
                                     La última y cuarta parada será en la discoteca 4:40 de la Zonta T. Allí no pagaran cover y podrán disfrutar de una buena fiesta.\n\
                                     En este lugar el trago corre por cuenta de cada participante.\n\
                                    \n\
                                    Información del evento\n\
                                    \n\
                                    Fecha: 18 de febrero\n\
                                    \n\
                                    Lugar de inicio: Bier Market \n\
                                    \n\
                                    Hora: 8:30 p.m.\n\
                                    \n\
                                    Precio del recorrido: $60.000 o 20 dólares',
                        imagen:'https://res.cloudinary.com/civico/image/upload/c_fill,f_auto,fl_lossy,h_368,w_1122/v1455220863/entity/event_image/file/940/000/56bcc4cb2f41f3c2be000940.jpg'
                    }
                    
                ]
            },
            {
                
                id: 2,
                nombre: 'Cali',
                detalles:'Cali, oficialmente Santiago de Cali, es un municipio colombiano, capital del departamento del Valle del Cauca, es la tercera ciudad más poblada de Colombia. \n\
                          Está situada en la región Sur del Valle del Cauca, entre la cordillera occidental y la cordillera central de los Andes. La ciudad forma parte del Área Metropolitana de Cali, \n\
                          junto con los municipios aledaños a ésta. Fue fundada el 25 de julio de 1536 por Sebastián de Belalcázar, lo que la convierte en una de las ciudades más antiguas de América.\n\
                            \n\
                          Es a su vez conocida como «la capital mundial de la salsa».\n\
                          Temperatura promedio= 23°C \n\
                          Altitud= 1018 metros\n\
                          Población= 2.394.870 habitantes ',
                imagen: 'http://static.panoramio.com/photos/large/43907931.jpg',
                sitios:[
                    {
                        id:1,
                        nombre:'El gato del río',
                        detalles:'El Gato del Río es una obra del pintor y escultor Hernando Tejada la cual donó a la ciudad de Cali y que fue instalada en la ribera del río tutelar de la ciudad,\n\
                                  en el sector noroeste de la ciudad, conocido como Normandía. Con el paso del tiempo se ha convertido en uno de los monumentos más emblemáticos de la ciudad,\n\
                                  junto con la estatua de Sebastián de Belalcázar y el Cerro de Cristo Rey.\n\
                                    \n\
                                  Diez años después de inaugurado el monumento, en el mes de Octubre de 2006 la Cámara de Comercio de Cali lideró una iniciativa de recuperación no solo del monumento, \n\
                                  sino también de sus alrededores. Para ello promovió la exhibición de una colección de quince esculturas complementarias, todas con la misma base estructural pero pintadas y \n\
                                  decoradas por artistas plásticos. La iniciativa llamada "las novias del gato" promovía además la adecuación de un parque alrededor del monumento original. \n\
                                  En el diseño de las gatas participaron reconocidos artistas colombianos como Maripaz Jaramillo y Omar Rayo',
                       imagen:'https://upload.wikimedia.org/wikipedia/commons/b/b2/Gato_de_Tejada_Cali.JPG'
                    },
                    {
                        id:2,
                        nombre:'Teatro Municipal Enrique Buenaventura',
                        detalles:'Anteriormente llamado Teatro Municipal de Cali, es un teatro declarado monumento nacional en 1982 que se encuentra en el centro de la ciudad de Santiago de Cali.\n\
                                  El teatro cuenta con un estilo clásico italiano y se encuentra adornado por múltiples obras de artistas nacionales y extranjeros.\n\
                                  La Sala Principal del teatro tiene una capacidad de 1039 espectadores que se dividen en diferentes localidades, cada uno con un nombre particular, \n\
                                  siendo la Luneta la primera y está situada a un nivel más bajo que el escenario. Le siguen el Primer y Segundo palco respectivamente divididos en cubículos numerados y \n\
                                  en los dos últimos niveles el anfiteatro y la galería.',
                        imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0b/CONTRASTES.JPG'
                    },
                    {
                        id:3,
                        nombre:'Iglesia de la Ermita',
                        detalles:'La iglesia la Ermita está ubicada en Santiago de Cali, Colombia. Originalmente fue una construcción pajiza de comienzos del siglo XVII, establecida en las cercanías del río Cali y\n\
                                  dedicada a Nuestra Señora de la Soledad y al Señor de la Caña.\n\
                                  En 1942, se construyó la iglesia que se ve hoy en día, la cual es uno de los referentes del paisaje arquitectónico de la Ciudad de Cali. La actual construcción es una iglesia gótica en miniatura,\n\
                                  y como muchas iglesias góticas en América está inspirada en la Catedral de Ulm, Alemania.\n\
                                  La nueva ermita está dedicada a Nuestra Señora de los Dolores y en su interior conserva la antigua imagen del Señor de la Caña en el altar lateral izquierdo.',
                        imagen:'https://upload.wikimedia.org/wikipedia/commons/2/2f/Ermita_cali.jpg'
                    }
                ],
                eventos:[
                    {
                        id:1,
                         nombre:'',
                         detalles:'',
                        imagen:''
                    }
                    
                ]
            },
            {
                
                id: 3,
                nombre: 'Bucaramanga',
                detalles:'Es una ciudad colombiana, capital del departamento de Santander. Está ubicada al nororiente del país sobre la cordillera Oriental, \n\
                          rama de la cordillera de los Andes, a orillas del río de Oro. Bucaramanga cuenta con 531.813 habitantes y junto con Floridablanca, \n\
                          Girón y Piedecuesta conforman el área metropolitana con un total de 1.126.945 habitantes\n\
                            \n\
                          Temperatura promedio= 23°C\n\
                          Altitud= 959 metros\n\
                          Poblacion= 528.352 habitantes ',
                imagen: 'https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg',
                sitios:[
                    {
                        id:1,
                        nombre:'Cañón del Chicamocha',
                        detalles:'El cañon del Chicamocha es un acidente geográfico en colombia ubicado en las riveras del río Chicamocha,\n\
                                  durante su recorrido por los departamentos de Boyacá y principalmente de Santander, donde alcanza su máxima \n\n\
                                  profundidad en inmediaciones de la ciudad de Bucaramanga, entre los municipios de Aratoca, Cepitá, Los Santos y Jordán.',
                       imagen:'http://santanderalextremo.com/wp-content/uploads/2013/02/panachi_003.jpg'
                    },
                    {
                        id:2,
                        nombre:'Mirador de Palonegro',
                        destalles:' ubicado al oriente de la ciudad, en la vía que conduce a la ciudad de Cúcuta. Está construido en el sector un parque en forma de colina,\n\
\n\                                 donde se puede apreciar desde su cima una panorámica imponente de la ciudad; hay senderos en concreto y en tierra que conducen a la cima donde \n\
\n\                                 hay una fuente con una estatua del Sagrado Corazón de Jesús, que es visitada en época de Semana Santa por los habitantes de la ciudad, sobre todo \n\
\n\                                 para realizar el Viacrucis en Viernes Santo; además los habitantes y feligreses del sector tienen la creencia que la estatua hace milagros.\n\
\n\                                 Cuenta además el parque con un escenario en forma de media torta o media luna para hacer teatro al aire libre u otras actividades culturales, sociales o religiosas.',
                        imagen:'http://www.riojapacific.com/wp/TravelDemoSources/TravelDemoSources/Website/wp-content/plugins/thethe-image-slider/timthumb.php?w=960&h=398&zc=1&src=http%3A%2F%2Fwww.riojapacific.com%2Fwp%2FTravelDemoSources%2FTravelDemoSources%2FWebsite%2Fwp-content%2Fuploads%2F2015%2F04%2FBUCA RAMANGA-SLIDER1.jpg'
                    }
                ],
                eventos:[
                    {
                        id:1,
                         nombre:'Concierto de Manu Chao en Bucaramanga',
                         detalles:'Pilas pues para una celebración llena de reggae, folk, rock y punk: ¡Todo está listo para que Manu Chao haga temblar a toda Bucaramanga!\n\
                                   Como ya se confirmó por parte de T310 y The Proyex Project, empresas organizadoras del evento, este emblemático artista se presentará en la Ciudad Bonita el próximo 12 de marzo.\n\
                                   Así que prepárate para entonar himnos como “Clandestino”, “Desaparecido”, “Mr. Bobby”, “Bienvenido a Tijuana” y “Mala Vida”, entre muchos más.\n\
                                   \n\
                                  El concierto se realizará en en el Club El Cortijo ubicado sobre el anillo vial y tendrá una capacidad para 5,000 personas. Sus boletas,\n\
                                  que se pueden adquirir en Tu Boleta tendrán un valor de $50.000 para las primeras 500 personas y $65.000 para el resto de boletería.\n\
                                  Seguramente muchos crecimos con su música y sus letras y ritmos aún forman parte de nuestras vidas.\n\
                                  Prepárate para vivir la magia de su música desde sus inicios con Mano Negra hasta la actualidad.',
                        imagen:'http://www.infoeventos.com.co/wp-content/uploads/2016/02/manu-chao-feature.jpg'
                    },
                    {
                        id:2,
                        nombre:'Interpark - Ciudad de la diversión',
                        detalles:'Disfruta de la ciudad de la diversión y la adrenalina más grande, moderna y segura jamas vista en Bucaramanga. Una ciudad de hierro que no quieres dejar de visitar esta navidad.\n\
                                  Te dejamos los precios de las entradas y los Pasaportes para un día de adrenalina.\n\
                                  \n\
                                  Localidad             	Precio\n\
\n\                               \n\
                                  ENTRADA                       $ 5.000\n\
                                  PASAPORTE Lunes a Jueves 	$ 20.000\n\
                                  PASAPORTE fines de semana 	$ 25.000',
                        imagen:'http://www.infoeventos.com.co/wp-content/uploads/2015/11/Banner-interparck-ciudad-de-hierro-bucaramanga.jpg'
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
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/authors"
         * Obtiene los parámetros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de libros y los headers.
         */
        $httpBackend.whenGET('api/ciudad').respond(function (method, url) {
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
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/authors/[numero]"
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
        $httpBackend.whenPOST('api/ciudades').respond(function (method, url, data) {
            var record = ng.fromJson(data);
            record.id = Math.floor(Math.random() * 10000);
            records.push(record);
            return [201, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/authors/[numero]"
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
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/authors/[numero]"
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


