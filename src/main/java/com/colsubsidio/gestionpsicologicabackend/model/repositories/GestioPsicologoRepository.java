/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.repositories;


import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroGestionPsicologicaDTO;
import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import com.colsubsidio.gestionpsicologicabackend.services.LogService;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsMapDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Slf4j
@Repository
public class GestioPsicologoRepository {
    
    
    @Autowired
    private JdbcTemplate jdbcTemplateCall;
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    
    @Autowired
    private LogService logService;
    
    @Autowired
    private UtilsMapDto utilsMapDto;
    
    @Autowired
    private GestionPsicoRepositoryI gestionI;
    
    
    public List<RegistroGestionPsicologicaDTO> buscarGestionesPendientes(String uuid,GestionPsicologicaKibana dtoKibana)
    throws Exception{
      
         GestionPsicologicaKibana.Service buscarGestiones = logService.initLogService("buscar-gestiones-pendientes", 
                 "", propertiesUtil.getRetornaDatosEnGestionQuery(), false);
       
      dtoKibana.getServices().add(buscarGestiones);
      List<RegistroGestionPsicologicaDTO>   list = jdbcTemplateCall.query(propertiesUtil.getRetornaDatosEnGestionQuery(), new BeanPropertyRowMapper<RegistroGestionPsicologicaDTO>(RegistroGestionPsicologicaDTO.class));
      List<RegistroGestionPsicologicaDTO>   listFinal = null;
      if(list!=null && !list.isEmpty()){
          listFinal = new ArrayList<>();
          log.info("{}Se recuperaron "+list.size()+"Registros",uuid);
          for(RegistroGestionPsicologicaDTO dt:list){
            List<GestionPsicologo> psicoGestionList =  gestionI.cargarGestionesRegistro(dt.getIdGestion());
            if(psicoGestionList!=null && !psicoGestionList.isEmpty()){
                dt.setGestionPsicologo(utilsMapDto.listGestionPsicologoDTO(psicoGestionList)); 
            }
            listFinal.add(dt);
          }
          logService.completeResponseService(buscarGestiones, true, list, true);
      }else{
            log.info("{} No Se recuperaron Registros");
              logService.completeResponseService(buscarGestiones, true, "No se encontraron registros", false);
      }
    
      return listFinal;
    
    }
    
    
    
    public List<RegistroGestionPsicologicaDTO> buscarGestionesPorCriterio(String uuid,GestionPsicologicaKibana dtoKibana,String querie)
    throws Exception{
      
         GestionPsicologicaKibana.Service buscarGestiones = logService.initLogService("buscar-gestiones-por-criterio", 
                 "", querie, false);
       
      dtoKibana.getServices().add(buscarGestiones);
      List<RegistroGestionPsicologicaDTO>   list = jdbcTemplateCall.query(querie, new BeanPropertyRowMapper<RegistroGestionPsicologicaDTO>(RegistroGestionPsicologicaDTO.class));
      List<RegistroGestionPsicologicaDTO>   listFinal = null;  
     if(list!=null && !list.isEmpty()){
          listFinal = new ArrayList<>();
          log.info("{}Se recuperaron "+list.size()+"Registros",uuid);
          for(RegistroGestionPsicologicaDTO dt:list){
            List<GestionPsicologo> psicoGestionList =  gestionI.cargarGestionesRegistro(dt.getIdGestion());
            if(psicoGestionList!=null && !psicoGestionList.isEmpty()){
                dt.setGestionPsicologo(utilsMapDto.listGestionPsicologoDTO(psicoGestionList)); 
            }
            listFinal.add(dt);
          }
          logService.completeResponseService(buscarGestiones, true, list, true);
      }else{
            log.info("{} No Se recuperaron Registros");
              logService.completeResponseService(buscarGestiones, true, "No se encontraron registros", false);
      }
    
      return list;
    
    }
    
    
    
}
