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

    public CiudadEntity getCiudad(Long id) throws BusinessLogicException;

    public CiudadEntity createCiudad(CiudadEntity entity);

    public CiudadEntity updateCiudad(CiudadEntity entity);

    public void deleteCiudad(Long id);

    public List<EventoEntity> getEventos(Long ciudadId);

    public EventoEntity getEvento(Long eventoId, Long ciudadId);

    public EventoEntity addEvento(Long ciudadId, EventoEntity eventoId);

    public void removeEvento(Long eventoId, Long ciudadId);

    public List<EventoEntity> replaceEventos(List<EventoEntity> eventos, Long ciudadId);

    public List<SitioEntity> getSitios(Long ciudadId);

    public SitioEntity getSitio(Long sitioId, Long ciudadId);

    public SitioEntity addSitio(Long ciudadId, SitioEntity sitio);

    public void removeSitio(Long sitioId, Long ciudadId);

    public List<SitioEntity> replaceSitios(List<SitioEntity> sitios, Long ciudadId);

    public SitioEntity updateSitio(Long id, Long idSitio, SitioEntity entity);

    public EventoEntity updateEvento(Long id, Long idEvento, EventoEntity entity);
}
