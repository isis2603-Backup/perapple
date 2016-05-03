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
         * recordUrlCiudad acepta cualquier url con el formato:
         * api/ciudades/(numero)
         * ej: api/ciudades/1
         */
        var recordUrlCiudad = new RegExp('api/ciudades/([0-9]+)');

        /*
         * @type RegExp
         * recordUrlSitios acepta cualquier url con el formato:
         * api/ciudades/(numero)/sitios
         * ej: api/ciudades/1/sitios
         */
        var recordUrlSitios = new RegExp('api/ciudades/([0-9]+)/sitios');

        /*
         * @type RegExp
         * recordUrlSitio acepta cualquier url con el formato:
         * api/ciudades/(numero)/sitios/(numero)
         * ej: api/ciudades/1/sitios/1
         */
        var recordUrlSitio = new RegExp('api/ciudades/([0-9]+)/sitios/([0-9]+)');

        /*
         * @type RegExp
         * recordUrlEventos acepta cualquier url con el formato:
         * api/ciudades/(numero)/eventos
         * ej: api/ciudades/1/eventos
         */
        var recordUrlEventos = new RegExp('api/ciudades/([0-9]+)/eventos');

        /*
         * @type RegExp
         * recordUrlEvento acepta cualquier url con el formato:
         * api/ciudades/(numero)/eventos/(numero)
         * ej: api/ciudades/1/eventos/1
         */
        var recordUrlEvento = new RegExp('api/ciudades/([0-9]+)/eventos/([0-9]+)');

        /*
         * @type RegExp
         * recordUrlHoteles acepta cualquier url con el formato:
         * api/ciudades/(numero)/hoteles
         * ej: api/ciudades/1/hoteles
         */
        var recordUrlHoteles = new RegExp('api/ciudades/([0-9]+)/hoteles');

        /*
         * @type RegExp
         * recordUrlHotel acepta cualquier url con el formato:
         * api/ciudades/(numero)/hoteles/(numero)
         * ej: api/ciudades/1/hoteles/1
         */
        var recordUrlHotel = new RegExp('api/ciudades/([0-9]+)/hoteles/([0-9]+)');

        /*
         * @type Array
         * records: Array con 3 ciudades por defecto
         */
        var records = [
        {
            id: 1,
            nombre: 'BogotÃ¡',
            detalles:'BogotÃ¡ es la capital de la RepÃºblica de Colombia y del departamento de Cundinamarca.\n\
                    Es la tercera capital mÃ¡s alta en AmÃ©rica del Sur (despuÃ©s de La Paz y Quito), \n\
                    a un promedio de 2625 metros sobre el nivel del mar.\n\
                    \n\
                    Temperatura promedio= 14Â°C\n\
                    Altitud= 2600 metros\n\
                    PoblaciÃ³n= 7.980.001 habitantes',
            imagen: 'http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg',
            sitios:[
                {
                    id:1,
                    nombre:'Museo del oro',
                    detalles:'El Museo del Oro del Banco de la RepÃºblica de Colombia es una instituciÃ³n abierta al pÃºblico cuya finalidad es la adquisiciÃ³n, \n\
                            conservaciÃ³n y exposiciÃ³n de piezas de orfebrerÃ­a y alfarerÃ­a de culturas indÃ­genas del periodo precolombino de la actual Colombia. \n\
                            EstÃ¡ ubicado en el costado oriental del parque Santander, en el centro histÃ³rico de BogotÃ¡.\n\
                            \n\
                            Posee la colecciÃ³n de orfebrerÃ­a prehispÃ¡nica mÃ¡s grande del mundo con aproximadamente treinta y cuatro mil piezas de oro y tumbaga,\n\
                            cerca de veinticinco mil objetos en cerÃ¡mica, piedra, concha, hueso y textiles. \n\
                            Expone piezas de diferentes culturas indÃ­genas asentadas en la actual Colombia antes de la llegada de los europeos, \n\
                            entre las que destacan la Calima, los muiscas, la NariÃ±o, la quimbaya, la sinÃº, la tairona, la San AgustÃ­n, la Tierradentro, la Tolima, \n\
                            entre otras cosas.',
                    imagen:'https://upload.wikimedia.org/wikipedia/commons/d/dc/BOG_Museo_del_Oro.JPG'
                },
                {
                    id:2,
                    nombre:'Parque de la 93',
                    detalles:'El parque de la 93 es un parque turÃ­stico en el norte de BogotÃ¡.\n\
                            Se encuentra ubicado en la localidad de Chapinero, entre las calles 93 A y 93 B y entre las carreras 11 A y 13, \n\
                            en el sector de El ChicÃ³. Fue inaugurado el 14 de junio de 1995.\n\
                            En el parque se encuentra la primera tienda de Starbucks y Carls Jr. en toda Colombia.',
                    imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0d/Bogot%C3%A1barrio_El_Chic%C3%B3_Parque_de_la_93.JPG'
                },
                {
                    id:3,
                    nombre:'La Candelaria',
                    detalles:'La Candelaria es tradicionalmente la localidad mÃ¡s reconocida de la ciudad pues de ella hace parte todo el centro histÃ³rico de BogotÃ¡, \n\
                            desde el sitio original de la fundaciÃ³n de la ciudad (segÃºn los historiadores la fecha es el 6 de agosto de 1538) hasta el establecimiento de la primera iglesia y la Plaza Mayor,\n\
                            plaza que actualmente se conoce como la Plaza de BolÃ­var y en la que estÃ¡ ubicada, en su costado oriental, la Catedral Primada de Colombia,\n\
                            nombre que adquiriÃ³ aquella primera iglesia construida en plena fundaciÃ³n de la ciudad.\n\
                            \n\
                            La Candelaria no sÃ³lo es el centro histÃ³rico de BogotÃ¡, con los aÃ±os se ha convertido ademÃ¡s en un importante centro comercial y de negocios para la ciudad pues ofrece mercados y \n\
                            oportunidades para todos los habitantes de la ciudad sin importar el origen de su condiciÃ³n social.',
                    imagen:'http://bogotabureau.com/herramientas/wp-content/uploads/2014/02/La-Candelaria.jpg'
                }
            ],
            eventos:[
                {
                    id:1,
                    nombre:'Carrera Zombie Survivor Race 5K ',
                    detalles:'El prÃ³ximo 28 de febrero, zombis y corredores serÃ¡n protagonistas de una competencia apocalÃ­ptica de 5 kilÃ³metros y con mÃ¡s de 10 obstÃ¡culos (agua, barro, tierra, entre otros).\n\
                            Durante la carrera Zombie Survivor Race 5K, los participantes tendrÃ¡n que sobrevivir a hordas de zombies que vendrÃ¡n con el Ãºnico fin de convertirlos en uno mÃ¡s de ellos.\n\
                             \n\
                             \n\
                             Hay tres manera de participar:\n\
                             \n\
                             1. Como corredor. Usted llevarÃ¡ un cinturÃ³n y banderines y sus deber es escapar de los zombis, mantener los banderines completos, superar obstÃ¡culos y llegar a la meta.\n\
                             2. Como zombi. Debe disfrazarse, asustar a los corredores y robarles los banderines. Cuando se participa como zombi puede moverse libremente por la pista\n\
                             3. Zombi y corredor. Es posible que usted primero un haga un papel y luego el otro.\n\
                             \n\
                             \n\
                            Â¿QuÃ© debo saber?\n\
                             \n\
                            El precio de la inscripciÃ³n estÃ¡ entre $85.000 y $140.000, el valor depende del rol que elija. Se incluye seguro mÃ©dico\n\
                             \n\
                            La inscripciÃ³n incluye:\n\
                            \n\
                            -CinturÃ³n con banderines de vida (entregado el dÃ­a de la carrera).\n\
                            \n\
                            -Camiseta oficial Zombie Survivor Race 5k.\n\
                            \n\
                            -NÃºmero de sobreviviente.\n\
                            \n\
                            -Chip de mediciÃ³n de tiempo.\n\
                            \n\
                            -Puntos de hidrataciÃ³n Refrigerio al finalizar (seas Zombie o Survivor).\n\
                            \n\
                            -Rifas y sorteos.\n\
                            \n\
                            -AtenciÃ³n mÃ©dica disponible durante todo el evento.\n\
                            \n\
                           - BaÃ±os VIP.\n\
                            \n\
                            -Guardarropas, ambulancia, escuadrones anti zombies en zonas seguras.\n\
                            \n\
                            -Vestidores Agua para breve higiene despuÃ©s de la travesÃ­a apocalÃ­tica.\n\
                            \n\
                            -CupÃ³n de descuento del 20% para el merchandising oficial de la Zombie-Survivor Race 5K.\n\
                            \n\
                            Se reciben corredores desde los 13 aÃ±os de edad. Pero para los menores de 18, los papÃ¡s deben llenar el formulario de â€œExoneraciÃ³n de Responsabilidadesâ€™â€™ con firma,\n\
|                               huella y anexa la copia de la cÃ©dula.\n\
                            \n\
                            HabrÃ¡ premios para:\n\
                            \n\
                            -Participante mÃ¡s rÃ¡pido de cada categorÃ­a y cada sexo.\n\
                            \n\
                            -Zombie con mÃ¡s banderines tomados de los participantes.\n\
                            \n\
                            -Mejor disfraz zombie.\n\
                            \n\
                            - Mejor disfraz sobreviviente.\n\
                            \n\
                            - Mejor disfraz grupo.'
                },
                {
                    id:2,
                    nombre:'BogotÃ¡ Pub Craw',
                    descripcion:'Acerca del tour\n\
                                Este jueves 18 de febrero, bogotanos y extranjeros podrÃ¡n disfrutar del Bogota Pub Crawl, evento que promete hacer un tour por los mejores bares de la ciudad.\n\
                                Todo el recorrido se hace en compaÃ±Ã­a de un guÃ­a que habla espaÃ±ol e inglÃ©s. A lo largo de la jornada habrÃ¡ un fotÃ³grafo que estarÃ¡ acompaÃ±ando a los participantes\n\
                                del tour y capturarÃ¡ los mejores momentos.\n\
                                \n\
                                QuÃ© debo saber\n\
                                \n\
                                La fiesta inicia en el Bier Market (carrera 13 No. 83-75 - zona T) a las 8:30 p.m. AllÃ­, todos los participantes recibirÃ¡n una manilla despuÃ©s de cancelar el precio \n\
                                del tour que es de $60.000. En esta primera parada podrÃ¡n tomar cerveza o vino y comer pizza sin lÃ­mite.\n\
                                Una hora y media despuÃ©s, el grupo serÃ¡ llevado a un bar donde recibirÃ¡ un shot de trago gratis y descuentos en la carta del lugar. HabrÃ¡n precios especiales en cervezas y cocteles.\n\
                                Tiempo despuÃ©s el grupo irÃ¡ a un segundo bar.\n\
                                La Ãºltima y cuarta parada serÃ¡ en la discoteca 4:40 de la Zonta T. AllÃ­ no pagaran cover y podrÃ¡n disfrutar de una buena fiesta.\n\
                                En este lugar el trago corre por cuenta de cada participante.\n\
                                \n\
                                InformaciÃ³n del evento\n\
                                \n\
                                Fecha: 18 de febrero\n\
                                \n\
                                Lugar de inicio: Bier Market \n\
                                \n\
                                Hora: 8:30 p.m.\n\
                                \n\
                                Precio del recorrido: $60.000 o 20 dÃ³lares'
                }
            ],
            hoteles:[
                {
                    id:1,
                    nombre:'Hotel Dann Avenida 19',
                    direccion:'Cl. 19 # 5 - 72'
                },
                {
                    id:2,
                    nombre:'Hotel Augusta',
                    direccion:'Avenida JimÃ©nez # 4 - 7'
                },
                {
                    id:3,
                    nombre:'Hotel Casa Galeria',
                    direccion:'Cra. 2 # 12 - 92'
                }
            ]
        },
        {
            id: 2,
            nombre: 'Cali',
            detalles:'Cali, oficialmente Santiago de Cali, es un municipio colombiano, capital del departamento del Valle del Cauca, es la tercera ciudad mÃ¡s poblada de Colombia. \n\
                    EstÃ¡ situada en la regiÃ³n Sur del Valle del Cauca, entre la cordillera occidental y la cordillera central de los Andes. La ciudad forma parte del Ã�rea Metropolitana de Cali, \n\
                    junto con los municipios aledaÃ±os a Ã©sta. Fue fundada el 25 de julio de 1536 por SebastiÃ¡n de BelalcÃ¡zar, lo que la convierte en una de las ciudades mÃ¡s antiguas de AmÃ©rica.\n\
                      \n\
                    Es a su vez conocida como Â«la capital mundial de la salsaÂ».\n\
                    Temperatura promedio= 23Â°C \n\
                    Altitud= 1018 metros\n\
                    PoblaciÃ³n= 2.394.870 habitantes ',
            imagen: 'http://static.panoramio.com/photos/large/43907931.jpg',
            sitios:[
                {
                    id:1,
                    nombre:'Zoologico de Cali',
                    detalles:'Es innegable que la regiÃ³n ha redescubierto al ZoolÃ³gico de Cali como epicentro de conservaciÃ³n,\n\
                            que goza de credibilidad en virtud de su transparencia, honestidad y profesionalismo. \n\
                            Por mÃ¡s de una dÃ©cada ha ejercido un liderazgo en la comunidad zoolÃ³gica nacional e internacional,\n\
                            promoviendo y acompaÃ±ando el desarrollo de otras instituciones de su misma naturaleza, \n\
                            y participando activamente en la consolidaciÃ³n de una comunidad mÃ¡s comprometida con la conservaciÃ³n de la biodiversidad. \n\
                            El ZoolÃ³gico de Cali ha crecido bajo una forma de organizaciÃ³n con objetivos claros y compartidos, \n\
                            sustentada en principios y valores en el marco de un pensamiento estratÃ©gico que propone relatos innovadores en \n\
                            una instituciÃ³n que contribuye a crear escenarios de bienestar para las comunidades humanas y la vida silvestre. \n\
                            El ZoolÃ³gico de Cali es una plataforma que promueve la construcciÃ³n del compromiso ambiental.',
                   imagen:'http://www.icesi.edu.co/blogs_estudiantes/zooincali/files/2012/09/mapa_final-1024x6401.jpg'
                },
                {
                    id:2,
                    nombre:'Teatro Municipal Enrique Buenaventura',
                    detalles:'Anteriormente llamado Teatro Municipal de Cali, es un teatro declarado monumento nacional en 1982 que se encuentra en el centro de la ciudad de Santiago de Cali.\n\
                            El teatro cuenta con un estilo clÃ¡sico italiano y se encuentra adornado por mÃºltiples obras de artistas nacionales y extranjeros.\n\
                            La Sala Principal del teatro tiene una capacidad de 1039 espectadores que se dividen en diferentes localidades, cada uno con un nombre particular, \n\
                            siendo la Luneta la primera y estÃ¡ situada a un nivel mÃ¡s bajo que el escenario. Le siguen el Primer y Segundo palco respectivamente divididos en cubÃ­culos numerados y \n\
                            en los dos Ãºltimos niveles el anfiteatro y la galerÃ­a.',
                    imagen:'https://upload.wikimedia.org/wikipedia/commons/0/0b/CONTRASTES.JPG'
                },
                {
                    id:3,
                    nombre:'Iglesia de la Ermita',
                    detalles:'La iglesia la Ermita estÃ¡ ubicada en Santiago de Cali, Colombia. Originalmente fue una construcciÃ³n pajiza de comienzos del siglo XVII, establecida en las cercanÃ­as del rÃ­o Cali y\n\
                            dedicada a Nuestra SeÃ±ora de la Soledad y al SeÃ±or de la CaÃ±a.\n\
                            En 1942, se construyÃ³ la iglesia que se ve hoy en dÃ­a, la cual es uno de los referentes del paisaje arquitectÃ³nico de la Ciudad de Cali. La actual construcciÃ³n es una iglesia gÃ³tica en miniatura,\n\
                            y como muchas iglesias gÃ³ticas en AmÃ©rica estÃ¡ inspirada en la Catedral de Ulm, Alemania.\n\
                            La nueva ermita estÃ¡ dedicada a Nuestra SeÃ±ora de los Dolores y en su interior conserva la antigua imagen del SeÃ±or de la CaÃ±a en el altar lateral izquierdo.',
                    imagen:'https://upload.wikimedia.org/wikipedia/commons/2/2f/Ermita_cali.jpg'
                }
            ],
            eventos:[
                {
                    id:1,
                    nombre:'No hay eventos disponibles',
                    detalles:'En estos momentos Cali no cuenta con presentaciÃ³n de algÃºn evento'
                }

            ],
            hoteles:[
                {
                    id:1,
                    nombre:'Hotel Dann Avenida 19',
                    direccion:'Cl. 19 # 5 - 72'
                },
                {
                    id:2,
                    nombre:'Hotel Augusta',
                    direccion:'Avenida JimÃ©nez # 4 - 7'
                },
                {
                    id:3,
                    nombre:'Hotel Casa Galeria',
                    direccion:'Cra. 2 # 12 - 92'
                }
            ]
        },
        {
            id: 3,
            nombre: 'Bucaramanga',
            detalles:'Es una ciudad colombiana, capital del departamento de Santander. EstÃ¡ ubicada al nororiente del paÃ­s sobre la cordillera Oriental, \n\
                    rama de la cordillera de los Andes, a orillas del rÃ­o de Oro. Bucaramanga cuenta con 531.813 habitantes y junto con Floridablanca, \n\
                    GirÃ³n y Piedecuesta conforman el Ã¡rea metropolitana con un total de 1.126.945 habitantes\n\
                      \n\
                    Temperatura promedio= 23Â°C\n\
                    Altitud= 959 metros\n\
                    Poblacion= 528.352 habitantes ',
            imagen: 'https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg',
            sitios:[
                {
                    id:1,
                    nombre:'CaÃ±Ã³n del Chicamocha',
                    detalles:'El caÃ±on del Chicamocha es un acidente geogrÃ¡fico en colombia ubicado en las riveras del rÃ­o Chicamocha,\n\
                            durante su recorrido por los departamentos de BoyacÃ¡ y principalmente de Santander, donde alcanza su mÃ¡xima \n\n\
                            profundidad en inmediaciones de la ciudad de Bucaramanga, entre los municipios de Aratoca, CepitÃ¡, Los Santos y JordÃ¡n.',
                   imagen:'http://santanderalextremo.com/wp-content/uploads/2013/02/panachi_003.jpg'
                },
                {
                    id:2,
                    nombre:'Mirador de Palonegro',
                    destalles:' ubicado al oriente de la ciudad, en la vÃ­a que conduce a la ciudad de CÃºcuta. EstÃ¡ construido en el sector un parque en forma de colina,\n\
                            \n\ donde se puede apreciar desde su cima una panorÃ¡mica imponente de la ciudad; hay senderos en concreto y en tierra que conducen a la cima donde \n\
                            \n\ hay una fuente con una estatua del Sagrado CorazÃ³n de JesÃºs, que es visitada en Ã©poca de Semana Santa por los habitantes de la ciudad, sobre todo \n\
                            \n\ para realizar el Viacrucis en Viernes Santo; ademÃ¡s los habitantes y feligreses del sector tienen la creencia que la estatua hace milagros.\n\
                            \n\ Cuenta ademÃ¡s el parque con un escenario en forma de media torta o media luna para hacer teatro al aire libre u otras actividades culturales, sociales o religiosas.',
                    imagen:'http://www.riojapacific.com/wp/TravelDemoSources/TravelDemoSources/Website/wp-content/plugins/thethe-image-slider/timthumb.php?w=960&h=398&zc=1&src=http%3A%2F%2Fwww.riojapacific.com%2Fwp%2FTravelDemoSources%2FTravelDemoSources%2FWebsite%2Fwp-content%2Fuploads%2F2015%2F04%2FBUCA RAMANGA-SLIDER1.jpg'
                }
            ],
            eventos:[
                {
                    id:1,
                    nombre:'Concierto de Manu Chao en Bucaramanga',
                    detalles:'Pilas pues para una celebraciÃ³n llena de reggae, folk, rock y punk: Â¡Todo estÃ¡ listo para que Manu Chao haga temblar a toda Bucaramanga!\n\
                            Como ya se confirmÃ³ por parte de T310 y The Proyex Project, empresas organizadoras del evento, este emblemÃ¡tico artista se presentarÃ¡ en la Ciudad Bonita el prÃ³ximo 12 de marzo.\n\
                            AsÃ­ que prepÃ¡rate para entonar himnos como â€œClandestinoâ€�, â€œDesaparecidoâ€�, â€œMr. Bobbyâ€�, â€œBienvenido a Tijuanaâ€� y â€œMala Vidaâ€�, entre muchos mÃ¡s.\n\
                            \n\
                            El concierto se realizarÃ¡ en en el Club El Cortijo ubicado sobre el anillo vial y tendrÃ¡ una capacidad para 5,000 personas. Sus boletas,\n\
                            que se pueden adquirir en Tu Boleta tendrÃ¡n un valor de $50.000 para las primeras 500 personas y $65.000 para el resto de boleterÃ­a.\n\
                            Seguramente muchos crecimos con su mÃºsica y sus letras y ritmos aÃºn forman parte de nuestras vidas.\n\
                            PrepÃ¡rate para vivir la magia de su mÃºsica desde sus inicios con Mano Negra hasta la actualidad.'
                },
                {
                    id:2,
                    nombre:'Interpark - Ciudad de la diversiÃ³n',
                    detalles:'Disfruta de la ciudad de la diversiÃ³n y la adrenalina mÃ¡s grande, moderna y segura jamas vista en Bucaramanga. Una ciudad de hierro que no quieres dejar de visitar esta navidad.\n\
                            Te dejamos los precios de las entradas y los Pasaportes para un dÃ­a de adrenalina.\n\
                            \n\
                            Localidad             	Precio\n\
                            \n\ \n\
                            ENTRADA                       $ 5.000\n\
                            PASAPORTE Lunes a Jueves 	$ 20.000\n\
                            PASAPORTE fines de semana 	$ 25.000'
                }
            ],
            hoteles:[
                {
                    id:1,
                    nombre:'Hotel Dann Avenida 19',
                    direccion:'Cl. 19 # 5 - 72'
                },
                {
                    id:2,
                    nombre:'Hotel Augusta',
                    direccion:'Avenida JimÃ©nez # 4 - 7'
                },
                {
                    id:3,
                    nombre:'Hotel Casa Galeria',
                    direccion:'Cra. 2 # 12 - 92'
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
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades"
         * Obtiene los parÃ¡metros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de ciudades y los headers.
         */
        $httpBackend.whenGET('api/ciudades').respond(function (method, url) {
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
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/idCiudad/sitios"
         * Obtiene los parÃ¡metros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de ciudades y los headers.
         */
        $httpBackend.whenGET(recordUrlSitios).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var record;
            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    record = ng.copy(value.sitios);
                }
            });
            return [200, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/idCiudad/eventos"
         * Obtiene los parÃ¡metros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de ciudades y los headers.
         */
        $httpBackend.whenGET(recordUrlEventos).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var record;
            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    record = ng.copy(value.eventos);
                }
            });
            return [200, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/idCiudad/hoteles"
         * Obtiene los parÃ¡metros de consulta "queryParams" para establecer
         * la pagina y la maxima cantida de records. Con los anteriores parametros
         * se realiza la simulacion de la paginacion.
         * Response: 200 -> Status ok, array de ciudades y los headers.
         */
        $httpBackend.whenGET(recordUrlHoteles).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var record;
            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    record = ng.copy(value.eventos);
                }
            });
            return [200, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/[numero]"
         * Obtiene el id de la url y el registro asociado dentro del array records.
         * Response: 200 -> Status ok, record -> ciudad y ningÃºn header.
         */
        $httpBackend.whenGET(recordUrlCiudad).respond(function (method, url) {

            var id = parseInt(url.split('/')[2]);
            var record;
            ng.forEach(records, function (value) {
                if (value.id === id) {
                    record = ng.copy(value);
                }
            });
            return [200, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/[numero]/sitios/[numero]"
         * Obtiene el id de la url y el registro asociado dentro del array sitios dentro del array de records.
         * Response: 200 -> Status ok, record -> sitio y ningÃºn header.
         */
        $httpBackend.whenGET(recordUrlSitio).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_sitio = parseInt(url.split('/')[4]);
            var sitio;
            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    ng.forEach(value.sitios, function (value2) {
                        if (value2.id === id_sitio) {
                            sitio = ng.copy(value2);
                        }
                    });
                }
            });
            return [200, sitio, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/[numero]/eventos/[numero]"
         * Obtiene el id de la url y el registro asociado dentro del array eventos dentro del array de records.
         * Response: 200 -> Status ok, record -> evento y ningÃºn header.
         */
        $httpBackend.whenGET(recordUrlEvento).respond(function (method, url) {

            console.log("entro al mock");

            var id_ciudad = parseInt(url.split('/')[2]);
            var id_evento = parseInt(url.split('/')[4]);
            var evento;

            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    ng.forEach(value.eventos, function (value2) {
                        if (value2.id === id_evento) {
                            evento = ng.copy(value2);
                        }
                    });
                }
            });
            return [200, evento, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud GET a la url "api/ciudades/[numero]/hoteles/[numero]"
         * Obtiene el id de la url y el registro asociado dentro del array hoteles dentro del array de records.
         * Response: 200 -> Status ok, record -> hotel y ningÃºn header.
         */
        $httpBackend.whenGET(recordUrlHotel).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_hotel = parseInt(url.split('/')[4]);
            var hotel;
            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    ng.forEach(value.eventos, function (value2) {
                        if (value2.id === id_hotel) {
                            hotel = ng.copy(value2);
                        }
                    });
                }
            });
            return [200, hotel, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/ciudades"
         * Obtiene el record de la ciudad desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al record de ciudad y lo guarda en el
         * array de records.
         * Response: 201 -> Status created, record -> ciudad y ningÃºn header.
         */
        $httpBackend.whenPOST('api/ciudades').respond(function (method, url, data) {
            var record = ng.fromJson(data);
            record.id = Math.floor(Math.random() * 10000);
            records.push(record);
            return [201, record, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/ciudades/idCiudad/sitios"
         * Obtiene el el objeto sitio desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al sitio y lo guarda en el
         * array de sitios de records.
         * Response: 201 -> Status created, record -> ciudad y ningÃºn header.
         */
        $httpBackend.whenPOST(recordUrlSitios).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var sitio = ng.fromJson(data);
            sitio.id = Math.floor(Math.random() * 10000);

            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    value.sitios.push(sitio);
                    return [201, sitio, {}];
                }
            });
            /**return [201, sitio, {}];*/
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/ciudades/idCiudad/eventos"
         * Obtiene el el objeto eventos desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al evento y lo guarda en el
         * array de eventos de records.
         * Response: 201 -> Status created, record -> evento y ningÃºn header.
         */
        $httpBackend.whenPOST(recordUrlEventos).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var evento = ng.fromJson(data);
            evento.id = Math.floor(Math.random() * 10000);

            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    value.eventos.push(evento);
                    return [201, evento, {}];
                }
            });
            /**return [201, evento, {}];*/
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud POST a la url "api/ciudades/idCiudad/hoteles"
         * Obtiene el objeto hotel desde el cuerpo de la peticion
         * Genera un id aleatorio y lo asocia al hotel y lo guarda en el
         * array de hoteles de records.
         * Response: 201 -> Status created, record -> hotel y ningÃºn header.
         */
        $httpBackend.whenPOST(recordUrlHoteles).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var hotel = ng.fromJson(data);
            hotel.id = Math.floor(Math.random() * 10000);

            ng.forEach(records, function (value) {
                if (value.id === id_ciudad) {
                    value.hoteles.push(hotel);
                    return [201, hotel, {}];
                }
            });
            /**return [201, hotel, {}];*/
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/ciudades/[numero]"
         * Obtiene el id del la url y el registro asociado dentro del array records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenDELETE(recordUrlCiudad).respond(function (method, url) {
            var id = parseInt(url.split('/')[2]);
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1);
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/ciudades/[numero]/sitios/[numero]"
         * Obtiene los ids del la url y el registro asociado dentro del array de sitios del array de records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenDELETE(recordUrlSitio).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_sitio = parseInt(url.split('/')[4]);
            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.sitios, function (sit, key) {
                        if (sit.id === id_sitio) {
                            ciud.sitios.splice(key, 1);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/ciudades/[numero]/eventos/[numero]"
         * Obtiene los ids del la url y el registro asociado dentro del array de eventos del array de records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenDELETE(recordUrlEvento).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_evento = parseInt(url.split('/')[4]);
            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.eventos, function (eve, key) {
                        if (eve.id === id_evento) {
                            ciud.eventos.splice(key, 1);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud DELETE a la url "api/ciudades/[numero]/hoteles/[numero]"
         * Obtiene los ids del la url y el registro asociado dentro del array de hoteles del array de records.
         * Luego realiza un splice "eliminar registro del array".
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenDELETE(recordUrlHotel).respond(function (method, url) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_hotel = parseInt(url.split('/')[4]);
            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.hoteles, function (hot, key) {
                        if (hot.id === id_hotel) {
                            ciud.hoteles.splice(key, 1);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/ciudad/[numero]"
         * Obtiene el id del la url y el record de ciudad desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(recordUrlCiudad).respond(function (method, url, data) {
            var id = parseInt(url.split('/')[2]);
            var record = ng.fromJson(data);
            ng.forEach(records, function (value, key) {
                if (value.id === id) {
                    records.splice(key, 1, record);
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/ciudad/[numero]/sitios/[numero]"
         * Obtienen los ids del la url y el sitio de esa ciudad desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(recordUrlSitio).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_sitio = parseInt(url.split('/')[4]);
            var sitio = ng.fromJson(data);

            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.sitios, function (sit, key) {
                        if (sit.id === id_sitio) {
                            ciud.sitios.splice(key, 1, sitio);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/ciudad/[numero]/eventos/[numero]"
         * Obtienen los ids del la url y el evento de esa ciudad desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(recordUrlEvento).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_evento = parseInt(url.split('/')[4]);
            var evento = ng.fromJson(data);

            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.eventos, function (eve, key) {
                        if (eve.id === id_evento) {
                            ciud.eventos.splice(key, 1, evento);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

        /*
         * Esta funcion se ejecuta al invocar una solicitud PUT a la url "api/ciudad/[numero]/hoteles/[numero]"
         * Obtienen los ids del la url y el hotel de esa ciudad desde el cuerpo de la peticion
         * Busca y reemplaza el anterior registro por el enviado en el cuerpo de la solicitud
         * Response: 204, no retorna ningun dato ni headers.
         */
        $httpBackend.whenPUT(recordUrlEvento).respond(function (method, url, data) {
            var id_ciudad = parseInt(url.split('/')[2]);
            var id_hotel = parseInt(url.split('/')[4]);
            var hotel = ng.fromJson(data);

            ng.forEach(records, function (ciud) {
                if (ciud.id === id_ciudad) {
                    ng.forEach(ciud.hoteles, function (hot, key) {
                        if (hot.id === id_hotel) {
                            ciud.hoteles.splice(key, 1, hotel);
                        }
                    });
                }
            });
            return [204, null, {}];
        });

    }]);
})(window.angular);






