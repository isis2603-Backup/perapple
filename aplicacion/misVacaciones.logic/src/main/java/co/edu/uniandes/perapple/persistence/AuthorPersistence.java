package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.AuthorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AuthorPersistence {

    private static final Logger logger = Logger.getLogger(AuthorPersistence.class.getName());

    @PersistenceContext(unitName = "MisVacacionesPU")
    protected EntityManager em;

    public AuthorEntity create(AuthorEntity entity) {
        logger.info("Creando un autor nuevo");
        em.persist(entity);
        logger.info("Autor creado");
        return entity;
    }

    public AuthorEntity update(AuthorEntity entity) {
        logger.log(Level.INFO, "Actualizando autor con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando autor con id={0}", id);
        AuthorEntity entity = em.find(AuthorEntity.class, id);
        em.remove(entity);
    }

    public AuthorEntity find(Long id) {
        logger.log(Level.INFO, "Consultando autor con id={0}", id);
        return em.find(AuthorEntity.class, id);
    }

    public List<AuthorEntity> findAll() {
        logger.info("Consultando todos los autores");
        Query q = em.createQuery("select u from AuthorEntity u");
        return q.getResultList();
    }
}
