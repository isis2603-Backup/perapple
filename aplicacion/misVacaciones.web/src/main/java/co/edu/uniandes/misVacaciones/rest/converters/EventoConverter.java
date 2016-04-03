package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.perapple.entities.EventoEntity;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class EventoConverter {

    public static List<EventoDTO> listEntity2DTO(List<EventoEntity> eventos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EventoDTO fullEntity2DTO(EventoEntity evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static EventoEntity fullDTO2Entity(EventoDTO evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
