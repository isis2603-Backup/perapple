
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
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.ItinerarioLogicException;

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

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ItinerarioLogicMock() {

    	if (itinerarios == null) {
            itinerarios = new ArrayList<>();

            itinerarios.add(new ItinerarioDTO(1L, "Verano 2016", "perapple", "12-05-2016","13-06-2016"));

            ArrayList<CiudadDTO> ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","07/05/2016", " 08/05/2016" ));
            ciudades.add(new CiudadDTO(2L, "Cali", "Sucursal del cielo", "http://static.panoramio.com/photos/large/43907931.jpg", "15/06/2016", "17/06/2016"));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los paques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "18/07/2016", "19/07/2016"));

            itinerarios.get(0).setCiudades(ciudades);

            itinerarios.add(new ItinerarioDTO(2L, "Invierno 2016", "perapple","12-07-2016","13-08-2016"));

            ciudades = new ArrayList<>();
             ciudades.add(new CiudadDTO(1L, "Bogota", "Bogotá es la capital de la República de Colombia", "http://aiesec.org.mx/wp-content/uploads/2015/08/bogota.jpg","07/05/2016", " 08/05/2016" ));
            ciudades.add(new CiudadDTO(2L, "Cali", "Sucursal del cielo", "http://static.panoramio.com/photos/large/43907931.jpg", "15/06/2016", "17/06/2016"));
            ciudades.add(new CiudadDTO(3L, "Bucaramanga", "Ciudad de los paques", "https://c1.staticflickr.com/3/2724/4176942891_3f6d1f1dcf_b.jpg", "18/07/2016", "19/07/2016"));
            itinerarios.get(1).setCiudades(ciudades);

        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de itinerarios");
    	logger.info("itinerarios" + itinerarios );
    }

//
	 //* Obtiene el listado de itinerarios.
	 //* @return lista de itienrarios
	// * @throws ItinerarioLogicException cuando no existe la lista en memoria
//
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
               for(CiudadDTO ciudadvieja : itinerario.getCiudades()){
                if(Objects.equals(ciudadvieja.getId(), idciudad)){
                    existeCiudad = true;
                    ciudadvieja =  ciudad;
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
        for (ItinerarioDTO itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {

            	// eliminando la ciudad
                itin = itinerario;
            	logger.info("eliminando la ciudad con id"+ idciudad + " del itinerario con id" + id);


            }
        }

        if(itin != null){
         for(CiudadDTO ciudad : itin.getCiudades()){
                if(Objects.equals(ciudad.getId(), idciudad)){
                    existeCiudad = true;
                   itin.getCiudades().remove(ciudad);
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
}
