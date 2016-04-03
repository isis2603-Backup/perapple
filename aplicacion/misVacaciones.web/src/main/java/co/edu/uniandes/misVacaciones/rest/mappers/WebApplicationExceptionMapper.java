package co.edu.uniandes.misVacaciones.rest.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de Excepciones BusinessLogicException a mensajes REST.
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    /**
     * Generador de una respuesta a partir de una excepción
     *
     * @param ex excecpión a convertir a una respuesta REST
     * @return Respuesta modificada
     */
    @Override
    public Response toResponse(WebApplicationException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(ex.getMessage()) // mensaje adicional
                .type("text/plain")
                .build();
    }
}
