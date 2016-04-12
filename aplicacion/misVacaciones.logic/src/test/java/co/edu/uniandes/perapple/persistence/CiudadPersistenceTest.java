/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.persistence;

import co.edu.uniandes.perapple.api.ICiudadLogic;
import co.edu.uniandes.perapple.entities.CiudadEntity;
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
public class CiudadPersistenceTest {

     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
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
    private CiudadPersistence ciudadPersistence;



    /**
     * Manejador base de datos de la prueba
     */
    @PersistenceContext
    private EntityManager em;


    //TODO definir que estrutura vamos a seguir @Before @Test @After o @Before con utx y metodos privados para crear y borrar
    public CiudadPersistenceTest() {
        this.data = new ArrayList();
    }
    @Inject
    UserTransaction utx;

  

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }
      private List<CiudadEntity> data;

    private void insertData() {
        for (int i = 0; i < 5; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
     @Test
    public void createCiudadTest() {
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity result = ciudadPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CiudadEntity entity = em.find(CiudadEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getDetalles(), entity.getDetalles());



    }

    @After
    public void tearDown() {
    }

}
