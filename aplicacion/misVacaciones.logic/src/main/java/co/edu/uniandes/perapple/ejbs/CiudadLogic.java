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
        logger.info("Termina proceso de consultar todas las ciudades");

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
        logger.log(Level.INFO, "Inicia proceso de consultar evento con id="+eventoId+" de la ciudad con id="+ciudadId);
        CiudadEntity ciudad = persistence.find(ciudadId);

        if (ciudad == null) {
            throw new BusinessLogicException("La ciudad de la que se quiere saber el evento no existe.");
        }

        List<EventoEntity> eventos = ciudad.getEventos();

        for (int i=0; i<eventos.size(); i++){
            EventoEntity e = eventos.get(i);
            if(e.getId()==eventoId){
                logger.log(Level.INFO, "Termina el proceso de consultar evento con id="+eventoId+" de la ciudad con id="+ciudadId);
                return e;
            }
        }

        throw new BusinessLogicException("El evento con id="+eventoId+" de la ciudad con id="+ciudadId+" no existe.");
    }

    @Override
    public EventoEntity addEvento(int ciudadId, EventoEntity evento) throws BusinessLogicException {
        //Validaciones:
        //1. La ciudad a la que se le agregará el evento ya existe.
        //2. El nuevo evento no existe en esa ciudad.
        //3. El nuevo ID a asignar al evento está disponible.

        logger.log(Level.INFO, "Inicia proceso de agregar un evento a la ciudad con id={0}",ciudadId);

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere agregar el evento no existe.");
        }

        if (validateEventoExiste(ciudadId, evento.getId())){
            throw new BusinessLogicException("El evento ya existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<EventoEntity> eventos = ciudadEntity.getEventos();

        eventos.add(evento);

        ciudadEntity.setEventos(eventos);

        return evento;
    }

    @Override
    public void removeEvento(int eventoId, int ciudadId) throws BusinessLogicException {
        //Validaciones
        //1. El evento existe en la ciudad determinada.
        //2. El evento ya existe.

        logger.log(Level.INFO, "Inicia proceso de eliminar un evento con id="+eventoId+"a la ciudad con id="+ciudadId);

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere eliminar el evento no existe.");
        }

        if (!validateEventoExiste(ciudadId, eventoId)){
            throw new BusinessLogicException("El evento no existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<EventoEntity> eventos = ciudadEntity.getEventos();

        for (int i=0; i<eventos.size(); i++){
            EventoEntity e = eventos.get(i);
            if(e.getId()==eventoId){
                eventos.remove(i);
                ciudadEntity.setEventos(eventos);
                logger.log(Level.INFO, "Termina el proceso de eliminar evento con id="+eventoId+" de la ciudad con id="+ciudadId);
            }
        }

        logger.log(Level.INFO, "No se pudo eliminar evento con id="+eventoId+" de la ciudad con id="+ciudadId);
    }

    @Override
    public EventoEntity updateEvento(int ciudadId, int eventoId, EventoEntity evento) throws BusinessLogicException {
        //Validaciones
        //1. El evento ya existe.
        //2. El evento existe en la ciudad determinada.
        //3. No se actualiza el ID.

        logger.log(Level.INFO, "Inicia proceso de actualizar un evento a la ciudad con id={0}",ciudadId);

        if (eventoId != evento.getId()){
            throw new BusinessLogicException("No se puede actualizar el ID.");
        }

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere actualizar el evento no existe.");
        }

        if (!validateEventoExiste(ciudadId, eventoId)){
            throw new BusinessLogicException("El evento no existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<EventoEntity> eventos = ciudadEntity.getEventos();

        for (int i=0; i<eventos.size(); i++){
            EventoEntity e = eventos.get(i);
            if(e.getId()==eventoId){
                eventos.set(i, evento);
                ciudadEntity.setEventos(eventos);
                logger.log(Level.INFO, "Termina el proceso de actualizar evento con id="+eventoId+" de la ciudad con id="+ciudadId);
                return evento;
            }
        }

        throw new BusinessLogicException("El evento con id="+eventoId+" de la ciudad con id="+ciudadId+" no se pudo actualizar.");
    }

    @Override
    public List<SitioEntity> getSitios(int ciudadId) throws BusinessLogicException {
        //Validaciones
        //1. La ciudad ya existe.
        logger.log(Level.INFO, "Inicia proceso de obtener sitios de la ciudad con id={0}", ciudadId);
        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad de la que se quieren saber los eventos no existe.");
        }
        List<SitioEntity> sitios = persistence.find(ciudadId).getSitios();
        logger.log(Level.INFO, "Termina proceso de obtener sitios de la ciudad con id={0}", ciudadId);

        return sitios;
    }

    @Override
    public SitioEntity getSitio(int sitioId, int ciudadId) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar sitio con id="+sitioId+" de la ciudad con id="+ciudadId);
        CiudadEntity ciudad = persistence.find(ciudadId);

        if (ciudad == null) {
            throw new BusinessLogicException("La ciudad de la que se quiere saber el sitio no existe.");
        }

        List<SitioEntity> sitios = ciudad.getSitios();

        for (int i=0; i<sitios.size(); i++){
            SitioEntity s = sitios.get(i);
            if(s.getId()==sitioId){
                logger.log(Level.INFO, "Termina el proceso de consultar sitio con id="+sitioId+" de la ciudad con id="+ciudadId);
                return s;
            }
        }

        throw new BusinessLogicException("El sitio con id="+sitioId+" de la ciudad con id="+ciudadId+" no existe.");
    }

    @Override
    public SitioEntity addSitio(int ciudadId, SitioEntity sitio) throws BusinessLogicException {
        //Validaciones:
        //1. La ciudad a la que se le agregará el sitio ya existe.
        //2. El nuevo sitio no existe en esa ciudad.
        //3. El nuevo ID a asignar al sitio está disponible.

        logger.log(Level.INFO, "Inicia proceso de agregar un sitio a la ciudad con id={0}",ciudadId);

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere agregar el sitio no existe.");
        }

        if (validateSitioExiste(ciudadId, sitio.getId())){
            throw new BusinessLogicException("El sitio ya existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<SitioEntity> sitios = ciudadEntity.getSitios();

        sitios.add(sitio);

        ciudadEntity.setSitios(sitios);

        return sitio;
    }

    @Override
    public void removeSitio(int sitioId, int ciudadId) throws BusinessLogicException {
        //Validaciones
        //1. El evento existe en la ciudad determinada.
        //2. El evento ya existe.

        logger.log(Level.INFO, "Inicia proceso de eliminar un sitio con id="+sitioId+"a la ciudad con id="+ciudadId);

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere eliminar el sitio no existe.");
        }

        if (!validateEventoExiste(ciudadId, sitioId)){
            throw new BusinessLogicException("El sitio no existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<SitioEntity> sitios = ciudadEntity.getSitios();

        for (int i=0; i<sitios.size(); i++){
            SitioEntity s = sitios.get(i);
            if(s.getId()==sitioId){
                sitios.remove(i);
                ciudadEntity.setSitios(sitios);
                logger.log(Level.INFO, "Termina el proceso de eliminar sitio con id="+sitioId+" de la ciudad con id="+ciudadId);
            }
        }

        logger.log(Level.INFO, "No se pudo eliminar sitio con id="+sitioId+" de la ciudad con id="+ciudadId);
    }

    @Override
    public SitioEntity updateSitio(int ciudadId, int sitioId, SitioEntity sitio) throws BusinessLogicException {
        //Validaciones
        //1. El sitio ya existe.
        //2. El sitio existe en la ciudad determinada.
        //3. No se actualiza el ID.

        logger.log(Level.INFO, "Inicia proceso de actualizar un sitio a la ciudad con id={0}",ciudadId);

        if (sitioId != sitio.getId()){
            throw new BusinessLogicException("No se puede actualizar el ID.");
        }

        if (!validateCiudadExiste(ciudadId)) {
            throw new BusinessLogicException("La ciudad a la que se le quiere actualizar el sitio no existe.");
        }

        if (!validateEventoExiste(ciudadId, sitioId)){
            throw new BusinessLogicException("El evento no existe en esa ciudad.");
        }

        CiudadEntity ciudadEntity = persistence.find(ciudadId);

        List<SitioEntity> sitios = ciudadEntity.getSitios();

        for (int i=0; i<sitios.size(); i++){
            SitioEntity s = sitios.get(i);
            if(s.getId()==sitioId){
                sitios.set(i, sitio);
                ciudadEntity.setSitios(sitios);
                logger.log(Level.INFO, "Termina el proceso de actualizar sitio con id="+sitioId+" de la ciudad con id="+ciudadId);
                return sitio;
            }
        }

        throw new BusinessLogicException("El sitio con id="+sitioId+" de la ciudad con id="+ciudadId+" no se pudo actualizar.");
    }



    //Validaciones

    private boolean validateCiudadExiste(int ciudadId) {
        CiudadEntity ciudad = persistence.find(ciudadId);
        if (ciudad == null) {
            return false;
        }
        return true;
    }

    private boolean validateEventoExiste(int ciudadId, int eventoId) {
        CiudadEntity ciudad = persistence.find(ciudadId);
        List<EventoEntity> eventos = ciudad.getEventos();

        for(int i=0; i<eventos.size(); i++){
            EventoEntity e = eventos.get(i);
            if(e.getId() == eventoId){
                return false;
            }
        }
        return true;
    }

    private boolean validateSitioExiste(int ciudadId, int sitioId) {
        CiudadEntity ciudad = persistence.find(ciudadId);
        List<SitioEntity> sitios = ciudad.getSitios();

        for(int i=0; i<sitios.size(); i++){
            SitioEntity s = sitios.get(i);
            if(s.getId() == sitioId){
                return false;
            }
        }
        return true;
    }
}
