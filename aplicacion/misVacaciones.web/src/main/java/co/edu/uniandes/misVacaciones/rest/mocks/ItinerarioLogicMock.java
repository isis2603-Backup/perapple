
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

import co.edu.uniandes.misVacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.EventoLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.ItinerarioLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.SitioLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Mock del recurso itinerarios (Mock del servicio REST)
 */
@Named
@Singleton
public class ItinerarioLogicMock {

	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ItinerarioLogicMock.class.getName());

	// listado de itinerarios
    public static ArrayList<ItinerarioDTO> itinerarios;
    public static ItinerarioDTO current;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ItinerarioLogicMock() {

        if(current == null)
        {
            current = new ItinerarioDTO();
        }
    	if (itinerarios == null) {
            itinerarios = new ArrayList<>();

            itinerarios.add(new ItinerarioDTO(1L, "Verano 2016", "p@earpple.com", "12-05-2016","13-06-2016"));

            ArrayList<CiudadDTO> ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","07/05/2016", " 08/05/2016" ));
            ciudades.add(new CiudadDTO(2L, "Cali", "Sucursal del cielo", "http://static.panoramio.com/photos/large/43907931.jpg", "15/06/2016", "17/06/2016"));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los parques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "18/07/2016", "19/07/2016"));

            itinerarios.get(0).setCiudades(ciudades);

            itinerarios.add(new ItinerarioDTO(2L, "Invierno 2016", "p@earpple.com","12-07-2016","13-08-2016"));

            ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","07/05/2016", " 08/05/2016" ));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los parques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "18/07/2016", "19/07/2016"));
            ciudades.add(new CiudadDTO(4L, "Barranquilla", "Puerta de oro de Colombia", "http://deborondo.com/wp-content/uploads/2015/04/identificador_de_barranquilla_4-800x500_c.jpg", "15/06/2016", "17/06/2016"));

            itinerarios.get(1).setCiudades(ciudades);

            itinerarios.add(new ItinerarioDTO(3L, "Primavera 2017", "p@earpple.com", "12-05-2016","20-05-2016"));

        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de itinerarios");
    	logger.info("itinerarios" + itinerarios );
        logger.info("current " + current);
    }

    /**
     * Retorna el itinerario que actualmente se esta visualizando
     * @return itinerario Actual
     */

    public ItinerarioDTO getCurrentItinerario()
    {
        logger.info("retornando currentItinerario "+ current);
        return current;
    }

    public ItinerarioDTO setCurrentItinerario(ItinerarioDTO nuevoCurrent)
    {
        logger.info("cambiando currentItinerario de " +current+" a "+ nuevoCurrent);
        current = nuevoCurrent;
        return current;
    }

        /**
	* Obtiene el listado de itinerarios.
	* @return lista de itienrarios
	* @throws ItinerarioLogicException cuando no existe la lista en memoria
        */


    public List<ItinerarioDTO> getItinerarios() throws ItinerarioLogicException {
    	if (itinerarios == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new ItinerarioLogicException("Error interno: lista de itinerarios no existe.");
    	}

    	logger.info("retornando todos los itinerarios");
    	return itinerarios;
    }

    /**
     * Obtiene un itinerario
     * @param id identificador de la itiinerario
     * @return itinerario encontrado
     * @throws ItinerarioLogicException cuando el itinerario no existe
     */
    public ItinerarioDTO getItinerario(Long id) throws ItinerarioLogicException {
    	logger.info("recibiendo solicitud de itinerario con id " + id);

    	// busca la ciudad con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)){
            	logger.info("retornando itinerario " + itinerario);
                return itinerario;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe itinerario con ese id");
        throw new ItinerarioLogicException("No existe itinerario con ese id");
    }

    /**
     * Agrega un itinerario a la lista.
     * @param newItinerario itinerario a adicionar
     * @throws ItinerarioLogicException cuando ya existe un itinerario con el id suministrado
     * @return itienrario agregado
     */
    public ItinerarioDTO createItinerario(ItinerarioDTO newItinerario) throws ItinerarioLogicException {
    	logger.info("recibiendo solicitud de agregar itinerario " + newItinerario);

    	// la nueva ciudad tiene id ?
    	if ( newItinerario.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ItinerarioDTO itinerario : itinerarios) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(itinerario.getId(), newItinerario.getId())){
	            	logger.severe("Ya existe un itinerario con ese id");
	                throw new ItinerarioLogicException("Ya existe un itinerario con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para el nuevo itinerario");
    		long newId = 1;
	        for (ItinerarioDTO itinerario : itinerarios) {
	            if (newId <= itinerario.getId()){
	                newId =  itinerario.getId() + 1;
	            }
	        }
	        newItinerario.setId(newId);
    	}

        // agrega el itinerario
    	logger.info("agregando itinerario" + newItinerario);
        itinerarios.add(newItinerario);
        return newItinerario;
    }

    /**
     * Actualiza los datos de un itinerario
     * @param id identificador de la ciudad a modificar
     * @param updatedItinerario itinerario a modificar
     * @return datos del itinerario modificado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    public ItinerarioDTO updateItinerario(Long id, ItinerarioDTO updatedItinerario) throws ItinerarioLogicException {
    	logger.info("recibiendo solictud de modificar itinerario " + updatedItinerario);

    	// busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

            	// modifica el itinerario
            	itinerario.setId(updatedItinerario.getId());
                itinerario.setNombre(updatedItinerario.getNombre());

                // retorna el itinerario modificada
            	logger.info("Modificando itinerario " + itinerario);
                return itinerario;
            }
        }

        // no encontró el itinerario con ese id ?
        logger.severe("No existe un itineraio con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    public void deleteItinerario(Long id) throws ItinerarioLogicException {
    	logger.info("recibiendo solictud de eliminar itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando itinerario " + itinerario);
                itinerarios.remove(itinerario);
                return;
            }
        }

        // no encontró el itinerario con ese id ?
        logger.severe("No existe un itinerario con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");
    }

    /**
     * Crea una ciudad en el itinerario con id dado, según una ciudad dada por párametro
     * @param id
     * @param ciudad
     * @throws ItinerarioLogicException
     */
    public void createCiudad(Long id, CiudadDTO ciudad) throws ItinerarioLogicException {

        logger.info("recibiendo solictud de agregar ciudad a itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

            	// elimina la ciudad
            	logger.info("agregando ciudad a" + itinerario);
                if(ciudad.getId()== null){

                    if(itinerario.getCiudades()!=null){
                    if(itinerario.getCiudades().size()>0)
                    ciudad.setId(itinerario.getCiudades().get(itinerario.getCiudades().size()-1).getId()+1);
                    else
                    ciudad.setId(1L);
                    }
                    else{
                        ArrayList<CiudadDTO> ciudadesN = new ArrayList<>();
                        ciudad.setId(1L);
                        ciudadesN.add(ciudad);
                        itinerario.setCiudades(ciudadesN);
                    }
                itinerario.getCiudades().add(ciudad);
                }
                else{itinerario.getCiudades().add(ciudad);

                }
                return;
            }
        }

        // no encontró el itinerario con ese id ?
        logger.severe("No existe un itinerario con ese id");

        throw new ItinerarioLogicException("No existe un itinerario con ese id"); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateCiudad(Long id, CiudadDTO ciudad, Long idciudad) throws ItinerarioLogicException, CiudadLogicException {

        boolean existeItinerario = false;
        boolean existeCiudad = false;

        for (ItinerarioDTO itinerario : itinerarios) {

            if (Objects.equals(itinerario.getId(), id)) {
                existeItinerario = true;
            	// modifica la ciudad
            	logger.info("modificando la ciudad"+ ciudad + " del itinerario " + itinerario);
               for(int i = 0; i<itinerario.getCiudades().size();i++){
                   CiudadDTO ciudadvieja = itinerario.getCiudades().get(i);
                if(Objects.equals(ciudadvieja.getId(), idciudad)){
                    existeCiudad = true;
                    itinerario.getCiudades().remove(i);
                    itinerario.getCiudades().add(i, ciudad);
                    logger.info("Ciudad modificada: "+ itinerario.getCiudades().get(i));
                }
               }

            }
        }

        // no encontró el itinerario con ese id ?
        if(!existeItinerario)
        {logger.severe("No existe un itinerario con ese id");

        throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }

        if(!existeCiudad){
        logger.severe("No existe una ciudad con ese id en el itinerario identificado");

        throw new CiudadLogicException("No existe una ciudad con ese id");
        }
    }

    public void deleteCiudad(Long id, Long idciudad) throws ItinerarioLogicException, CiudadLogicException {

        boolean existeCiudad = false;
    logger.info("recibiendo solicitud de eliminar ciudad con id " + idciudad + " del itinerario con id " +id);

    ItinerarioDTO itin=null;
    	// busca el itinerario con el id suministrado
        for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
            ItinerarioDTO itinerario = itinerarios.get(i);
            if (Objects.equals(itinerario.getId(), id)) {

            	// eliminando la ciudad
                itin = itinerario;
            	logger.info("eliminando la ciudad con id"+ idciudad + " del itinerario con id" + id);


            }
        }

        if(itin != null){
            ArrayList<CiudadDTO> ciudades = itin.getCiudades();
         for( int i = 0; i<ciudades.size();i++){
                if(Objects.equals(ciudades.get(i).getId(), idciudad)){
                    existeCiudad = true;
                   ciudades.remove(i);
                }
               }
        }
        // no encontró el itinerario con ese id ?
        if(itin == null){
        logger.severe("No existe un itinerario con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        if(!existeCiudad){
        logger.severe("No existe una ciudad con ese id en el itinerario con ese id");
        throw new CiudadLogicException("No existe una ciudad con ese id");
        }
    }

    public List<CiudadDTO> getCiudades(Long id) throws ItinerarioLogicException {

    logger.info("recibiendo solicitud retonro de las cidudades del itnerario con id " +id);

    	// busca el itinerario con el id suministrado
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

            	// retornando las ciudades
            	logger.info("retornando el arreglo de ciudades"+ itinerario.getCiudades());
             return itinerario.getCiudades();

            }
        }

        // no encontró el itinerario con ese id ?

        logger.severe("No existe un itinerario con ese id");
        throw new ItinerarioLogicException("No existe un itinerario con ese id");




    }

    /**
     *
     * @param id
     * @param idciudad
     * @param sitio
     * @return el sitio que se agrega
     * @throws ItinerarioLogicException
     * @throws CiudadLogicException
     */
    public SitioDTO createSitioDeInteres(Long id, Long idciudad, SitioDTO sitio) throws ItinerarioLogicException, CiudadLogicException {
        logger.info("recibiendo solictud de agregar sitio a ciudad con id " + idciudad + " de itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        ItinerarioDTO iti = null;
        CiudadDTO cdad = null;
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
                iti = itinerario;
                for(CiudadDTO ciudad : itinerario.getCiudades()){
                    if(Objects.equals(ciudad.getId(), idciudad)){
                        cdad = ciudad;
                        logger.info("agregando sitio a" + ciudad);
                        if (sitio.getId() == null) {
                            if (ciudad.getSitios()!= null) {
                                if (ciudad.getSitios().size() > 0) {
                                    sitio.setId(ciudad.getSitios().get(ciudad.getSitios().size() - 1).getId() + 1);
                                } else {
                                    sitio.setId(1L);
                                }
                            } else {
                                ArrayList<SitioDTO> sitiosN = new ArrayList<>();
                                sitio.setId(1L);
                                sitiosN.add(sitio);
                                ciudad.setSitios(sitiosN);
                            }
                            ciudad.getSitios().add(sitio);
                        } else {
                             logger.info("sitio ya tenía id");
                            ciudad.getSitios().add(sitio);
                        }
                    }
                }
            }
        }

        // no encontró el itinerario con ese id ?
        if(iti == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }

        if(cdad == null){
            logger.severe("No existe una ciudad con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }

        return sitio;
    }

    /**
     *
     * @param id
     * @param idciudad
     * @param idsitio
     * @throws ItinerarioLogicException
     * @throws CiudadLogicException
     * @throws SitioLogicException
     */
    public void deleteSitioDeInteres(Long id, Long idciudad, Long idsitio) throws ItinerarioLogicException, CiudadLogicException, SitioLogicException {
        logger.info("recibiendo solicitud de eliminar sitio con id " + idsitio + " de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
        SitioDTO sitio = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            ciudad = ciudadActual;
                            logger.info("buscado el sitio con id " + idsitio);
                            ArrayList<SitioDTO> sitios = ciudad.getSitios();
                            for( int k = 0; k<sitios.size() && sitio == null;k++){
                                SitioDTO sitioActual = sitios.get(k);
                                if(Objects.equals(sitioActual.getId(), idsitio)){
                                    logger.info("eliminando el sitio con id " + idsitio);
                                    sitio = sitioActual;
                                    sitios.remove(k);
                                }
                            }
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        if(ciudad == null){
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        // no encontró el sitio con ese id ?
        if(sitio == null){
            logger.severe("No existe un sitio con ese idsitio en la ciudad con ese idciudad del itinerario con ese id");
            throw new SitioLogicException("No existe un sitio con ese id");
        }
    }

    /**
     *
     * @param id
     * @param idciudad
     * @return
     * @throws ItinerarioLogicException
     * @throws CiudadLogicException
     */
    public ArrayList<SitioDTO> fetchSitiosDeInteres(Long id, Long idciudad) throws ItinerarioLogicException, CiudadLogicException {
        logger.info("recibiendo solicitud de listar sitios de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            logger.info("retornando sitios" + ciudadActual.getSitios());
                            ciudad = ciudadActual;
                            return ciudad.getSitios();
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        else {
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
    }

    /**
     *
     * @param id
     * @param idciudad
     * @param idsitio
     * @return
     * @throws ItinerarioLogicException
     * @throws CiudadLogicException
     * @throws SitioLogicException
     */
    public SitioDTO fetchSitioDeInteres(Long id, Long idciudad, Long idsitio) throws ItinerarioLogicException, CiudadLogicException, SitioLogicException {
        logger.info("recibiendo solicitud de listar sitio con id " + idsitio + " de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
        SitioDTO sitio = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            ciudad = ciudadActual;
                            logger.info("buscado el sitio con id " + idsitio);
                            ArrayList<SitioDTO> sitios = ciudad.getSitios();
                            for( int k = 0; k<sitios.size() && sitio == null;k++){
                                SitioDTO sitioActual = sitios.get(k);
                                if(Objects.equals(sitioActual.getId(), idsitio)){
                                    logger.info("listando el sitio con id " + idsitio);
                                    return sitioActual;
                                }
                            }
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        else if(ciudad == null){
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        // no encontró el sitio con ese id ?
        else {
            logger.severe("No existe un sitio con ese idsitio en la ciudad con ese idciudad del itinerario con ese id");
            throw new SitioLogicException("No existe un sitio con ese id");
        }
    }

    /**
     * Crea un nuevo evento en la ciudad con el id dado en el itinerario con el id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param evento elemento tipo evento que se desea agregar
     * @return el evento que se desea agregar
     * @throws ItinerarioLogicException si no existe itinerario
     * @throws CiudadLogicException si no existe ciudad
     * @throws EventoLogicException si ya existe una ciudad con el id del evento que se quiere agregar
     */
    public EventoDTO createEvento(Long id, Long idciudad, EventoDTO evento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException {
        logger.info("recibiendo solictud de agregar evento a ciudad con id " + idciudad + " de itinerario con id " + id);

    	// busca el itinerario con el id suministrado
        ItinerarioDTO iti = null;
        CiudadDTO cdad = null;
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
                iti = itinerario;
                for(CiudadDTO ciudad : itinerario.getCiudades()){
                    if(Objects.equals(ciudad.getId(), idciudad)){
                        cdad = ciudad;
                        logger.info("agregando evento a" + ciudad);
                        if (evento.getId() == null) {
                            if (ciudad.getEventos()!= null) {
                                if (ciudad.getEventos().size() > 0) {
                                    evento.setId(ciudad.getSitios().get(ciudad.getSitios().size() - 1).getId() + 1);
                                } else {
                                    evento.setId(1L);
                                }
                            } else {
                                ArrayList<EventoDTO> eventosN = new ArrayList<>();
                                evento.setId(1L);
                                eventosN.add(evento);
                                ciudad.setEventos(eventosN);
                            }
                            ciudad.getEventos().add(evento);
                        } else {
                             logger.info("evento ya tenía id");
                             for(EventoDTO eve :ciudad.getEventos()){
                             if(Objects.equals(eve.getId(), evento.getId()))
                             {
                                 throw new EventoLogicException("Ya existe un evento con el id del evento a agregar... \\(o0o)/");
                             }
                             }
                            ciudad.getEventos().add(evento);
                        }
                    }
                }
            }
        }

        // no encontró el itinerario con ese id ?
        if(iti == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }

        if(cdad == null){
            logger.severe("No existe una ciudad con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }

        return evento;    }


    /**
     * Borra un evento con el id dado en una ciudad con id dado en un itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param idevento identificador del evento
     * @throws ItinerarioLogicException si no existe itinerario
     * @throws CiudadLogicException si no existe ciudad
     * @throws EventoLogicException si no existe evento
     */
    public void deleteEvento(Long id, Long idciudad, Long idevento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException {
         logger.info("recibiendo solicitud de eliminar evento con id " + idevento + " de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
        EventoDTO evento = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            ciudad = ciudadActual;
                            logger.info("buscado el evento con id " + idevento);
                            ArrayList<EventoDTO> eventos = ciudad.getEventos();
                            for( int k = 0; k<eventos.size() && evento == null;k++){
                                EventoDTO eventoActual = eventos.get(k);
                                if(Objects.equals(eventoActual.getId(), idevento)){
                                    logger.info("eliminando el evento con id " + idevento);
                                    evento = eventoActual;
                                    eventos.remove(k);
                                }
                            }
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        if(ciudad == null){
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        // no encontró el evento con ese id ?
        if(evento == null){
            logger.severe("No existe un evento con ese idevento en la ciudad con ese idciudad del itinerario con ese id");
            throw new EventoLogicException("No existe un evento con ese id");
        }}

    /**
     * retorna los eventos de una ciudad con id dado de un itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @return lista de evetos de la ciudad con id dado del itinerario con id dado
     * @throws ItinerarioLogicException si no existe itinerario con dado
     * @throws CiudadLogicException si no existe ciudad con id dado
     */
    public ArrayList<EventoDTO> fetchEventos(Long id, Long idciudad) throws ItinerarioLogicException, CiudadLogicException {
     logger.info("recibiendo solicitud de listar eventos de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            logger.info("retornando eventos" + ciudadActual.getEventos());
                            ciudad = ciudadActual;
                            return ciudad.getEventos();
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        else {
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }}

    /**
     * Retorna un evento con id dado de una ciudad con id dado de un itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param idevento identificador del evento
     * @return el evento buscado
     * @throws ItinerarioLogicException si no existe el itinerario
     * @throws CiudadLogicException si no existe la ciudad
     * @throws EventoLogicException si no existe el evento
     */

    public EventoDTO fetchEvento(Long id, Long idciudad, Long idevento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException {

    logger.info("recibiendo solicitud de listar evento con id " + idevento + " de la ciudad con id" + idciudad + " del itinerario con id " + id);

        ItinerarioDTO itin = null;
        CiudadDTO ciudad = null;
        EventoDTO evento = null;
            // busca el itinerario con el id suministrado
            for (int i = 0 ;i<itinerarios.size() && itin == null; i++) {
                ItinerarioDTO itinerario = itinerarios.get(i);
                if (Objects.equals(itinerario.getId(), id)) {
                    // busca la ciudad
                    itin = itinerario;
                    logger.info("buscando la ciudad con id "+ idciudad + " del itinerario con id" + id);
                    ArrayList<CiudadDTO> ciudades = itin.getCiudades();
                    for( int j = 0; j<ciudades.size() && ciudad == null;j++){
                        CiudadDTO ciudadActual = ciudades.get(j);
                        if(Objects.equals(ciudadActual.getId(), idciudad)){
                            ciudad = ciudadActual;
                            logger.info("buscado el evento con id " + idevento);
                            ArrayList<EventoDTO> eventos = ciudad.getEventos();
                            for( int k = 0; k<eventos.size() && evento == null;k++){
                                EventoDTO eventoActual = eventos.get(k);
                                if(Objects.equals(eventoActual.getId(), idevento)){
                                    logger.info("listando el evento con id " + idevento);
                                    return eventoActual;
                                }
                            }
                        }
                    }
                }
            }
        // no encontró el itinerario con ese id ?
        if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        else if(ciudad == null){
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
        // no encontró el evento con ese id ?
        else {
            logger.severe("No existe un evento con ese idevento en la ciudad con ese idciudad del itinerario con ese id");
            throw new EventoLogicException("No existe un evento con ese id");
        }


    }

    /**
     * Obtiene los itinerarios por identificación de viajero
     * @param id identificador del viajero
     * @return los itinerarios del viajero con identificador dado
     */
    public ArrayList<ItinerarioDTO> getItinerariosViajero(String id) {

        ArrayList<ItinerarioDTO> itinerariosresp = new ArrayList<>();

        for(ItinerarioDTO iti : itinerarios)
        {
        if(Objects.equals(iti.getViajero(), id)){

            itinerariosresp.add(iti);
        }
        }


        return itinerariosresp;
    }

    /**
     * Obtiene la ciudad con el idciudad dado, del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificadot de la ciudad
     * @return ciudad buscada
     * @throws ItinerarioLogicException si no existe itinerario
     * @throws CiudadLogicException  si no existe ciudad
     */
    public CiudadDTO getCiudad(Long id, Long idciudad) throws ItinerarioLogicException, CiudadLogicException {
        CiudadDTO resp = null;
        ItinerarioDTO itin = null;
        for (ItinerarioDTO itinerario : itinerarios) {
            if(Objects.equals(itinerario.getId(), id))
            {
            itin = itinerario;
                for (CiudadDTO ciudad : itinerario.getCiudades()) {
                    if(Objects.equals(ciudad.getId(), idciudad))
                    {
                        resp = ciudad;
                        return resp;
                    }
                }
            }
        }

       if(itin == null){
            logger.severe("No existe un itinerario con ese id");
            throw new ItinerarioLogicException("No existe un itinerario con ese id");
        }
        // no encontró la ciudad con ese id ?
        else if(resp == null){
            logger.severe("No existe una ciudad con ese idciudad en el itinerario con ese id");
            throw new CiudadLogicException("No existe una ciudad con ese id");
        }
    return resp;

    }


}
