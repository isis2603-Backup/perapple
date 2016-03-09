package co.edu.uniandes.misVacaciones.rest.converters;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.uniandes.misVacaciones.rest.exceptions.ViajeroLogicException;

/**
 * Convertidor de Excepciones ViajeroLogicException a mensajes REST.
 */
@Provider
public class ViajeroLogicExceptionMapper implements ExceptionMapper<ViajeroLogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(ViajeroLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}

}