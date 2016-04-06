package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
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
        logger.info("Inicia proceso de consultar todas las ciudades.");
        List<CiudadEntity> ciudades = persistence.findAll();
        logger.info("Termina proceso de consultar todos los libros");
       
        return ciudades;
    }

    @Override
    public CiudadEntity getCiudad(int id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar libro con id={0}", id);
        CiudadEntity ciudad = persistence.find(id);
        if (ciudad == null) {
            logger.log(Level.SEVERE, "El libro con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return ciudad;
    }

    @Override
    public CiudadEntity createCiudad(CiudadEntity entity) throws BusinessLogicException {
        //Validaciones:
        //1. La ciudad a crear no existe.
        //2. El nuevo ID a asignar a la ciudad está disponible.
        logger.info("Inicia proceso de creación de una ciudad");
        if (validateCiudadExiste(entity.getId())) {
            throw new BusinessLogicException("Ya existe una ciudad con ese id. No se puede crear. ");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación de ciudad");
        return entity;
    }

    @Override
    public CiudadEntity updateCiudad(CiudadEntity entity) throws BusinessLogicException {
        //Validaciones
        //1. La ciudad, evento o sitio de interés ya existe.
        //2. El evento o sitio de interés existe en la ciudad determinada.
        //3. No se actualiza el ID.
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        if (!validateCiudadExiste(entity.getId())) {
            throw new BusinessLogicException("La ciudad que se quiere actualizar no existe.");
        }
        //falta......
        CiudadEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteCiudad(Long id) {
        //Validaciones
        //1. La ciudad, evento o sitio de interés ya existe.
        //2. El evento o sitio de interés existe en la ciudad determinada.
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
    public EventoEntity addEvento(Long ciudadId, EventoEntity evento) {
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
    public SitioEntity addSitio(Long ciudadId, SitioEntity sitio) {
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

    @Override
    public SitioEntity updateSitio(Long id, Long idSitio, SitioEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoEntity updateEvento(Long id, Long idEvento, EventoEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Validaciones
    
    private boolean validateCiudadExiste(int idCiudad) {
        
        CiudadEntity ciudad = persistence.find(idCiudad);
        
        if (ciudad == null) {
            return false;
        }
        
        return true;
    }
}
