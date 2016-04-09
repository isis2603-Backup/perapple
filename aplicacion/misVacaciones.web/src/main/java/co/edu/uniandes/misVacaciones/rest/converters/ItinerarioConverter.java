package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class ItinerarioConverter {

    public static List<ItinerarioDTO> listEntity2DTO(List<ItinerarioEntity> currentItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ItinerarioDTO fullEntity2DTO(ItinerarioEntity currentItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ItinerarioEntity fullDTO2Entity(ItinerarioDTO nuevoCurrent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<ItinerarioEntity> listDTO2Entity(List<ItinerarioDTO> currentItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
