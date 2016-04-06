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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViajeroEntity getViajero(Long id) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViajeroEntity createViajero(ViajeroEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViajeroEntity updateViajero(ViajeroEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteViajero(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity addItinerario(Long bookId, Long authorId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItinerario(Long bookId, Long authorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> books, Long authorId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItinerarioEntity> getItinerarios(Long viajeroId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity getItinerario(Long viajeroId, Long itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
