package co.edu.uniandes.perapple.entities;


import co.edu.uniandes.csw.crud.api.podam.strategy.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
public class ItinerarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private boolean esCurrent;


    @PodamExclude
    @OneToMany (mappedBy = "itinerario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CiudadItinerarioEntity> ciudades = new ArrayList<>();

    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @PodamStrategyValue(DateStrategy.class)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @PodamExclude
    @ManyToOne//(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private ViajeroEntity viajero;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEsCurrent(boolean esCurrent) {
        this.esCurrent = esCurrent;
    }

    public void setCiudades(List<CiudadItinerarioEntity> ciudades) {
        this.ciudades = ciudades;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getEsCurrent() {
        return esCurrent;
    }

    public List<CiudadItinerarioEntity> getCiudades() {
        return ciudades;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public ViajeroEntity getViajero() {
        return viajero;
    }

}
