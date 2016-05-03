package co.edu.uniandes.perapple.entities;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class CiudadEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @PodamExclude
    private int id;

    @Column
    private String nombre;

    @Column
    private String detalles;

    @Column
    private String imagen;


    @PodamExclude
    @OneToMany(mappedBy="ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SitioEntity> sitios;

    @PodamExclude
    @OneToMany(mappedBy="ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoEntity> eventos;

    @PodamExclude
    @OneToMany(mappedBy="ciudad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CiudadItinerarioEntity> ciudadesItinerario;

    public void setCiudadesItinerario(List<CiudadItinerarioEntity> ciudadesItinerario) {
        this.ciudadesItinerario = ciudadesItinerario;
    }

    public List<CiudadItinerarioEntity> getCiudadesItinerario() {
        return ciudadesItinerario;
    }

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

    public void setDetalles(String detalles)
    {
        this.detalles=detalles;
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
