/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.misVacaciones.rest.resources;


import co.edu.uniandes.misVacaciones.rest.dtos.ViajeroDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.ViajeroLogicException;
import co.edu.uniandes.misVacaciones.rest.mocks.ViajeroLogicMock;
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
 * Clase que implementa el recurso REST correspondiente a "cities".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "cities".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/cities"
 *
 * @author Asistente
 */
@Path("viajeros")
@Produces("application/json")
@RequestScoped
public class ViajeroResource {

	@Inject
	ViajeroLogicMock viajeroLogic;

	/**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws CiudadLogicException excepción retornada por la lógica
	 */
    @GET
    public List<ViajeroDTO> getViajeros() throws ViajeroLogicException {
        return viajeroLogic.getViajeros();
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CiudadLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroDTO getViajero(@PathParam("id") Long id) throws ViajeroLogicException {
        return viajeroLogic.getViajero(id);
    }

    /**
     * Agrega una ciudad
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public ViajeroDTO createViajero(ViajeroDTO viajero) throws ViajeroLogicException {
        return viajeroLogic.createViajero(viajero);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajeroDTO updateViajero(@PathParam("id") Long id, ViajeroDTO viajero) throws ViajeroLogicException {
        return viajeroLogic.updateViajero(id, viajero);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViajero(@PathParam("id") Long id) throws ViajeroLogicException {
    	viajeroLogic.deleteViajero(id);
    }

}
