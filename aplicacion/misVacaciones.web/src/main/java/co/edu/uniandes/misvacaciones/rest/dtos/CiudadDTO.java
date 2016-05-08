/*
 * CiudadDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misvacaciones.rest.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;




/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Perappple
 */
@XmlRootElement
public class CiudadDTO {
    private int id;
    private String nombre;
    private String detalles;
    private List<SitioDTO> sitios;
    private List<EventoDTO> eventos;
    private String imagen;



    /**
     * @return El id de la ciudad
     */
    public int getId() {
        return id;
    }

    /**
     * @param id El nuevo id de la ciudad ?
     */
    public void setId(int id) {
        this.id = id;
    }

     /**
     * @return los detalles de la ciudad
     */
    public String getDetalles() {
        return detalles;
    }

     /**
     * @return Lista de sitios de la ciudad
     */
    public List<SitioDTO> getSitios() {
        return sitios;
    }

    public void setSitios(List<SitioDTO> sitios)
    {
        this.sitios = sitios;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    /**
     * @return Lista de eventos de la ciudad
     */
    public List<EventoDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoDTO> eventos)
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
