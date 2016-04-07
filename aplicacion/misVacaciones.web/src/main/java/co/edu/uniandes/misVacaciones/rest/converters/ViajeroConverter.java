/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.misVacaciones.rest.converters;
import co.edu.uniandes.misVacaciones.rest.dtos.ViajeroDTO;
import co.edu.uniandes.perapple.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;



public abstract class ViajeroConverter {
    /**
     * Constructor privado para evitar la creación del constructor implícito de
     * Java
     */
private ViajeroConverter {

}
    public static ViajeroDTO basicEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            ViajeroDTO dto = new ViajeroDTO();
            dto.setId(entity.getIdentificador());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());
            dto.setImagen(entity.getImagen());
            return dto;
        }
        else {
            return null;
        }
    }

    private static ViajeroEntity basicDTO2Entity(ViajeroDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = new ViajeroEntity();
            entity.setId(dto.getIdentificador());
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
            entity.setImagen(dto.getImagen());

            return entity;

        } else {
            return null;
        }
    }

    public static List<ViajeroDTO> listEntity2DTO(List<ViajeroEntity> entities) {
        List<ViajeroDTO> dtos = new ArrayList<>();
        if (entities != null) {
            for (ViajeroEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
       public static List<ViajeroEntity> listDTO2Entity(List<ViajeroDTO> dtos) {
        List<ViajeroEntity> entities = new ArrayList<>();
        if (dtos != null) {
            for (ViajeroDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }





    public static ViajeroDTO fullEntity2DTO(ViajeroEntity entity) {
        if (entity != null) {
            ViajeroDTO dto = basicEntity2DTO(entity);
            dto.setItinerarios(ItinerarioConverter.listEntity2DTO(entity.getItinerarios()));
            return dto;
        } else {
            return null;
        }
    }

    public static ViajeroEntity fullDTO2Entity(ViajeroDTO dto) {
        if (dto != null) {
            ViajeroEntity entity = basicDTO2Entity(dto);
            entity.setItinerarios(ItinerarioConverter.listDTO2Entity(dto.getItinerarios()));
            return entity;
        } else {
            return null;
        }
    }
}