package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.SitioItinerarioDTO;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class SitioItinerarioConverter {
    
    public static List<SitioItinerarioDTO> listEntity2DTO(List<SitioItinerarioEntity> sitios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static SitioItinerarioDTO fullEntity2DTO(SitioItinerarioEntity sitio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static SitioItinerarioEntity fullDTO2Entity(SitioItinerarioDTO sitio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
