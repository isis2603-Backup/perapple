package co.edu.uniandes.misVacaciones.rest.mocks;

/**
 *
 * @author Perapple
 */
import co.edu.uniandes.misVacaciones.rest.dtos.ViajeroDTO;
import co.edu.uniandes.misVacaciones.rest.exceptions.ViajeroLogicException;
import static co.edu.uniandes.misVacaciones.rest.mocks.ViajeroLogicMock.viajeros;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;


/**
 * Mock del recurso Viajeros (Mock del servicio REST)
 */
@Named
@Singleton
public class ViajeroLogicMock {

	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ViajeroLogicMock.class.getName());

	// listado de viajeros
    public static ArrayList<ViajeroDTO> viajeros;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ViajeroLogicMock() {

    	if (viajeros == null) {
            viajeros = new ArrayList<>();
            viajeros.add(new ViajeroDTO(1L, "Perapple", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(2L, "Camilo Mendoza", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(3L, "Harold Gonzalez", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(4L, "María Remolina", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(5L, "Esteban Dalel", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(6L, "Nicolas Galvis", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));
            viajeros.add(new ViajeroDTO(7L, "Daniel Althviz", "p@erapple", "perapple","http://cdnstatic.visualizeus.com/thumbs/03/03/apple,colorful,logos,pear-0303435d1a5f1f8f70a1ea3429f072cb_h.jpg"));

        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de viajeros");
    	logger.info("viajeros" + viajeros );
    }

	/**
	 * Obtiene el listado de viajeros.
	 * @return lista de viajeros
	 * @throws ViajeroLogicException cuando no existe la lista en memoria
	 */
    public List<ViajeroDTO> getViajeros() throws ViajeroLogicException {
    	if (viajeros == null) {
    		logger.severe("Error interno: lista de viajeros no existe.");
    		throw new ViajeroLogicException("Error interno: lista de viajeros no existe.");
    	}

    	logger.info("retornando todos los viajeros");
    	return viajeros;
    }

    /**
     * Obtiene un viajero
     * @param id identificador del viajero
     * @return viajero encontrado
     * @throws ViajeroLogicException cuando el viajero no existe
     */
    public ViajeroDTO getViajero(Long id) throws ViajeroLogicException {
    	logger.info("recibiendo solicitud de viajero con id " + id);

    	// busca el viajero con el id suministrado
        for (ViajeroDTO viajero : viajeros) {
            if (Objects.equals(viajero.getId(), id)){
            	logger.info("retornando viajero " + viajero);
                return viajero;
            }
        }

        // si no encuentra el viajero
        logger.severe("No existe viajero con ese id");
        throw new ViajeroLogicException("No existe viajero con ese id");
    }

    /**
     * Agrega un viajero a la lista.
     * @param newViajero viajero a adicionar
     * @throws ViajeroLogicException cuando ya existe un viajero con el id suministrado
     * @return viajero agregado
     */
    public ViajeroDTO createViajero(ViajeroDTO newViajero) throws ViajeroLogicException {
    	logger.info("recibiendo solicitud de agregar viajero " + newViajero);

    	// el nuevo viajero tiene id
    	if ( newViajero.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ViajeroDTO viajero : viajeros) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(viajero.getId(), newViajero.getId())){
	            	logger.severe("Ya existe un viajero con ese id");
	                throw new ViajeroLogicException("Ya existe un viajero con ese id");
	            }
	        }

	    // el nuevo viajero no tiene id
    	} else {

    		// genera un id para el viajero
    		logger.info("Generando id para el nuevo viajero");
    		long newId = 1;
	        for (ViajeroDTO viajero : viajeros) {
	            if (newId <= viajero.getId()){
	                newId =  viajero.getId() + 1;
	            }
	        }
	        newViajero.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando viajero " + newViajero);
        viajeros.add(newViajero);
        return newViajero;
    }

    /**
     * Actualiza los datos de un viajero
     * @param id identificador del viajero a modificar
     * @param viajero viajero a modificar
     * @return datos del viajero modificado
     * @throws ViajeroLogicException cuando no existe un viajero con el id suministrado
     */
    public ViajeroDTO updateViajero(Long id, ViajeroDTO updatedViajero) throws ViajeroLogicException {
    	logger.info("recibiendo solictud de modificar viajero " + updatedViajero);

    	// busca el viajero con el id suministrado
        for (ViajeroDTO viajero : viajeros) {
            if (Objects.equals(viajero.getId(), id)) {

            	// modifica el viajero
            	viajero.setId(updatedViajero.getId());
                viajero.setName(updatedViajero.getName());
                viajero.setEmail(updatedViajero.getEmail());
                viajero.setImage(updatedViajero.getImage());
                viajero.setPassword(updatedViajero.getPassword());

                // retorna el viajero modificado
            	logger.info("Modificando viajero " + viajero);
                return viajero;
            }
        }

        // no encontró el viajero con ese id
        logger.severe("No existe un viajero con ese id");
        throw new ViajeroLogicException("No existe una ciudad con ese id");
    }

    /**
     * Elimina los datos de un viajero
     * @param id identificador del viajero a eliminar
     * @throws ViajeroLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteViajero(Long id) throws ViajeroLogicException {
    	logger.info("recibiendo solictud de eliminar viajero con id " + id);

    	// busca el viajero con el id suministrado
        for (ViajeroDTO viajero : viajeros) {
            if (Objects.equals(viajero.getId(), id)) {

            	// elimina el viajero
            	logger.info("eliminando viajero " + viajero);
                viajeros.remove(viajero);
                return;
            }
        }

        // no encontró el viajero con ese id
        logger.severe("No existe un viajero con ese id");
        throw new ViajeroLogicException("No existe un viajero con ese id");
    }
}
