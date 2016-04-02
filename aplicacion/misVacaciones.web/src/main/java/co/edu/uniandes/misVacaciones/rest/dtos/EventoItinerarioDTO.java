package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.Date;

/*
 * EventoItinerarioDTO
 * Objeto de transferencia de datos de Eventos en Itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
public class EventoItinerarioDTO {
    private EventoDTO evento;
    private ItinerarioDTO itinerario;
    private Date fechaIni;
    private Date fechaFin;
    
    public EventoItinerarioDTO(){
        
    }

    public EventoItinerarioDTO(EventoDTO evento, ItinerarioDTO itinerario, Date fechaIni, Date fechaFin) {
        this.evento = evento;
        this.itinerario = itinerario;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public EventoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoDTO evento) {
        this.evento = evento;
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
