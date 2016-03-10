/*
 * CiudadResource.java
 * Clase que representa el recurso "/ciudades"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.misVacaciones.rest.resources;


import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.EventoLogicException;
import co.edu.uniandes.misVacaciones.rest.exceptions.SitioLogicException;
import co.edu.uniandes.misVacaciones.rest.mocks.CiudadLogicMock;
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
 * Clase que implementa el recurso REST correspondiente a "ciudades".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "ciudades".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/ciudades"
 *
 * @author Perapple
 */
@Path("ciudades")
@Produces("application/json")
@RequestScoped
public class CiudadResource {

	@Inject
	CiudadLogicMock ciudadLogic;

	/**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws CiudadLogicException excepción retornada por la lógica
	 */
    @GET
    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
        return ciudadLogic.getCiudades();
    }

    /**
	 * Obtiene el listado de sitios de una ciudad.
         * @param idCiudad id de la ciudad en la que se quieren consultar los sitios
	 * @return lista de sitios de una ciudad
	 * @throws CiudadLogicException excepción retornada por la lógica
	 */

    @GET
     @Path("{id: \\d+}/sitios")
    public List<SitioDTO> getSitios(@PathParam("id") Long idCiudad) throws CiudadLogicException
    {
        return ciudadLogic.getSitios(idCiudad);
    }

     /**
	 * Obtiene el listado de eventos de una ciudad.
         * @param idCiudad id de la ciudad en la que se quieren consultar los eventos
	 * @return lista de eventos de una ciudad
	 * @throws CiudadLogicException excepción retornada por la lógica
	 */

    @GET
     @Path("{id: \\d+}/eventos")
    public List<EventoDTO> getEventos(@PathParam("id")Long idCiudad) throws CiudadLogicException
    {
        return ciudadLogic.getEventos(idCiudad);
    }


    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO geCiudad(@PathParam("id") Long id) throws CiudadLogicException {
        return ciudadLogic.getCiudad(id);
    }

    /**
     * Obtiene un  sitio
     * @param id identificador de la ciudad
     * @param idSitio identficador del sitio
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     * @throws SitioLogicException cuando el sitio no existe
     */
    @GET
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public SitioDTO getSitio(@PathParam("id") Long id, @PathParam("idSitio")Long idSitio) throws CiudadLogicException,SitioLogicException {
        return ciudadLogic.getSitio(idSitio, id);
    }

    /**
     * Obtiene un  sitio
     * @param id identificador de la ciudad
     * @param idEvento identficador del evento
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     * @throws EventoLogicException cuando el evento no existe
     */
    @GET
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public EventoDTO getEvento(@PathParam("id") Long id, @PathParam("idEvento")Long idEvento) throws CiudadLogicException,EventoLogicException {
        return ciudadLogic.getEvento(idEvento, id);
    }


     /**
     * Agrega una ciudad
     * @param ciudad ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CiudadDTO agregarCiudad(CiudadDTO ciudad) throws CiudadLogicException {
        return ciudadLogic.fundarCiudad(ciudad);
    }

     /**
     * Agrega un sitio a una ciudad
     * @param idCiudad id de la ciudad donde se agrega el sitio
     * @param sitio sitio que se agrega a la ciudad
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
     @Path("{id: \\d+}/sitios")
    public void agregarSitio(@PathParam("id")Long idCiudad, SitioDTO sitio) throws CiudadLogicException {
        ciudadLogic.crearSitioEnCiudad(idCiudad, sitio);
    }

     /**
     * Agrega un sitio a una ciudad
     * @param idCiudad id de la ciudad donde se agrega el evento
     * @param evento evento que se agrega a la ciudad
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
     @Path("{id: \\d+}/eventos")
    public void agregarEvento(@PathParam("id")Long idCiudad, EventoDTO evento) throws CiudadLogicException {
        ciudadLogic.crearEventoEnCiudad(idCiudad, evento);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param ciudad ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO actualizarCiudad(@PathParam("id") Long id, CiudadDTO ciudad) throws CiudadLogicException {
        return ciudadLogic.actualizarCiudad(id, ciudad);
    }

       /**
     * Actualiza los datos de un sitio
     * @param id identificador de la ciudad donde se encuentra el sitio
     * @param sitio sitio a modificar
     * @param idSitio id del sitio a modificar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws SitioLogicException cuando no existe un sitio con el idSitio suministrado
     */
    @PUT
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public void actualizarSitio(@PathParam("id") Long id, SitioDTO sitio, @PathParam("idSitio") Long idSitio) throws SitioLogicException, CiudadLogicException {
       ciudadLogic.actualizarSitio(id, sitio, idSitio);
    }

       /**
     * Actualiza los datos de un sitio
     * @param id identificador de la ciudad donde se encuentra el sitio
     * @param evento evento a modificar
     * @param idEvento id del evento a modificar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws EventoLogicException cuando no existe un evento con el idEvento suministrado
     */
    @PUT
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public void actualizarEvento(@PathParam("id") Long id, EventoDTO evento, @PathParam("idSitio") Long idEvento) throws EventoLogicException, CiudadLogicException {
       ciudadLogic.actualizarEvento(id, evento, idEvento);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarCiudad(@PathParam("id") Long id) throws CiudadLogicException {
    	ciudadLogic.arrazarCiudad(id);
    }

    /**
     * Elimina los datos de un sitio
     * @param idCiudad identificador de la ciudad donde se encuentra el sitio a eliminar
     * @param idSitio identificador del sitio a eliminar
     * @throws SitioLogicException cuando no existe un sitio con el idSitio suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el idCiudad suministrado
     */
    @DELETE
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public void borrarSitio(@PathParam("id") Long idCiudad,@PathParam("idSitio") Long idSitio) throws SitioLogicException, CiudadLogicException {
    	ciudadLogic.borrarSitio(idCiudad, idSitio);
    }

    /**
     * Elimina los datos de un sitio
     * @param idCiudad identificador de la ciudad donde se encuentra el evento a eliminar
     * @param idEvento identificador del evento a eliminar
     * @throws EventoLogicException cuando no existe un evento con el idEvento suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el idCiudad suministrado
     */
    @DELETE
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public void borrarEvento(@PathParam("id") Long idCiudad,@PathParam("idEvento") Long idEvento) throws EventoLogicException, CiudadLogicException {
    	ciudadLogic.borrarEvento(idCiudad, idEvento);
    }
    

}
