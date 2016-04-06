package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IViajeroLogic {

    public List<ViajeroEntity> getItinerarios();

    public ViajeroEntity getViajero(Long id) throws BusinessLogicException;

    public ViajeroEntity createViajero(ViajeroEntity entity);

    public ViajeroEntity updateViajero(ViajeroEntity entity);

    public void deleteViajero(Long id);

    public ItinerarioEntity addItinerario(Long bookId, Long authorId) throws BusinessLogicException;

    public void removeItinerario(Long bookId, Long authorId);

    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> books, Long authorId) throws BusinessLogicException;

    public List<ItinerarioEntity> getItinerarios(Long viajeroId);

    public ItinerarioEntity getItinerario(Long viajeroId, Long itinerarioId);
}
