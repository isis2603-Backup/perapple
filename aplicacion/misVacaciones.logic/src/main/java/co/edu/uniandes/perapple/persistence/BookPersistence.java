package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.BookEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BookPersistence {

    private static final Logger logger = Logger.getLogger(BookPersistence.class.getName());

    @PersistenceContext(unitName = "MisVacacionesPU")
    protected EntityManager em;

    public BookEntity find(Long id) {
        logger.log(Level.INFO, "Consultando libro con id={0}", id);
        return em.find(BookEntity.class, id);
    }

    public List<BookEntity> findAll() {
        logger.info("Consultando todos los libros");
        Query q = em.createQuery("select u from BookEntity u");
        return q.getResultList();
    }

    public BookEntity create(BookEntity entity) {
        logger.info("Creando un libro nuevo");
        em.persist(entity);
        logger.info("Libro creado");
        return entity;
    }

    public BookEntity update(BookEntity entity) {
        logger.log(Level.INFO, "Actualizando libro con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        logger.log(Level.INFO, "Borrando libro con id={0}", id);
        BookEntity entity = em.find(BookEntity.class, id);
        em.remove(entity);
    }
}
