package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.entities.ViajeroEntity;
import co.edu.uniandes.perapple.entities.ItinerarioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.ItinerarioPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.perapple.api.IItinerarioLogic;
import co.edu.uniandes.perapple.entities.CiudadItinerarioEntity;
import co.edu.uniandes.perapple.entities.EventoItinerarioEntity;
import co.edu.uniandes.perapple.entities.SitioItinerarioEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

@Stateless
public class ItinerarioLogic implements IItinerarioLogic {
//TODO Implementar los métodos correspondientes, identificar necesidad de otros métodos

    private static final Logger LOGGER = Logger.getLogger(ItinerarioLogic.class.getName());

    @Inject
    private ItinerarioPersistence persistence;

    //Posibilidad de inyectar mas instancias de las otras persistencias de ser necesario

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        LOGGER.info("Inicia proceso de consultar todos los itinerarios.");
        List<ItinerarioEntity> itinerarios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los itinerarios");

        return itinerarios;
    }

    @Override
    public List<ItinerarioEntity> getItinerariosViajero(int idViajero) {
        LOGGER.info("Inicia proceso de consultar todos los itinerarios asociados a ese viajero");

        List<ItinerarioEntity> itinerarios = getItinerarios();
        List<ItinerarioEntity> itinerariosV = new ArrayList<>();

        for (int i=0; i<itinerarios.size(); i++){
            ItinerarioEntity iti = itinerarios.get(i);
            if(iti.getViajero().getId() == idViajero){
                itinerariosV.add(iti);
            }
        }

        LOGGER.info("Termina proceso de consultar todos los itinerarios asociados a ese viajero");

        return itinerariosV;
    }

    @Override
    public ItinerarioEntity getItinerario(int id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar itinerario con id={0}", id);
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            LOGGER.log(Level.SEVERE, "El itinerario con el id {0} no existe", id);
            throw new IllegalArgumentException("El itinerario solicitado no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar itinerario con id={0}", id);
        return itinerario;
    }

    /**
     *  Crea un nuevo itinerario partiendo de una entidad dada
     * @param entity entidad con la información del nuevo itinerario
     * @return entidad desconectada de la base de datos
     * @throws BusinessLogicException en caso de que no se cumpla con las validaciones
     */
    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinessLogicException{

        LOGGER.info("Inicia proceso de creación de itinerario");

        if(entity.getFechaInicio() == null || entity.getFechaFin() == null)
        {
            throw new BusinessLogicException("Las fechas no son inválidas, fechas nulas");
        }
        if (entity.getFechaInicio().compareTo(entity.getFechaFin())>0) {
            throw new BusinessLogicException("Las fechas no son inválidas, fecha de inicio posterior a la fecha finalización");
        }
        if( "".equals(entity.getNombre()) || entity.getNombre() == null)
        {
            throw new BusinessLogicException("El nombre proporcionado para el itinerario no es válido");
        }

        int idViajero = entity.getViajero().getId();
        String nombreItinerario = entity.getNombre();

        if(!validacionNombreUnico(idViajero, nombreItinerario))
        {
            throw new BusinessLogicException("El viajero ya cuenta con un itinerario con el nombre dado");
        }

        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de itinerario");
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) throws BusinessLogicException{

        if(!validarExistenciaItinerario(entity.getId()))
        {
            throw new BusinessLogicException("El itinerario con el id suministrado no existe");
        }
        persistence.update(entity);
        return entity;
    }

    @Override
    public void deleteItinerario(int id) throws BusinessLogicException{

        if(!validarExistenciaItinerario(id))
        {
            throw new BusinessLogicException("El itinerario con el id suministrado no existe");
        }
        persistence.delete(id);
    }

    @Override
    public ViajeroEntity getViajero(int itinerarioId) throws BusinessLogicException {
        if(!validarExistenciaItinerario(itinerarioId))
        {
            throw new BusinessLogicException("El itinerario con el id suministrado no existe");
        }
        return persistence.find(itinerarioId).getViajero();
    }

    @Override
    public CiudadItinerarioEntity addCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) throws BusinessLogicException{

        //Validaciones
        //1. El itinerario ya existe y fue creado por el viajero.
        //2. La fecha inicial es anterior a la fecha final.
        //3. La fecha de visita a la ciudad está dentro de la fecha de visita del itinerario.
        //4. En las fechas ingresada no hay otra ciudad.
        //5. La ciudad no se encuentra ya en ese itinerario.

        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario al que se le quiere agregar la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        if (!validarFechas(itinerario, ciudad)) {
            throw new BusinessLogicException("Las fechas del la ciudad o del itinerario+ciudad no concuerdan.");
        }

        if (!validarNoHayOtraCiudadEnEsasFechas(itinerario, ciudad)){
            throw new BusinessLogicException("Ya hay otra ciudad en esas fechas.");
        }

        if (validarCiudadExisteEnItinerario(itinerario, ciudad.getId())){
            throw new BusinessLogicException("Ya existe esa misma ciudad en el itinerario.");
        }

        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        ciudades.add(ciudad);

        itinerario.setCiudades(ciudades);

        persistence.update(itinerario);

        return ciudad;
    }

    @Override
    public void deleteCiudad(int ciudadId, int itinerarioId) throws BusinessLogicException {
        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario al que se le quiere eliminar la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        boolean encontro = false;
        int indice = -1;

        for (int i=0; i<ciudades.size() && !encontro; i++){
            CiudadItinerarioEntity cIti = ciudades.get(i);
            if(cIti.getId() == ciudadId){
                encontro = true;
                indice = i;
            }
        }

        if (!encontro){
            throw new BusinessLogicException("La ciudad que se quiere borrar no existe en ese itinerario.");
        }

        ciudades.remove(indice);

        itinerario.setCiudades(ciudades);

        persistence.update(itinerario);
    }

    @Override
    public List<CiudadItinerarioEntity> replaceCiudades(List<CiudadItinerarioEntity> ciudades, int itinerarioId) throws BusinessLogicException {
        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario al que se le quiere agregar la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        itinerario.setCiudades(ciudades);

        persistence.update(itinerario);

        return ciudades;
    }

    @Override
    public CiudadItinerarioEntity updateCiudad(CiudadItinerarioEntity ciudad, int itinerarioId) throws BusinessLogicException {
        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario al que se le quiere actualizar la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        boolean encontro = false;

        for (int i=0; i<ciudades.size() && !encontro; i++){
            CiudadItinerarioEntity cIti = ciudades.get(i);
            if(cIti.getId() == ciudad.getId()){
                encontro = true;
                ciudades.remove(i);
                ciudades.add(ciudad);
            }
        }

        if (!encontro){
            throw new BusinessLogicException("La ciudad que se quiere actualizar no existe en ese itinerario.");
        }

        itinerario.setCiudades(ciudades);

        persistence.update(itinerario);

        return ciudad;
    }

    @Override
    public CiudadItinerarioEntity getCiudad(int itinerarioId, int ciudadId) throws BusinessLogicException {
        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario del que se queire obtener la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        boolean encontro = false;

        for (int i=0; i<ciudades.size() && !encontro; i++){
            CiudadItinerarioEntity cIti = ciudades.get(i);
            if(cIti.getId() == ciudadId){
                return cIti;
            }
        }

        throw new BusinessLogicException("La ciudad que se quiere borrar no existe en ese itinerario.");
    }

    @Override
    public List<CiudadItinerarioEntity> getCiudades(int itinerarioId) throws BusinessLogicException {
        if (!validarExistenciaItinerario(itinerarioId)) {
            throw new BusinessLogicException("El itinerario del que se queire obtener la ciudad no existe.");
        }

        ItinerarioEntity itinerario = persistence.find(itinerarioId);

        return itinerario.getCiudades();
    }



    @Override
    public List<SitioItinerarioEntity> getSitios(int itinerarioId, int ciudadId) throws BusinessLogicException {
        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        return ciudad.getSitios();
    }

    @Override
    public SitioItinerarioEntity getSitio(int itinerarioId, int ciudadId, int sitioId) throws BusinessLogicException {
        List<SitioItinerarioEntity> sitios = getSitios(itinerarioId, ciudadId);

        for (int i=0; i<sitios.size(); i++){
            SitioItinerarioEntity sitio = sitios.get(i);
            if (sitio.getId()==sitioId){
                return sitio;
            }
        }

        throw new BusinessLogicException("El sitio que se quiere obtener no existe en ese itinerario.");
    }

    @Override
    public SitioItinerarioEntity createSitio(int itinerarioId, int ciudadId, SitioItinerarioEntity sitio) throws BusinessLogicException {
        //Validaciones
        //1. El viajero es el creador del itinerario.
        //2. La fecha de visita al sitio de interés está dentro de la fecha de visita a la ciudad.
        //3. En la fecha ingresada no hay otro sitio de interés o evento.
        //4. El sitio de interés no se encuentra ya en esa ciudad.

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        if(!validarFechasSitio(sitio, ciudad)){
            throw new BusinessLogicException("Las fechas del sitio no concuerdan con las fechas de la ciudad");
        }

        if (validarSitioExisteEnCiudad(ciudad, sitio.getId())){
            throw new BusinessLogicException("Ya existe esa misma ciudad en el itinerario.");
        }

        List<SitioItinerarioEntity> sitios = ciudad.getSitios();

        sitios.add(sitio);

        ciudad.setSitios(sitios);

        updateCiudad(ciudad, itinerarioId);

        return sitio;
    }

    @Override
    public SitioItinerarioEntity updateSitio(int itinerarioId, int ciudadId, SitioItinerarioEntity sitio) throws BusinessLogicException{

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        List<SitioItinerarioEntity> sitios = ciudad.getSitios();

        boolean encontro = false;

        for (int i=0; i<sitios.size() && !encontro; i++){
            SitioItinerarioEntity sIti = sitios.get(i);
            if(sIti.getId() == sitio.getId()){
                encontro = true;
                sitios.remove(i);
                sitios.add(sitio);
            }
        }

        if (!encontro){
            throw new BusinessLogicException("El sitio que se quiere actualizar no existe en esa ciudad.");
        }

        ciudad.setSitios(sitios);

        updateCiudad(ciudad, itinerarioId);

        return sitio;
    }

    @Override
    public void deleteSitio(int itinerarioId, int ciudadId, int sitioId) throws BusinessLogicException {

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        List<SitioItinerarioEntity> sitios = ciudad.getSitios();

        boolean encontro = false;
        int indice = -1;

        for (int i=0; i<sitios.size() && !encontro; i++){
            SitioItinerarioEntity sIti = sitios.get(i);
            if(sIti.getId() == sitioId){
                encontro = true;
                indice = i;
            }
        }

        if (!encontro){
            throw new BusinessLogicException("La ciudad que se quiere borrar no existe en ese itinerario.");
        }

        sitios.remove(indice);

        ciudad.setSitios(sitios);

        updateCiudad(ciudad, itinerarioId);
    }


    @Override
    public List<EventoItinerarioEntity> getEventos(int itinerarioId, int ciudadId) throws BusinessLogicException {
        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        return ciudad.getEventos();
    }

    @Override
    public EventoItinerarioEntity getEvento(int itinerarioId, int ciudadId, int eventoId) throws BusinessLogicException {
        List<EventoItinerarioEntity> eventos = getEventos(itinerarioId, ciudadId);

        for (int i=0; i<eventos.size(); i++){
            EventoItinerarioEntity evento = eventos.get(i);
            if (evento.getId()==eventoId){
                return evento;
            }
        }

        throw new BusinessLogicException("El evento que se quiere obtener no existe en ese itinerario.");
    }

    @Override
    public EventoItinerarioEntity createEvento(int itinerarioId, int ciudadId, EventoItinerarioEntity evento) throws BusinessLogicException {
        //Validaciones
        //1. El viajero es el creador del itinerario.
        //2. La fecha de visita al evento de interés está dentro de la fecha de visita a la ciudad.
        //3. En la fecha ingresada no hay otro evento de interés o evento.
        //4. El evento de interés no se encuentra ya en esa ciudad.

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        if(!validarFechasEvento(evento, ciudad)){
            throw new BusinessLogicException("Las fechas del evento no concuerdan con las fechas de la ciudad");
        }

        if (validarEventoExisteEnCiudad(ciudad, evento.getId())){
            throw new BusinessLogicException("Ya existe esa misma ciudad en el itinerario.");
        }

        List<EventoItinerarioEntity> eventos = ciudad.getEventos();

        eventos.add(evento);

        ciudad.setEventos(eventos);

        updateCiudad(ciudad, itinerarioId);

        return evento;
    }

    @Override
    public EventoItinerarioEntity updateEvento(int itinerarioId, int ciudadId, EventoItinerarioEntity evento) throws BusinessLogicException{

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        List<EventoItinerarioEntity> eventos = ciudad.getEventos();

        boolean encontro = false;

        for (int i=0; i<eventos.size() && !encontro; i++){
            EventoItinerarioEntity eIti = eventos.get(i);
            if(eIti.getId() == evento.getId()){
                encontro = true;
                eventos.remove(i);
                eventos.add(evento);
            }
        }

        if (!encontro){
            throw new BusinessLogicException("El evento que se quiere actualizar no existe en esa ciudad.");
        }

        ciudad.setEventos(eventos);

        updateCiudad(ciudad, itinerarioId);

        return evento;
    }

    @Override
    public void deleteEvento(int itinerarioId, int ciudadId, int eventoId) throws BusinessLogicException {

        CiudadItinerarioEntity ciudad = getCiudad(itinerarioId, ciudadId);

        List<EventoItinerarioEntity> eventos = ciudad.getEventos();

        boolean encontro = false;
        int indice = -1;

        for (int i=0; i<eventos.size() && !encontro; i++){
            EventoItinerarioEntity eIti = eventos.get(i);
            if(eIti.getId() == eventoId){
                encontro = true;
                indice = i;
            }
        }

        if (!encontro){
            throw new BusinessLogicException("La ciudad que se quiere borrar no existe en ese itinerario.");
        }

        eventos.remove(indice);

        ciudad.setEventos(eventos);

        updateCiudad(ciudad, itinerarioId);
    }

    @Override
    public ItinerarioEntity getCurrentItinerario(int idViajero) throws BusinessLogicException {

        List<ItinerarioEntity> itinerarios = getItinerariosViajero(idViajero);

        for (int i=0; i<itinerarios.size(); i++){
            ItinerarioEntity itinerario = itinerarios.get(i);
            if(itinerario.getEsCurrent()){
                return itinerario;
            }
        }

        throw new BusinessLogicException("No se ha establecido un itinerario current aun.");
    }

    @Override
    public ItinerarioEntity setCurrentItinerario(int idViajero, int idItinerario) throws BusinessLogicException {

        List<ItinerarioEntity> itinerarios = getItinerariosViajero(idViajero);
        ItinerarioEntity current = null;
        boolean encontro = false;

        for (int i=0; i<itinerarios.size(); i++){
            ItinerarioEntity itinerario = itinerarios.get(i);
            if(itinerario.getEsCurrent()){
                itinerario.setEsCurrent(false);
                persistence.update(itinerario);
            }
            if(itinerario.getId()==idItinerario){
                encontro = true;
                itinerario.setEsCurrent(true);
                current = itinerario;
                persistence.update(current);
            }
        }

        if(!encontro){
            throw new BusinessLogicException("No existe itinerario con ese id para ser establecido como current.");
        }

        return current;
    }

    /** Validaciones */

    private boolean validacionNombreUnico(int idViajero, String nombreItinerario) {

        List<ItinerarioEntity> itinerarios = getItinerariosViajero(idViajero);
        boolean respuesta = true;

        for(int i = 0; i< itinerarios.size() && !respuesta; i++)
        {
            ItinerarioEntity iti = itinerarios.get(i);
            if(iti.getNombre().equals(nombreItinerario))
            {
                respuesta  = false;
                break;
            }
        }
        return respuesta;
    }

    private boolean validarExistenciaItinerario(int id) {
        return persistence.find(id)!= null;
    }

    private boolean validarFechas(ItinerarioEntity itinerario, CiudadItinerarioEntity ciudad) {
        //2. La fecha inicial es anterior a la fecha final.
        //3. La fecha de visita a la ciudad está dentro de la fecha de visita del itinerario.
        if(ciudad.getFechaIni().after(ciudad.getFechaFin())){
            return false;
        }
        if(ciudad.getFechaIni().before(itinerario.getFechaInicio()) && ciudad.getFechaFin().after(itinerario.getFechaFin())){
            return false;
        }
        return true;
    }

    private boolean validarNoHayOtraCiudadEnEsasFechas(ItinerarioEntity itinerario, CiudadItinerarioEntity ciudad) {
        //4. En las fechas ingresada no hay otra ciudad.
        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        Date fI = ciudad.getFechaIni();
        Date fF = ciudad.getFechaFin();

        for (int i=0; i<ciudades.size(); i++){
            CiudadItinerarioEntity cIti = ciudades.get(i);
            if(cIti.getFechaFin().after(fI) || cIti.getFechaIni().before(fF)){
                return false;
            }
        }

        return true;
    }

    private boolean validarCiudadExisteEnItinerario(ItinerarioEntity itinerario, int id) {

        List<CiudadItinerarioEntity> ciudades = itinerario.getCiudades();

        for (int i=0; i<ciudades.size(); i++){
            CiudadItinerarioEntity cIti = ciudades.get(i);
            if(cIti.getId() == id){
                return true;
            }
        }

        return false;
    }

    private boolean validarFechasSitio(SitioItinerarioEntity sitio, CiudadItinerarioEntity ciudad) {
        if(sitio.getFechaIni().after(sitio.getFechaFin())){
            return false;
        }
        if(sitio.getFechaIni().before(ciudad.getFechaIni()) && sitio.getFechaFin().after(ciudad.getFechaFin())){
            return false;
        }
        return true;
    }

    private boolean validarSitioExisteEnCiudad(CiudadItinerarioEntity ciudad, int sitioId) {
        List<SitioItinerarioEntity> sitios = ciudad.getSitios();

        for (int i=0; i<sitios.size(); i++){
            SitioItinerarioEntity sIti = sitios.get(i);
            if(sIti.getId() == sitioId){
                return true;
            }
        }
        return false;
    }

    private boolean validarFechasEvento(EventoItinerarioEntity evento, CiudadItinerarioEntity ciudad) {
        if(evento.getFechaIni().after(evento.getFechaFin())){
            return false;
        }
        return !(evento.getFechaIni().before(ciudad.getFechaIni()) && evento.getFechaFin().after(ciudad.getFechaFin()));
    }

    private boolean validarEventoExisteEnCiudad(CiudadItinerarioEntity ciudad, int eventoId) {
        List<EventoItinerarioEntity> eventos = ciudad.getEventos();

        for (int i=0; i<eventos.size(); i++){
            EventoItinerarioEntity eIti = eventos.get(i);
            if(eIti.getId() == eventoId){
                return true;
            }
        }
        return false;
    }
}
