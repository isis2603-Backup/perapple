package co.edu.uniandes.misvacaciones.rest.converters;

import co.edu.uniandes.misvacaciones.rest.dtos.ItinerarioDTO;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class ItinerarioConverter {

    private ItinerarioConverter() {

    }

    public static ItinerarioDTO basicEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setViajero(ViajeroConverter.basicEntity2DTO(entity.getViajero()));
            dto.setFechaInicio(entity.getFechaInicio());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }

    public static ItinerarioEntity basicDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = new ItinerarioEntity();
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setViajero(ViajeroConverter.basicDTO2Entity(dto.getViajero()));
            entity.setFechaInicio(dto.getFechaInicio());
            entity.setFechaFin(dto.getFechaFin());
            return entity;
        } else {
            return null;
        }
    }

    public static List<ItinerarioDTO> listEntity2DTO(List<ItinerarioEntity> entities) {
        List<ItinerarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<ItinerarioEntity> listDTO2Entity(List<ItinerarioDTO> dtos) {
        List<ItinerarioEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (ItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static ItinerarioDTO fullEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = basicEntity2DTO(entity);
            dto.setCiudades(CiudadItinerarioConverter.listEntity2DTO(entity.getCiudades()));
            return dto;
        } else {
            return null;
        }
    }

    public static ItinerarioEntity fullDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = basicDTO2Entity(dto);
            entity.setCiudades(CiudadItinerarioConverter.listDTO2Entity(dto.getCiudades()));
            return entity;
        } else {
            return null;
        }
    }
}
