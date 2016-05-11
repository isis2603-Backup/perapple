/*
 * ItinerarioResource.java
 * Clase que representa el recurso "/viajeros"
 * Implementa varios métodos para manipular los itinerarios
 */
package co.edu.uniandes.misvacaciones.rest.resources;


import co.edu.uniandes.misvacaciones.rest.converters.EventoItinerarioConverter;
import co.edu.uniandes.misvacaciones.rest.converters.SitioItinerarioConverter;
import co.edu.uniandes.misvacaciones.rest.converters.CiudadItinerarioConverter;
import co.edu.uniandes.misvacaciones.rest.converters.ItinerarioConverter;
import co.edu.uniandes.misvacaciones.rest.dtos.CiudadItinerarioDTO;
import co.edu.uniandes.misvacaciones.rest.dtos.EventoItinerarioDTO;
import co.edu.uniandes.misvacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misvacaciones.rest.dtos.SitioItinerarioDTO;
import co.edu.uniandes.misvacaciones.rest.exceptions.CiudadLogicException;
import co.edu.uniandes.misvacaciones.rest.exceptions.EventoLogicException;
import co.edu.uniandes.misvacaciones.rest.exceptions.ItinerarioLogicException;
import co.edu.uniandes.misvacaciones.rest.exceptions.SitioLogicException;
import co.edu.uniandes.perapple.api.IItinerarioLogic;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
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
 * Clase que implementa el recurso REST correspondiente a "itinerarios".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta
 * "/api" y este recurso tiene la ruta "itinerarios".
 * Al ejecutar la aplicación, el recurse será accesibe a través de la
 * ruta "/api/cities"
 *
 * @author Perapple
 */
