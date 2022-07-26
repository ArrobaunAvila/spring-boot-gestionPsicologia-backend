/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.services;


import com.colsubsidio.gestionpsicologicabackend.client.KibbanaClientRest;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.Params;

import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.Query;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.Script;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.SearchResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.Term;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.UpdateAppointmentRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.UpdateAppointmentResponse;

import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search.Hits;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search.Principal;
import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import com.colsubsidio.gestionpsicologicabackend.utils.Constantes;
import com.colsubsidio.gestionpsicologicabackend.utils.DateUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.Utils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
@Slf4j
public class UpdateKibanaService {
    
 @Autowired
 private LogService logService;  
 
 @Autowired
 private PropertiesUtil propertiesUtil;
 
 @Autowired
 private KibbanaClientRest kibanaClient;
 
 @Autowired
 private Utils utils;
 
 @Autowired
 private DateUtil utilsDate;
 
 
    
    @Async
    public boolean actualizarElastic(String uuid,GestionPsicologicaKibana dtoKibana,Map<String, String> datos,GestionPsicologo gs,
            String idDoc,String estado){
        
        UpdateAppointmentRequest request = new UpdateAppointmentRequest();
        UpdateAppointmentResponse result;
        Query query= new Query() ;

        Term term = new Term();
        term.setId(idDoc);
        query.setTerm(term);
        request.setQuery(query);
        Script script = new Script();
        script.setSource("ctx._source.datos = params.datos");
        Params params = new Params();
        
        script.setParams(params);
        request.setScript(script);
        boolean res=Boolean.FALSE;
        GestionPsicologicaKibana.Service logUpdate = logService.initLogService("update-elastic", propertiesUtil.getEndpointElasticService(), request, true);
        dtoKibana.getServices().add(logUpdate);
        if(estado!=null && !estado.isEmpty()){
            datos.put("estadoGestion",estado);
        }
//        if(gs!=null){
//            if(datos.getComentarioGestionUnoPsicologo()==null || datos.getComentarioGestionUnoPsicologo().isEmpty()){
//            datos.setComentarioGestionUnoPsicologo(gs.getComentarioGestion());
//            datos.setFechaGestionUnoPsicologo(utilsDate.dateToString(gs.getFechaGestion(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
//        }else if(datos.getComentarioGestionDosPsicologo()==null || datos.getComentarioGestionDosPsicologo().isEmpty()){
//             datos.setComentarioGestionDosPsicologo(gs.getComentarioGestion());
//             datos.setFechaGestionDosPsicologo(utilsDate.dateToString(gs.getFechaGestion(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
//        }else{
//            datos.setComentarioGestionTresPsicologo(gs.getComentarioGestion());
//            datos.setFechaGestionTresPsicologo(utilsDate.dateToString(gs.getFechaGestion(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
//        }
//         
//        }
        params.setDatos(datos);
        
        try{
            result = kibanaClient.updateAppointment(utils.authHeader(), request);
            if(result!=null && result.getUpdated()!= null && result.getUpdated()>0 ) {
                log.info("{} - se actualizo elastic para la gestion con ID: {}", uuid,datos.get("idGestion"));
                logService.completeResponseService(logUpdate, true, result, true);
                res=Boolean.TRUE;
             } else {
                log.info("{} - No se actualizo elastic para la gestion con ID: {}", uuid,datos.get("idGestion"));
                logService.completeResponseService(logUpdate, true,
                        "No se actualizo elastic para la gestion con ID "+datos.get("idGestion"), false);
            }
            dtoKibana.setOk(Boolean.TRUE);
        }catch(Exception e){
            dtoKibana.setOk(Boolean.FALSE);
             dtoKibana.setError(e.getMessage());
            logService.completeResponseService(logUpdate, false, e.getMessage(), false);
            log.error(uuid+" Excepcion actualizando gestion en kibana: "+datos.get("idGestion"), e); 
            e.printStackTrace();
        }
        return res;
    }
    
    
    public SearchResponse searchDatos(String uuid,String idGestion,GestionPsicologicaKibana dtoKibana){
        
        String querie=this.propertiesUtil.getElasticSearchQuery().replace("#idGestion#", idGestion);
        GestionPsicologicaKibana.Service consultarKibana = logService.initLogService("consultar-kibana",
                this.propertiesUtil.getEndpointElasticService(), querie, true);
        dtoKibana.getServices().add(consultarKibana);
        Principal principal;
         Map<String, String> datos=null;
        SearchResponse response=new SearchResponse();
        try{
            
            principal = kibanaClient.getDatos(utils.authHeader(), querie);

            if (principal != null && principal.getHits() != null && principal.getHits() != null && principal.getHits().getHits() != null && principal.getHits().getHits().size() > 0) {
                logService.completeResponseService(consultarKibana, true, principal, true);
                for (Hits hs : principal.getHits().getHits()) {
                    if (hs.get_source() == null || hs.get_source().getDatos() == null) {
                         logService.completeResponseService(consultarKibana, true, "No se han encontrado registro en Kibana", false);
                    }else{
                         datos=hs.get_source().getDatos();
                         if(datos!=null){
                             response.setDatos(datos);
                             response.setIdDoc(hs.get_id());
                         }
                         
                         
                         logService.completeResponseService(consultarKibana, true, datos, true);
                    }
                   
                }

            }else{
               logService.completeResponseService(consultarKibana, true, "No se han encontrado registro en Kibana", false);
           }
        dtoKibana.setOk(Boolean.TRUE);
        }catch(Exception e){
            dtoKibana.setOk(Boolean.FALSE);
           logService.completeResponseService(consultarKibana, false, e.getMessage(), false);
           log.error(uuid+" Excepcion buscando gestion en kibana: "+idGestion, e); 
        }
        
        return response;
        
    }
    
    
    
}
