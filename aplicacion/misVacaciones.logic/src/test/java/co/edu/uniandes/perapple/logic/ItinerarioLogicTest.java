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
import co.edu.uniandes.perapple.entities.EventoEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author camen, dalthviz, mcrg
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
    private List<CiudadEntity> ciudadesData = new ArrayList<>();
    private List<EventoEntity> eventosData = new ArrayList<>();
    private List<SitioEntity> sitiosData = new ArrayList<>();

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
        em.createQuery("delete from SitioItinerarioEntity").executeUpdate();
        em.createQuery("delete from EventoItinerarioEntity").executeUpdate();
        em.createQuery("delete from CiudadItinerarioEntity").executeUpdate();
        em.createQuery("delete from SitioEntity").executeUpdate();
        em.createQuery("delete from EventoEntity").executeUpdate();
        em.createQuery("delete from CiudadEntity").executeUpdate();
        em.createQuery("delete from ItinerarioEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 2; i++) {
            ViajeroEntity viajero = factory.manufacturePojo(ViajeroEntity.class);
            viajero.setEmail(viajero.getName().split(" ")[0]+"@perapple.com");
            em.persist(viajero);
            viajerosData.add(viajero);
        }

        for (int i = 0; i < 5; i++) {
            SitioEntity sitios = factory.manufacturePojo(SitioEntity.class);
            em.persist(sitios);
            sitiosData.add(sitios);
        }
        for (int i = 0; i < 5; i++) {
            EventoEntity eventos = factory.manufacturePojo(EventoEntity.class);
            em.persist(eventos);
            eventosData.add(eventos);
        }

        for (int i = 0; i < 5; i++) {
            CiudadEntity ciudad = factory.manufacturePojo(CiudadEntity.class);
            ciudad.setEventos(eventosData); //Mismos sitios para todas las ciudades
            ciudad.setSitios(sitiosData); //Mismos eventos para todas las ciudades
            //System.out.println(ciudad.getNombre());
            em.persist(ciudad);
            ciudadesData.add(ciudad);
        }

        for (int i = 0; i < 3; i++) {

            ItinerarioEntity entity = factory.manufacturePojo(ItinerarioEntity.class);
            System.out.println("Nombre Itinerario: "+entity.getNombre());

            Date fecha = new Date();
            long hoy = fecha.getTime();
            int unDia = (1000 * 60 * 60 * 24);
            
            //2 itinerarios iguales con diferente viajero (i==0 e i==1)
            //2 itinerarios diferente con igual viajero (i==1 e i==2)
            if (i==0){
                entity.setViajero(viajerosData.get(0));
                fecha = new Date();
                fecha.setTime(hoy);
                entity.setFechaInicio(fecha);
                fecha = new Date();
                fecha.setTime(hoy+30*unDia);
                entity.setFechaFin(fecha);

                List<CiudadItinerarioEntity> ciudadesI = new ArrayList<>();

                CiudadItinerarioEntity cIE;

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(0));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 4*unDia);
                cIE.setFechaFin(fecha);
                List<SitioItinerarioEntity> sitios = new ArrayList<>();
                SitioItinerarioEntity sitio = new SitioItinerarioEntity();
                sitio.setId(sitiosData.get(0).getId());
                sitio.setSitio(sitiosData.get(0));
                sitio.setCiudad(cIE);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + unDia);
                sitio.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 2*unDia);
                sitio.setFechaFin(fecha);
                sitios.add(sitio);
                cIE.setSitios(sitios);
                List<EventoItinerarioEntity> eventos = new ArrayList<>();
                EventoItinerarioEntity evento = new EventoItinerarioEntity();
                evento.setId(eventosData.get(0).getId());
                evento.setEvento(eventosData.get(0));
                evento.setCiudad(cIE);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + unDia);
                evento.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 2*unDia);
                evento.setFechaFin(fecha);
                eventos.add(evento);
                cIE.setEventos(eventos);
                ciudadesI.add(cIE);

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(1));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 5*unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 10*unDia);
                cIE.setFechaFin(fecha);
                ciudadesI.add(cIE);

		entity.setCiudades(ciudadesI);
            }
            else if(i==1){
                entity.setViajero(viajerosData.get(1));

                fecha = new Date();
                fecha.setTime(hoy);
                entity.setFechaInicio(fecha);
                fecha = new Date();
                fecha.setTime(hoy+30*unDia);
                entity.setFechaFin(fecha);

                List<CiudadItinerarioEntity> ciudadesI = new ArrayList<>();

                CiudadItinerarioEntity cIE;

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(0));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 4*unDia);
                cIE.setFechaFin(fecha);
                ciudadesI.add(cIE);

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(1));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 5*unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 10*unDia);
                cIE.setFechaFin(fecha);
                ciudadesI.add(cIE);

		entity.setCiudades(ciudadesI);
            }
            else{
                entity.setViajero(viajerosData.get(0));

                fecha = new Date();
                fecha.setTime(hoy+2*unDia);
                entity.setFechaInicio(fecha);
                fecha = new Date();
                fecha.setTime(hoy+20*unDia);
                entity.setFechaFin(fecha);

                List<CiudadItinerarioEntity> ciudadesI = new ArrayList<>();

                CiudadItinerarioEntity cIE;

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(1));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 3*unDia);
                cIE.setFechaFin(fecha);
                ciudadesI.add(cIE);

                cIE= new CiudadItinerarioEntity();
                cIE.setItinerario(entity);
                cIE.setCiudad(ciudadesData.get(2));
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 4*unDia);
                cIE.setFechaIni(fecha);
                fecha = new Date();
                fecha.setTime(entity.getFechaInicio().getTime() + 8*unDia);
                cIE.setFechaFin(fecha);
                ciudadesI.add(cIE);

		entity.setCiudades(ciudadesI);
            }

            em.persist(entity);
            itinerariosData.add(entity);
        }
    }
    
    @Test
    public void getItinerariosTest(){
        
        List<ItinerarioEntity> lista = itinerarioLogic.getItinerarios();
        
        assertEquals(lista.size(), itinerariosData.size());
    }
    
    @Test
    public void createItinerarioTest(){
        try{
            ItinerarioEntity expected = factory.manufacturePojo(ItinerarioEntity.class);

            Date fecha = new Date();
            long hoy = fecha.getTime();
            int unDia = (1000 * 60 * 60 * 24);

            expected.setViajero(viajerosData.get(1));
            fecha.setTime(hoy);
            expected.setFechaInicio(fecha);
            fecha.setTime(hoy+30*unDia);
            expected.setFechaFin(fecha);

            System.out.println("fI expected "+expected.getFechaInicio().toString());
            System.out.println("fI expected "+expected.getNombre());

            ItinerarioEntity created = itinerarioLogic.createItinerario(expected);

            ItinerarioEntity result = em.find(ItinerarioEntity.class, created.getId());

            System.out.println("fI result "+result.getFechaInicio().toString());
            System.out.println("fI result "+result.getNombre());

            assertNotNull(result);
            assertNotNull(expected);
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getNombre(), result.getNombre());

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String expectedFI = df.format(expected.getFechaInicio());
            String expectedFF = df.format(expected.getFechaFin());
            String resultFI = df.format(result.getFechaInicio());
            String resultFF = df.format(result.getFechaFin());

            assertEquals(expectedFI, resultFI);
            assertEquals(expectedFF, resultFF);

        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getItinerariosViajeroTest(){
        int idViajero = viajerosData.get(0).getId();
            
        List<ItinerarioEntity> lista = itinerarioLogic.getItinerariosViajero(idViajero);

        for(int i=0; i<lista.size(); i++){
            ItinerarioEntity ie = lista.get(i);

            assertEquals(ie.getViajero().getId(), idViajero);
        }
    }

    @Test
    public void getItinerarioTest(){
        try{
            ItinerarioEntity itinerarioABuscar = itinerariosData.get(0);
            
            ItinerarioEntity itinerarioEncontrado = itinerarioLogic.getItinerario(itinerarioABuscar.getId());

            assertEquals(itinerarioEncontrado.getId(), itinerarioABuscar.getId());
            assertEquals(itinerarioEncontrado.getNombre(), itinerarioABuscar.getNombre());
            assertEquals(itinerarioEncontrado.getViajero().getId(), itinerarioABuscar.getViajero().getId());
            assertEquals(itinerarioEncontrado.getCiudades().size(), itinerarioABuscar.getCiudades().size());
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String itinerarioEncontradoFI = df.format(itinerarioEncontrado.getFechaInicio());
            String itinerarioEncontradoFF = df.format(itinerarioEncontrado.getFechaFin());
            String itinerarioABuscarFI = df.format(itinerarioABuscar.getFechaInicio());
            String itinerarioABuscarFF = df.format(itinerarioABuscar.getFechaFin());
            
            assertEquals(itinerarioEncontradoFI, itinerarioABuscarFI);
            assertEquals(itinerarioEncontradoFF, itinerarioABuscarFF);

        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void deleteItinerarioTest(){
        try{
            ItinerarioEntity itinerarioAEliminar = itinerariosData.get(0);
            
            itinerarioLogic.deleteItinerario(itinerarioAEliminar.getId());

            assertEquals(itinerariosData.size()-1, itinerarioLogic.getItinerarios().size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    //sirve
    @Test
    public void getViajeroTest(){
        try{
            ItinerarioEntity itinerarioABuscarViajero = itinerariosData.get(0);
            
            ViajeroEntity v = itinerarioLogic.getViajero(itinerarioABuscarViajero.getId());

            assertEquals(itinerarioABuscarViajero.getViajero().getId(), v.getId());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getCiudadesTest(){
        try{
            ItinerarioEntity itinerarioABuscarCiudades = itinerariosData.get(0);
            
            List<CiudadItinerarioEntity> lista = itinerarioLogic.getCiudades(itinerarioABuscarCiudades.getId());

            assertEquals(2, lista.size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getCiudadTest(){
        try{
            ItinerarioEntity itinerarioABuscarCiudad = itinerariosData.get(0);
            
            CiudadItinerarioEntity real = itinerarioABuscarCiudad.getCiudades().get(0);
            
            CiudadItinerarioEntity encontrada = itinerarioLogic.getCiudad(itinerarioABuscarCiudad.getId(), real.getId());

            assertEquals(real.getId(), encontrada.getId());
            assertEquals(real.getSitios().size(), encontrada.getSitios().size());
            assertEquals(real.getCiudad().getNombre(), encontrada.getCiudad().getNombre());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void addCiudadTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            CiudadEntity ciu = ciudadesData.get(3);
            
            CiudadItinerarioEntity ciud = new CiudadItinerarioEntity();
            
            Date fecha1 = new Date();
            Date fecha2 = new Date();
            int unDia = (1000 * 60 * 60 * 24);
            
            ciud.setItinerario(iti);
            ciud.setCiudad(ciu);
            fecha1.setTime(iti.getFechaInicio().getTime() + 11*unDia);
            ciud.setFechaIni(fecha1);
            fecha2.setTime(iti.getFechaInicio().getTime() + 15*unDia);
            ciud.setFechaFin(fecha2);
            
            itinerarioLogic.addCiudad(ciud, iti.getId());
            
            assertEquals(iti.getCiudades().size()+1, itinerarioLogic.getCiudades(iti.getId()).size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void deleteCiudadTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            List<CiudadItinerarioEntity> lista = iti.getCiudades();
            
            CiudadItinerarioEntity ciudadAEliminar = lista.get(0);
            
            itinerarioLogic.deleteCiudad(ciudadAEliminar.getId(), iti.getId());
            
            assertEquals(lista.size()-1, itinerarioLogic.getCiudades(iti.getId()).size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void updateCiudadTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            List<CiudadItinerarioEntity> lista = iti.getCiudades();
            
            CiudadItinerarioEntity ciudadAActualizar = lista.get(0);
            
            CiudadEntity nueva = ciudadesData.get(3);
            
            ciudadAActualizar.setCiudad(nueva);
            
            itinerarioLogic.updateCiudad(ciudadAActualizar, iti.getId());
            
            CiudadItinerarioEntity ciudadActualizada = itinerarioLogic.getCiudad(iti.getId(), ciudadAActualizar.getId());
            
            assertEquals(nueva.getNombre(), ciudadActualizada.getCiudad().getNombre());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void replaceCiudadesTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            Date fecha;
            int unDia = (1000 * 60 * 60 * 24);

            List<CiudadItinerarioEntity> ciudadesI = new ArrayList<>();
            CiudadItinerarioEntity cIE;

            cIE= new CiudadItinerarioEntity();
            cIE.setItinerario(iti);
            cIE.setCiudad(ciudadesData.get(3));
            fecha = new Date();
            fecha.setTime(iti.getFechaInicio().getTime() + unDia);
            cIE.setFechaIni(fecha);
            fecha = new Date();
            fecha.setTime(iti.getFechaInicio().getTime() + 4*unDia);
            cIE.setFechaFin(fecha);
            ciudadesI.add(cIE);
            
            itinerarioLogic.replaceCiudades(ciudadesI, iti.getId());

            assertEquals(ciudadesI.size(), itinerarioLogic.getCiudades(iti.getId()).size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getSitiosTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            CiudadItinerarioEntity ciu = iti.getCiudades().get(0);
            
            List<SitioItinerarioEntity> sitios = itinerarioLogic.getSitios(iti.getId(), ciu.getId());

            assertEquals(ciu.getSitios().size(), sitios.size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getEventosTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            CiudadItinerarioEntity ciu = iti.getCiudades().get(0);
            
            List<EventoItinerarioEntity> eventos = itinerarioLogic.getEventos(iti.getId(), ciu.getId());

            assertEquals(ciu.getEventos().size(), eventos.size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getSitioTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            CiudadItinerarioEntity ciu = iti.getCiudades().get(0);
            
            SitioItinerarioEntity sitioReal = ciu.getSitios().get(0);
            SitioItinerarioEntity sitioEncontrado = itinerarioLogic.getSitio(iti.getId(), ciu.getId(), sitioReal.getId());

            assertEquals(sitioReal.getSitio().getNombre(), sitioEncontrado.getSitio().getNombre());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void getEventoTest(){
        try{
            ItinerarioEntity iti = itinerariosData.get(0);
            
            CiudadItinerarioEntity ciu = iti.getCiudades().get(0);
            
            EventoItinerarioEntity eventoReal = ciu.getEventos().get(0);
            EventoItinerarioEntity eventoEncontrado = itinerarioLogic.getEvento(iti.getId(), ciu.getId(), eventoReal.getId());

            assertEquals(eventoReal.getEvento().getNombre(), eventoEncontrado.getEvento().getNombre());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void createSitioTest(){
        try{
            Date fecha;
            int unDia = (1000 * 60 * 60 * 24);
            
            ItinerarioEntity iti = itinerariosData.get(0);
            
            CiudadItinerarioEntity ciu = iti.getCiudades().get(0);
            
            SitioItinerarioEntity sitio = new SitioItinerarioEntity();
            sitio.setId(sitiosData.get(1).getId());
            sitio.setSitio(sitiosData.get(1));
            sitio.setCiudad(ciu);
            fecha = new Date();
            fecha.setTime(iti.getFechaInicio().getTime() + 2*unDia);
            sitio.setFechaIni(fecha);
            fecha = new Date();
            fecha.setTime(iti.getFechaInicio().getTime() + 3*unDia);
            sitio.setFechaFin(fecha);
            
            itinerarioLogic.createSitio(iti.getId(), ciu.getId(), sitio);

            assertEquals(ciu.getSitios().size()+1, itinerarioLogic.getSitios(iti.getId(), ciu.getId()).size());
            
        }catch (BusinessLogicException ex) {
            fail(ex.getLocalizedMessage());
        } catch (Exception ex ){
            fail(ex.getLocalizedMessage());
        }
    }
}
