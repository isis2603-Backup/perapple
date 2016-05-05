package co.edu.uniandes.misVacaciones.rest;

import co.edu.uniandes.misVacaciones.rest.converters.CiudadConverter;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.misVacaciones.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.misVacaciones.rest.resources.CiudadResource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class CiudadResourceIT {

    private final int OK = Status.OK.getStatusCode();
    private final int CREATED = Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Status.NO_CONTENT.getStatusCode();

    private final String ciudadPath = "ciudades";
    private final String sitiosPath = "sitios";
    private final String eventosPath= "eventos";

    private final static List<CiudadDTO> oraculo = new ArrayList<>();
    private final static List<SitioDTO> oraculoSitios = new ArrayList<>();
    private final static List<EventoDTO> oraculoEventos= new ArrayList<>();
 
    private WebTarget target;
    private final String apiPath = "api";
    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

        @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.perapple:misVacaciones.logic:1.0-SNAPSHOT")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(CiudadResource.class.getPackage())
                .addPackage(CiudadDTO.class.getPackage())
                .addPackage(CiudadConverter.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    @Before
    public void setUpTest() {
        target = ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            CiudadDTO ciudad = factory.manufacturePojo(CiudadDTO.class);
            ciudad.setId(i);
            List<SitioDTO> sitiosList = new ArrayList<>();
            List<EventoDTO> eventosList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                SitioDTO sitios = factory.manufacturePojo(SitioDTO.class);
                sitios.setId(i);
                sitiosList.add(sitios);
                
                EventoDTO eventos = factory.manufacturePojo(EventoDTO.class);
                eventos.setId(i);
                eventosList.add(eventos);
             }
           

            ciudad.setSitios(sitiosList);
            ciudad.setEventos(eventosList);

            oraculo.add(ciudad);

        }
    }
    
    @Test
    @InSequence(1)
    public void ciudadTest() {
        CiudadDTO ciudad = oraculo.get(0);
        Response response = target.path(ciudadPath).request()
                .post(Entity.entity(ciudad, MediaType.APPLICATION_JSON));

        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);

        Assert.assertEquals("No se creo la ciudad",CREATED, response.getStatus());

        Assert.assertNotNull("No hubo resouesta de ciudad creada", ciudadTest);
        Assert.assertEquals("No coincide el nombre de la ciudad",ciudad.getNombre(), ciudadTest.getNombre());
        Assert.assertEquals("No coinciden los detalles de la ciudad",ciudad.getDetalles(), ciudadTest.getDetalles());
        Assert.assertEquals("No coincide la imagen de la ciudad",ciudad.getImagen(), ciudadTest.getImagen());
        Assert.assertEquals("No coincide el id de la ciudad",ciudad.getId(), ciudadTest.getId());
        Assert.assertEquals("No coinciden los eventos de la ciudad",ciudad.getEventos(), ciudadTest.getEventos());
        Assert.assertEquals("No coinciden los sitios de la ciudad",ciudad.getSitios(), ciudadTest.getSitios());
        
    }

    @Test
    @InSequence(2)
    public void getCiudadById() {
        CiudadDTO ciudadTest = target.path(ciudadPath)
                .path(oraculo.get(0).getId()+" ")
                .request().get(CiudadDTO.class);
        
        Assert.assertNotNull("No hubo respuesta de ciudad creada", ciudadTest );
        Assert.assertEquals("No coincide el id de la ciudad ",ciudadTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals("No coicide el nombre de la ciudad",ciudadTest.getNombre(), oraculo.get(0).getNombre());
        Assert.assertEquals("No coinciden los detalles de la ciudad",ciudadTest.getDetalles(), oraculo.get(0).getDetalles());
        Assert.assertEquals("No coincide la imagen de la ciudad ",ciudadTest.getImagen(), oraculo.get(0).getImagen());
        Assert.assertEquals("No coinciden los eventos de la ciudad",ciudadTest.getEventos(),oraculo.get(0).getEventos());
        Assert.assertEquals("No coinciden los sitios de la ciudad",ciudadTest.getSitios(),oraculo.get(0).getSitios());
    }

    @Test
    @InSequence(3)
    public void listCiudadTest() {
        Response response = target.path(ciudadPath)
                .request().get();

        List<CiudadDTO> listCiudadTest = response.readEntity(new GenericType<List<CiudadDTO>>() {
        });
        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(1, listCiudadTest.size());
    }

    @Test
    @InSequence(4)
    public void updateCiudadTest() {
        CiudadDTO ciudad = oraculo.get(0);
        CiudadDTO ciudadChanged = factory.manufacturePojo(CiudadDTO.class);
       
        ciudad.setNombre(ciudadChanged.getNombre());
        ciudad.setDetalles(ciudadChanged.getDetalles());
        ciudad.setImagen(ciudadChanged.getImagen());
        ciudad.setEventos(ciudadChanged.getEventos());
        ciudad.setSitios(ciudadChanged.getSitios());
        
        Response response = target.path(ciudadPath).path(ciudad.getId()+" ")
                .request().put(Entity.entity(ciudad, MediaType.APPLICATION_JSON));
        
        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);
        Assert.assertEquals(OK, response.getStatus());
        
        Assert.assertNotNull("No hubo respuesta de ciudad creada ", ciudadTest);
        Assert.assertEquals("No coincide el nombre de la ciudad",ciudad.getNombre(), ciudadTest.getNombre());
        Assert.assertEquals("No coinciden los detalles de la ciudad",ciudad.getDetalles(), ciudadTest.getDetalles());
        Assert.assertEquals("No coincide el id de la ciudad",ciudad.getId(), ciudadTest.getId());
        Assert.assertEquals("No coincide la imagen de la ciudad",ciudad.getImagen(), ciudadTest.getImagen());
        Assert.assertEquals("No coinciden los eventos de la ciudad",ciudad.getEventos(),ciudadTest.getEventos());
        Assert.assertEquals("No coinciden los sitios de la ciudad",ciudad.getSitios(),ciudadTest.getSitios());
    }

    @Test
    @InSequence(9)
    public void deleteCiudadTest() {
        CiudadDTO ciudad = oraculo.get(0);
        Response response = target.path(ciudadPath).path(ciudad.getId()+" ")
                .request().delete();
        Assert.assertEquals("No se borro la ciudad",NO_CONTENT, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void addSitioTest() {

        SitioDTO sitio = oraculoSitios.get(0);
        CiudadDTO ciudad = oraculo.get(0);

        Response response = target.path(sitiosPath).request()
                .post(Entity.entity(sitio, MediaType.APPLICATION_JSON));

        SitioDTO sitiosTest = (SitioDTO) response.readEntity(SitioDTO.class);
        Assert.assertEquals("No coincide el id del sitio",sitio.getId(), sitiosTest.getId());
        Assert.assertEquals("No coincide el nombre del sitio",sitio.getNombre(), sitiosTest.getNombre());
        Assert.assertEquals("No coinciden los detalles del sitio",sitio.getDetalles(), sitiosTest.getDetalles());
        Assert.assertEquals("No coincide la imagen del sitio",sitio.getImagen(), sitiosTest.getImagen());
        Assert.assertEquals("No se creo el sitio",CREATED, response.getStatus());

        response = target.path(ciudadPath).path(ciudad.getId()+"")
                .path(sitiosPath).path(sitio.getId()+"").request()
                .post(Entity.entity(sitio, MediaType.APPLICATION_JSON));

        ArrayList<SitioDTO> adicion = new ArrayList<SitioDTO>();
        adicion.add(sitio);
        ciudad.setSitios(adicion);
        
        
        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);
        Assert.assertEquals("La respuesta del servidor no fue OK",OK, response.getStatus());
        Assert.assertEquals("No hay el numero de sitios correcto",adicion.size(),ciudadTest.getSitios().size());
       
    }

    @Test
    @InSequence(6)
    public void listSitiosTest() {
        CiudadDTO ciudad = oraculo.get(0);

        Response response = target.path(ciudadPath)
                .path(ciudad.getId()+"")
                .path(sitiosPath)
                .request().get();
        
        Assert.assertEquals("La respuesta a la solicitud no fue OK", OK, response.getStatus());

        List<SitioDTO> sitiosListTest = response.readEntity(new GenericType<List<SitioDTO>>() {
        });
        
        Assert.assertEquals("No hay el numero correcto de sitios",ciudad.getSitios().size() ,sitiosListTest.size());
        Assert.assertEquals("No es el sitio correcto", ciudad.getSitios().get(0).getNombre(), sitiosListTest.get(0).getNombre());
    }
    
    @Test
    @InSequence(7)
    public void addEventoTest() {

        EventoDTO evento = oraculoEventos.get(0);
        CiudadDTO ciudad = oraculo.get(0);

        Response response = target.path(eventosPath).request()
                .post(Entity.entity(evento, MediaType.APPLICATION_JSON));

        EventoDTO eventosTest = (EventoDTO) response.readEntity(EventoDTO.class);
        Assert.assertEquals("No coincide el id del evento",evento.getId(), eventosTest.getId());
        Assert.assertEquals("No coincide el nombre del evento",evento.getNombre(), eventosTest.getNombre());
        Assert.assertEquals("No coinciden los detalles del evento",evento.getDetalles(), eventosTest.getDetalles());
        Assert.assertEquals("No coincide la imagen del evento",evento.getImagen(), eventosTest.getImagen());
        Assert.assertEquals("No coincide la fecha del evento", evento.getFechaEvento(), eventosTest.getFechaEvento()); //hay que formatear la fecha?
        Assert.assertEquals("No se creo el evento",CREATED, response.getStatus());

        response = target.path(ciudadPath).path(ciudad.getId()+"")
                .path(eventosPath).path(evento.getId()+"").request()
                .post(Entity.entity(evento, MediaType.APPLICATION_JSON));

        ArrayList<EventoDTO> adicion = new ArrayList<EventoDTO>();
        adicion.add(evento);
        ciudad.setEventos(adicion);
        
        
        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);
        Assert.assertEquals("La respuesta del servidor no fue OK",OK, response.getStatus());
        Assert.assertEquals("No hay el numero de eventos correcto",adicion.size(),ciudadTest.getEventos().size());
       
    }

    @Test
    @InSequence(8)
    public void listEventosTest() {
        CiudadDTO ciudad = oraculo.get(0);

        Response response = target.path(ciudadPath)
                .path(ciudad.getId()+"")
                .path(eventosPath)
                .request().get();
        
        Assert.assertEquals("La respuesta a la solicitud no fue OK", OK, response.getStatus());

        List<EventoDTO> eventosListTest = response.readEntity(new GenericType<List<EventoDTO>>() {
        });
        
        Assert.assertEquals("No hay el numero correcto de eventos",ciudad.getEventos().size() ,eventosListTest.size());
        Assert.assertEquals("No es el evento correcto", ciudad.getEventos().get(0).getNombre(), eventosListTest.get(0).getNombre());
    }
//
//    @Test
//    @InSequence(7)
//    public void getAuthorsTest() {
//        AuthorDTO authors = oraculoAuthors.get(0);
//        BookDTO book = oraculo.get(0);
//
//        AuthorDTO authorsTest = target.path(bookPath)
//                .path(book.getId().toString()).path(authorsPath)
//                .path(authors.getId().toString())
//                .request().get(AuthorDTO.class);
//
//        Assert.assertEquals(authors.getId(), authorsTest.getId());
//        Assert.assertEquals(authors.getName(), authorsTest.getName());
//        Assert.assertEquals(authors.getBirthDate(), authorsTest.getBirthDate());
//    }
//
//    @Test
//    @InSequence(8)
//    public void removeAuthorsTest() {
//        AuthorDTO authors = oraculoAuthors.get(0);
//        BookDTO book = oraculo.get(0);
//
//        Response response = target.path(bookPath).path(book.getId().toString())
//                .path(authorsPath).path(authors.getId().toString())
//                .request().delete();
//        Assert.assertEquals(NO_CONTENT, response.getStatus());
//    }
//
//    private static Date getMaxDate() {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 9999);
//        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
//        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
//        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
//        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
//        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
//        return c.getTime();
//    }
}