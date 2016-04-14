/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.logic;

import co.edu.uniandes.perapple.api.IItinerarioLogic;
import co.edu.uniandes.perapple.ejbs.ItinerarioLogic;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author camen, dalthviz
 */
@RunWith(Arquillian.class)
public class ItinerarioLogicTest {

    /**
     * Generador de info aleatoria
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Elemento de logica a probar
     */
    @Inject
    private IItinerarioLogic itinerarioLogic;

    /**
     * Manejador base de datos de la prueba
     */
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ItinerarioEntity> itinerariosData = new ArrayList<>();

    private List<CiudadItinerarioEntity> ciudadesItinerarioData = new ArrayList<>();

    private List<EventoItinerarioEntity> eventosItinerarioData = new ArrayList<>();
    
    private List<SitioItinerarioEntity> sitiosItinerarioData = new ArrayList<>();
    
    private List<ViajeroEntity> viajerosData = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ItinerarioEntity.class.getPackage())
                .addPackage(ItinerarioLogic.class.getPackage())
                .addPackage(IItinerarioLogic.class.getPackage())
                .addPackage(ItinerarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    //TODO definir que estrutura vamos a seguir @Before @Test @After o @Before con utx y metodos privados para crear y borrar
    public ItinerarioLogicTest() {
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from CiudadItinerarioEntity").executeUpdate();
        em.createQuery("delete from SitioItinerarioEntity").executeUpdate();
        em.createQuery("delete from EventoItinerarioEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity viajeros = factory.manufacturePojo(ViajeroEntity.class);
            System.out.println(viajeros.getEmail());
            em.persist(viajeros);
            viajerosData.add(viajeros);
        }

        for (int i = 0; i < 3; i++) {
            CiudadItinerarioEntity ciudades = factory.manufacturePojo(CiudadItinerarioEntity.class);
            em.persist(ciudades);
            ciudadesItinerarioData.add(ciudades);
        }

        for (int i = 0; i < 3; i++) {
            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            entity.setViajero(viajerosData.get(i));
            System.out.println(entity.getViajero().getName());
            for (CiudadItinerarioEntity item : entity.getCiudades()) {
                item.setItinerario(entity);
            }

            //entity.getAuthors().add(authorsData.get(0));

            //entity.setEditorial(editorialData.get(0));

            em.persist(entity);
            itinerariosData.add(entity);
        }
    }

    @After
    public void tearDown() {
    }

}
