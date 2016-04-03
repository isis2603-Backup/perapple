package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
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

    public void removeCiudad(Long ciudadId, Long itinerarioId);

    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, Long itinerarioId);

    public ItinerarioEntity getCurrentItinerario();

    public ItinerarioEntity setCurrentItinerario(ItinerarioEntity entity);

}
