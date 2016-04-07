/*
 * SitioDTO
 * Objeto de transferencia de datos de Sitios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de Eventos.
 * @author Perapple
 */
public class SitioDTO {
    private int id;
    private String nombre;
    private String detalles;
    private String imagen;
    private Date fechaSitio;

    /**
     * Constructor por defecto
     */
    public SitioDTO() {
	}


    public SitioDTO(int id, String nombre, String detalles, String imagen, Date fechaSitio) {
        this.id = id;
        this.nombre = nombre;
        this.detalles = detalles;
        this.imagen = imagen;
        this.fechaSitio = fechaSitio;
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


    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + id
                + ", nombre : \"" + nombre + "\" "
                + ", descripcion: \""+ detalles+"\" "
                + ", fechaSitio: \""+ fechaSitio+"\" "
                + ", imagen : \""+imagen+"\"}" ;
    }
}
