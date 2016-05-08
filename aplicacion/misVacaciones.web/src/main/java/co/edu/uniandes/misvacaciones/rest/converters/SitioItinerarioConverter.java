package co.edu.uniandes.misvacaciones.rest.converters;

import co.edu.uniandes.misvacaciones.rest.dtos.SitioItinerarioDTO;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camen
 */
public abstract class SitioItinerarioConverter {

    private SitioItinerarioConverter() {
    }

    public static SitioItinerarioEntity refDTOEntity(SitioItinerarioDTO dto){
        if(dto != null){
            SitioItinerarioEntity entity = new SitioItinerarioEntity();
            entity.setId(dto.getId());
            return entity;
        } else {
            return null;
        }
    }

    public static SitioItinerarioDTO refEntity2DTO(SitioItinerarioEntity entity){
        if(entity != null){
            SitioItinerarioDTO dto = new SitioItinerarioDTO();
            dto.setId(entity.getId());
            dto.setFechaIni(entity.getFechaIni());
            dto.setFechaFin(entity.getFechaFin());
            return dto;
        } else {
            return null;
        }
    }

    private static SitioItinerarioEntity basicDTO2Entity(SitioItinerarioDTO dto){
        if(dto != null){
            SitioItinerarioEntity entity = new SitioItinerarioEntity();
            entity.setId(dto.getId());
            entity.setFechaIni(dto.getFechaIni());
            entity.setFechaFin(dto.getFechaFin());
            entity.setSitio(SitioConverter.fullDTO2Entity(dto.getSitio())); //Revisar
            entity.setCiudad(CiudadItinerarioConverter.basicDTO2Entity(dto.getCiudad()));
            return entity;
        } else {
            return null;
        }
    }

    private static SitioItinerarioDTO basicEntity2DTO(SitioItinerarioEntity entity){
        if(entity != null){
            SitioItinerarioDTO dto = new SitioItinerarioDTO();
            dto.setId(entity.getId());
            dto.setFechaIni(entity.getFechaIni());
            dto.setFechaFin(entity.getFechaFin());
            dto.setSitio(SitioConverter.fullEntity2DTO(entity.getSitio())); //Revisar
            dto.setCiudad(CiudadItinerarioConverter.basicEntity2DTO(entity.getCiudad()));
            return dto;
        } else {
            return null;
        }
    }

    public static List<SitioItinerarioEntity> listDTO2Entity(List<SitioItinerarioDTO> dtos){
        List<SitioItinerarioEntity> entities = new ArrayList<>();
        if(dtos != null){
             for (SitioItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static List<SitioItinerarioDTO> listEntity2DTO(List<SitioItinerarioEntity> entities) {
        List<SitioItinerarioDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (SitioItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static SitioItinerarioEntity fullDTO2Entity(SitioItinerarioDTO dto) {
        if(dto != null){
            return basicDTO2Entity(dto);
        } else {
            return null;
        }
    }

    public static SitioItinerarioDTO fullEntity2DTO(SitioItinerarioEntity entity) {
        if(entity != null){
            return basicEntity2DTO(entity);
        } else {
            return null;
        }
    }
}
