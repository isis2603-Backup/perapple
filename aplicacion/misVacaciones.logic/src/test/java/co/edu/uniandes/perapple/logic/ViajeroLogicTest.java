/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.logic;

import co.edu.uniandes.perapple.api.IViajeroLogic;
import co.edu.uniandes.perapple.ejbs.ViajeroLogic;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ViajeroPersistence;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.junit.Assert;
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
public class ViajeroLogicTest {

    /**
     * Generador de info aleatoria
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Elemento de logica a probar
     */
    @Inject
    private IViajeroLogic viajeroLogic;

    /**
     * Manejador base de datos de la prueba
     */
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ViajeroEntity> data = new ArrayList<>();

    private List<ItinerarioEntity> itinerariosData = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(IViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

       private void insertData() {
        for (int i = 0; i < 3; i++) {
            ItinerarioEntity itinerarios = factory.manufacturePojo(ItinerarioEntity.class);
            itinerarios.setFechaFin(Date.from(LocalDateTime.MAX.toInstant(ZoneOffset.UTC)));
            itinerarios.setFechaInicio(Date.from(LocalDateTime.MIN.toInstant(ZoneOffset.UTC)));
            itinerarios.setNombre("Itinerario " + i);
            em.persist(itinerarios);
            itinerariosData.add(itinerarios);
        }

        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            ArrayList<ItinerarioEntity> iti = new ArrayList<>();
            iti.add(itinerariosData.get(i));
            entity.setItinerarios(iti);

            em.persist(entity);
            data.add(entity);

            itinerariosData.get(i).setViajero(entity);
        }
    }

    @Test
    public void getViajerosTest() {
        List<ViajeroEntity> resultList = viajeroLogic.getViajeros();
        List<ViajeroEntity> expectedList = em.createQuery("SELECT u from ViajeroEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (ViajeroEntity expected : expectedList) {
            boolean found = false;
            for (ViajeroEntity result : resultList) {
                if (result.getId() == expected.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViajeroTest() {
        try {
            ViajeroEntity result = viajeroLogic.getViajero(data.get(0).getId());
            ViajeroEntity expected = em.find(ViajeroEntity.class, data.get(0).getId());

            Assert.assertNotNull(expected);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getEmail(), result.getEmail());
            Assert.assertEquals(expected.getImage(), result.getImage());
            Assert.assertEquals(expected.getPassword(), result.getPassword());
        } catch (BusinessLogicException be){
            Assert.fail(be.getMessage());
        }
    }

    @Test
    public void createViajeroTest() {
        try {
            ViajeroEntity expected = factory.manufacturePojo(ViajeroEntity.class);
            ViajeroEntity created = viajeroLogic.createViajero(expected);

            ViajeroEntity result = em.find(ViajeroEntity.class, created.getId());

            Assert.assertNotNull(result);
            Assert.assertNotNull(result);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getEmail(), result.getEmail());
            Assert.assertEquals(expected.getImage(), result.getImage());
            Assert.assertEquals(expected.getPassword(), result.getPassword());
        } catch (BusinessLogicException be) {
            Assert.fail(be.getMessage());
        }
    }

    @Test
    public void updateAuthorTest() {
        try {
            ViajeroEntity entity = data.get(0);
            ViajeroEntity expected = factory.manufacturePojo(ViajeroEntity.class);

            expected.setId(entity.getId());

            viajeroLogic.updateViajero(expected);

            ViajeroEntity result = em.find(ViajeroEntity.class, entity.getId());

            Assert.assertNotNull(expected);
            Assert.assertEquals(expected.getId(), result.getId());
            Assert.assertEquals(expected.getName(), result.getName());
            Assert.assertEquals(expected.getEmail(), result.getEmail());
            Assert.assertEquals(expected.getImage(), result.getImage());
            Assert.assertEquals(expected.getPassword(), result.getPassword());
        } catch (BusinessLogicException be) {
            Assert.fail(be.getMessage());
        }
    }

    @Test
    public void deleteAuthorTest() {
        try {
            ViajeroEntity entity = data.get(1);
            viajeroLogic.deleteViajero(entity.getId());
            ViajeroEntity expected = em.find(ViajeroEntity.class, entity.getId());
            Assert.assertNull(expected);
        } catch (BusinessLogicException be) {
            Assert.fail(be.getMessage());
        }
    }
}
