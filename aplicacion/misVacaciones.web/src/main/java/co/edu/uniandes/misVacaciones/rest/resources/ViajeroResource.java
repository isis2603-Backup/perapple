/*
 * CityResource.java
 * Clase que representa el recurso "/viajeros"
 * Implementa varios métodos para manipular los viajeros
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
 * Clase que implementa el recurso REST correspondiente a "viajeros".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "viajeors".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/viajeros"
 *
 * @author Perapple
 */
@Path("viajeros")
@Produces("application/json")
@RequestScoped
public class ViajeroResource {

	@Inject
	ViajeroLogicMock viajeroLogic;

	/**
	 * Obtiene el listado de viajeros.
	 * @return lista de viajeros
	 * @throws CiudadLogicException excepción retornada por la lógica
	 */
    @GET
    public List<ViajeroDTO> getViajeros() throws ViajeroLogicException {
        return viajeroLogic.getViajeros();
    }

    /**
     * Obtiene un viajero
     * @param id identificador del viajero
     * @return viajero encontrado
     * @throws ViajeroLogicException cuando el viajero no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ViajeroDTO getViajero(@PathParam("id") Long id) throws ViajeroLogicException {
        return viajeroLogic.getViajero(id);
    }

    /**
     * Agrega un viajero
     * @param viajero viajero a agregar
     * @return datos del viajero a agregar
     * @throws ViajeroLogicException cuando ya existe un viajero con el id suministrado
     */
    @POST
    public ViajeroDTO createViajero(ViajeroDTO viajero) throws ViajeroLogicException {
        return viajeroLogic.createViajero(viajero);
    }

    /**
     * Actualiza los datos de un viajero
     * @param id identificador del viajero a modificar
     * @param viajero viajero a modificar
     * @return datos del viajero modificado
     * @throws ViajeroLogicException cuando no existe un viajero con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ViajeroDTO updateViajero(@PathParam("id") Long id, ViajeroDTO viajero) throws ViajeroLogicException {
        return viajeroLogic.updateViajero(id, viajero);
    }

    /**
     * Elimina los datos de un viajero
     * @param id identificador del viajero a eliminar
     * @throws ViajeroLogicException cuando no existe un viajero con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteViajero(@PathParam("id") Long id) throws ViajeroLogicException {
    	viajeroLogic.deleteViajero(id);
    }

}
