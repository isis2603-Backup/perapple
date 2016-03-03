/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.misVacaciones.rest.mocks;

/**
 *
 * @author Asistente
 */
import co.edu.uniandes.misVacaciones.rest.dtos.CityDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.CityLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
@Named
@Singleton
public class CityLogicMock {

	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(CityLogicMock.class.getName());

	// listado de ciudades
    public static ArrayList<CityDTO> cities;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public CityLogicMock() {

    	if (cities == null) {
            cities = new ArrayList<>();
            cities.add(new CityDTO(1L, "Bogota"));
            cities.add(new CityDTO(2L, "Cali"));
            cities.add(new CityDTO(3L, "Medellin"));
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + cities );
    }

	/**
	 * Obtiene el listado de personas.
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria
	 */
    public List<CityDTO> getCities() throws CityLogicException {
    	if (cities == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new CityLogicException("Error interno: lista de ciudades no existe.");
    	}

    	logger.info("retornando todas las ciudades");
    	return cities;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    public CityDTO getCity(Long id) throws CityLogicException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CityDTO city : cities) {
            if (Objects.equals(city.getId(), id)){
            	logger.info("retornando ciudad " + city);
                return city;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe ciudad con ese id");
        throw new CityLogicException("No existe ciudad con ese id");
    }

    /**
     * Agrega una ciudad a la lista.
     * @param newCity ciudad a adicionar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public CityDTO createCity(CityDTO newCity) throws CityLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);

    	// la nueva ciudad tiene id ?
    	if ( newCity.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (CityDTO city : cities) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newCity.getId())){
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new CityLogicException("Ya existe una ciudad con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id paa la nueva ciudad");
    		long newId = 1;
	        for (CityDTO city : cities) {
	            if (newId <= city.getId()){
	                newId =  city.getId() + 1;
	            }
	        }
	        newCity.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando ciudad " + newCity);
        cities.add(newCity);
        return newCity;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public CityDTO updateCity(Long id, CityDTO updatedCity) throws CityLogicException {
    	logger.info("recibiendo solictud de modificar ciudad " + updatedCity);

    	// busca la ciudad con el id suministrado
        for (CityDTO city : cities) {
            if (Objects.equals(city.getId(), id)) {

            	// modifica la ciudad
            	city.setId(updatedCity.getId());
                city.setName(updatedCity.getName());

                // retorna la ciudad modificada
            	logger.info("Modificando ciudad " + city);
                return city;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CityLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteCity(Long id) throws CityLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);

    	// busca la ciudad con el id suministrado
        for (CityDTO city : cities) {
            if (Objects.equals(city.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando ciudad " + city);
                cities.remove(city);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe una ciudad con ese id");
        throw new CityLogicException("No existe una ciudad con ese id");
    }
}
