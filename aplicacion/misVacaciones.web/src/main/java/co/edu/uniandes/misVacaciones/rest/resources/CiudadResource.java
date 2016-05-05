/*
 * CiudadResource.java
 * Clase que representa el recurso "/ciudades"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.misVacaciones.rest.resources;

import co.edu.uniandes.misVacaciones.rest.converters.EventoConverter;
import co.edu.uniandes.misVacaciones.rest.converters.SitioConverter;
import co.edu.uniandes.misVacaciones.rest.converters.CiudadConverter;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.perapple.api.ICiudadLogic;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.EventoEntity;
import co.edu.uniandes.perapple.entities.SitioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que implementa el recurso REST correspondiente a "ciudades".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "ciudades". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/ciudades"
 *
 * @author Perapple
 */
@Path("ciudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CiudadResource {

    private static final Logger LOGGER = Logger.getLogger(CiudadResource.class.getName());

    @Inject
    ICiudadLogic ciudadLogic;

    /**
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     */
    @GET
    public List<CiudadDTO> getCiudades() {
        return CiudadConverter.listEntity2DTO(ciudadLogic.getCiudades());
    }

    /**
     * Obtiene el listado de sitios de una ciudad.
     *
     * @param idCiudad id de la ciudad en la que se quieren consultar los sitios
     * @return lista de sitios de una ciudad
     */
    @GET
    @Path("{id: \\d+}/sitios")
    public List<SitioDTO> getSitios(@PathParam("id") int idCiudad) {
        try {
            return SitioConverter.listEntity2DTO(ciudadLogic.getSitios(idCiudad));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene el listado de eventos de una ciudad.
     *
     * @param idCiudad id de la ciudad en la que se quieren consultar los
     * eventos
     * @return lista de eventos de una ciudad
     */
    @GET
    @Path("{id: \\d+}/eventos")
    public List<EventoDTO> getEventos(@PathParam("id") int idCiudad) {
        try {
            return EventoConverter.listEntity2DTO(ciudadLogic.getEventos(idCiudad));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene una ciudad
     *
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     */
    @GET
    @Path("{id: \\d+}")
    public CiudadDTO geCiudad(@PathParam("id") int id) {
        try {
            return CiudadConverter.fullEntity2DTO(ciudadLogic.getCiudad(id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene un sitio
     *
     * @param id identificador de la ciudad
     * @param idSitio identficador del sitio
     * @return ciudad encontrada
     */
    @GET
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public SitioDTO getSitio(@PathParam("id") int id, @PathParam("idSitio") int idSitio) {
        try {
            return SitioConverter.fullEntity2DTO(ciudadLogic.getSitio(idSitio, id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene un sitio
     *
     * @param id identificador de la ciudad
     * @param idEvento identficador del evento
     * @return ciudad encontrada
     */
    @GET
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public EventoDTO getEvento(@PathParam("id") int id, @PathParam("idEvento") int idEvento) {
        try {
            return EventoConverter.fullEntity2DTO(ciudadLogic.getEvento(idEvento, id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega una ciudad
     *
     * @param ciudad ciudad a agregar
     * @return datos de la ciudad a agregar
     */
    @POST
    //@StatusCreated
    public CiudadDTO agregarCiudad(CiudadDTO ciudad) {
        CiudadEntity entity = CiudadConverter.fullDTO2Entity(ciudad);
        try {
            return CiudadConverter.fullEntity2DTO(ciudadLogic.createCiudad(entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un sitio a una ciudad
     *
     * @param idCiudad id de la ciudad donde se agrega el sitio
     * @param sitio sitio que se agrega a la ciudad
     * @return datos del sitio agregado
     */
    @POST
    @Path("{id: \\d+}/sitios")
    public SitioDTO agregarSitio(@PathParam("id") int idCiudad, SitioDTO sitio) {
        SitioEntity entity = SitioConverter.fullDTO2Entity(sitio);
        try {
            return SitioConverter.fullEntity2DTO(ciudadLogic.addSitio(idCiudad, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un evento a una ciudad
     *
     * @param idCiudad id de la ciudad donde se agrega el evento
     * @param evento evento que se agrega a la ciudad
     * @return datos del evento agregado
     */
    @POST
    @Path("{id: \\d+}/eventos")
    public EventoDTO agregarEvento(@PathParam("id") int idCiudad, EventoDTO evento) {
        EventoEntity entity = EventoConverter.fullDTO2Entity(evento);
        try {
            return EventoConverter.fullEntity2DTO(ciudadLogic.addEvento(idCiudad, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Actualiza los datos de una ciudad
     *
     * @param id identificador de la ciudad a modificar
     * @param ciudad ciudad a modificar
     * @return datos de la ciudad modificadao
     */
    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO actualizarCiudad(@PathParam("id") int id, CiudadDTO ciudad) {
        CiudadEntity entity = CiudadConverter.fullDTO2Entity(ciudad);
        entity.setId(id);
        try {
            CiudadEntity oldEntity = ciudadLogic.getCiudad(id);
            // TODO
            //entity.setEventos(oldEntity.getEventos());
            //entity.setSitios(oldEntity.getSitios());
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, "La ciudad no existe", ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        try {
            return CiudadConverter.fullEntity2DTO(ciudadLogic.updateCiudad(entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Actualiza los datos de un sitio
     *
     * @param id identificador de la ciudad donde se encuentra el sitio
     * @param sitio sitio a modificar
     * @param idSitio id del sitio a modificar
     * @return el sitio modificado
     */
    @PUT
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public SitioDTO actualizarSitio(@PathParam("id") int id, @PathParam("idSitio") int idSitio, SitioDTO sitio) {
        SitioEntity entity = SitioConverter.fullDTO2Entity(sitio);
        try {
            return SitioConverter.fullEntity2DTO(ciudadLogic.updateSitio(id, idSitio, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Actualiza los datos de un sitio
     *
     * @param id identificador de la ciudad donde se encuentra el sitio
     * @param evento evento a modificar
     * @param idEvento id del evento a modificar
     * @return el evento modificado
     */
    @PUT
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public EventoDTO actualizarEvento(@PathParam("id") int id, @PathParam("idEvento") int idEvento, EventoDTO evento) {
        EventoEntity entity = EventoConverter.fullDTO2Entity(evento);
        try {
            return EventoConverter.fullEntity2DTO(ciudadLogic.updateEvento(id, idEvento, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Elimina los datos de una ciudad
     *
     * @param id identificador de la ciudad a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarCiudad(@PathParam("id") int id) {
        try {
            ciudadLogic.deleteCiudad(id);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Elimina los datos de un sitio
     *
     * @param idCiudad identificador de la ciudad donde se encuentra el sitio a
     * eliminar
     * @param idSitio identificador del sitio a eliminar
     */
    @DELETE
    @Path("{id: \\d+}/sitios/{idSitio: \\d+}")
    public void borrarSitio(@PathParam("id") int idCiudad, @PathParam("idSitio") int idSitio) {
        try {
            ciudadLogic.removeSitio(idCiudad, idSitio);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Elimina los datos de un sitio
     *
     * @param idCiudad identificador de la ciudad donde se encuentra el evento a
     * eliminar
     * @param idEvento identificador del evento a eliminar
     */
    @DELETE
    @Path("{id: \\d+}/eventos/{idEvento: \\d+}")
    public void borrarEvento(@PathParam("id") int idCiudad, @PathParam("idEvento") int idEvento) {
        try {
            ciudadLogic.removeEvento(idCiudad, idEvento);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

}
