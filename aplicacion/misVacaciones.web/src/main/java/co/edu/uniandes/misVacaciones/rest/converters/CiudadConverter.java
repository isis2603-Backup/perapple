package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class CiudadConverter {

    public static List<CiudadDTO> listEntity2DTO(List<CiudadEntity> ciudades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static CiudadDTO fullEntity2DTO(CiudadEntity ciudad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static CiudadEntity fullDTO2Entity(CiudadDTO ciudad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
