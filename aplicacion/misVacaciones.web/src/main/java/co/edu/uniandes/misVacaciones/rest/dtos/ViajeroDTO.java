/*
 * ViajeroDTO
 * Objeto de transferencia de datos de Viajeros.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Objeto de transferencia de datos de Viajeros.
 * @author Perapple
 */
@XmlRootElement
public class ViajeroDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private String image;
    @PodamExclude
    private List<ItinerarioDTO> itinerarios;

    public void setItinerarios(List<ItinerarioDTO> itinerarios) {
        this.itinerarios = itinerarios;
    }

    public List<ItinerarioDTO> getItinerarios() {
        return itinerarios;
    }

    /**
     * Retorna el id del viajero
     * @return el id del viajero
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el id del viajero
     * @param id el id a modificar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna el nombre del viajero
     * @return el nombre del viajero
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre del viajero
     * @param name el nombre a modificar
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Retorna el email del viajero
     * @return el email del viajero
     */
    public String getEmail() {
        return email;
    }
    /**
     * Modifica el email del viajero
     * @param email el email a modificar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna la contraseña del viajero
     * @return la contraseña del viajero
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica la contraseña del viajero
     * @param password la contrasñea a modificar
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna la imagen del viajero
     * @return la imagen del viajero
     */
    public String getImage() {
        return image;
    }

    /**
     * Modifica la imagen del viajero
     * @param image la imagen a modificar
     */
    public void setImage(String image) {
        this.image = image;
    }

//    /**
//     * Convierte el objeto a una cadena
//     * @return el objeto como una cadena JSON.
//     */
//    @Override
//    public String toString() {
//    	return "{ id : " + getId() + ", name : \"" + getName() + "\", email: \"" + getEmail() + "\", password: \"" + getPassword() + "\", image: \"" + getImage() + "\" }" ;
//    }
}
