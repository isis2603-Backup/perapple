/*
 * ItinerarioDTO
 * Objeto de transferencia de datos de Itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Objeto de transferencia de datos de Itinerarios.
 * @author Asistente
 */
public class ItinerarioDTO {
    private int id;
    private String nombre;
    private ViajeroDTO viajero;
    private Date fechaInicio;
    private Date fechaFin;
    private List<CiudadItinerarioDTO> ciudades;

    /**
     * Constructor por defecto
     */
    public ItinerarioDTO() {
	}


    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param nombre nombre de la ciudad
     * @param viajero
     * @param fechaInicio
     * @param fechaFin
     */

    public ItinerarioDTO(int id, String nombre, ViajeroDTO viajero, Date fechaInicio, Date fechaFin) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.viajero = viajero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ciudades = new ArrayList<>();
    }
	/**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    public ViajeroDTO getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CiudadItinerarioDTO> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<CiudadItinerarioDTO> ciudades) {
        this.ciudades = ciudades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convierte el objeto a una cadena
     * @return objeto
     */
    @Override
    public String toString() {

        String objeto = " ";
        objeto = "{ id : " + id
                + ", nombre : \"" + nombre
                + "\" , fechaInicio: "+fechaInicio
                +" , fechaFin: "+fechaFin
                +" , ciudades: [";
        //for \(*0*)/
        if(ciudades!=null){
        for(int i = 0; i<ciudades.size();i++)
        {
            if(i<ciudades.size()-1)
            {

                objeto += ciudades.get(i).toString()+" , ";
            }
            else
            {
                objeto += ciudades.get(i).toString();
            }
        }}

         objeto +="]"
                + " }" ;

        return objeto;
    }

}
