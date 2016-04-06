package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ViajeroPersistence;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.perapple.api.IViajeroLogic;
import co.edu.uniandes.perapple.api.IItinerarioLogic;

@Stateless
public class ViajeroLogic implements IViajeroLogic {

    private static final Logger logger = Logger.getLogger(ViajeroLogic.class.getName());

    @Inject
    private ViajeroPersistence persistence;


     //Posibilidad de inyectar mas instancias de las otras persistencias de ser necesario

    @Override
    public List<ViajeroEntity> getItinerarios() {
        logger.info("Inicia proceso de consultar todas los viajeros.");
        List<ViajeroEntity> viajeros = persistence.findAll();
        logger.info("Termina proceso de consultar todos los libros");

        return viajeros;
    }

    @Override
    public ViajeroEntity getViajero(Long id) throws BusinessLogicException {
    logger.log(Level.INFO, "Inicia proceso de consultar viajero con id={0}", id);
        ViajeroEntity viajero = persistence.find(id);
        if (viajero == null) {
            logger.log(Level.SEVERE, "El viajero con el id {0} no existe", id);
            throw new BusinessLogicException("El viajero solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar El viajero con id={0}", id);
        return viajero;
    }

    @Override
    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException{
    //Validaciones:
        //1. El viajero a crear no existe.
        //2. El nuevo ID a asignar al viajero está disponible.
        logger.info("Inicia proceso de creación de un viajero");
        if (validateViajeroExiste(entity.getId())) {
            throw new BusinessLogicException("Ya existe un viajero con ese id. No se puede crear. ");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación del viajero");
        return entity;
    }

    @Override
    public ViajeroEntity updateViajero(ViajeroEntity entity) throws BusinessLogicException{
        //Validaciones
        //1. El viajero ya existe.
        //2. No se actualiza el ID.
        logger.log(Level.INFO, "Inicia proceso de actualizar viajero con id={0}", entity.getId());
        if (!validateViajeroExiste(entity.getId())) {
            throw new BusinessLogicException("el viajero que se quiere actualizar no existe.");
        }
        ViajeroEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar viajero con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteViajero(Long id) {
        //Validaciones
        //1. El viajero ya existe.
        logger.log(Level.INFO, "Inicia proceso de borrar viajero con id={0}", id);
        if (!validateViajeroExiste(id)) {
            throw new BusinessLogicException("el viajero que se quiere eliminar no existe.");
        }
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar viajero con id={0}", id);
    }

    @Override
    public ItinerarioEntity addItinerario(Long bookId, Long authorId) throws BusinessLogicException {
        //Validaciones
        //1. el itinerario no existe
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItinerario(Long bookId, Long authorId) {
        //Validaciones
        //1. El itinerario ya existe.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> books, Long authorId) throws BusinessLogicException {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItinerarioEntity> getItinerarios(Long viajeroId) {
        //Validaciones
        //1. La ciudad ya existe.
        logger.log(Level.INFO, "Inicia proceso de obtener itinerarios del viajero con id={0}", viajeroId);
        if (!validateViajeroExiste(viajeroId)) {
            throw new BusinessLogicException("El viajero del que se quieren saber los itinerarios no existe.");
        }
        List<ViajeroEntity> itinerarios = persistence.find(viajeroId).getItinerarios();
        logger.log(Level.INFO, "Termina proceso de obtener itinerarios del viajero con id={0}", viajeroId);

        return itinerarios;
    }

    @Override
    public ItinerarioEntity getItinerario(Long viajeroId, Long itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private boolean validateViajeroExiste(int viajeroId) {

        ViajeroEntity ciudad = persistence.find(viajeroId);

        if (ciudad == null) {
            return false;
        }

        return true;
    }

}
