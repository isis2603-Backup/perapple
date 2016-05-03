package co.edu.uniandes.misVacaciones.rest.converters;

import co.edu.uniandes.misVacaciones.rest.dtos.CiudadDTO;
import co.edu.uniandes.perapple.entities.CiudadEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class CiudadConverter {

    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     */
    private CiudadConverter() {
    }

    public static CiudadDTO basicEntity2DTO(CiudadEntity entity) {
        if (entity != null) {
            CiudadDTO dto = new CiudadDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setDetalles(entity.getDetalles());
            dto.setImagen(entity.getImagen());

            return dto;
        } else {
            return null;
        }
    }

    public static CiudadEntity basicDTO2Entity(CiudadDTO dto) {
        if (dto != null) {
            CiudadEntity entity = new CiudadEntity();
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setDetalles(dto.getDetalles());
            entity.setImagen(dto.getImagen());

            return entity;
        } else {
            return null;
        }
    }

    public static List<CiudadDTO> listEntity2DTO(List<CiudadEntity> entities) {
        List<CiudadDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (CiudadEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<CiudadEntity> listDTO2Entity(List<CiudadDTO> dtos) {
        List<CiudadEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (CiudadDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    public static CiudadDTO fullEntity2DTO(CiudadEntity entity) {
        if (entity != null) {
            CiudadDTO dto = basicEntity2DTO(entity);
            dto.setEventos(EventoConverter.listEntity2DTO(entity.getEventos()));
            dto.setSitios(SitioConverter.listEntity2DTO(entity.getSitios()));
            return dto;
        } else {
            return null;
        }
    }

    public static CiudadEntity fullDTO2Entity(CiudadDTO dto) {
        if (dto != null) {
            CiudadEntity entity = basicDTO2Entity(dto);
            entity.setEventos(EventoConverter.listDTO2Entity(dto.getEventos()));
            entity.setSitios(SitioConverter.listDTO2Entity(dto.getSitios()));
            return entity;
        } else {
            return null;
        }
    }
}
