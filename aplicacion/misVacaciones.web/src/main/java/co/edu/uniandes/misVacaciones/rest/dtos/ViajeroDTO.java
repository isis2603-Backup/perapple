/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.misVacaciones.rest.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class ViajeroDTO {
    private Long id;
    private String name;
    private String email;
    private String password; 
    private String image;

    /**
     * Constructor por defecto
     */
    public ViajeroDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del viajero
     * @param name nombre del viajero
     * @param email email del viajero
     * @param password contraseña del viajero
     * @param image imagen de perfil del viajero
     */
    public ViajeroDTO(Long id, String name, String email, String password, String image) {
		super();
		this.id = id;
		this.name = name;
                this.email = email;
                this.password = password;
                this.image = image;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Convierte el objeto a una cadena
     * @return el objeto como una cadena JSON.
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", name : \"" + getName() + "\", email: \"" + getEmail() + "\", password: \"" + getPassword() + "\", image: \"" + getImage() + "\" }" ;
    }
}
