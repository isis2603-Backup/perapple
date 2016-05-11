package co.edu.uniandes.misvacaciones.rest.converters;

import co.edu.uniandes.misvacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.perapple.entities.SitioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class SitioConverter {

    private SitioConverter() {
    }

    public static SitioDTO basicEntity2DTO(SitioEntity entity) {
        if (entity != null) {
            SitioDTO dto = new SitioDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setDetalles(entity.getDetalles());
            dto.setImagen(entity.getImagen());

            return dto;
        } else {
            return null;
        }
    }

    private static SitioEntity basicDTO2Entity(SitioDTO dto) {
        if (dto != null) {
            SitioEntity entity = new SitioEntity();
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setDetalles(dto.getDetalles());
            entity.setImagen(dto.getImagen());

            return entity;
        } else {
            return null;
        }
    }

    public static List<SitioDTO> listEntity2DTO(List<SitioEntity> entities) {
        List<SitioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (SitioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<SitioEntity> listDTO2Entity(List<SitioDTO> dtos) {
        List<SitioEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (SitioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static SitioDTO fullEntity2DTO(SitioEntity entity) {
        if (entity != null) {

            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }

    public static SitioEntity fullDTO2Entity(SitioDTO dto) {
        if (dto != null) {

            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

}
