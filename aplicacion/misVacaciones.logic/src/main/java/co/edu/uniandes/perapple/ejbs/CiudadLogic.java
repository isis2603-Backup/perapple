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
        logger.log(Level.INFO, "Inicia proceso de consultar ciudad con id={0}", id);
        CiudadEntity ciudad = persistence.find(id);
        if (ciudad == null) {
            logger.log(Level.SEVERE, "La ciudad con el id {0} no existe", id);
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
        //1. La ciudad ya existe.
        //2. No se actualiza el ID.
        logger.log(Level.INFO, "Inicia proceso de actualizar ciudad con id={0}", entity.getId());
        if (!validateCiudadExiste(entity.getId())) {
            throw new BusinessLogicException("La ciudad que se quiere actualizar no existe.");
        }
        CiudadEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar ciudad con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteCiudad(int id) throws BusinessLogicException {
        //Validaciones
        //1. La ciudad ya existe.
        logger.log(Level.INFO, "Inicia proceso de borrar ciudad con id={0}", id);
        if (!validateCiudadExiste(id)) {
            throw new BusinessLogicException("La ciudad que se quiere eliminar no existe.");
        }
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar ciudad con id={0}", id);
    }

    @Override
    public List<EventoEntity> getEventos(int ciudadId) throws BusinessLogicException {
        //Validaciones
        //1. La ciudad ya existe.
        logger.log(Level.INFO, "Inicia proceso de obtener eventos de la ciudad con id={0}", ciudadId);
        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad de la que se quieren saber los eventos no existe.");
        }
        List<EventoEntity> eventos = persistence.find(ciudadId).getEventos();
        logger.log(Level.INFO, "Termina proceso de obtener eventos de la ciudad con id={0}", ciudadId);
        
        return eventos;
    }

    @Override
    public EventoEntity getEvento(int eventoId, int ciudadId) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar evento con id={0} de la ciudad con id={1}", eventoId, ciudadId);
        CiudadEntity ciudad = persistence.find(ciudadId);
        if (ciudad == null) {
            logger.log(Level.SEVERE, "El libro con el id {0} no existe", id);
            throw new BusinessLogicException("La ciudad solicitado no existe");
        }
        List<EventoEntity> eventos = ciudad.getEventos();
        EventoEntity eventoEntity = new EventoEntity();
        eventoEntity.setId(eventoId);
        int index = eventos.indexOf(eventoEntity);
        if (index >= 0) {
            return eventos.get(index);
        }
        logger.log(Level.INFO, "Termina proceso de consultar ciudad con id={0}", id);
        return null;
        //Revisar
    }

    @Override
    public EventoEntity addEvento(int ciudadId, EventoEntity evento) {
        
    }

    @Override
    public void removeEvento(int eventoId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SitioEntity> getSitios(int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioEntity getSitio(int sitioId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioEntity addSitio(int ciudadId, SitioEntity sitio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSitio(int sitioId, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SitioEntity> replaceSitios(List<SitioEntity> sitios, int ciudadId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SitioEntity updateSitio(int id, int idSitio, SitioEntity entity) {
        //Validaciones
        //1. La ciudad, evento o sitio de interés ya existe.
        //2. El evento o sitio de interés existe en la ciudad determinada.
        //3. No se actualiza el ID.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoEntity updateEvento(int id, int idEvento, EventoEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Validaciones
    
    private boolean validateCiudadExiste(int ciudadId) {
        
        CiudadEntity ciudad = persistence.find(ciudadId);
        
        if (ciudad == null) {
            return false;
        }
        
        return true;
    }
}
