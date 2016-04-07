/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.api;


import co.edu.uniandes.perapple.entities.CiudadEntity;
import co.edu.uniandes.perapple.entities.EventoEntity;
import co.edu.uniandes.perapple.entities.SitioEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author perapple
 */
public interface ICiudadLogic {

    public List<CiudadEntity> getCiudades();

    public CiudadEntity getCiudad(int id) throws BusinessLogicException;

    public CiudadEntity createCiudad(CiudadEntity entity) throws BusinessLogicException;

    public CiudadEntity updateCiudad(CiudadEntity entity) throws BusinessLogicException;

    public void deleteCiudad(int id) throws BusinessLogicException ;

    public List<EventoEntity> getEventos(int ciudadId) throws BusinessLogicException;

    public EventoEntity getEvento(int eventoId, int ciudadId) throws BusinessLogicException;

    public EventoEntity addEvento(int ciudadId, EventoEntity evento) throws BusinessLogicException;

    public void removeEvento(int eventoId, int ciudadId) throws BusinessLogicException;
    
    public EventoEntity updateEvento(int ciudadId, int eventoId, EventoEntity evento) throws BusinessLogicException;

    public List<SitioEntity> getSitios(int ciudadId) throws BusinessLogicException;

    public SitioEntity getSitio(int sitioId, int ciudadId) throws BusinessLogicException;

    public SitioEntity addSitio(int ciudadId, SitioEntity sitio) throws BusinessLogicException;

    public void removeSitio(int sitioId, int ciudadId) throws BusinessLogicException;

    public SitioEntity updateSitio(int ciudadId, int sitioId, SitioEntity sitio) throws BusinessLogicException;
}
