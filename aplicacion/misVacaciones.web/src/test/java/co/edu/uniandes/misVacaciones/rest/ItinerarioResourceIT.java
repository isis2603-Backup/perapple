package co.edu.uniandes.misVacaciones.rest;

import co.edu.uniandes.misVacaciones.rest.adapters.DateAdapter;
import co.edu.uniandes.misVacaciones.rest.converters.ItinerarioConverter;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.misVacaciones.rest.providers.CreatedFilter;
import co.edu.uniandes.misVacaciones.rest.resources.ItinerarioResource;
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
public class ItinerarioResourceIT {

    private final int OK = Status.OK.getStatusCode();
    private final int CREATED = Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Status.NO_CONTENT.getStatusCode();
    private final int ERROR = Status.INTERNAL_SERVER_ERROR.getStatusCode();

    private final String itinerarioPath = "itinerarios";
    private final String ciudadesPath = "ciudades";

    private final static List<ItinerarioDTO> oraculo = new ArrayList<>();
    private final static List<CiudadItinerarioDTO> oraculoCiudadesItinerario = new ArrayList<>();
    private final static List<CiudadDTO> oraculoCiudadesDTO = new ArrayList<>();

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
                .addPackage(ItinerarioResource.class.getPackage())
                .addPackage(ItinerarioDTO.class.getPackage())
                .addPackage(ItinerarioConverter.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                .addPackage(DateAdapter.class.getPackage())
                .addPackage(CreatedFilter.class.getPackage())
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

            oraculoCiudadesDTO.add(ciudad);

            ItinerarioDTO itinerario = factory.manufacturePojo(ItinerarioDTO.class);

            //modificar dto con @podamExclude para poder poner las fechas, el viajero correctamente
            itinerario.setFechaInicio(getDate(0));
            itinerario.setFechaFin(getDate(1));
            //itinerario.setViajero(null);

            //itinerario.setId(i + 1L);
            List<CiudadItinerarioDTO> ciudadItinerarioList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                CiudadItinerarioDTO ciudadItinerario = factory.manufacturePojo(CiudadItinerarioDTO.class);
                //ciudadItinerario.setId(i + 1L);
                ciudadItinerarioList.add(ciudadItinerario);
                oraculoCiudadesItinerario.add(ciudadItinerario);
            }
            if(i > 1 )
            {
            itinerario.setCiudades(ciudadItinerarioList);
            }
            oraculo.add(itinerario);


        }
    }

    @Test
    @InSequence(1)
    public void createItinerarioTest() {
        ItinerarioDTO itinerario = oraculo.get(0);

        //Prueba itinerario con fechas correctas

        Response response = target.path(itinerarioPath).request()
                .post(Entity.entity(itinerario, MediaType.APPLICATION_JSON));

        ItinerarioDTO itinerarioTest = (ItinerarioDTO) response.readEntity(ItinerarioDTO.class);

        Assert.assertEquals("No se creo el itinerario", CREATED, response.getStatus());
        Assert.assertNotNull("No hubo respuesta de itinerario creado", itinerarioTest);
        Assert.assertEquals("No coincide el nombre del itinerario",itinerario.getNombre(), itinerarioTest.getNombre());
        Assert.assertEquals("No coincide la fecha fin del itinerario",itinerario.getFechaFin(), itinerarioTest.getFechaFin());
        Assert.assertEquals("No coincide la fecha inicio del itinerario",itinerario.getFechaInicio(), itinerarioTest.getFechaInicio());
        Assert.assertEquals("No coincide el id del itinerario",itinerario.getId(), itinerarioTest.getId());
        Assert.assertEquals("No coinciden las ciudades del itinerario",itinerario.getCiudades(), itinerarioTest.getCiudades());

        //Prueba volver a crear el itinerario

         response = target.path(itinerarioPath).request()
                .post(Entity.entity(itinerario, MediaType.APPLICATION_JSON));

         //revisar que status recibe asociado a que se genera un error
          Assert.assertEquals("Se creo el itinerario", ERROR, response.getStatus());

          //prueba con fechas invertidas
          itinerario = oraculo.get(1);
          Date ini = itinerario.getFechaInicio();
          Date fin = itinerario.getFechaFin();
          itinerario.setFechaInicio(fin);
          itinerario.setFechaFin(ini);

         response = target.path(itinerarioPath).request()
                .post(Entity.entity(itinerario, MediaType.APPLICATION_JSON));

         //revisar que status recibe asociado a que se genera un error
          Assert.assertEquals("Se creo el itinerario", ERROR, response.getStatus());


    }

    @Test
    @InSequence(2)
    public void getItinerarioById() {
        ItinerarioDTO itinerarioTest = target.path(itinerarioPath)
                .path(oraculo.get(0).getId()+"")
                .request().get(ItinerarioDTO.class);

        Assert.assertNotNull("No hubo respuesta de itinerario creado", itinerarioTest);
        Assert.assertEquals("No coincide el nombre del itinerario",oraculo.get(0).getNombre(), itinerarioTest.getNombre());
        Assert.assertEquals("No coincide la fecha fin del itinerario",oraculo.get(0).getFechaFin(), itinerarioTest.getFechaFin());
        Assert.assertEquals("No coincide la fecha inicio del itinerario",oraculo.get(0).getFechaInicio(), itinerarioTest.getFechaInicio());
        Assert.assertEquals("No coincide el id del itinerario",oraculo.get(0).getId(), itinerarioTest.getId());
        Assert.assertEquals("No coinciden las ciudades del itinerario",oraculo.get(0).getCiudades(), itinerarioTest.getCiudades());

    }

    @Test
    @InSequence(3)
    public void listItinerarioTest() {
        Response response = target.path(itinerarioPath)
                .request().get();

        List<ItinerarioDTO> listItinerarioTest = response.readEntity(new GenericType<List<ItinerarioDTO>>() {
        });
        Assert.assertEquals(OK, response.getStatus());
        Assert.assertEquals(1, listItinerarioTest.size());
    }

    @Test
    @InSequence(4)
    public void updateBookTest() {
        ItinerarioDTO itinerario = oraculo.get(2);
        ItinerarioDTO itinerarioChanged = factory.manufacturePojo(ItinerarioDTO.class);

        //itinerario.setFechaInicio(getDate(0));
        //itinerario.setFechaFin(getDate(1));

        itinerario.setNombre(itinerarioChanged.getNombre());
        itinerario.setViajero(itinerarioChanged.getViajero());
        itinerario.setCiudades(itinerarioChanged.getCiudades());

        Response response = target.path(itinerarioPath).path(itinerario.getId()+"")
                .request().put(Entity.entity(itinerario, MediaType.APPLICATION_JSON));
        ItinerarioDTO itinerarioTest = (ItinerarioDTO) response.readEntity(ItinerarioDTO.class);

        //Logro hacer la actualización - según status
        Assert.assertEquals(OK, response.getStatus());

        //Prueba de modificación
        Assert.assertNotNull("No hubo respuesta de itinerario creado", itinerarioTest);
        Assert.assertEquals("No coincide el nombre del itinerario",itinerario.getNombre(), itinerarioTest.getNombre());
        Assert.assertEquals("No coincide la fecha fin del itinerario",itinerario.getFechaFin(), itinerarioTest.getFechaFin());
        Assert.assertEquals("No coincide la fecha inicio del itinerario",itinerario.getFechaInicio(), itinerarioTest.getFechaInicio());
        Assert.assertEquals("No coincide el id del itinerario",itinerario.getId(), itinerarioTest.getId());
        Assert.assertEquals("No coinciden las ciudades del itinerario",itinerario.getCiudades(), itinerarioTest.getCiudades());

    }

    @Test
    @InSequence(9)
    public void deleteItinerarioTest() {
        ItinerarioDTO itinerario = oraculo.get(0);

        //prueba de borrado
        Response response = target.path(itinerarioPath).path(itinerario.getId()+"")
                .request().delete();
        Assert.assertEquals(NO_CONTENT, response.getStatus());

//        //prueba de borrado de nuevo
//         response = target.path(itinerarioPath).path(itinerario.getId()+"")
//                .request().delete();
//        Assert.assertEquals(ERROR, response.getStatus());
//


    }

//    @Test
//    @InSequence(5)
//    public void addCiudadTest() {
//
//        CiudadItinerarioDTO ciudad = oraculoCiudadesItinerario.get(0);
//        ItinerarioDTO itinerario = oraculo.get(0);
//
//        Response response = target.path("ciudades").request()
//                .post(Entity.entity(ciudad, MediaType.APPLICATION_JSON));
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
    private static Date getDate( int type) {
        Calendar c = Calendar.getInstance();
        if(type == 0){
        c.set(Calendar.YEAR, 1999);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMinimum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
        }
        else
        {c.set(Calendar.YEAR, 9999);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));

        }
        return c.getTime();
    }
}
