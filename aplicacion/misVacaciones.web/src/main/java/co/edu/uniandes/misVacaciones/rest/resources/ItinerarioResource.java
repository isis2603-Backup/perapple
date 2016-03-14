/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.misVacaciones.rest.resources;


import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.EventoLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.ItinerarioLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.SitioLogicException;
import co.edu.uniandes.misVacaciones.rest.mocks.ItinerarioLogicMock;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "itinerarios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "itinerarios".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/cities"
 *
 * @author Perapple
 */
@Path("itinerarios")
@Produces("application/json")
@RequestScoped
public class ItinerarioResource {

	@Inject
	ItinerarioLogicMock itinerarioLogic;

      /**
       * El itinerario que actualmente se este manejando
       * @return el itinerario que actualmente se esta manejando
       * @throws ItinerarioLogicException
       */

      @GET
      @Path("current")
    public ItinerarioDTO getCurrentItinerario() throws ItinerarioLogicException {
        return itinerarioLogic.getCurrentItinerario();
    }

    @PUT
    @Path("current")
    public ItinerarioDTO setCurrentItinerario(ItinerarioDTO nuevoCurrent){
        return itinerarioLogic.setCurrentItinerario(nuevoCurrent);
    }

	/**
	 * Obtiene el listado de Itinerarios.
	 * @return lista de itinerarios
	 * @throws ItinerarioLogicException excepción retornada por la lógica
	 */
    @GET
    public List<ItinerarioDTO> getItinerarios() throws ItinerarioLogicException {
        return itinerarioLogic.getItinerarios();
    }

    /**
     * Obtiene un itinerario
     * @param id identificador del itinerario
     * @return itinerario encontrado
     * @throws ItinerarioLogicException cuando el itineraio no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") Long id) throws ItinerarioLogicException {
        return itinerarioLogic.getItinerario(id);
    }

    /**
     * Agrega un itinerario
     * @param itinerario itinerario a agregar
     * @return datos del itinerario a agregar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) throws ItinerarioLogicException {
        return itinerarioLogic.createItinerario(itinerario);
    }

    /**
     * Actualiza los datos de un itineario
     * @param id identificador del itinerario a modificar
     * @param itinerario itinerario a modificar
     * @return datos del itinerario a modificada
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerario) throws ItinerarioLogicException {
        return itinerarioLogic.updateItinerario(id, itinerario);
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioLogicException {
    	itinerarioLogic.deleteItinerario(id);
    }

    /**
     * Crea ciudad en el itinerario con id dado
     * @param id identificador del itinerario a agregar ciudad
     * @param ciudad dto de ciudad a agregar
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.ItinerarioLogicException
     */
    @POST
    @Path("{id: \\d+}/ciudades")
    public void createCiudad(@PathParam("id")Long id, CiudadDTO ciudad) throws ItinerarioLogicException
    {
        itinerarioLogic.createCiudad(id, ciudad);
    }
    /**
     * Actualiza los datos de una ciudad de un itineario
     * @param id identificador del itinerario a modificar
     * @param ciudad datos modificados de la ciudad
     * @param idciudad identificadot de la ciudad
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}")
    public void updateCiudades(@PathParam("id") Long id, CiudadDTO ciudad, @PathParam("idciudad") Long idciudad) throws ItinerarioLogicException, CiudadLogicException {

        itinerarioLogic.updateCiudad(id, ciudad, idciudad);
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @param idciudad
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id,@PathParam("idciudad") Long idciudad) throws ItinerarioLogicException, CiudadLogicException {
    	itinerarioLogic.deleteCiudad(id, idciudad);
    }

    /**
     * Obtiene las ciudades del itinerario
     * @param id identificador del itinerario
     * @return lista de ciudades del itinerario
     * @throws ItinerarioLogicException cuando el itineraio no existe
     */
    @GET
    @Path("{id: \\d+}/ciudades")
    public List<CiudadDTO> getCiudades(@PathParam("id") Long id) throws ItinerarioLogicException {
        return itinerarioLogic.getCiudades(id);
    }

