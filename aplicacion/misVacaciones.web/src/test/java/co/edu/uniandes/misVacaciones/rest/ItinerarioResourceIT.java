package co.edu.uniandes.misVacaciones.rest;

import co.edu.uniandes.misVacaciones.rest.adapters.DateAdapter;
import co.edu.uniandes.misVacaciones.rest.converters.ItinerarioConverter;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.CiudadItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.misVacaciones.rest.dtos.ViajeroDTO;
import co.edu.uniandes.misVacaciones.rest.mappers.EJBExceptionMapper;
import co.edu.uniandes.misVacaciones.rest.providers.CreatedFilter;
import co.edu.uniandes.misVacaciones.rest.resources.ItinerarioResource;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ItinerarioResourceIT {

    private final int OK = Status.OK.getStatusCode();
    private final int CREATED = Status.CREATED.getStatusCode();
    private final int NO_CONTENT = Status.NO_CONTENT.getStatusCode();
    private final int ERROR = Status.INTERNAL_SERVER_ERROR.getStatusCode();
    private final int NOT_FOUND = Status.NOT_FOUND.getStatusCode();

    private final String itinerarioPath = "itinerarios";
    private final String ciudadesPath = "ciudades";
    private final String viajeroPath = "viajeros";

    private final static List<ItinerarioDTO> oraculo = new ArrayList<>();
    private final static List<CiudadItinerarioDTO> oraculoCiudadesItinerario = new ArrayList<>();
    private final static List<CiudadDTO> oraculoCiudadesDTO = new ArrayList<>();
    private static ViajeroDTO oraculoViajero = new ViajeroDTO();
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


            ViajeroDTO viajero = factory.manufacturePojo(ViajeroDTO.class);
        for (int i = 0; i < 5; i++) {

            CiudadDTO ciudad = factory.manufacturePojo(CiudadDTO.class);

            oraculoCiudadesDTO.add(ciudad);

            ItinerarioDTO itinerario = factory.manufacturePojo(ItinerarioDTO.class);

            //modificar dto con @podamExclude para poder poner las fechas, el viajero correctamente
            itinerario.setFechaInicio(getDate(0));
            itinerario.setFechaFin(getDate(1));
            itinerario.setViajero(viajero);

            //itinerario.setId(i + 1L);
            List<CiudadItinerarioDTO> ciudadItinerarioList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                CiudadItinerarioDTO ciudadItinerario = factory.manufacturePojo(CiudadItinerarioDTO.class);
                //ciudadItinerario.setId(i + 1L);
                ciudadItinerario.setFechaIni(getDate(2016, Calendar.APRIL, Calendar.MONDAY));
                ciudadItinerario.setFechaIni(getDate(2016, Calendar.APRIL, Calendar.FRIDAY));


                ciudadItinerarioList.add(ciudadItinerario);
                oraculoCiudadesItinerario.add(ciudadItinerario);
            }
            if(i > 1 )
            {
            itinerario.setCiudades(ciudadItinerarioList);
            }
            oraculo.add(itinerario);


        }
        oraculoViajero = viajero;
        viajero.setItinerarios(oraculo);
    }

    @Test
    @InSequence(1)
    public void createItinerarioTest() {

        //Crea el viajero
        Response responseV = target.path(viajeroPath).request().post(Entity.entity(oraculoViajero, MediaType.APPLICATION_JSON));
        Assert.assertEquals("No se creo el viajero", OK, responseV.getStatus());
        ItinerarioDTO itinerario = oraculo.get(0);

        //Prueba itinerario con fechas correctas
       Response response = target.path(itinerarioPath).request()
                .post(Entity.entity(itinerario, MediaType.APPLICATION_JSON));


        Assert.assertEquals("No se creo el itinerario", OK, response.getStatus());
        ItinerarioDTO itinerarioTest = (ItinerarioDTO) response.readEntity(ItinerarioDTO.class);

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

         Response response =target.path(itinerarioPath)
                .path(oraculo.get(0).getId()+"")
                .request().get();

         Assert.assertNotEquals("No se encontro el itinerario",NOT_FOUND, response.getStatus());

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

        Assert.assertEquals(OK, response.getStatus());
        List<ItinerarioDTO> listItinerarioTest = response.readEntity(new GenericType<List<ItinerarioDTO>>() {
        });

        Assert.assertEquals(1, listItinerarioTest.size());
    }

    @Test
    @InSequence(4)
    public void updateItinerarioTest() {
        ItinerarioDTO itinerario = oraculo.get(2);
        ItinerarioDTO itinerarioChanged = factory.manufacturePojo(ItinerarioDTO.class);

        //itinerario.setFechaInicio(getDate(0));
        //itinerario.setFechaFin(getDate(1));

        itinerario.setNombre(itinerarioChanged.getNombre());
        itinerario.setViajero(itinerarioChanged.getViajero());
        itinerario.setCiudades(itinerarioChanged.getCiudades());

        Response response = target.path(itinerarioPath).path(itinerario.getId()+"")
                .request().put(Entity.entity(itinerario, MediaType.APPLICATION_JSON));

        //Logro hacer la actualización - según status
        Assert.assertEquals(OK, response.getStatus());

        ItinerarioDTO itinerarioTest = (ItinerarioDTO) response.readEntity(ItinerarioDTO.class);



        //Prueba de modificación
        Assert.assertNotNull("No hubo respuesta de itinerario creado", itinerarioTest);
        Assert.assertEquals("No coincide el nombre del itinerario",itinerario.getNombre(), itinerarioTest.getNombre());
        Assert.assertEquals("No coincide la fecha fin del itinerario",itinerario.getFechaFin(), itinerarioTest.getFechaFin());
        Assert.assertEquals("No coincide la fecha inicio del itinerario",itinerario.getFechaInicio(), itinerarioTest.getFechaInicio());
        Assert.assertEquals("No coincide el id del itinerario",itinerario.getId(), itinerarioTest.getId());
        Assert.assertEquals("No coinciden las ciudades del itinerario",itinerario.getCiudades(), itinerarioTest.getCiudades());

    }

    @Test
    @InSequence(5)
    public void deleteItinerarioTest() {
        ItinerarioDTO itinerario = oraculo.get(0);

        //prueba de borrado
        Response response = target.path(itinerarioPath).path(itinerario.getId()+"")
                .request().delete();
        Assert.assertEquals("No se borro el itinerario",NO_CONTENT, response.getStatus());

//        //prueba de borrado de nuevo
//         response = target.path(itinerarioPath).path(itinerario.getId()+"")
//                .request().delete();
//        Assert.assertEquals(ERROR, response.getStatus());
//


    }

    @Test
    @InSequence(6)
    public void addCiudadTest() {
        CiudadDTO ciudadDTO = oraculoCiudadesDTO.get(0);
        CiudadItinerarioDTO ciudadItinerario = oraculoCiudadesItinerario.get(0);
        ItinerarioDTO itinerario = oraculo.get(0);

        //Creacion de una ciudad
        Response response = target.path("ciudades").request()
                .post(Entity.entity(ciudadDTO, MediaType.APPLICATION_JSON));

        Assert.assertEquals("No se creo la ciudad",CREATED, response.getStatus());

        CiudadDTO ciudadTest = (CiudadDTO) response.readEntity(CiudadDTO.class);
        Assert.assertEquals("la ciudad no tiene el mismo id ",ciudadDTO.getId(), ciudadTest.getId());
        Assert.assertEquals("la ciudad no tiene los mismos detalles",ciudadDTO.getDetalles(), ciudadTest.getDetalles());
        Assert.assertEquals("la ciudad no tiene los mismos eventos",ciudadDTO.getEventos(), ciudadTest.getEventos());
        Assert.assertEquals("la ciudad no tiene los mismos sitios",ciudadDTO.getSitios(), ciudadTest.getSitios());
        Assert.assertEquals("la ciudad no tiene la misma imagen",ciudadDTO.getImagen(), ciudadTest.getImagen());
        Assert.assertEquals("la ciudad no tiene el mismo nombre",ciudadDTO.getNombre(), ciudadTest.getNombre());

        //creacion del itinerario
        response = target.path("itinerarios").request()
                .post(Entity.entity(itinerario, MediaType.APPLICATION_JSON));


        Assert.assertEquals("No se creo el itinerario",CREATED, response.getStatus());

        //adiciona a ciudadItinerario la ciudad creada
        ciudadItinerario.setCiudad(ciudadDTO);

        //operacion de adicion de la ciudadItinerario
        response = target.path(itinerarioPath).path(itinerario.getId()+"")
                .path(ciudadesPath).request()
                .post(Entity.entity(ciudadItinerario, MediaType.APPLICATION_JSON));


        //Crea una lista con la ciudad Itinerario con ciudad inicial
        ArrayList<CiudadItinerarioDTO> adicion = new ArrayList<CiudadItinerarioDTO>();
        //Adiciona la ciudadItinerario a la lista de ciudadesItinerario
        adicion.add(ciudadItinerario);
        //adiciona la lista de ciudadesItinerario al itinerario
        itinerario.setCiudades(adicion);

        //itinerario de respuesta
        ItinerarioDTO itinerarioTest = (ItinerarioDTO) response.readEntity(ItinerarioDTO.class);

        Assert.assertEquals("Respuesta del servidor no fue OK",OK, response.getStatus());
        Assert.assertEquals("No hay el numero de ciudadItinerario correcto",  adicion.size(), itinerarioTest.getCiudades().size() );
        Assert.assertEquals("No se agrego la ciudad correctamente",ciudadItinerario.getCiudad(), itinerarioTest.getCiudades().get(0).getCiudad());

    }

    @Test
    @InSequence(7)
    public void listCiudadesTest() {
        ItinerarioDTO itinerario = oraculo.get(0);

        Response response = target.path(itinerarioPath)
                .path(itinerario.getId()+"")
                .path(ciudadesPath)
                .request().get();

        Assert.assertEquals("La respuesta a la solicitud no fue OK", OK, response.getStatus());

        List<CiudadItinerarioDTO> ciudadItinerarioListTest = response.readEntity(new GenericType<List<CiudadItinerarioDTO>>() {
        });
        Assert.assertEquals("No hay el numero correcto de ciudadesitinerarios",itinerario.getCiudades().size(), ciudadItinerarioListTest.size());
        Assert.assertEquals("No es la ciudad correcta", itinerario.getCiudades().get(0).getCiudad() , ciudadItinerarioListTest.get(0).getCiudad());
    }

//    @Test
//    @InSequence(8)
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
//    @InSequence(9)
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
    private static Date getDate( int year, int month, int day  ) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_YEAR, day);
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));

        return c.getTime();
    }
}
