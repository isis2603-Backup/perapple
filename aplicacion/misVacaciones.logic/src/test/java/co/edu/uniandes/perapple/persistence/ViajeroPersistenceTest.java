/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author camen, dalthviz
 */
@RunWith(Arquillian.class)
public class ViajeroPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Generador de info aleatoria
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Elemento de logica a probar
     */
    @Inject
    private ViajeroPersistence viajeroPersistence;

    /**
     * Manejador base de datos de la prueba
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Manejador de transacciones (limites de transacciones - commit, rollback, begin, etc)
     */
     @Inject
    UserTransaction utx;
    //TODO definir que estrutura vamos a seguir @Before @Test @After o @Before con utx y metodos privados para crear y borrar

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private List<ViajeroEntity> data = new ArrayList<>();

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

     @Test
    public void createViajeroTest() {
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        ViajeroEntity result = viajeroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ViajeroEntity entity = em.find(ViajeroEntity.class, result.getId());

         Assert.assertEquals(newEntity.getName(), entity.getName());
         Assert.assertEquals(newEntity.getImage(), entity.getImage());
         Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
         Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
         Assert.assertEquals(newEntity.getItinerarios(), entity.getItinerarios());
    }

     @Test
    public void getViajerosTest() {
        List<ViajeroEntity> list = viajeroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViajeroEntity ent : list) {
            boolean found = false;
            for (ViajeroEntity entity : data) {
                if (ent.getId() == entity.getId()) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getViajeroTest() {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = viajeroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getPassword(), entity.getPassword());
        Assert.assertEquals(newEntity.getItinerarios(), entity.getItinerarios());
    }

    @Test
    public void deleteViajeroTest() {
        ViajeroEntity entity = data.get(0);
        viajeroPersistence.delete(entity.getId());
        ViajeroEntity deleted = em.find(ViajeroEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @Test
    public void updateItinerarioTest() {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity newEntity = factory.manufacturePojo(ViajeroEntity.class);
        newEntity.setId(entity.getId());

        viajeroPersistence.update(newEntity);

        ViajeroEntity resp = em.find(ViajeroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
        Assert.assertEquals(newEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(newEntity.getPassword(), resp.getPassword());
        Assert.assertEquals(newEntity.getItinerarios(), resp.getItinerarios());
    }
}
