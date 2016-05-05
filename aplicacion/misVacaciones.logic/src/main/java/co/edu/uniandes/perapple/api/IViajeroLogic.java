package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IViajeroLogic {

    public List<ViajeroEntity> getViajeros() ;

    public ViajeroEntity getViajero(int id) throws BusinessLogicException;

    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException;

    public ViajeroEntity updateViajero(ViajeroEntity entity)throws BusinessLogicException;

    public void deleteViajero(int id) throws BusinessLogicException;
}
