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

    public ItinerarioEntity updateItinerario(ItinerarioEntity entity)throws BusinessLogicException;

    public void deleteItinerario(int id)throws BusinessLogicException;

    public ViajeroEntity getViajero(int itinerarioId);

    public CiudadItinerarioEntity addCiudad(CiudadItinerarioEntity ciudad, int itinerarioId);

    public void deleteCiudad(int ciudadId, int itinerarioId);

    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, int itinerarioId);

    public ItinerarioEntity getCurrentItinerario();

    public ItinerarioEntity setCurrentItinerario(ItinerarioEntity entity);

    public CiudadItinerarioEntity updateCiudad(CiudadItinerarioEntity ciudad, int itinerarioId);

    public CiudadItinerarioEntity getCiudad(int itinerarioId, int ciudadId);

    public List<CiudadItinerarioEntity> getCiudades(int itinerarioId);

    public SitioItinerarioEntity updateSitio (int itinerarioId, int ciudadId, int sitioId);

    public SitioItinerarioEntity createSitio(int itinerarioId, int ciudadId, SitioItinerarioEntity sitio);

    public void deleteSitio(int itinerarioId, int ciudadId, int sitioId);

    public List<SitioItinerarioEntity> getSitios(int itinerarioId, int ciudadId);

    public SitioItinerarioEntity getSitio(int itinerarioId, int ciudadId, int sitioId);

    public EventoItinerarioEntity createEvento(int itinerarioId, int ciudadId, EventoItinerarioEntity evento);

    public void deleteEvento(int itinerarioId, int ciudadId, int eventoId);

    public List<EventoItinerarioEntity> getEventos(int itinerarioId, int ciudadId);

    public EventoItinerarioEntity getEvento(int itinerarioId, int ciudadId, int eventoId);

    public List<ItinerarioEntity> getItinerariosViajero(int viajeroId);
}
