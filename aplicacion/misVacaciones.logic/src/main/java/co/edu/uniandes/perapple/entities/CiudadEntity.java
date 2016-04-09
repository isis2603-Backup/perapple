package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class CiudadEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String detalles;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToMany
    private List<SitioEntity> sitios;

    @OneToMany
    private List<EventoEntity> eventos;

    @Column
    private String imagen;

    public int getId()
    {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDetalles()
    {
        return detalles;
    }

    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    public Date getFechaFin()
    {
        return fechaFin;
    }

    public List<SitioEntity> getSitios() {
        return sitios;
    }

    public List<EventoEntity> getEventos()
    {
        return eventos;
    }

    public String getImagen()
    {
        return imagen;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }

    public void setDetalles(String Detalles)
    {
        this.detalles=Detalles;
    }


    public void setFechaInicio(Date fecha)
    {
        this.fechaInicio=fecha;
    }

    public void setFechaFin(Date fecha)
    {
        this.fechaFin=fecha;
    }

    public void setSitios(List<SitioEntity> sitios)
    {
        this.sitios=sitios;
    }

    public void setEventos(List<EventoEntity> eventos)
    {
        this.eventos=eventos;
    }

    public void setImagen(String imagen)
    {
        this.imagen=imagen;
    }
}
