package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CiudadEntity extends BaseEntity implements Serializable {

  
@Id
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
  private ArrayList<SitioEntity> sitios;

  @OneToMany
    private ArrayList<EventoEntity> eventos;

@Column
  private String imagen;


public int getIdentificador()
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

public Date getFecahInicio()
{
    return fechaInicio;
}

public Date getFechaFin()
{
    return fechaFin;
}

 public ArrayList<SitioEntity> getSitios() {
        return sitios;
    }

 public ArrayList<EventoEntity> getEventos()
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

 public void setnombre(String nombre)
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

 public void setSiios(ArrayList<SitioEntity> sitios)
 {
     this.sitios=sitios;
 }

 public void setEventos(ArrayList<EventoEntity> eventos)
 {
     this.eventos=eventos;
 }

 public void setImagen(String imagen)
 {
     this.imagen=imagen;
 }
}
