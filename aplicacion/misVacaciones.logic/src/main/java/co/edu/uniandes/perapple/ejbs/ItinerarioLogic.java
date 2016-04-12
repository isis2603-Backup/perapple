package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.perapple.api.IItinerarioLogic;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import java.util.ArrayList;
import java.util.logging.Level;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {
//TODO Implementar los métodos correspondientes, identificar necesidad de otros métodos

    private static final Logger logger = Logger.getLogger(ItinerarioLogic.class.getName());

    @Inject
    private ItinerarioPersistence persistence;

    //Posibilidad de inyectar mas instancias de las otras persistencias de ser necesario

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        return persistence.findAll();
    }

    @Override
    public ItinerarioEntity getItinerario(int id) throws Exception {
        logger.log(Level.INFO, "Inicia proceso de consultar itinerario con id={0}", id);
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            logger.log(Level.SEVERE, "El itinerario con el id {0} no existe", id);
            throw new IllegalArgumentException("El itinerario solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar itinerario con id={0}", id);
        return itinerario;
    }

    /**
     *  Crea un nuevo itinerario partiendo de una entidad dada
     * @param entity entidad con la información del nuevo itinerario
     * @return entidad desconectada de la base de datos
     * @throws BusinessLogicException en caso de que no se cumpla con las validaciones
     */
    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinessLogicException{

      logger.info("Inicia proceso de creación de itinerario");
        if(entity.getFechaInicio() == null || entity.getFechaFin() == null)
        {
            throw new BusinessLogicException("Las fechas no son inválidas, fechas nulas");
        }
        if (entity.getFechaInicio().compareTo(entity.getFechaFin())>0) {
            throw new BusinessLogicException("Las fechas no son inválidas, fecha de inicio posterior a la fecha finalización");
        }
        if(entity.getNombre().equals("")|| entity.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre proporcionado para el itinerario no es válido");

        }
        String viajero = entity.getViajero().getName();
        String nombreItinerario = entity.getNombre();
        if(!validacionNombreUnico(viajero,nombreItinerario))
        {
            throw new BusinessLogicException("El viajero ya cuenta con un itinerario con el nombre dado");

        }

        persistence.create(entity);
        logger.info("Termina proceso de creación de itinerario");
        return entity;
    }

    /**
     * Comprobación de validez de nombre para un itinerario de manera poco eficiente
     * @param viajero
     * @return si el nombre es válido o no
     */
    private boolean validacionNombreUnico(String viajero, String nombreItinerario) {

        List<ItinerarioEntity> itinerarios = persistence.findAll();
        boolean respuesta = false;

        for(int i = 0; i< itinerarios.size() && !respuesta; i++)
        {
            if(itinerarios.get(i).getNombre().equals(nombreItinerario)
                    && itinerarios.get(i).getViajero().getName().equals(viajero))
            {
             respuesta  = true;
            }
        }

    return respuesta;
    }

     private boolean validarExistenciaItinerario(int id) {

    return (persistence.find(id)!= null);

    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinessLogicException{

        if(!validarExistenciaItinerario(entity.getId()))
        {
            throw new BusinessLogicException("El itinerario con el id suministrado no existe");
        }
        persistence.update(entity);
        return entity;
    }



    @Override
    public void deleteItinerario(int id)throws BusinessLogicException{

         if(!validarExistenciaItinerario(id))
        {
            throw new BusinessLogicException("El itinerario con el id suministrado no existe");
        }
    persistence.delete(id);

    }


    @Override
    public ViajeroEntity getViajero(int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadItinerarioEntity addCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCiudad(int ciudadId, int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity getCurrentItinerario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity setCurrentItinerario(ItinerarioEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadItinerarioEntity updateCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadItinerarioEntity getCiudad(int itinerarioId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CiudadItinerarioEntity> getCiudades(int itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioItinerarioEntity updateSitio(int itinerarioId, int ciudadId, int sitioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioItinerarioEntity createSitio(int itinerarioId, int ciudadId, SitioItinerarioEntity sitio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteSitio(int itinerarioId, int ciudadId, int sitioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SitioItinerarioEntity> getSitios(int itinerarioId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioItinerarioEntity getSitio(int itinerarioId, int ciudadId, int sitioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoItinerarioEntity createEvento(int itinerarioId, int ciudadId, EventoItinerarioEntity evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEvento(int itinerarioId, int ciudadId, int eventoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventoItinerarioEntity> getEventos(int itinerarioId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoItinerarioEntity getEvento(int itinerarioId, int ciudadId, int eventoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItinerarioEntity> getItinerariosViajero(int viajeroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}
