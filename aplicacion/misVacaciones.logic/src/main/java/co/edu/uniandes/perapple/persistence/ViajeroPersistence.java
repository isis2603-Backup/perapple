package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ViajeroPersistence {

    private static final Logger LOGGER = Logger.getLogger(ViajeroPersistence.class.getName());

    @PersistenceContext(unitName = "MisVacacionesPU")
    protected EntityManager em;

    public ViajeroEntity create(ViajeroEntity entity) {
        LOGGER.info("Creando un viajero nuevo");
        em.persist(entity);
        LOGGER.info("Viajero creado");
        return entity;
    }

    public ViajeroEntity update(ViajeroEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando viajero con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(int id) {
        LOGGER.log(Level.INFO, "Borrando viajero con id={0}", id);
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.remove(entity);
    }

    public ViajeroEntity find(int id) {
        LOGGER.log(Level.INFO, "Consultando viajero con id={0}", id);
        return em.find(ViajeroEntity.class, id);
    }

    public List<ViajeroEntity> findAll() {
        LOGGER.info("Consultando todos los viajeros");
        Query q = em.createQuery("select u from ViajeroEntity u");
        return q.getResultList();
    }
}
