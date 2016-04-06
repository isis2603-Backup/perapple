package co.edu.uniandes.perapple.entities;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author camen
 */
public class EventoItinerarioEntity {
    @ManyToOne
    private EventoEntity evento;
    
    @ManyToOne
    private CiudadItinerarioEntity ciudad;
    
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public EventoEntity getEvento() {
        return evento;
    }

    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    public CiudadItinerarioEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadItinerarioEntity ciudad) {
        this.ciudad = ciudad;
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
