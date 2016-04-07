package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.SitioDTO;
import co.edu.uniandes.perapple.entities.SitioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class SitioConverter {

    public static SitioDTO basicEntity2DTO(SitioEntity entity) {
        if (entity != null) {
            SitioDTO dto = new SitioDTO();
            dto.setId(entity.getIdentificador());
            dto.setNombre(entity.getName());
            dto.setDetalles(entity.getDetalles());
            dto.setImagen(entity.getImagen());
            dto.setFechaSitio(entity.getFechaSitio());
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
            entity.setFechaSitio(dto.getFechaSitio());
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
            SitioDTO dto = basicEntity2DTO(entity);
            //Cuando se implementen calificaciones
            //dto.setCalificaciones(SitioConverter.listEntity2DTO(entity.getCalificaciones)));
            return dto;
        } else {
            return null;
        }
    }

    public static SitioEntity fullDTO2Entity(SitioDTO dto) {
        if (dto != null) {
            SitioEntity entity = basicDTO2Entity(dto);
            //Cuando se implementen calificaciones
            //entity.setCalificaciones(SitioConverter.listDTO2Entity(dto.getCalificaciones()));
            return entity;
        } else {
            return null;
        }
    }
    
}
