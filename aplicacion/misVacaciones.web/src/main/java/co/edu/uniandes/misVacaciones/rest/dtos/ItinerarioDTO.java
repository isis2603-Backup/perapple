/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.ArrayList;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class ItinerarioDTO {
    private Long id;
    private String nombre;
    private String viajero;
    private String fechaInicio;
    private String fechaFin;
    private ArrayList<CiudadDTO> ciudades;

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

    public ItinerarioDTO(Long id, String nombre, String viajero, String fechaInicio, String fechaFin) {
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
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    public String getViajero() {
        return viajero;
    }

    public void setViajero(String viajero) {
        this.viajero = viajero;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<CiudadDTO> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<CiudadDTO> ciudades) {
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
        }

         objeto +="],"
                + ", }" ;

        return objeto;
    }

}
