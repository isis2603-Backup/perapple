package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.EventoItinerarioDTO;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class EventoItinerarioConverter {
    public static List<EventoItinerarioDTO> listEntity2DTO(List<EventoItinerarioEntity> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EventoItinerarioDTO fullEntity2DTO(EventoItinerarioEntity evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EventoItinerarioEntity fullDTO2Entity(EventoItinerarioDTO evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
