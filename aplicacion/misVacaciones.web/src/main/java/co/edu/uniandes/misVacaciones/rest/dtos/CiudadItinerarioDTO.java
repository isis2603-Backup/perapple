package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.Date;

/**
 * CiudadDItinerarioTO
 * Objeto de transferencia de datos de Ciudades en Itinearios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
public class CiudadItinerarioDTO {
    private int id;
    private CiudadDTO ciudad;
    private ItinerarioDTO itinerario;
    private Date fechaIni;
    private Date fechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public ItinerarioDTO getItinerario() {
        return itinerario;
    }

    public void setItinerario(ItinerarioDTO itinerario) {
        this.itinerario = itinerario;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}