package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import co.edu.uniandes.perapple.persistence.CiudadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.perapple.api.ICiudadLogic;
import co.edu.uniandes.perapple.entities.EventoEntity;
import co.edu.uniandes.perapple.entities.SitioEntity;

@Stateless
public class CiudadLogic implements ICiudadLogic {
//TODO Implementar los métodos correspondientes, identificar necesidad de otros métodos

    private static final Logger logger = Logger.getLogger(CiudadLogic.class.getName());

    @Inject
    private CiudadPersistence persistence;

     //Posibilidad de inyectar mas instancias de las otras persistencias de ser necesario

    @Override
    public List<CiudadEntity> getCiudades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity getCiudad(Long id) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity createCiudad(CiudadEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity updateCiudad(CiudadEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCiudad(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventoEntity> getEventos(Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoEntity getEvento(Long eventoId, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity addEvento(Long ciudadId, Long eventoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeEvento(Long eventoId, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SitioEntity> getSitios(Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioEntity getSitio(Long sitioId, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity addSitio(Long ciudadId, Long sitioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSitio(Long sitioId, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SitioEntity> replaceSitios(List<SitioEntity> sitios, Long ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