@Path("itinerarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItinerarioResource {

    private static final Logger LOGGER = Logger.getLogger(CiudadResource.class.getName());

    @Inject
    IItinerarioLogic itinerarioLogic;

    /**
    * El itinerario que actualmente se este manejando
    * @param idViajero
    * @return el itinerario que actualmente se esta manejando
    * @throws ItinerarioLogicException
    */
    @GET
    @Path("current/{idViajero: \\d+}")
    public ItinerarioDTO getCurrentItinerario(@PathParam("idViajero") int idViajero){
        try {
            return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.getCurrentItinerario(idViajero));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Path("current/{idViajero: \\d+}")
    public ItinerarioDTO setCurrentItinerario(ItinerarioDTO nuevoCurrent, @PathParam("idViajero") int idViajero){
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(nuevoCurrent);
        try {
            return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.setCurrentItinerario(idViajero, entity.getId()));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

   /**
    * Obtiene el listado de Itinerarios.
    * @return lista de itinerarios
    * @throws ItinerarioLogicException excepción retornada por la lógica
    */
    @GET
    public List<ItinerarioDTO> getItinerarios() {
        return ItinerarioConverter.listEntity2DTO(itinerarioLogic.getItinerarios());
    }

    /**
     * Obtiene un itinerario
     * @param id identificador del itinerario
     * @return itinerario encontrado
     * @throws ItinerarioLogicException cuando el itineraio no existe
     */
    @GET
    @Path("{id: \\d+}")
    public ItinerarioDTO getItinerario(@PathParam("id") int id) {
        try {
            return ItinerarioConverter.fullEntity2DTO(itinerarioLogic.getItinerario(id));
        } catch (Exception ex){
            LOGGER.log(Level.SEVERE, "La ciudad no existe", ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un itinerario
     * @param itinerario itinerario a agregar
     * @return datos del itinerario a agregar
     * @throws ItinerarioLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) {

        LOGGER.info("itinerario a adicionar: " + itinerario.getNombre() + "," + itinerario.getFechaInicio() + "," + itinerario.getFechaFin() + "," + itinerario.getViajero().toString());
        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        ItinerarioDTO iter = null;
        try {
            iter = ItinerarioConverter.fullEntity2DTO(itinerarioLogic.createItinerario(entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
        return iter;
    }

    /**
     * Actualiza los datos de un itineario
     * @param id identificador del itinerario a modificar
     * @param itinerario itinerario a modificar
     * @return datos del itinerario a modificada
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") int id, ItinerarioDTO itinerario) {

        ItinerarioEntity entity = ItinerarioConverter.fullDTO2Entity(itinerario);
        entity.setId(id);

        try {
             itinerarioLogic.getItinerario(id);
            } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "El itinerario no existe", ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }

        ItinerarioDTO dto = null;

        try {
            dto = ItinerarioConverter.fullEntity2DTO(itinerarioLogic.updateItinerario(entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }

        return dto;
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") int id) {
        try {
            itinerarioLogic.deleteItinerario(id);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Crea ciudad en el itinerario con id dado
     * @param id identificador del itinerario a agregar ciudad
     * @param ciudad dto de ciudad a agregar
     */
    @POST
    @Path("{id: \\d+}/ciudades")
    public CiudadItinerarioDTO createCiudad(@PathParam("id")int id, CiudadItinerarioDTO ciudad) {
        CiudadItinerarioEntity entity = CiudadItinerarioConverter.fullDTO2Entity(ciudad);
        try {
            return CiudadItinerarioConverter.fullEntity2DTO(itinerarioLogic.addCiudad(entity, id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }
    /**
     * Actualiza los datos de una ciudad de un itineario
     * @param id identificador del itinerario a modificar
     * @param ciudad datos modificados de la ciudad
     * @param idciudad identificadot de la ciudad
     * @return CiudadItinerario modificada
     */
    @PUT
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}")
    public CiudadItinerarioDTO updateCiudades(@PathParam("id") int id, CiudadItinerarioDTO ciudad, @PathParam("idciudad") int idciudad) {
        CiudadItinerarioEntity entity = CiudadItinerarioConverter.fullDTO2Entity(ciudad);
        LOGGER.log(Level.INFO, "Modificando ciudadItinerario con id: {0}", idciudad);
        try {
            return CiudadItinerarioConverter.fullEntity2DTO(itinerarioLogic.updateCiudad(entity, id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Elimina los datos de un itinerario
     * @param id identificador del itinerario a eliminar
     * @param idciudad
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}")
    public void deleteCiudad(@PathParam("id") int id,@PathParam("idciudad") int idciudad) {
        try {
            itinerarioLogic.deleteCiudad(idciudad,id);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene las ciudades del itinerario
     * @param id identificador del itinerario
     * @return lista de ciudades del itinerario
     * @throws ItinerarioLogicException cuando el itineraio no existe
     */
    @GET
    @Path("{id: \\d+}/ciudades")
    public List<CiudadItinerarioDTO> getCiudades(@PathParam("id") int id) {
        try {
            return CiudadItinerarioConverter.listEntity2DTO(itinerarioLogic.getCiudades(id));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene la ciudad con el idciudad dado, del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificadot de la ciudad
     * @return ciudad buscada
     * @throws ItinerarioLogicException si no existe itinerario
     * @throws CiudadLogicException  si no existe ciudad
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad:\\d+}")
    public CiudadItinerarioDTO getCiudad(@PathParam("id") int id, @PathParam("idciudad") int idciudad) {
        try {
            return CiudadItinerarioConverter.fullEntity2DTO(itinerarioLogic.getCiudad(id, idciudad));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Agrega un sitio de interes en una ciudad con el id dado del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param sitio el sitio a agregar
     * @return el sitio que agregó
     */
    @POST
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios")
    public SitioItinerarioDTO createSitioInteres(@PathParam("id")int id, @PathParam("idciudad") int idciudad, SitioItinerarioDTO sitio) {
        SitioItinerarioEntity entity = SitioItinerarioConverter.fullDTO2Entity(sitio);
        try {
            return SitioItinerarioConverter.fullEntity2DTO(itinerarioLogic.createSitio(id, idciudad, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

     /**
     * Elimina los datos de un sitio de interes en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idsitio identificador del sitio a eliminar
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios/{idsitio: \\d+}")
    public void deleteSitioDeInteres(@PathParam("id") int id,@PathParam("idciudad") int idciudad, @PathParam("idsitio") int idsitio) {
        try {
            itinerarioLogic.deleteSitio(id, idciudad, idsitio);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

     /**
     * Obtiene el listado de sitios de una ciudad en un itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @return
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misvacaciones.rest.exceptions.CiudadLogicException
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios")
    public List<SitioItinerarioDTO> fetchSitiosInteres(@PathParam("id")int id, @PathParam("idciudad") int idciudad) {
        try {
            return SitioItinerarioConverter.listEntity2DTO(itinerarioLogic.getSitios(id, idciudad));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

         /**
     * Obtiene los datos de un sitio de interes en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idsitio identificador del sitio a buscar
     * @return
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws SitioLogicException cuando no existe un sitio con el id sumunistrado
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/sitios/{idsitio: \\d+}")
    public SitioItinerarioDTO fetchSitioDeInteres(@PathParam("id") int id,@PathParam("idciudad") int idciudad, @PathParam("idsitio") int idsitio) {
        try {
            return SitioItinerarioConverter.fullEntity2DTO(itinerarioLogic.getSitio(id, idciudad, idsitio));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

     /**
     * Agrega un evento en una ciudad con el id dado del itinerario con id dado
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @param evento el evento a agregar
     * @return el evento que agregó
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misvacaciones.rest.exceptions.CiudadLogicException
     */
    @POST
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos")
    public EventoItinerarioDTO createEvento(@PathParam("id")int id, @PathParam("idciudad") int idciudad, EventoItinerarioDTO evento){
        EventoItinerarioEntity entity = EventoItinerarioConverter.fullDTO2Entity(evento);
        try {
            return EventoItinerarioConverter.fullEntity2DTO(itinerarioLogic.createEvento(id, idciudad, entity));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

     /**
     * Elimina los datos de un evento en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idevento identificador del evento a eliminar
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws EventoLogicException cuando no existe un evento con el id sumunistrado
     */
    @DELETE
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos/{idevento: \\d+}")
    public void deleteEvento(@PathParam("id") int id,@PathParam("idciudad") int idciudad, @PathParam("idevento") int idevento) {
        try {
            itinerarioLogic.deleteEvento(id, idciudad, idevento);
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

     /**
     * Obtiene el listado de eventos de una ciudad en un itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad
     * @return lista de eventos de la ciudad con el id dado del itinerario con el id dado
     * @throws ItinerarioLogicException cuando no existe -----
     * @throws co.edu.uniandes.misvacaciones.rest.exceptions.CiudadLogicException
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos")
    public List<EventoItinerarioDTO> fetchEventos(@PathParam("id")int id, @PathParam("idciudad") int idciudad) {
        try {
            return EventoItinerarioConverter.listEntity2DTO(itinerarioLogic.getEventos(id, idciudad));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Obtiene los datos de un evento en una ciudad del itinerario
     * @param id identificador del itinerario
     * @param idciudad identificador de la ciudad en el itinerario
     * @param idevento identificador del evento a buscar
     * @return evento buscado
     * @throws ItinerarioLogicException cuando no existe un itinerario con el id suministrado
     * @throws CiudadLogicException cuando no existe una ciudad con el id suministrado
     * @throws EventoLogicException cuando no existe un sitio con el id sumunistrado
     */
    @GET
    @Path("{id: \\d+}/ciudades/{idciudad: \\d+}/eventos/{idevento: \\d+}")
    public EventoItinerarioDTO fetchEvento(@PathParam("id") int id,@PathParam("idciudad") int idciudad, @PathParam("idevento") int idevento) {
        try {
            return EventoItinerarioConverter.fullEntity2DTO(itinerarioLogic.getEvento(id, idciudad, idevento));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.NOT_FOUND);
        }
    }

    /**
     * Retorna la lista de itinerarios asociados a un viajero particular
     * @param id del viajero del que se quieren obtener sus itienrarios
     * @return lista de itinerarios
     */
    @GET
    @Path("viajero/{idviajero: }")
    public List<ItinerarioDTO> getItinerariosViajero(@PathParam("idviajero") int id){
    	return ItinerarioConverter.listEntity2DTO(itinerarioLogic.getItinerariosViajero(id));
    }
}