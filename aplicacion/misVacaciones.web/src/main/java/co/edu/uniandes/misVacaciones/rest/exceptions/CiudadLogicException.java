package co.edu.uniandes.misVacaciones.rest.exceptions;

/**
 * Representa las excepciones de la lógica de CityLogic
 */
public class CiudadLogicException extends Exception {

    /**
     * versión usada en la serialización de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public CiudadLogicException() {
        //Constructor por defecto
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public CiudadLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public CiudadLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public CiudadLogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
