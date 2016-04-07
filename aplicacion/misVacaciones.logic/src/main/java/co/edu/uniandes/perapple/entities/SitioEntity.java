package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SitioEntity extends BaseEntity implements Serializable {

@Id
private int id;

@Column
private String nombre;

@Column
private String detalles;

@Column
private String imagen;

@Temporal(TemporalType.DATE)
private Date fechaSitio;

@ManyToOne
private CiudadEntity ciudad;

    public int getIdentificador() {
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
