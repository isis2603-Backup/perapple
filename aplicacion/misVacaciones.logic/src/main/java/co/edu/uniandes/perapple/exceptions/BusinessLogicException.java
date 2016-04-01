package co.edu.uniandes.perapple.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessLogicException extends Exception {

    public BusinessLogicException() {
    }

    /**
     * Constructor con un mensaje
     *
     * @param message mensaje de la excepción
     */
    public BusinessLogicException(String message) {
        super(message);
    }

    /**
     * Constructor con una causa
     *
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinessLogicException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor con mensaje y causa.
     *
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

}
