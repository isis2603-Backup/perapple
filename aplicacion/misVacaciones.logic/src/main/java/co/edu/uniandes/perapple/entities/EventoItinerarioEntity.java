package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author camen
 */
@Entity
public class EventoItinerarioEntity implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @PodamExclude
    @ManyToOne
    private EventoEntity evento;

    @PodamExclude
    @ManyToOne
    private CiudadItinerarioEntity ciudad;

    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaIni;

    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
