package co.edu.uniandes.misvacaciones.rest.dtos;

import java.util.Date;

/*
 * EventoItinerarioDTO
 * Objeto de transferencia de datos de Eventos en Itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
public class EventoItinerarioDTO {
    private int id;
    private EventoDTO evento;
    private CiudadItinerarioDTO ciudad;
    private Date fechaIni;
    private Date fechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
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

    public CiudadItinerarioDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadItinerarioDTO ciudad) {
        this.ciudad = ciudad;
    }
}
