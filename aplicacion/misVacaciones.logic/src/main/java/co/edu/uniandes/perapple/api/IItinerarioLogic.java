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

    public ItinerarioEntity getItinerario(int id) throws Exception;

    public ItinerarioEntity createItinerario(ItinerarioEntity entity)throws BusinessLogicException;

    public void deleteItinerario(int id)throws BusinessLogicException;
    
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity)throws BusinessLogicException;

    public ViajeroEntity getViajero(int itinerarioId) throws BusinessLogicException;
    
    public List<ItinerarioEntity> getItinerariosViajero(int viajeroId);
    
    public List<CiudadItinerarioEntity> getCiudades(int itinerarioId) throws BusinessLogicException;
    
    public CiudadItinerarioEntity getCiudad(int itinerarioId, int ciudadId) throws BusinessLogicException;

    public CiudadItinerarioEntity addCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) throws BusinessLogicException;

    public void deleteCiudad(int ciudadId, int itinerarioId) throws BusinessLogicException;
    
    public CiudadItinerarioEntity updateCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) throws BusinessLogicException;

    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, int itinerarioId) throws BusinessLogicException;

    public List<SitioItinerarioEntity> getSitios(int itinerarioId, int ciudadId) throws BusinessLogicException;

    public SitioItinerarioEntity getSitio(int itinerarioId, int ciudadId, int sitioId) throws BusinessLogicException;
    
    public SitioItinerarioEntity createSitio(int itinerarioId, int ciudadId, SitioItinerarioEntity sitio) throws BusinessLogicException;
    
    public void deleteSitio(int itinerarioId, int ciudadId, int sitioId) throws BusinessLogicException;
    
    public SitioItinerarioEntity updateSitio (int itinerarioId, int ciudadId, SitioItinerarioEntity sitio) throws BusinessLogicException;

    public List<EventoItinerarioEntity> getEventos(int itinerarioId, int ciudadId) throws BusinessLogicException;

    public EventoItinerarioEntity getEvento(int itinerarioId, int ciudadId, int eventoId) throws BusinessLogicException;

    public EventoItinerarioEntity createEvento(int itinerarioId, int ciudadId, EventoItinerarioEntity evento) throws BusinessLogicException;

    public void deleteEvento(int itinerarioId, int ciudadId, int eventoId) throws BusinessLogicException;

    public EventoItinerarioEntity updateEvento (int itinerarioId, int ciudadId, EventoItinerarioEntity evento) throws BusinessLogicException;

    public ItinerarioEntity getCurrentItinerario(int idViajero) throws BusinessLogicException; 

    public ItinerarioEntity setCurrentItinerario(int idViajero, int idItinerario) throws BusinessLogicException;
}
