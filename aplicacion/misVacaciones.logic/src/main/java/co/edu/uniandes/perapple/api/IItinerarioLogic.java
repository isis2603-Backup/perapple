package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IItinerarioLogic {

    public List<ItinerarioEntity> getItinerarios();

    public ItinerarioEntity getItinerario(Long id) throws BusinessLogicException;

    public ItinerarioEntity createItinerario(ItinerarioEntity entity);

    public ItinerarioEntity updateItinerario(ItinerarioEntity entity);

    public void deleteItinerario(Long id);

    public ViajeroEntity getViajero(Long itinerarioId);

    public CiudadItinerarioEntity addCiudad(CiudadItinerarioEntity ciudad, Long itinerarioId);

    public void deleteCiudad(Long ciudadId, Long itinerarioId);

    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, Long itinerarioId);

    public ItinerarioEntity getCurrentItinerario();

    public ItinerarioEntity setCurrentItinerario(ItinerarioEntity entity);
    
    public CiudadItinerarioEntity updateCiudad(CiudadItinerarioEntity ciudad, Long itinerarioId);
    
    public CiudadItinerarioEntity getCiudad(Long itinerarioId, Long ciudadId);
    
    public List<CiudadItinerarioEntity> getCiudades(Long itinerarioId);
    
    public SitioItinerarioEntity updateSitio (Long itinerarioId, Long ciudadId, Long sitioId);
    
    public SitioItinerarioEntity createSitio(Long itinerarioId, Long ciudadId, SitioItinerarioEntity sitio);
    
    public void deleteSitio(Long itinerarioId, Long ciudadId, Long sitioId);
    
    public List<SitioItinerarioEntity> getSitios(Long itinerarioId, Long ciudadId);
    
    public SitioItinerarioEntity getSitio(Long itinerarioId, Long ciudadId, Long sitioId);
    
    public EventoItinerarioEntity createEvento(Long itinerarioId, Long ciudadId, EventoItinerarioEntity evento);
    
    public void deleteEvento(Long itinerarioId, Long ciudadId, Long eventoId);
    
    public List<EventoItinerarioEntity> getEventos(Long itinerarioId, Long ciudadId);
    
    public EventoItinerarioEntity getEvento(Long itinerarioId, Long ciudadId, Long eventoId);
    
    public List<ItinerarioEntity> getItinerariosViajero(Long viajeroId);
}
