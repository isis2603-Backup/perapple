package co.edu.uniandes.perapple.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author camen
 */
@Entity
public class CiudadItinerarioEntity implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @PodamExclude
    @ManyToOne
    private CiudadEntity ciudad;

    @PodamExclude
    @ManyToOne
    private ItinerarioEntity itinerario;

    @PodamExclude
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoItinerarioEntity> eventos = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SitioItinerarioEntity> sitios = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date fechaIni;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
