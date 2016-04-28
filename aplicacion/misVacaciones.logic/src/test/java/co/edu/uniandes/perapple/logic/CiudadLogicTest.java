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
        em.createQuery("delete from SitioEntity").executeUpdate();
        em.createQuery("delete from EventoEntity").executeUpdate();
        em.createQuery("delete from CiudadEntity").executeUpdate();

    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SitioEntity sitios = factory.manufacturePojo(SitioEntity.class);
            em.persist(sitios);
            sitiosData.add(sitios);
        }
        for (int i = 0; i < 3; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            em.persist(eventos);
            eventosData.add(eventos);
        }

        for (int i = 0; i < 3; i++) {

            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);
            ArrayList<SitioEntity> sitios = new ArrayList<>();
            ArrayList<EventoEntity> eventos = new ArrayList<>();

            sitios.add(sitiosData.get(i));
            eventos.add(eventosData.get(i));

            entity.setSitios(sitios);
            entity.setEventos(eventos);

            em.persist(entity);
            data.add(entity);

            sitiosData.get(i).setCiudad(entity);
            eventosData.get(i).setCiudad(entity);
        }

    }

    @Test
    public void createCiudadTest() {
        try {
            CiudadEntity expected = factory.manufacturePojo(CiudadEntity.class);
            CiudadEntity created = ciudadLogic.createCiudad(expected);

            CiudadEntity result = em.find(CiudadEntity.class, created.getId());

            assertNotNull(result);
            assertNotNull(expected);
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getNombre(), result.getNombre());
            assertEquals(expected.getDetalles(), result.getDetalles());
            assertEquals(expected.getImagen(), result.getImagen());

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> resultList = ciudadLogic.getCiudades();
        List<CiudadEntity> expectedList = em.createQuery("SELECT u from CiudadEntity u").getResultList();
        assertEquals(expectedList.size(), resultList.size());
        for (CiudadEntity expected : expectedList) {
            boolean found = false;
            for (CiudadEntity result : resultList) {
                if (result.getId() == expected.getId()) {
                    found = true;
                } else {
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void getCiudadTest() {
        try {
            CiudadEntity result = ciudadLogic.getCiudad(data.get(0).getId());
            CiudadEntity expected = em.find(CiudadEntity.class, data.get(0).getId());

            assertNotNull(expected);
            assertNotNull(result);
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getNombre(), result.getNombre());
            assertEquals(expected.getDetalles(), result.getDetalles());
            assertEquals(expected.getImagen(), result.getImagen());

            //Mirar si faltan atributos y si van las listas
        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void deleteCiudadTest() {
        try {
            CiudadEntity ciudad_entity = data.get(1);
            ciudadLogic.deleteCiudad(ciudad_entity.getId());
            CiudadEntity deleted = em.find(CiudadEntity.class, ciudad_entity.getId());
            assertNull(deleted);

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void updateCiudadTest() {
        try {
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

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getEventoTest() {
        try {
            CiudadEntity entity = data.get(0);
            EventoEntity eventoEntity = eventosData.get(0);
            EventoEntity response = ciudadLogic.getEvento(eventoEntity.getId(), entity.getId());

            EventoEntity expected = getCiudadEvento(entity.getId(), eventoEntity.getId());

            assertNotNull(expected);
            assertNotNull(response);
            assertEquals(expected.getId(), response.getId());
            assertEquals(expected.getNombre(), response.getNombre());
            assertEquals(expected.getDetalles(), response.getDetalles());
            assertEquals(expected.getImagen(), response.getImagen());
            assertEquals(expected.getFechaEvento(), response.getFechaEvento());
        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void getSitioTest() {
        try {

            CiudadEntity entity = data.get(0);
            SitioEntity sitioEntity = sitiosData.get(0);
            SitioEntity response = ciudadLogic.getSitio(sitioEntity.getId(), entity.getId());

            SitioEntity expected = getCiudadSitio(entity.getId(), sitioEntity.getId());

            assertNotNull(expected);
            assertNotNull(response);
            assertEquals(expected.getId(), response.getId());
            assertEquals(expected.getNombre(), response.getNombre());
            assertEquals(expected.getDetalles(), response.getDetalles());
            assertEquals(expected.getImagen(), response.getImagen());
        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void replaceEventoTest() {

        try {
            EventoEntity entity = eventosData.get(0);
            EventoEntity expected = factory.manufacturePojo(EventoEntity.class);
            expected.setCiudad(data.get(0));
            expected.setId(entity.getId());

            ciudadLogic.updateEvento(data.get(0).getId(), entity.getId(), expected);

            EventoEntity result = getCiudadEvento(data.get(0).getId(), entity.getId());

            Assert.assertNotNull(expected);

            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getCiudad().getId(), result.getCiudad().getId());
            assertEquals(expected.getDetalles(), result.getDetalles());
            assertEquals(expected.getImagen(), result.getImagen());
            assertEquals(expected.getNombre(), result.getNombre());

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void replaceSitioTest() {
        try {
            SitioEntity entity = sitiosData.get(0);
            SitioEntity expected = factory.manufacturePojo(SitioEntity.class);
            expected.setCiudad(data.get(0));
            expected.setId(entity.getId());

            ciudadLogic.updateSitio(data.get(0).getId(), entity.getId(), expected);

            SitioEntity result = getCiudadSitio(data.get(0).getId(), entity.getId());

            Assert.assertNotNull(expected);

            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getCiudad().getId(), result.getCiudad().getId());
            assertEquals(expected.getDetalles(), result.getDetalles());
            assertEquals(expected.getImagen(), result.getImagen());
            assertEquals(expected.getNombre(), result.getNombre());

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void removeEventoTest() {
        try {
            ciudadLogic.removeEvento(eventosData.get(0).getId(), data.get(0).getId());

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void removeSitioTest() {
        try {
            ciudadLogic.removeSitio(sitiosData.get(0).getId(), data.get(0).getId());

        } catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        }
    }

    private EventoEntity getCiudadEvento(int ciudadId, int eventoId) {
        Query q = em.createQuery("Select DISTINCT e from CiudadEntity c join c.eventos e where c.id = :ciudadId and e.id=:eventoId");
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("eventoId", eventoId);

        return (EventoEntity) q.getSingleResult();
    }

    private SitioEntity getCiudadSitio(int ciudadId, int sitioId) {
        Query q = em.createQuery("Select DISTINCT s from CiudadEntity c join c.sitios s where c.id = :ciudadId and s.id=:sitioId");
        q.setParameter("ciudadId", ciudadId);
        q.setParameter("sitioId", sitioId);

        return (SitioEntity) q.getSingleResult();
    }
}
