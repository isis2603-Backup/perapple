package co.edu.uniandes.misVacaciones.rest;

import co.edu.uniandes.misvacaciones.rest.converters.ViajeroConverter;
import co.edu.uniandes.misvacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misvacaciones.rest.dtos.ViajeroDTO;
import co.edu.uniandes.misvacaciones.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.misvacaciones.rest.resources.ViajeroResource;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ViajeroResourceIT {

    private final int OK = Status.OK.getStatusCode();
    private final int CREATED = Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Status.NO_CONTENT.getStatusCode();
    private final int ERROR = Status.INTERNAL_SERVER_ERROR.getStatusCode();
    private final int NOT_FOUND = Status.NOT_FOUND.getStatusCode();
    
    private final String viajeroPath = "viajeros";

    private final static List<ViajeroDTO> oraculo = new ArrayList<>();

    private WebTarget target;
    private final String apiPath = "api";
    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

    private final static Date FECHA_FIN = Date.from(LocalDateTime.of(2016, Month.DECEMBER, 25, 12, 0).toInstant(ZoneOffset.UTC));

    private final static Date FECHA_INI = Date.from(LocalDateTime.of(2016, Month.APRIL, 20, 12, 0).toInstant(ZoneOffset.UTC));

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.perapple:misVacaciones.logic:1.0-SNAPSHOT")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ViajeroResource.class.getPackage())
                .addPackage(ViajeroDTO.class.getPackage())
                .addPackage(ViajeroConverter.class.getPackage())
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
            ViajeroDTO viajero = factory.manufacturePojo(ViajeroDTO.class);
            viajero.setId(i + 1);
            List<ItinerarioDTO> itinerarios = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ItinerarioDTO itinerario = factory.manufacturePojo(ItinerarioDTO.class);
                itinerario.setId(i + 1);
                itinerario.setFechaFin(FECHA_FIN);
                itinerario.setFechaInicio(FECHA_INI);
                itinerarios.add(itinerario);
            }
            viajero.setItinerarios(itinerarios);
           
            oraculo.add(viajero);

        }
    }

    @Test
    @InSequence(1)
    public void createViajeroTest() {
        ViajeroDTO viajero = oraculo.get(0);
        Response response = target.path(viajeroPath).request()
                .post(Entity.entity(viajero, MediaType.APPLICATION_JSON));

        Assert.assertEquals("No se creo el viajero", CREATED, response.getStatus());
        ViajeroDTO viajeroTest = (ViajeroDTO) response.readEntity(ViajeroDTO.class);

        Assert.assertEquals(viajero.getName(), viajeroTest.getName());
        Assert.assertEquals(viajero.getEmail(), viajeroTest.getEmail());
        Assert.assertEquals(viajero.getImage(), viajeroTest.getImage());
        Assert.assertEquals(viajero.getPassword(), viajeroTest.getPassword());

    }
    
    @Test
    @InSequence(2)
    public void getViajeroById() {
        Response response = target.path(viajeroPath)
                .path(String.valueOf(oraculo.get(0).getId()))
                .request().get();
        Assert.assertNotEquals("No se encontró el viajero", NOT_FOUND, response.getStatus());

        ViajeroDTO viajeroTest = target.path(viajeroPath)
                .path(String.valueOf(oraculo.get(0).getId()))
                .request().get(ViajeroDTO.class);
        Assert.assertEquals(viajeroTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(viajeroTest.getName(),oraculo.get(0).getName());
        Assert.assertEquals(viajeroTest.getEmail(), oraculo.get(0).getEmail());
        Assert.assertEquals(viajeroTest.getImage(), oraculo.get(0).getImage());
        Assert.assertEquals(viajeroTest.getPassword(), oraculo.get(0).getPassword());
    }

    @Test
    @InSequence(3)
    public void listViajeroTest() {
        Response response = target.path(viajeroPath)
                .request().get();
        
        Assert.assertEquals("No se encontró los viajeros", OK, response.getStatus());
        List<ViajeroDTO> listViajerosTest = response.readEntity(new GenericType<List<ViajeroDTO>>() {
        });
        Assert.assertEquals(1, listViajerosTest.size());
    }

    @Test
    @InSequence(4)
    public void updateViajeroTest() {
        ViajeroDTO viajero = oraculo.get(0);
        ViajeroDTO viajeroCambiado = factory.manufacturePojo(ViajeroDTO.class);
        viajero.setEmail(viajeroCambiado.getEmail());
        viajero.setName(viajeroCambiado.getName());
        viajero.setImage(viajeroCambiado.getImage());
        viajero.setPassword(viajeroCambiado.getPassword());
        Response response = target.path(viajeroPath).path(String.valueOf(viajero.getId()))
                .request().put(Entity.entity(viajero, MediaType.APPLICATION_JSON));
        Assert.assertEquals("No se actualizó el viajero", OK, response.getStatus());
        ViajeroDTO viajeroTest = (ViajeroDTO) response.readEntity(ViajeroDTO.class);;
        Assert.assertEquals(viajero.getName(), viajeroTest.getName());
        Assert.assertEquals(viajero.getEmail(), viajeroTest.getEmail());
        Assert.assertEquals(viajero.getImage(), viajeroTest.getImage());
        Assert.assertEquals(viajero.getPassword(), viajeroTest.getPassword());
    }

    @Test
    @InSequence(5)
    public void deleteViajeroTest() {
        ViajeroDTO viajero = oraculo.get(0);
        Response response = target.path(viajeroPath).path(String.valueOf(viajero.getId()))
                .request().delete();
        Assert.assertEquals(NO_CONTENT, response.getStatus());
    }
}
