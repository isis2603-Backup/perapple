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
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
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
    private final String apiPath = "/api";
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
    public void createCiudadTest() {
        CiudadDTO ciudad = oraculo.get(0);
        Response response = target.path(ciudadPath).request()
                .post(Entity.entity(ciudad, MediaType.APPLICATION_JSON));


        Assert.assertEquals(CREATED, response.getStatus());

        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);

        Assert.assertEquals(ciudad.getNombre(), ciudadTest.getNombre());
    }
    @Test
    @InSequence(2)
    public void getCiudadById() {
        Response response = target.path(ciudadPath)
                .path(String.valueOf(oraculo.get(0).getId()))
                .request().get();
        Assert.assertNotEquals("No se encontróla ciudad", NOT_FOUND, response.getStatus());

        CiudadDTO ciudadTest = target.path(ciudadPath)
                .path(String.valueOf(oraculo.get(0).getId()))
                .request().get(CiudadDTO.class);
        Assert.assertEquals(ciudadTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(ciudadTest.getNombre(), oraculo.get(0).getNombre());
        Assert.assertEquals(ciudadTest.getDetalles(), oraculo.get(0).getDetalles());



    }
    @Test
    @InSequence(3)
    public void listCiudadesTest() {
        Response response = target.path(ciudadPath)
                .request().get();

        Assert.assertEquals("No se encontró las ciudades", OK, response.getStatus());
        List<CiudadDTO> listCiudadesTest = response.readEntity(new GenericType<List<CiudadDTO>>() {
        });
        Assert.assertEquals(1, listCiudadesTest.size());
    }
    @Test
    @InSequence(4)
    public void updateCiudadTest() {
        CiudadDTO ciudad= oraculo.get(0);
        CiudadDTO ciudadCambiado = factory.manufacturePojo(CiudadDTO.class);
        ciudad.setNombre(ciudadCambiado.getNombre());
        ciudad.setDetalles(ciudadCambiado.getDetalles());

        Response response = target.path(ciudadPath).path(String.valueOf(ciudad.getId()))
                .request().put(Entity.entity(ciudad, MediaType.APPLICATION_JSON));
        Assert.assertEquals("No se actualizó la ciudad", OK, response.getStatus());
        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);;
        Assert.assertEquals(ciudad.getNombre(), ciudadTest.getNombre());
        Assert.assertEquals(ciudad.getDetalles(), ciudadTest.getDetalles());

    }
    @Test
    @InSequence(5)
    public void deleteViajeroTest() {
        CiudadDTO ciudad = oraculo.get(0);
        Response response = target.path(ciudadPath).path(String.valueOf(ciudad.getId()))
                .request().delete();
        Assert.assertEquals(NO_CONTENT, response.getStatus());
    }
//    @Test
//    @InSequence(2)
//    public void getBookById() {
//        BookDTO bookTest = target.path(bookPath)
//                .path(oraculo.get(0).getId().toString())
//                .request().get(BookDTO.class);
//        Assert.assertEquals(bookTest.getId(), oraculo.get(0).getId());
//        Assert.assertEquals(bookTest.getName(), oraculo.get(0).getName());
//        Assert.assertEquals(bookTest.getDescription(), oraculo.get(0).getDescription());
//        Assert.assertEquals(bookTest.getIsbn(), oraculo.get(0).getIsbn());
//        Assert.assertEquals(bookTest.getImage(), oraculo.get(0).getImage());
//    }
//
//    @Test
//    @InSequence(3)
//    public void listBookTest() {
//        Response response = target.path(bookPath)
//                .request().get();
//
//        List<BookDTO> listBookTest = response.readEntity(new GenericType<List<BookDTO>>() {
//        });
//        Assert.assertEquals(OK, response.getStatus());
//        Assert.assertEquals(1, listBookTest.size());
//    }
//
//    @Test
//    @InSequence(4)
//    public void updateBookTest() {
//        BookDTO book = oraculo.get(0);
//        BookDTO bookChanged = factory.manufacturePojo(BookDTO.class);
//        bookChanged.setPublishDate(getMaxDate());
//        book.setName(bookChanged.getName());
//        book.setDescription(bookChanged.getDescription());
//        book.setIsbn(bookChanged.getIsbn());
//        book.setImage(bookChanged.getImage());
//        book.setPublishDate(bookChanged.getPublishDate());
//        Response response = target.path(bookPath).path(book.getId().toString())
//                .request().put(Entity.entity(book, MediaType.APPLICATION_JSON));
//        BookDTO bookTest = (BookDTO) response.readEntity(BookDTO.class);
//        Assert.assertEquals(OK, response.getStatus());
//        Assert.assertEquals(book.getName(), bookTest.getName());
//        Assert.assertEquals(book.getDescription(), bookTest.getDescription());
//        Assert.assertEquals(book.getIsbn(), bookTest.getIsbn());
//        Assert.assertEquals(book.getImage(), bookTest.getImage());
//    }
//
//    @Test
//    @InSequence(9)
//    public void deleteBookTest() {
//        BookDTO book = oraculo.get(0);
//        Response response = target.path(bookPath).path(book.getId().toString())
//                .request().delete();
//        Assert.assertEquals(NO_CONTENT, response.getStatus());
//    }
//
//    @Test
//    @InSequence(5)
//    public void addAuthorsTest() {
//
//        AuthorDTO authors = oraculoAuthors.get(0);
//        BookDTO book = oraculo.get(0);
//
//        Response response = target.path("authors").request()
//                .post(Entity.entity(authors, MediaType.APPLICATION_JSON));
//
//        AuthorDTO authorsTest = (AuthorDTO) response.readEntity(AuthorDTO.class);
//        Assert.assertEquals(authors.getId(), authorsTest.getId());
//        Assert.assertEquals(authors.getName(), authorsTest.getName());
//        Assert.assertEquals(authors.getBirthDate(), authorsTest.getBirthDate());
//        Assert.assertEquals(CREATED, response.getStatus());
//
//        response = target.path(bookPath).path(book.getId().toString())
//                .path(authorsPath).path(authors.getId().toString()).request()
//                .post(Entity.entity(authors, MediaType.APPLICATION_JSON));
//
//        authorsTest = (AuthorDTO) response.readEntity(AuthorDTO.class);
//        Assert.assertEquals(OK, response.getStatus());
//        Assert.assertEquals(authors.getId(), authorsTest.getId());
//    }
//
//    @Test
//    @InSequence(6)
//    public void listAuthorsTest() {
//        BookDTO book = oraculo.get(0);
//
//        Response response = target.path(bookPath)
//                .path(book.getId().toString())
//                .path(authorsPath)
//                .request().get();
//
//        List<AuthorDTO> authorsListTest = response.readEntity(new GenericType<List<AuthorDTO>>() {
//        });
//        Assert.assertEquals(OK, response.getStatus());
//        Assert.assertEquals(1, authorsListTest.size());
//    }
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
