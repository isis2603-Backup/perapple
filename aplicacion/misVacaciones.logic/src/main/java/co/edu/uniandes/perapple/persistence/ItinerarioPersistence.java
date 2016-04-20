package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItinerarioPersistence {

    private static final Logger logger = Logger.getLogger(ItinerarioPersistence.class.getName());

    @PersistenceContext(unitName = "MisVacacionesPU")
    protected EntityManager em;

    public ItinerarioEntity find(int id) {
        logger.log(Level.INFO, "Consultando Itinerario con id={0}", id);
        System.out.println("debería imprimirme 2 veces");
        return em.find(ItinerarioEntity.class, id);
    }

    public List<ItinerarioEntity> findAll() {
        logger.info("Consultando todos los itinerarios");
        Query q = em.createQuery("select u from ItinerarioEntity u");
        return q.getResultList();
    }

    /**Creacion de metodos con sentencia de busqueda sql para encontrar cosas mas rapido
    *   public List<ItinerarioEntity> findByViajero(String nombreViajero) {
    *   logger.info("Consultando todos los itinerarios");
    *   Query q = em.createQuery("select u from ItinerarioEntity u");
    *   return q.getResultList();
    *}
    */

    public ItinerarioEntity create(ItinerarioEntity entity) {
        logger.info("Creando un itinerario nuevo");
        em.persist(entity);
        logger.info("Itinerario creado");
        return find(entity.getId());
    }

    public ItinerarioEntity update(ItinerarioEntity entity) {
        logger.log(Level.INFO, "Actualizando itinerario con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(int id) {
        logger.log(Level.INFO, "Borrando itinerario con id={0}", id);
        ItinerarioEntity entity = em.find(ItinerarioEntity.class, id);
        em.remove(entity);
    }
}
