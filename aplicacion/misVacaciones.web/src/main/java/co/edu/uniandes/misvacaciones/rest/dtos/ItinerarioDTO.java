/*
 * ItinerarioDTO
 * Objeto de transferencia de datos de Itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misvacaciones.rest.dtos;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Objeto de transferencia de datos de Itinerarios.
 * @author Asistente
 */
@XmlRootElement
public class ItinerarioDTO {
    private int id;
    private String nombre;
    @PodamExclude
    private ViajeroDTO viajero;
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaFin;
    @PodamExclude
    private List<CiudadItinerarioDTO> ciudades;

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

    public void setFechaInicio (Date fechaInicio)
    {
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

}

