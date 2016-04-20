/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.logic;

import co.edu.uniandes.perapple.api.ICiudadLogic;
import co.edu.uniandes.perapple.ejbs.CiudadLogic;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.SitioEntity;
import co.edu.uniandes.perapple.entities.EventoEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.CiudadPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author camen, dalthviz, hegm
 */
@RunWith(Arquillian.class)
public class CiudadLogicTest {

    /**
     * Generador de info aleatoria con Podam
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * Elemento de logica a probar
     */
    @Inject
    private ICiudadLogic ciudadLogic;

    /**
     * Manejador base de datos de la prueba
     */
    @PersistenceContext
    private EntityManager em;


    @Inject
    private UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();

    private List<SitioEntity> sitiosData = new ArrayList<>();

    private List<EventoEntity> eventosData = new ArrayList<>();


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(ICiudadLogic.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    //TODO definir que estrutura vamos a seguir @Before @Test @After o @Before con utx y metodos privados para crear y borrar

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

    private void clearData()
    {
         em.createQuery("delete from SitioEntity").executeUpdate();
         em.createQuery("delete from EventoEntity").executeUpdate();
        em.createQuery("delete from CiudadEntity").executeUpdate();

    }

    
    private void insertData()
    {
        for (int i = 0; i < 3; i++) {
            SitioEntity sitios = factory.manufacturePojo(SitioEntity.class);
            //se agrega algo?
            em.persist(sitios);
            sitiosData.add(sitios);
        }
        for (int i = 0; i < 3; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            //se agrega algo?
            em.persist(eventos);
            eventosData.add(eventos);
        }

        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);

            sitiosData.get(0).setCiudad(entity);
            eventosData.get(0).setCiudad(entity); 
        }
    }

     @Test
    public void createCiudadTest()  {
        try{
        CiudadEntity expected = factory.manufacturePojo(CiudadEntity.class);
       CiudadEntity created= ciudadLogic.createCiudad(expected);

        CiudadEntity result = em.find(CiudadEntity.class, created.getId());


        assertNotNull(result);
        assertNotNull(expected);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNombre(), result.getNombre());
        assertEquals(expected.getDetalles(),result.getDetalles() );
        assertEquals(expected.getImagen(),result.getImagen() );
        assertEquals(expected.getFechaInicio(),result.getFechaInicio());
            assertEquals(expected.getFechaFin(), result.getFechaFin());
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }
    @Test
    public void getCiudadesTest()
    {
         List<CiudadEntity> resultList=  ciudadLogic.getCiudades();
        List<CiudadEntity> expectedList = em.createQuery("SELECT u from CiudadEntity u").getResultList();
       assertEquals(expectedList.size(), resultList.size());
        for (CiudadEntity expected : expectedList) {
            boolean found = false;
            for (CiudadEntity result : resultList) {
                if (result.getId()==expected.getId()) {
                    found = true;
                } else {
                }
            }
           assertTrue(found);
        }
    }

    @Test
    public void getCiudadTest()
    {
        try{
        CiudadEntity result= ciudadLogic.getCiudad(data.get(0).getId());
        CiudadEntity expected= em.find(CiudadEntity.class,data.get(0).getId());

        assertNotNull(expected);
        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getNombre(), result.getNombre());
        assertEquals(expected.getDetalles(), result.getDetalles());
        assertEquals(expected.getImagen(), result.getImagen());
        assertEquals(expected.getFechaInicio(), result.getFechaInicio());
        assertEquals(expected.getFechaFin(), result.getFechaFin());

        //Mirar si faltan atributos y si van las listas

        }catch(BusinessLogicException ex) {
           fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void deleteCiudadTest()
    {try{
        CiudadEntity ciudad_entity= data.get(1);
        ciudadLogic.deleteCiudad(ciudad_entity.getId());
        CiudadEntity deleted=em.find(CiudadEntity.class, ciudad_entity.getId());
        assertNull(deleted);

    }catch(BusinessLogicException ex) {
           fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void updateCiudadTest()
    {
        try{
            CiudadEntity entity = data.get(0);
            CiudadEntity pojoEntity = factory.manufacturePojo(CiudadEntity.class);
            pojoEntity.setNombre("Nueva_Ciudad_prueba");
            pojoEntity.setId(entity.getId());
            ciudadLogic.updateCiudad(pojoEntity);

            CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

            assertEquals(pojoEntity.getId(), resp.getId());
           assertEquals(pojoEntity.getNombre(), resp.getNombre());
            assertEquals(pojoEntity.getDetalles(), resp.getDetalles());
            assertEquals(pojoEntity.getImagen(), resp.getImagen());
           assertEquals(pojoEntity.getFechaInicio(), resp.getFechaInicio());
           assertEquals(pojoEntity.getFechaFin(), resp.getFechaFin());

        }catch(BusinessLogicException ex) {
           fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getEventoTest() {
        try{
        CiudadEntity entity = data.get(0);
        EventoEntity eventoEntity = eventosData.get(0);
        EventoEntity response = ciudadLogic.getEvento(entity.getId(), eventoEntity.getId());

       EventoEntity expected = getCiudadEvento(entity.getId(), eventoEntity.getId()); 

      assertNotNull(expected);
        assertNotNull(response);
       assertEquals(expected.getId(), response.getId());
      assertEquals(expected.getNombre(), response.getNombre());
        assertEquals(expected.getDetalles(), response.getDetalles());
       assertEquals(expected.getImagen(), response.getImagen());
       assertEquals(expected.getFechaEvento(), response.getFechaEvento());
        }catch(BusinessLogicException ex) {
           fail(ex.getLocalizedMessage());
        }
    }
    
    
    @Test
    public void getSitioTest()
    {
         try{
        CiudadEntity entity = data.get(0);
        SitioEntity sitioEntity = sitiosData.get(0);
        SitioEntity response = ciudadLogic.getSitio(entity.getId(), sitioEntity.getId());

       SitioEntity expected = getCiudadSitio(entity.getId(), sitioEntity.getId()); 

      assertNotNull(expected);
        assertNotNull(response);
       assertEquals(expected.getId(), response.getId());
      assertEquals(expected.getNombre(), response.getNombre());
        assertEquals(expected.getDetalles(), response.getDetalles());
       assertEquals(expected.getImagen(), response.getImagen());
       assertEquals(expected.getFechaSitio(), response.getFechaSitio());
        }catch(BusinessLogicException ex) {
           fail(ex.getLocalizedMessage());
        }
    }
    
    
    
    @Test
    public void replaceEventoTest()
    {
        try{ CiudadEntity entity = data.get(0);
            List<EventoEntity> list = eventosData.subList(1, 3);
            ciudadLogic.updateEvento(entity.getId(),list.get(0).getId(),list.get(0));

            CiudadEntity expected = em.find(CiudadEntity.class, entity.getId());

            Assert.assertNotNull(expected);
            Assert.assertFalse(expected.getEventos().contains(eventosData.get(0)));
            Assert.assertTrue(expected.getEventos().contains(eventosData.get(1)));
            Assert.assertTrue(expected.getEventos().contains(eventosData.get(2)));
        
        }catch(BusinessLogicException ex){
            fail(ex.getLocalizedMessage());
        }
    }
    
    
    @Test
    public void replaceSitioTest()
    {
         try{ CiudadEntity entity = data.get(0);
            List<SitioEntity> list = sitiosData.subList(1, 3);
            ciudadLogic.updateSitio(entity.getId(),list.get(0).getId(),list.get(0));

            CiudadEntity expected = em.find(CiudadEntity.class, entity.getId());

            Assert.assertNotNull(expected);
            Assert.assertFalse(expected.getSitios().contains(sitiosData.get(0)));
            Assert.assertTrue(expected.getSitios().contains(sitiosData.get(1)));
            Assert.assertTrue(expected.getSitios().contains(sitiosData.get(2)));
        
        }catch(BusinessLogicException ex){
            fail(ex.getLocalizedMessage());
        }
        
    }
    
    @Test(expected= NoResultException.class)
    public void removeEventoTest()
    {
        try{
        ciudadLogic.removeEvento(eventosData.get(0).getId(), data.get(0).getId());
        
        }catch(BusinessLogicException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test(expected= NoResultException.class)
    public void removeSitioTest()
    {
         try{
        ciudadLogic.removeSitio(sitiosData.get(0).getId(), data.get(0).getId());
        
        }catch(BusinessLogicException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }
    
    private EventoEntity getCiudadEvento(int ciudadId, int eventoId)
    {
        Query q = em.createQuery("Select DISTINCT a from CiudadEntity c join c.eventos e where c.id = :ciudadId and e.id=:eventoId");
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("eventoId", eventoId);

        return (EventoEntity) q.getSingleResult(); 
    }

    private SitioEntity getCiudadSitio(int ciudadId, int sitioId)
    {
        Query q = em.createQuery("Select DISTINCT a from CiudadEntity c join c.sitios s where c.id = :ciudadId and s.id=:sitioId");
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("sitioId", sitioId);

        return (SitioEntity) q.getSingleResult();
    }

   // @BeforeClass
    //public static void setUpClass() {
    //}

    //@AfterClass
    //public static void tearDownClass() {
    //}

    //@Before
    //public void setUp() {
    //}

    //@After
    //public void tearDown() {
    //}

}
