package co.edu.uniandes.perapple.entities;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author camen
 */
public class SitioItinerarioEntity {
    @ManyToOne
    private SitioEntity sitio;
    
    @ManyToOne
    private CiudadItinerarioEntity ciudad;
    
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public SitioEntity getSitio() {
        return sitio;
    }

    public void setSitio(SitioEntity sitio) {
        this.sitio = sitio;
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
