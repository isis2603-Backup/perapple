/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.misVacaciones.rest.mocks;

/**
 *
 * @author Perapple
 */
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.SitioLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.EventoLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 * Contiene la lista de ciudades
 */
@Named
@Singleton
public class CiudadLogicMock {

	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(CiudadLogicMock.class.getName());

	// listado de ciudades
    public static ArrayList<CiudadDTO> ciudades;


    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public CiudadLogicMock() {
        //TODO crear ciudades iniciales
    	if (ciudades == null) {
            ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","", "" ));
            ciudades.add(new CiudadDTO(2L, "Cali", "Sucursal del cielo", "http://static.panoramio.com/photos/large/43907931.jpg", "", ""));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los parques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "", ""));
            ciudades.add(new CiudadDTO(4L, "Barranquilla", "Puerta de oro de Colombia", "http://deborondo.com/wp-content/uploads/2015/04/identificador_de_barranquilla_4-800x500_c.jpg", "", ""));
            ciudades.add(new CiudadDTO(5L, "Cartagena", "Ajá de Colombia", "http://deborondo.com/wp-content/uploads/2015/04/identificador_de_barranquilla_4-800x500_c.jpg", "", ""));

             ArrayList<EventoDTO> eventos1 = new ArrayList<>();
            eventos1.add(new EventoDTO(1L, "Bogotá Pub Craw", "Acerca del tour\\n\\\n" +
                " Bogotanos y extranjeros podrán disfrutar del Bogota Pub Crawl, evento que promete hacer un tour por los mejores bares de la ciudad.\n\\n" +
                " Todo el recorrido se hace en compañía de un guía que habla español e inglés. A lo largo de la jornada habrá un fotógrafo que estará acompañando a los participantes\\n\\\n" +
                " del tour y capturará los mejores momentos.\\n\\\n" +
                " \n\\n" +
                " Qué debo saber\\n\\\n" +
                " \\n\\\n" +
                " La fiesta inicia en el Bier Market (carrera 13 No. 83-75 - zona T) a las 8:30 p.m. Allí, todos los participantes recibirán una manilla después de cancelar el precio \\n\\\n" +
                " del tour que es de $60.000. En esta primera parada podrán tomar cerveza o vino y comer pizza sin límite.\\n\\\n" +
                " Una hora y media después, el grupo será llevado a un bar donde recibirá un shot de trago gratis y descuentos en la carta del lugar. Habrán precios especiales en cervezas y cocteles.\\n\\\n" +
                " Tiempo después el grupo irá a un segundo bar.\\n\\\n" +
                " La última y cuarta parada será en la discoteca 4:40 de la Zonta T. Allí no pagaran cover y podrán disfrutar de una buena fiesta.\\n\\\n" +
                " En este lugar el trago corre por cuenta de cada participante.\\n\\", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg"));

            eventos1.add(new EventoDTO(2L, "Carrera Zombie Survivor Race 5K" , "El próximo 28 de febrero, zombis y corredores serán protagonistas de una competencia apocalíptica de 5 kilómetros y con más de 10 obstáculos (agua, barro, tierra, entre otros).\\n\\\n" +
            "Durante la carrera Zombie Survivor Race 5K, los participantes tendrán que sobrevivir a hordas de zombies que vendrán con el único fin de convertirlos en uno más de ellos.\\n\\\n" +
            "\\n\\\n" +
            "\\n\\\n" +
            "Hay tres manera de participar:\\n\\\n" +
            "\\n\\\n" +
            "1. Como corredor. Usted llevará un cinturón y banderines y sus deber es escapar de los zombis, mantener los banderines completos, superar obstáculos y llegar a la meta.\\n\\\n" +
            "2. Como zombi. Debe disfrazarse, asustar a los corredores y robarles los banderines. Cuando se participa como zombi puede moverse libremente por la pista\\n\\\n" +
            "3. Zombi y corredor. Es posible que usted primero un haga un papel y luego el otro.\\n\\\n" +
            "\\n\\\n" +
            "\\n\\\n" +
            "¿Qué debo saber?\\n\\\n" +
            "\\n\\\n" +
            "El precio de la inscripción está entre $85.000 y $140.000, el valor depende del rol que elija. Se incluye seguro médico\\n\\\n" +
            "\\n\\\n" +
            "La inscripción incluye:\\n\\\n" +
            "\\n\\\n" +
            "-Cinturón con banderines de vida (entregado el día de la carrera).\\n\\\n" +
            "\\n\\\n" +
            "-Camiseta oficial Zombie Survivor Race 5k.\\n\\\n" +
            "\\n\\\n" +
            "-Número de sobreviviente.\\n\\\n" +
            "\\n\\\n" +
            "-Chip de medición de tiempo.\\n\\\n" +
            "\\n\\\n" +
            "-Puntos de hidratación Refrigerio al finalizar (seas Zombie o Survivor).\\n\\\n" +
            "\\n\\\n" +
            "-Rifas y sorteos.\\n\\\n" +
            "\\n\\\n" +
            "-Atención médica disponible durante todo el evento.\\n\\\n" +
            "\\n\\\n" +
            "- Baños VIP.\\n\\\n" +
            "\\n\\\n" +
            "-Guardarropas, ambulancia, escuadrones anti zombies en zonas seguras.\\n\\\n" +
            "\\n\\\n" +
            "-Vestidores Agua para breve higiene después de la travesía apocalítica.\\n\\\n" +
            "\\n\\\n" +
            "-Cupón de descuento del 20% para el merchandising oficial de la Zombie-Survivor Race 5K.\\n\\\n" +
            "\\n\\\n" +
            "Se reciben corredores desde los 13 años de edad. Pero para los menores de 18, los papás deben llenar el formulario de “Exoneración de Responsabilidades’’ con firma,\\n\\\n" +
            "huella y anexa la copia de la cédula.\\n\\\n" +
            "\\n\\\n" +
            "Habrá premios para:\\n\\\n" +
            "\\n\\\n" +
            "-Participante más rápido de cada categoría y cada sexo.\\n\\\n" +
            "\\n\\\n" +
            "-Zombie con más banderines tomados de los participantes.\\n\\\n" +
            "\\n\\\n" +
            "-Mejor disfraz zombie.\\n\\\n" +
            "\\n\\\n" +
            "- Mejor disfraz sobreviviente.\\n\\\n" +
            "\\n\\\n" +
            "- Mejor disfraz grupo.", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg"));
            ciudades.get(0).setEventos(eventos1);
            ArrayList<SitioDTO> sitios1 = new ArrayList<>();

            sitios1.add(new SitioDTO(1L, "Museo del oro", "El Museo del Oro del Banco de la República de Colombia es una institución abierta al público cuya finalidad es la adquisición, \\n\\\n" +
                "conservación y exposición de piezas de orfebrería y alfarería de culturas indígenas del periodo precolombino de la actual Colombia. \\n\\\n" +
                "Está ubicado en el costado oriental del parque Santander, en el centro histórico de Bogotá.\\n\\\n" +
                "\\n\\\n" +
                "Posee la colección de orfebrería prehispánica más grande del mundo con aproximadamente treinta y cuatro mil piezas de oro y tumbaga,\\n\\\n" +
                "cerca de veinticinco mil objetos en cerámica, piedra, concha, hueso y textiles. \\n\\\n" +
                "Expone piezas de diferentes culturas indígenas asentadas en la actual Colombia antes de la llegada de los europeos, \\n\\\n" +
                "entre las que destacan la Calima, los muiscas, la Nariño, la quimbaya, la sinú, la tairona, la San Agustín, la Tierradentro, la Tolima, \\n\\\n" +
                "entre otras cosas.", "https://upload.wikimedia.org/wikipedia/commons/d/dc/BOG_Museo_del_Oro.JPG"));
            sitios1.add(new SitioDTO(2L, "Parque de la 93", "El parque de la 93 es un parque turístico en el norte de Bogotá.\\n\\\n" +
                    "Se encuentra ubicado en la localidad de Chapinero, entre las calles 93 A y 93 B y entre las carreras 11 A y 13, \\n\\\n" +
                    "en el sector de El Chicó. Fue inaugurado el 14 de junio de 1995.\\n\\\n" +
                    "En el parque se encuentra la primera tienda de Starbucks y Carls Jr. en toda Colombia.", "https://upload.wikimedia.org/wikipedia/commons/0/0d/Bogot%C3%A1barrio_El_Chic%C3%B3_Parque_de_la_93.JPG"));
            ciudades.get(0).setSitios(sitios1);


            ArrayList<SitioDTO> sitios2 = new ArrayList<>();

            sitios2.add(new SitioDTO(1L, "Zoologico de Cali", "Es innegable que la región ha redescubierto al Zoológico de Cali como epicentro de conservación,\\n\\\n" +
                " que goza de credibilidad en virtud de su transparencia, honestidad y profesionalismo. \\n\\\n" +
                " Por más de una década ha ejercido un liderazgo en la comunidad zoológica nacional e internacional,\\n\\\n" +
                " promoviendo y acompañando el desarrollo de otras instituciones de su misma naturaleza, \\n\\\n" +
                " y participando activamente en la consolidación de una comunidad más comprometida con la conservación de la biodiversidad. \\n\\\n" +
                " El Zoológico de Cali ha crecido bajo una forma de organización con objetivos claros y compartidos, \\n\\\n" +
                " sustentada en principios y valores en el marco de un pensamiento estratégico que propone relatos innovadores en \\n\\\n" +
                " una institución que contribuye a crear escenarios de bienestar para las comunidades humanas y la vida silvestre. \\n\\\n" +
                " El Zoológico de Cali es una plataforma que promueve la construcción del compromiso ambiental.", "http://www.icesi.edu.co/blogs_estudiantes/zooincali/files/2012/09/mapa_final-1024x6401.jpg"));
            sitios2.add(new SitioDTO(2L, "Teatro Municipal Enrique Buenaventura", "Anteriormente llamado Teatro Municipal de Cali, es un teatro declarado monumento nacional en 1982 que se encuentra en el centro de la ciudad de Santiago de Cali.\\n\\\n" +
            "El teatro cuenta con un estilo clásico italiano y se encuentra adornado por múltiples obras de artistas nacionales y extranjeros.\\n\\\n" +
            "La Sala Principal del teatro tiene una capacidad de 1039 espectadores que se dividen en diferentes localidades, cada uno con un nombre particular, \\n\\\n" +
            "siendo la Luneta la primera y está situada a un nivel más bajo que el escenario. Le siguen el Primer y Segundo palco respectivamente divididos en cubículos numerados y \\n\\\n" +
            "en los dos últimos niveles el anfiteatro y la galería.", "https://upload.wikimedia.org/wikipedia/commons/0/0b/CONTRASTES.JPG"));
            ciudades.get(1).setSitios(sitios2);

            ArrayList<EventoDTO> eventos3 = new ArrayList<>();
            eventos3.add(new EventoDTO(1L, "Concierto de Manu Chao en Bucaramanga", "Pilas pues para una celebración llena de reggae, folk, rock y punk: ¡Todo está listo para que Manu Chao haga temblar a toda Bucaramanga!\\n\\\n" +
                    "Como ya se confirmó por parte de T310 y The Proyex Project, empresas organizadoras del evento, este emblemático artista se presentará en la Ciudad Bonita el próximo 12 de marzo.\\n\\\n" +
                    "Así que prepárate para entonar himnos como “Clandestino”, “Desaparecido”, “Mr. Bobby”, “Bienvenido a Tijuana” y “Mala Vida”, entre muchos más.\\n\\\n" +
                    "\\n\\\n" +
                    "El concierto se realizará en en el Club El Cortijo ubicado sobre el anillo vial y tendrá una capacidad para 5,000 personas. Sus boletas,\\n\\\n" +
                    "que se pueden adquirir en Tu Boleta tendrán un valor de $50.000 para las primeras 500 personas y $65.000 para el resto de boletería.\\n\\\n" +
                    "Seguramente muchos crecimos con su música y sus letras y ritmos aún forman parte de nuestras vidas.\\n\\\n" +
                    "Prepárate para vivir la magia de su música desde sus inicios con Mano Negra hasta la actualidad.", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg"));

            eventos3.add(new EventoDTO(2L, "Interpark - Ciudad de la diversión", "Disfruta de la ciudad de la diversión y la adrenalina más grande, moderna y segura jamas vista en Bucaramanga. Una ciudad de hierro que no quieres dejar de visitar esta navidad.\\n\\\n" +
                "Te dejamos los precios de las entradas y los Pasaportes para un día de adrenalina.\\n\\\n" +
                "\\n\\\n" +
                "Localidad             	Precio\\n\\\n" +
                "\\n\\ \\n\\\n" +
                "ENTRADA                       $ 5.000\\n\\\n" +
                "PASAPORTE Lunes a Jueves 	$ 20.000\\n\\\n" +
                "PASAPORTE fines de semana 	$ 25.000","https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg"));
            ciudades.get(2).setEventos(eventos3);
            ArrayList<SitioDTO> sitios3 = new ArrayList<>();

            sitios3.add(new SitioDTO(1L, "Cañón del Chicamocha", "El cañon del Chicamocha es un acidente geográfico en colombia ubicado en las riveras del río Chicamocha,\\n\\\n" +
                "durante su recorrido por los departamentos de Boyacá y principalmente de Santander, donde alcanza su máxima \\n\\n\\\n" +
                "profundidad en inmediaciones de la ciudad de Bucaramanga, entre los municipios de Aratoca, Cepitá, Los Santos y Jordán.", "http://santanderalextremo.com/wp-content/uploads/2013/02/panachi_003.jpg"));
            sitios3.add(new SitioDTO(2L, "Mirador de Palonegro", "ubicado al oriente de la ciudad, en la vía que conduce a la ciudad de Cúcuta. Está construido en el sector un parque en forma de colina,\\n\\\n" +
                "\\n\\ donde se puede apreciar desde su cima una panorámica imponente de la ciudad; hay senderos en concreto y en tierra que conducen a la cima donde \\n\\\n" +
                "\\n\\ hay una fuente con una estatua del Sagrado Corazón de Jesús, que es visitada en época de Semana Santa por los habitantes de la ciudad, sobre todo \\n\\\n" +
                "\\n\\ para realizar el Viacrucis en Viernes Santo; además los habitantes y feligreses del sector tienen la creencia que la estatua hace milagros.\\n\\\n" +
                "\\n\\ Cuenta además el parque con un escenario en forma de media torta o media luna para hacer teatro al aire libre u otras actividades culturales, sociales o religiosas."
                    , "http://www.riojapacific.com/wp/TravelDemoSources/TravelDemoSources/Website/wp-content/plugins/thethe-image-slider/timthumb.php?w=960&h=398&zc=1&src=http%3A%2F%2Fwww.riojapacific.com%2Fwp%2FTravelDemoSources%2FTravelDemoSources%2FWebsite%2Fwp-content%2Fuploads%2F2015%2F04%2FBUCA RAMANGA-SLIDER1.jpg"));
            ciudades.get(2).setSitios(sitios3);

        }
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + ciudades );
    }

	/**
	 * Obtiene el listado de personas.
	 * @return lista de ciudades
	 * @throws CiudadLogicException cuando no existe la lista en memoria
	 */
    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
    	if (ciudades == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new CiudadLogicException("Error interno: lista de ciudades no existe.");
    	}

    	logger.info("retornando todas las ciudades");
    	return ciudades;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     */
    public CiudadDTO getCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO city : ciudades) {
            if (Objects.equals(city.getId(), id)){
            	logger.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new CiudadLogicException("No existe ciudad con ese id");
    }

    /**
     * Agrega una ciudad a la lista.
     * @param nuevaCiudad ciudad a adicionar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public CiudadDTO fundarCiudad(CiudadDTO nuevaCiudad) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + nuevaCiudad);

    	// la nueva ciudad tiene id ?
    	if ( nuevaCiudad.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (CiudadDTO city : ciudades) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), nuevaCiudad.getId())){
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new CiudadLogicException("Ya existe una ciudad con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para la nueva ciudad");
    		long newId = 1;
	        for (CiudadDTO ciudad : ciudades) {
	            if (newId <= ciudad.getId()){
	                newId =  ciudad.getId() + 1;
	            }
	        }
	        nuevaCiudad.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando ciudad " + nuevaCiudad);
        ciudades.add(nuevaCiudad);
        return nuevaCiudad;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param modificarCiudad ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    public CiudadDTO actualizarCiudad(Long id, CiudadDTO modificarCiudad) throws CiudadLogicException {
    	logger.info("recibiendo solictud de modificar ciudad " + modificarCiudad);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)) {

            	// modifica la ciudad
            	ciudad.setId(modificarCiudad.getId());
                ciudad.setNombre(modificarCiudad.getNombre());

                // retorna la ciudad modificada
            	logger.info("Modificando ciudad " + ciudad);
                return ciudad;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    public void arrazarCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando ciudad " + ciudad);
                ciudades.remove(ciudad);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
    }

    public void crearSitioEnCiudad(Long idCiudad, SitioDTO nuevoSitio) throws CiudadLogicException
    {
        logger.info("recibiendo solicitud de agregar sitio en la ciudad con id " + idCiudad);

    	    	// busca la ciudad con el id suministrado
	        for (CiudadDTO ciudad : ciudades) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(ciudad.getId(), idCiudad)){

                        logger.info("agregando sitio a ciudad:"+ ciudad);
                        if(nuevoSitio.getId()== null){

                    if(ciudad.getSitios()!=null){
                    if(ciudad.getSitios().size()>0)
                    nuevoSitio.setId(ciudad.getSitios().get(ciudad.getSitios().size()-1).getId()+1);
                    else
                    nuevoSitio.setId(1L);
                    }
                    else{
                        ArrayList<SitioDTO> sitiosN = new ArrayList<>();
                        nuevoSitio.setId(1L);
                        sitiosN.add(nuevoSitio);
                        ciudad.setSitios(sitiosN);
                    }
                ciudad.getSitios().add(nuevoSitio);
                }
                else{ciudad.getSitios().add(nuevoSitio);

                }
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");

        throw new CiudadLogicException("No existe una ciudad con ese id");
    }

    public void actualizarSitio(Long id, SitioDTO sitio, Long idSitio)throws CiudadLogicException, SitioLogicException
    {
         boolean existeCiudad = false;
        boolean existeSitio= false;

        for (CiudadDTO ciudad : ciudades) {

            if (Objects.equals(ciudad.getId(), id)) {
                existeCiudad = true;
            	// modifica la ciudad
            	logger.info("modificando el sitio"+ sitio + " de la ciudad " + ciudad);
               for(SitioDTO antiguoSitio : ciudad.getSitios()){
                if(Objects.equals(antiguoSitio.getId(), idSitio)){
                    existeSitio = true;
                    antiguoSitio =  sitio;
                }
               }

            }
        }

        // no encontró alguna ciudad con ese id ?
        if(!existeCiudad)
        {logger.severe("No existe una ciudad con ese id");

        throw new CiudadLogicException("No existe una ciudad con ese id");
        }

        if(!existeSitio){
        logger.severe("No existe un sitio con ese id en la ciudad indicada");

        throw new SitioLogicException("No existe un sitio con ese id");
        }

    }

    public void borrarSitio(Long idCiudad, Long idSitio) throws CiudadLogicException, SitioLogicException
    {
         boolean existeSitio=false;
        logger.info("recibiendo solicitud de eliminar sitio con id " + idSitio + " de la ciudad con id " +idCiudad);

    CiudadDTO stadt=null;
    	// busca la ciudad  con el id suministrado
        for (int i = 0 ;i<ciudades.size() && stadt == null; i++) {
            CiudadDTO ciudad = ciudades.get(i);
            if (Objects.equals(ciudad.getId(), idCiudad)) {

            	// eliminando el sitio
                stadt = ciudad;
            	logger.info("eliminando el sitio con id"+ idSitio + " de la ciudad con id" + idCiudad);


            }
        }

        if(stadt != null){
            ArrayList<SitioDTO> sitios = stadt.getSitios();
         for( int i = 0; i<sitios.size();i++){
                if(Objects.equals(sitios.get(i).getId(), idSitio)){
                    existeSitio = true;
                   sitios.remove(i);
                }
               }
        }
        // no encontró la ciudad con ese id ?
        if(stadt == null){
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        if(!existeSitio){
        logger.severe("No existe un sitio con ese id en la ciudad con ese idCiudad");
        throw new SitioLogicException("No existe un Sitio con ese id");
        }


    }

    public void crearEventoEnCiudad(Long idCiudad, EventoDTO nuevoEvento) throws CiudadLogicException
    {
        logger.info("recibiendo solicitud de agregar evento en la ciudad con id " + idCiudad);

    	    	// busca la ciudad con el id suministrado
	        for (CiudadDTO ciudad : ciudades) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(ciudad.getId(), idCiudad)){

                        logger.info("agregando evento a ciudad:"+ ciudad);
                        if(nuevoEvento.getId()== null){

                    if(ciudad.getEventos()!=null){
                    if(ciudad.getEventos().size()>0)
                    nuevoEvento.setId(ciudad.getEventos().get(ciudad.getEventos().size()-1).getId()+1);
                    else
                    nuevoEvento.setId(1L);
                    }
                    else{
                        ArrayList<EventoDTO> eventosN = new ArrayList<>();
                        nuevoEvento.setId(1L);
                        eventosN.add(nuevoEvento);
                        ciudad.setEventos(eventosN);
                    }
                ciudad.getEventos().add(nuevoEvento);
                }
                else{ciudad.getEventos().add(nuevoEvento);

                }
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");

        throw new CiudadLogicException("No existe una ciudad con ese id");
    }


    public void actualizarEvento(Long id, EventoDTO evento, Long idEvento)throws CiudadLogicException, EventoLogicException
    {
         boolean existeCiudad = false;
        boolean existeEvento= false;

        for (CiudadDTO ciudad : ciudades) {

            if (Objects.equals(ciudad.getId(), id)) {
                existeCiudad = true;
            	// modifica la ciudad
            	logger.info("modificando el evento"+ evento + " de la ciudad " + ciudad);
               for(EventoDTO antiguoEvento : ciudad.getEventos()){
                if(Objects.equals(antiguoEvento.getId(), idEvento)){
                    existeEvento = true;
                    antiguoEvento =  evento;
                }
               }

            }
        }

        // no encontró alguna ciudad con ese id ?
        if(!existeCiudad)
        {logger.severe("No existe una ciudad con ese id");

        throw new CiudadLogicException("No existe una ciudad con ese id");
        }

        if(!existeEvento){
        logger.severe("No existe un evento con ese id en la ciudad indicada");

        throw new EventoLogicException("No existe un evento con ese id");
        }
    }


     public void borrarEvento(Long idCiudad, Long idEvento) throws CiudadLogicException, EventoLogicException
    {
         boolean existeEvento=false;
        logger.info("recibiendo solicitud de eliminar evento con id " + idEvento + " de la ciudad con id " +idCiudad);

    CiudadDTO stadt=null;
    	// busca la ciudad  con el id suministrado
        for (int i = 0 ;i<ciudades.size() && stadt == null; i++) {
            CiudadDTO ciudad = ciudades.get(i);
            if (Objects.equals(ciudad.getId(), idCiudad)) {

            	// eliminando el evento
                stadt = ciudad;
            	logger.info("eliminando el evento con id"+ idEvento + " de la ciudad con id" + idCiudad);


            }
        }

        if(stadt != null){
            ArrayList<EventoDTO> eventos = stadt.getEventos();
         for( int i = 0; i<eventos.size();i++){
                if(Objects.equals(eventos.get(i).getId(), idEvento)){
                    existeEvento = true;
                   eventos.remove(i);
                }
               }
        }
        // no encontró la ciudad con ese id ?
        if(stadt == null){
        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        if(!existeEvento){
        logger.severe("No existe un evento con ese id en la ciudad con ese idCiudad");
        throw new EventoLogicException("No existe un Evento con ese id");
        }


    }

      public List<SitioDTO> getSitios(Long idCiudad) throws CiudadLogicException
      {

    logger.info("recibiendo solicitud retorno de los sitios de la ciudad con id " +idCiudad);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), idCiudad)) {

            	// retornando los sitios
            	logger.info("retornando el arreglo de sitios"+ ciudad.getSitios());
             return ciudad.getSitios();

            }
        }

        // no encontró la ciudad con ese id ?

        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
       }

      public SitioDTO getSitio(Long idSitio, Long idCiudad) throws SitioLogicException,CiudadLogicException
      {
          boolean existeSitio=false;
          CiudadDTO stadt=null;
          SitioDTO ort=null;

          logger.info("recibiendo solicitud retorno de un sitio con id:"+ idSitio+" "+ "de la lista de sitios la ciudad con id:" + idCiudad);

            for (int i=0; i<ciudades.size()&& stadt==null;i++) {
                CiudadDTO ciudad=ciudades.get(i);
                if (Objects.equals(ciudad.getId(), idCiudad)){
                    stadt=ciudad;
              for(SitioDTO sitio:stadt.getSitios())
               {
                   if(Objects.equals(sitio.getId(), idSitio))
                    existeSitio=true;
                   ort=sitio;
            }

          }
        }

        // si no encuentra la ciudad
        if(stadt==null){
        logger.severe("No existe ciudad con ese id");
        throw new CiudadLogicException("No existe ciudad con ese id");
        }
        if(!existeSitio){
                 logger.severe("No existe sitio con ese id");
        throw new SitioLogicException("No existe sitio con ese id");
        }

         return ort;
      }

      public EventoDTO getEvento(Long idEvento, Long idCiudad) throws EventoLogicException,CiudadLogicException
      {
          boolean existeEvento=false;
          CiudadDTO stadt=null;
          EventoDTO ereignis=null;

          logger.info("recibiendo solicitud retorno de un evento con id:"+ idEvento+" "+ "de la lista de sitios la ciudad con id:" + idCiudad);

            for (int i=0; i<ciudades.size()&& stadt==null;i++) {
                CiudadDTO ciudad=ciudades.get(i);
                if (Objects.equals(ciudad.getId(), idCiudad)){
                    stadt=ciudad;
              for(EventoDTO evento:stadt.getEventos())
               {
                   if(Objects.equals(evento.getId(), idEvento))
                    existeEvento=true;
                   ereignis=evento;
            }

          }
        }

        // si no encuentra la ciudad
        if(stadt==null){
        logger.severe("No existe ciudad con ese id");
        throw new CiudadLogicException("No existe ciudad con ese id");
        }
        if(!existeEvento){
                 logger.severe("No existe evento con ese id");
        throw new EventoLogicException("No existe evento con ese id");
        }

         return ereignis;
      }

       public List<EventoDTO> getEventos(Long idCiudad) throws CiudadLogicException
      {

    logger.info("recibiendo solicitud retorno de los eventos de la ciudad con id " +idCiudad);

    	// busca la ciudad con el id suministrado
        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), idCiudad)) {

            	// retornando los eventos
            	logger.info("retornando el arreglo de eventos"+ ciudad.getEventos());
             return ciudad.getEventos();

            }
        }

        // no encontró la ciudad con ese id ?

        logger.severe("No existe una ciudad con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
       }



}

