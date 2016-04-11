package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.CiudadItinerarioDTO;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class CiudadItinerarioConverter {
      
    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     */
    private CiudadItinerarioConverter() {
        
    }
    
    public static CiudadItinerarioDTO basicEntity2DTO(CiudadItinerarioEntity entity) {
        
        if (entity != null) {
            CiudadItinerarioDTO dto = new CiudadItinerarioDTO();
            dto.setId(entity.getId());
            dto.setFechaIni(entity.getFechaIni());
            dto.setFechaFin(entity.getFechaFin());
            dto.setCiudad(CiudadConverter.basicEntity2DTO(entity.getCiudad()));
            dto.setItinerario(ItinerarioConverter.basicEntity2DTO(entity.getItinerario()));
            return dto;
        } else {
            return null;
        }
    }
    
    public static CiudadItinerarioEntity basicDTO2Entity(CiudadItinerarioDTO dto) {
        if (dto != null) {
            CiudadItinerarioEntity entity = new CiudadItinerarioEntity();
            entity.setId(dto.getId());
            entity.setFechaIni(dto.getFechaIni());
            entity.setFechaFin(dto.getFechaFin());
            entity.setItinerario(ItinerarioConverter.basicDTO2Entity(dto.getItinerario()));
            entity.setCiudad(CiudadConverter.basicDTO2Entity(dto.getCiudad()));
            return entity;
        } else {
            return null;
        }
    }

    public static List<CiudadItinerarioDTO> listEntity2DTO(List<CiudadItinerarioEntity> entities) {
        List<CiudadItinerarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (CiudadItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
    public static List<CiudadItinerarioEntity> listDTO2Entity(List<CiudadItinerarioDTO> dtos) {
        List<CiudadItinerarioEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (CiudadItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static CiudadItinerarioDTO fullEntity2DTO(CiudadItinerarioEntity entity) {
        if (entity != null) {
            CiudadItinerarioDTO dto = basicEntity2DTO(entity);
            dto.setEventos(EventoItinerarioConverter.listEntity2DTO(entity.getEventos()));
            dto.setSitios(SitioItinerarioConverter.listEntity2DTO(entity.getSitios()));
            return dto;
        } else {
            return null;
        }
    }

    public static CiudadItinerarioEntity fullDTO2Entity(CiudadItinerarioDTO dto) {
        if (dto != null) {
            CiudadItinerarioEntity entity = basicDTO2Entity(dto);
            entity.setEventos(EventoItinerarioConverter.listDTO2Entity(dto.getEventos()));
            entity.setSitios(SitioItinerarioConverter.listDTO2Entity(dto.getSitios()));
            return entity;
        } else {
            return null;
        }
    }

    
}
