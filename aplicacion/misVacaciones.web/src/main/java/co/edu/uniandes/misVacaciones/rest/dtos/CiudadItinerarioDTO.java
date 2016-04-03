package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.ArrayList;
import java.util.Date;

/**
 * CiudadDItinerarioTO
 * Objeto de transferencia de datos de Ciudades en Itinearios.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
public class CiudadItinerarioDTO {
    private CiudadDTO ciudad;
    private ItinerarioDTO itinerario;
    private ArrayList<EventoItinerarioDTO> eventos;
    private ArrayList<SitioItinerarioDTO> sitios;
    private Date fechaIni;
    private Date fechaFin;
    
    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public ItinerarioDTO getItinerario() {
        return itinerario;
    }

    public void setItinerario(ItinerarioDTO itinerario) {
        this.itinerario = itinerario;
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

    public ArrayList<EventoItinerarioDTO> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<EventoItinerarioDTO> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<SitioItinerarioDTO> getSitios() {
        return sitios;
    }

    public void setSitios(ArrayList<SitioItinerarioDTO> sitios) {
        this.sitios = sitios;
    }
}