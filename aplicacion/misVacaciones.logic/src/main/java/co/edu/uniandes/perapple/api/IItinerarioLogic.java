package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IItinerarioLogic {

    public List<ItinerarioEntity> getItinerarios();

    public ItinerarioEntity getItinerario(Long id) throws BusinessLogicException;

    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinessLogicException;

    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinessLogicException;

    public void deleteItinerario(Long id);

    public ViajeroEntity getViajero(Long itinerarioId);

    public CiudadEntity addCiudad(Long ciudadId, Long itinerarioId) throws BusinessLogicException;

    public void removeCiudad(Long ciudadId, Long itinerarioId);

    public List<CiudadEntity> replaceCiudades(List<CiudadEntity> ciudades, Long itinerarioId) throws BusinessLogicException;

}
