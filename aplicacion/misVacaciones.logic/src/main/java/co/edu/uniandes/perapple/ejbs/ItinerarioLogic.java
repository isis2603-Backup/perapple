package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ViajeroPersistence;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.perapple.api.IItinerarioLogic;
import co.edu.uniandes.perapple.entities.CiudadEntity;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {
//TODO Implementar los métodos correspondientes, identificar necesidad de otros métodos

    private static final Logger logger = Logger.getLogger(ItinerarioLogic.class.getName());

    @Inject
    private ItinerarioPersistence persistence;

    //Posibilidad de inyectar mas instancias de las otras persistencias de ser necesario

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteItinerario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViajeroEntity getViajero(Long itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity addCiudad(Long ciudadId, Long itinerarioId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCiudad(Long ciudadId, Long itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CiudadEntity> replaceCiudades(List<CiudadEntity> ciudades, Long itinerarioId) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
