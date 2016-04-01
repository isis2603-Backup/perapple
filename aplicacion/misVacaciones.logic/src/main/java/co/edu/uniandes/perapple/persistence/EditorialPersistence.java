package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.EditorialEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EditorialPersistence {

    private static final Logger logger = Logger.getLogger(EditorialPersistence.class.getName());

    @PersistenceContext(unitName = "MisVacacionesPU")
    protected EntityManager em;

    public EditorialEntity create(EditorialEntity entity) {
        logger.info("Creando una editorial nueva");
        em.persist(entity);
        logger.info("Creando una editorial nueva");
        return entity;
    }

    public EditorialEntity update(EditorialEntity entity) {
        logger.log(Level.INFO, "Actualizando editorial con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando editorial con id={0}", id);
        EditorialEntity entity = em.find(EditorialEntity.class, id);
        em.remove(entity);
    }

    public EditorialEntity find(Long id) {
        logger.log(Level.INFO, "Consultando editorial con id={0}", id);
        return em.find(EditorialEntity.class, id);
    }

    public List<EditorialEntity> findAll() {
        logger.info("Consultando todas las editoriales");
        Query q = em.createQuery("select u from EditorialEntity u");
        return q.getResultList();
    }
}
