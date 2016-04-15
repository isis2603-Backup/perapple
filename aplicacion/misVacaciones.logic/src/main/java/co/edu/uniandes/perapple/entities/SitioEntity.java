package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
public class SitioEntity implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;

@Column
private String nombre;

@Column
private String detalles;

@Column
private String imagen;

@PodamStrategyValue(DateStrategy.class)
@Temporal(TemporalType.DATE)
private Date fechaSitio;

@PodamExclude
@ManyToOne
private CiudadEntity ciudad;

@PodamExclude
@OneToMany (mappedBy = "sitio", cascade = CascadeType.ALL, orphanRemoval = true)
private List<SitioItinerarioEntity> sitiosItinerario;


    public void setSitiosItinerario(List<SitioItinerarioEntity> sitiosItinerario) {
        this.sitiosItinerario = sitiosItinerario;
    }

    public List<SitioItinerarioEntity> getSitiosItinerario() {
        return sitiosItinerario;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaSitio() {
        return fechaSitio;
    }

    public void setFechaSitio(Date fechaSitio) {
        this.fechaSitio = fechaSitio;
    }

    public CiudadEntity getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
    }






}
