/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.services;

import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologoResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.SearchResponse;

import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.GestionPsicoRepositoryI;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.Utils;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsMapDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
@Slf4j
public class TareasCronService {
    
    @Autowired
    private GestionPsicoRepositoryI gestionPsicoRepositoryI;
    
    @Autowired
    private LogService logService;
    
    @Autowired
    private Utils utils;
    
    @Autowired
    private UpdateKibanaService updateKibanaService;
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    
    @Autowired
    private UtilsMapDto utilsMapDto;
    
    public void process(){
       
        String uuid=utils.uuid();
        log.info("{}- inicia cron ",uuid);
        GestionPsicologicaKibana dtoKibana = new GestionPsicologicaKibana();
        List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
        dtoKibana.setServices(services);
        dtoKibana.setStartdate(new Date());
        List<GestionPsicologo> listaGestion = gestioPsicoloPendienteKibana(uuid, dtoKibana);
        if(listaGestion!=null && !listaGestion.isEmpty()){
             for(GestionPsicologo obj:listaGestion){
//              SearchResponse search=  updateKibanaService.searchDatos(uuid, obj.getIdRegistroGestion().getIdGestion().toString(), dtoKibana);   
//              Map<String, String> datos=  search.getDatos();
//              boolean res=updateKibanaService.actualizarElastic(uuid, dtoKibana, datos, obj,search.getIdDoc(),"");
//              if(res){
//                  actualizarRegistroEnviado(obj, uuid, dtoKibana);
//              }
             
                 boolean res=tareasKibana(obj, uuid, dtoKibana);
                 if(res){
                     actualizarRegistroEnviado(obj, uuid, dtoKibana);
                 }
             
             }
             
             logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibaSideCron());
        }else{
            log.debug("{}- no se encontraron datos",uuid);
        }
       
         
         
    }
    

    
    private List<GestionPsicologo> gestioPsicoloPendienteKibana(String uuid, GestionPsicologicaKibana dtoKibana){
        
        List<GestionPsicologo> list=null;
        GestionPsicologicaKibana.Service consultarPendientes = logService.initLogService("consultar-pendientes-envio-kibana",
                "", "", false);
        dtoKibana.getServices().add(consultarPendientes);
        try{
            
            list = gestionPsicoRepositoryI.gestionesNoEnviadasKibana(Boolean.FALSE);
            logService.completeResponseService(consultarPendientes, true, list, true);
        }catch(Exception e){
            logService.completeResponseService(consultarPendientes, false, e.getMessage(), false);
            log.error(uuid+" Excepcion buscando gestiones psicologo a enviar", e);
        }
        return list;
    }
    

   private void actualizarRegistroEnviado(GestionPsicologo obj,String uuid, GestionPsicologicaKibana dtoKibana){
          GestionPsicologicaKibana.Service actualizandoRegistro = 
                  logService.initLogService("actualizando registro enviadoKibana true",
                "", "", false);
          dtoKibana.getServices().add(actualizandoRegistro);
          try{
              obj.setEnvioKibana(Boolean.TRUE);
              gestionPsicoRepositoryI.saveAndFlush(obj);
              logService.completeResponseService(actualizandoRegistro, true, obj, true);
          }catch(Exception e){
              logService.completeResponseService(actualizandoRegistro, false, e, false);
              log.error(uuid+" Excepcion actualizando gestiones psicologo a enviar", e);
          }
          
        
    }

     
   public boolean tareasKibana(GestionPsicologo obj,String uuid, GestionPsicologicaKibana dtoKibana){
       boolean res=Boolean.FALSE;      
       try{
             GestionPsicologoResponse response= new GestionPsicologoResponse();
             response.setDescripcion("guardado con exito");
             response.setEstado("OK");
             response.setRegistro(utilsMapDto.registroDto(obj.getIdRegistroGestion()));
             response.setGestionPsicologo(utilsMapDto.gestionPsicologoDTO(obj));
             response.getGestionPsicologo().setUsuarioNombre(obj.getIdUsuarioGestiona().getNombreUsuario()+" "+obj.getIdUsuarioGestiona().getApellidoUsuario());
             dtoKibana.setGestionPsicologo(response.getGestionPsicologo());
             dtoKibana.setRegistroGestion(response.getRegistro());
             logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibanaSideRegistrar());
             SearchResponse responseUpdate=updateKibanaService.searchDatos(uuid, obj.getIdRegistroGestion().getIdGestion().toString(), dtoKibana);
             
             if(responseUpdate!=null && responseUpdate.getDatos()!=null){
                  responseUpdate.getDatos().put("cantidadDeGestiones",obj.getIdRegistroGestion().getCantidadGestiones().toString());
                  responseUpdate.getDatos().put("estadoGestion", obj.getIdRegistroGestion().getIdEstadoGestion().getNombre());
                  updateKibanaService.actualizarElastic(uuid, dtoKibana, responseUpdate.getDatos(), null, responseUpdate.getIdDoc(),obj.getIdRegistroGestion().getIdEstadoGestion().getNombre());
        
             }
              res=Boolean.TRUE;
             }catch(Exception e){
                  log.error(uuid+" Excepcion actualizando gestiones psicologo a enviar", e);
                 res=Boolean.FALSE;   
             }
       
         return res;
   }




}



