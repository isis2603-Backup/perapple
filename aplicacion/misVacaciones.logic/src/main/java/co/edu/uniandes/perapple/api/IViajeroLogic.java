package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IViajeroLogic {



    public List<ViajeroEntity> getItinerarios();

    public ViajeroEntity getViajero(int id) throws BusinessLogicException;

    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException;

    public ViajeroEntity updateViajero(ViajeroEntity entity)throws BusinessLogicException;

    public void deleteViajero(int id) throws BusinessLogicException;

    public ItinerarioEntity addItinerario(Long bookId, Long authorId) throws BusinessLogicException;

    public void removeItinerario(Long bookId, Long authorId);

    public List<ItinerarioEntity> replaceItinerarios(List<ItinerarioEntity> books, Long authorId) throws BusinessLogicException;

    public List<ItinerarioEntity> getItinerarios(int viajeroId)throws BusinessLogicException;

    public ItinerarioEntity getItinerario(int viajeroId, int itinerarioId);
}
