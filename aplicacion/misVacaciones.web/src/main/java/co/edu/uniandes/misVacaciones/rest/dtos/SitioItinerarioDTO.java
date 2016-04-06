package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.Date;

/*
 * SitioItinerarioDTO
 * Objeto de transferencia de datos de Sitios en Itinerarios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
public class SitioItinerarioDTO {
    private SitioDTO sitio;
    private Date fechaIni;
    private Date fechaFin;

    public SitioDTO getSitio() {
        return sitio;
    }

    public void setSitio(SitioDTO sitio) {
        this.sitio = sitio;
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
