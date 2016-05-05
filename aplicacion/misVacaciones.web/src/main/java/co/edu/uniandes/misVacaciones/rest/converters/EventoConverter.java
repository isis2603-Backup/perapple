package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.EventoDTO;
import co.edu.uniandes.perapple.entities.EventoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class EventoConverter {

    private EventoConverter() {
    }

    public static EventoDTO basicEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setDetalles(entity.getDetalles());
            dto.setImagen(entity.getImagen());
            dto.setFechaEvento(entity.getFechaEvento());
            return dto;
        } else {
            return null;
        }
    }

    private static EventoEntity basicDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setDetalles(dto.getDetalles());
            entity.setImagen(dto.getImagen());
            entity.setFechaEvento(dto.getFechaEvento());
            return entity;
        } else {
            return null;
        }
    }

    public static List<EventoDTO> listEntity2DTO(List<EventoEntity> entities) {
        List<EventoDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (EventoEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<EventoEntity> listDTO2Entity(List<EventoDTO> dtos) {
        List<EventoEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (EventoDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static EventoDTO fullEntity2DTO(EventoEntity entity) {
        if (entity != null) {
             
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    public static EventoEntity fullDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

}
