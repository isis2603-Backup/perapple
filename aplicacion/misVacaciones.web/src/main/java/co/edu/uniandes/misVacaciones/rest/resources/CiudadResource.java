/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.misVacaciones.rest.resources;


import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;
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
     * Agrega una ciudad
     * @param ciudad ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CiudadLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public CiudadDTO fundarCiudad(CiudadDTO ciudad) throws CiudadLogicException {
        return ciudadLogic.fundarCiudad(ciudad);
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
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void arrazarCiudad(@PathParam("id") Long id) throws CiudadLogicException {
    	ciudadLogic.arrazarCiudad(id);
    }

}
