/*
 * CiudadDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.ArrayList;


/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Perappple
 */
public class CiudadDTO {
    private Long id;
    private String nombre;
    private String detalles;
    private String fechaInicio;
    private String fechaFin;
    private ArrayList<SitioDTO> sitios;
    private ArrayList<EventoDTO> eventos;
    private String imagen;

    /**
     * Constructor por defecto
     */
    public CiudadDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param nombre nombre de la ciudad
     * @param detalles detalles de la ciudad
     * @param imagen imagen respresentativa de la ciudad
     * @param fechaInicio fecha de inicio visita ciudad
     * @param fechaFin fecha fin visita ciudad
     */
    public CiudadDTO(Long id, String nombre, String detalles, String imagen, String fechaInicio, String fechaFin) {
		super();
		this.id = id;
		this.nombre = nombre;
                this.detalles=detalles;
                this.imagen=imagen;
                this.fechaInicio=fechaInicio;
                this.fechaFin= fechaFin;
                this.eventos= new ArrayList<>();
                this.sitios= new ArrayList<>();
	}

	/**
     * @return El id de la ciudad
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El nuevo id de la ciudad ?
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * @return los detalles de la ciudad
     */
    public String getDetalles() {
        return detalles;
    }

     /**
     * @return La fecha inicio visita en la ciudad
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

     /**
     * @return La fecha fin de visita en la ciudad
     */
    public String getFechaFin() {
        return fechaFin;
    }

     /**
     * @return Lista de sitios de la ciudad
     */
    public ArrayList<SitioDTO> getSitios() {
        return sitios;
    }

   public void setSitios(ArrayList<SitioDTO> sitios)
   {
       this.sitios = sitios;
   }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    /**
     * @return Lista de eventos de la ciudad
     */
    public ArrayList<EventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<EventoDTO> eventos)
    {
        this.eventos = eventos;
    }

     /**
     * @return La imagen representativa de la ciudad
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @return El nombre de la ciudad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nuevo nombre de la ciudad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convierte el objeto a una cadena
     * @return
     */
    @Override
    public String toString() {

        String ciudad= " ";

        ciudad = "{ id : " + id
                + ", nombre : \"" + nombre
                + "\" , fechaInicio: "+fechaInicio
                +" , fechaFin: "+fechaFin
                +" , sitios: [";

        if(sitios!=null){

            for(int i = 0; i<sitios.size();i++)
        {
            if(i<sitios.size()-1 && sitios!=null)
            {

                ciudad += sitios.get(i).toString()+" , ";
            }
            else
            {
                ciudad += sitios.get(i).toString();
            }
        }
        }

         ciudad +="],"
                 + " eventos: [";

         if(eventos!=null){

             for(int i = 0; i<eventos.size();i++)
        {
            if(i<eventos.size()-1 )
            {

                ciudad += eventos.get(i).toString()+" , ";
            }
            else
            {
                ciudad += eventos.get(i).toString();
            }
        }
         }

          ciudad +="], detalles: "+
                  detalles
                  + ", imagen: \""+imagen+" \" }";


        return ciudad;
    }
}
