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
            ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","07/05/2016", " 08/05/2016" ));
            ciudades.add(new CiudadDTO(2L, "Cali", "Sucursal del cielo", "http://static.panoramio.com/photos/large/43907931.jpg", "15/06/2016", "17/06/2016"));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los paques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "18/07/2016", "19/07/2016"));
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