    /**
     * Agrega un sitio de interes en una ciudad con el id dado del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param sitio el sitio a agregar
     * @return el sitio que agregó
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException
     */
    @POST
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios")
    public SitioDTO createSitioInteres(@PathParam("id")Long id, @PathParam("idciudad") Long idciudad, SitioDTO sitio) throws ItinerarioLogicException, CiudadLogicException
    {
        return itinerarioLogic.createSitioDeInteres(id, idciudad, sitio);
    }

     /**
     * Elimina los datos de un sitio de interes en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idsitio identificador del sitio a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws SitioLogicException cuando no existe un sitio con el id sumunistrado
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios/{idsitio: \\d+}")
    public void deleteSitioDeInteres(@PathParam("id") Long id,@PathParam("idciudad") Long idciudad, @PathParam("idsitio") Long idsitio) throws ItinerarioLogicException, CiudadLogicException, SitioLogicException {
    	itinerarioLogic.deleteSitioDeInteres(id, idciudad, idsitio);
    }

     /**
     * Obtiene el listado de sitios de una ciudad en un itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @return
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios")
    public ArrayList<SitioDTO> fetchSitiosInteres(@PathParam("id")Long id, @PathParam("idciudad") Long idciudad) throws ItinerarioLogicException, CiudadLogicException
    {
        return itinerarioLogic.fetchSitiosDeInteres(id, idciudad);
    }

         /**
     * Obtiene los datos de un sitio de interes en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idsitio identificador del sitio a buscar
     * @return
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws SitioLogicException cuando no existe un sitio con el id sumunistrado
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios/{idsitio: \\d+}")
    public SitioDTO fetchSitioDeInteres(@PathParam("id") Long id,@PathParam("idciudad") Long idciudad, @PathParam("idsitio") Long idsitio) throws ItinerarioLogicException, CiudadLogicException, SitioLogicException {
    	return itinerarioLogic.fetchSitioDeInteres(id, idciudad, idsitio);
    }

     /**
     * Agrega un evento en una ciudad con el id dado del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param evento el evento a agregar
     * @return el evento que agregó
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException
     */
    @POST
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos")
    public EventoDTO createEvento(@PathParam("id")Long id, @PathParam("idciudad") Long idciudad, EventoDTO evento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException
    {
        return itinerarioLogic.createEvento(id, idciudad, evento);
    }

     /**
     * Elimina los datos de un evento en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idevento identificador del evento a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws EventoLogicException cuando no existe un evento con el id sumunistrado
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos/{idevento: \\d+}")
    public void deleteEvento(@PathParam("id") Long id,@PathParam("idciudad") Long idciudad, @PathParam("idevento") Long idevento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException, SitioLogicException {
    	itinerarioLogic.deleteEvento(id, idciudad, idevento);
    }

     /**
     * Obtiene el listado de eventos de una ciudad en un itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @return lista de eventos de la ciudad con el id dado del itinerario con el id dado
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos")
    public ArrayList<EventoDTO> fetchEventos(@PathParam("id")Long id, @PathParam("idciudad") Long idciudad) throws ItinerarioLogicException, CiudadLogicException
    {
        return itinerarioLogic.fetchEventos(id, idciudad);
    }

    /**
     * Obtiene los datos de un evento en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idevento identificador del evento a buscar
     * @return evento buscado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws EventoLogicException cuando no existe un sitio con el id sumunistrado
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos/{idevento: \\d+}")
    public EventoDTO fetchEvento(@PathParam("id") Long id,@PathParam("idciudad") Long idciudad, @PathParam("idevento") Long idevento) throws ItinerarioLogicException, CiudadLogicException, EventoLogicException {
    	return itinerarioLogic.fetchEvento(id, idciudad, idevento);
    }

    /**
     * Retorna la lista de itinerarios asociados a un viajero particular
     * @param id del viajero del que se quieren obtener sus itienrarios
     * @return lista de itinerarios
     */
    @GET
    @Path("viajero/{idviajero: }")
    public ArrayList<ItinerarioDTO> getItinerariosViajero(@PathParam("idviajero") String id){
    	return itinerarioLogic.getItinerariosViajero(id);
    }
}
