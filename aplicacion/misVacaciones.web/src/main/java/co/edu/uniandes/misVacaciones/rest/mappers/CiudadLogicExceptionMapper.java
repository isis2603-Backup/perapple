package co.edu.uniandes.misVacaciones.rest.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.uniandes.misVacaciones.rest.exceptions.CiudadLogicException;

/**
 * Convertidor de Excepciones CityLogicException a mensajes REST.
 */
@Provider
public class CiudadLogicExceptionMapper implements ExceptionMapper<CiudadLogicException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param ex excecpión a convertir a una respuesta REST
     */
    @Override
    public Response toResponse(CiudadLogicException ex) {
        // retorna una respuesta
        return Response
                .status(Response.Status.NOT_FOUND) // estado HTTP 404
                .entity(ex.getMessage()) // mensaje adicional
                .type("text/plain")
                .build();
    }

}
