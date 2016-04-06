package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author camen
 */
@Entity
public class CiudadItinerarioEntity extends BaseEntity implements Serializable{
    @ManyToOne
    private CiudadEntity ciudad;
    
    @ManyToOne
    private ItinerarioEntity itinerario;
    
    @OneToMany(mappedBy = "ciudad")
    private List<EventoItinerarioEntity> eventos = new ArrayList<>();
    
    @OneToMany(mappedBy = "ciudad")
    private List<SitioItinerarioEntity> sitios = new ArrayList<>();
    
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
    }

    public ItinerarioEntity getItinerario() {
        return itinerario;
    }

    public void setItinerario(ItinerarioEntity itinerario) {
        this.itinerario = itinerario;
    }

    public List<EventoItinerarioEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoItinerarioEntity> eventos) {
        this.eventos = eventos;
    }

    public List<SitioItinerarioEntity> getSitios() {
        return sitios;
    }

    public void setSitios(List<SitioItinerarioEntity> sitios) {
        this.sitios = sitios;
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
