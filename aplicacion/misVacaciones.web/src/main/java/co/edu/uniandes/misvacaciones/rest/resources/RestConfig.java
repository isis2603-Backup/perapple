/*
 * RestConfig.java
 * Configura el servicio JAX-RS.
 */
package co.edu.uniandes.misvacaciones.rest.resources;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 * Clase que indica que este proyecto web ofrece servicios REST.
 * Adicionalmente, esta clase define el prefijo por defecto de las rutas a los recursos.
 *
 * (non-Javadoc)
 * @see javax.ws.rs.core.Application
 */
@ApplicationPath("/api")
public class RestConfig extends Application { }