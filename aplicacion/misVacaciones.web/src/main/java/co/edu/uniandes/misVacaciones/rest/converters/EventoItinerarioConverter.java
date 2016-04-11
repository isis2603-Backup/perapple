package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.EventoItinerarioDTO;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class EventoItinerarioConverter {
        public static EventoItinerarioEntity refDTOEntity(EventoItinerarioDTO dto){
        if(dto != null){
            EventoItinerarioEntity entity = new EventoItinerarioEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }
    
    public static EventoItinerarioDTO refEntity2DTO(EventoItinerarioEntity entity){
        if(entity != null){
            EventoItinerarioDTO dto = new EventoItinerarioDTO();
            dto.setId(entity.getId());
            dto.setFechaIni(entity.getFechaIni());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }
    
    private static EventoItinerarioEntity basicDTO2Entity(EventoItinerarioDTO dto){
        if(dto != null){
            EventoItinerarioEntity entity = new EventoItinerarioEntity();
            entity.setId(dto.getId());
            entity.setFechaIni(dto.getFechaIni());
            entity.setFechaFin(dto.getFechaFin());
            entity.setEvento(EventoConverter.fullDTO2Entity(dto.getEvento())); //Revisar
            entity.setCiudad(CiudadItinerarioConverter.basicDTO2Entity(dto.getCiudad()));
            return entity;
        } else {
            return null;
        }
    }
    
    private static EventoItinerarioDTO basicEntity2DTO(EventoItinerarioEntity entity){
        if(entity != null){
            EventoItinerarioDTO dto = new EventoItinerarioDTO();
            dto.setId(entity.getId());
            dto.setFechaIni(entity.getFechaIni());
            dto.setFechaFin(entity.getFechaFin());
            dto.setEvento(EventoConverter.fullEntity2DTO(entity.getEvento())); //Revisar
            dto.setCiudad(CiudadItinerarioConverter.basicEntity2DTO(entity.getCiudad()));
            return dto;
        } else {
            return null;
        }
    }
    
    public static List<EventoItinerarioEntity> listDTO2Entity(List<EventoItinerarioDTO> dtos){
        List<EventoItinerarioEntity> entities = new ArrayList<>();
        if(dtos != null){
             for (EventoItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static List<EventoItinerarioDTO> listEntity2DTO(List<EventoItinerarioEntity> entities) {
        List<EventoItinerarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (EventoItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;    }

    public static EventoItinerarioEntity fullDTO2Entity(EventoItinerarioDTO dto) {
        if(dto != null){
            EventoItinerarioEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }
    
    public static EventoItinerarioDTO fullEntity2DTO(EventoItinerarioEntity entity) {
        if(entity != null){
            EventoItinerarioDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }    
    }
}
