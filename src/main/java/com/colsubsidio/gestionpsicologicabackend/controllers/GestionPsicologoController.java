/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.controllers;

import com.colsubsidio.gestionpsicologicabackend.dto.ActualizaEstadoGestionRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.ActualizarEstadoGestionResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.BuscarEstadosResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.BusquedaCriterioResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPiscologoRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologoResponse;
import com.colsubsidio.gestionpsicologicabackend.services.GestionPsicologoService;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.Utils;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionesPendientesResponse;
import com.colsubsidio.gestionpsicologicabackend.utils.DateUtil;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author HP
 */

@RestController
@RequestMapping("/api/psicologo")
public class GestionPsicologoController {
    
    
   
  @Autowired
  private GestionPsicologoService gestionPsicologoService;
  
  @Autowired
  private Utils utils;
  
  @Autowired
  private PropertiesUtil propertiesUtil;
  
  @Autowired
  private DateUtil utilDate;
    
   @RequestMapping(value="/pendientes", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody  
   @CrossOrigin
   public ResponseEntity<GestionesPendientesResponse> pendientes(@RequestParam Map<String, String> params){
         
       
      try {
          if(utils.validateCriterios(utils.valorMapa(params, "userLogin"))){
              return new ResponseEntity<>(gestionPsicologoService.buscarGestionesPendientes(utils.uuid(),utils.valorMapa(params, "userLogin")), HttpStatus.OK);
          }else{
              GestionesPendientesResponse response= new GestionesPendientesResponse();
              response.setDescripcion("Falta paramero UserLogin");
              response.setEstado("FALSE");
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
          }
          
      } catch (Exception ex) {
         return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
      }
       
       
   } 
   
    @RequestMapping(value="/registrar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<GestionPsicologoResponse> registrar(@RequestBody GestionPiscologoRequest request) {
            
        try {
            
             if(utils.validateCriterios(request.getComentarioGestion())
                && utils.validateCriterios(request.getIdGestion())     
                && utils.validateCriterios(request.getUserLogin())){
                
                    return new ResponseEntity<>(gestionPsicologoService.registrar(utils.uuid(),request), HttpStatus.OK);
                
                
                }else{
                     GestionPsicologoResponse response= new GestionPsicologoResponse(); 
                     response.setEstado("FALSE");
                     response.setDescripcion("Los valores : userLogin, idGestion, comentarioGestion son requeridos");
                     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                 }
            
            
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    
    
    @RequestMapping(value="/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<ActualizarEstadoGestionResponse> actualizarEstado(@RequestBody ActualizaEstadoGestionRequest request) {

        try {
             if(utils.validateCriterios(request.getIdEstado())
                     && utils.validateCriterios(request.getIdGestion())
                     && utils.validateCriterios(request.getUserLogin())){
                 return new ResponseEntity<ActualizarEstadoGestionResponse>(gestionPsicologoService.actualizaEstado(utils.uuid(),request), HttpStatus.OK); 
             }else{
                 ActualizarEstadoGestionResponse response= new ActualizarEstadoGestionResponse();
                 response.setDescripcion("Los valores : userLogin, idGestion, idEstado son requeridos");
                 response.setEstado("FALSE");
                 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
             } 
             
           
        } catch (Exception ex) {
          
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
           
        }

    }
    
   @RequestMapping(value="/criterios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
   @CrossOrigin
   public ResponseEntity<BusquedaCriterioResponse> busquedaPorCriterio(@RequestParam Map<String, String> params){
       
         
      try {
            BusquedaCriterioResponse busquedaCriterioResponse= new BusquedaCriterioResponse();
            if(!utils.validateCriterios(utils.valorMapa(params, "userLogin"))){
                BusquedaCriterioResponse response= new BusquedaCriterioResponse();
                response.setEstado("FALSE");
                response.setDescripcion("Falta paramero UserLogin");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }if(!utils.validateCriterios(utils.valorMapa(params, "fechaRegistroInicial"))
                   && !utils.validateCriterios(utils.valorMapa(params, "numeroDocumento"))
                   && !utils.validateCriterios(utils.valorMapa(params, "nombre"))
                   && !utils.validateCriterios(utils.valorMapa(params, "celular"))
                   && !utils.validateCriterios(utils.valorMapa(params, "correo"))
                   && !utils.validateCriterios(utils.valorMapa(params, "estado")
                   )
            ){
                   
                       busquedaCriterioResponse.setDescripcion("DEBE INGRESAR AL MENOS UN CRITERIO");
                       busquedaCriterioResponse.setEstado("FALSE");
                       return new ResponseEntity<>(busquedaCriterioResponse, HttpStatus.BAD_REQUEST);
               
                   
                   
                          
             }if(( utils.validateCriterios(utils.valorMapa(params, "fechaRegistroInicial")) 
                     &&  !utils.validateCriterios(utils.valorMapa(params, "fechaRegistroFinal")))
                     || ( !utils.validateCriterios(utils.valorMapa(params, "fechaRegistroInicial"))
                         && utils.validateCriterios(utils.valorMapa(params, "fechaRegistroFinal"))
                     )){
                 
                       busquedaCriterioResponse.setDescripcion("SI BUSCA POR FECHAS DEBE INGRESAS FECHA INICIAL Y FECHA FINAL");
                       busquedaCriterioResponse.setEstado("FALSE");
                       return new ResponseEntity<>(busquedaCriterioResponse, HttpStatus.BAD_REQUEST);
             
             
             
             
             }
             
             if(  utils.validateCriterios(utils.valorMapa(params, "fechaRegistroInicial"))
                   &&  utils.validateCriterios(utils.valorMapa(params, "fechaRegistroFinal"))  
                   &&  ( !utils.validateFecha(utils.valorMapa(params, "fechaRegistroInicial"))
                        ||  !utils.validateFecha(utils.valorMapa(params, "fechaRegistroFinal"))
                    )) {
                 
                 
                          busquedaCriterioResponse.setDescripcion(this.propertiesUtil.getValidateDateMsj());
                          busquedaCriterioResponse.setEstado("FALSE");
                          return new ResponseEntity<>(busquedaCriterioResponse, HttpStatus.BAD_REQUEST);
                 
                 
                 
                      }
             
             
             if(utils.validateCriterios(utils.valorMapa(params, "fechaRegistroInicial"))
                     && utils.validateCriterios(utils.valorMapa(params, "fechaRegistroFinal"))) {
                 
                Date ini=utilDate.stringTodate(utils.valorMapa(params, "fechaRegistroInicial"), this.propertiesUtil.getFormatoFechaBusqueda());
                Date fin=utilDate.stringTodate(utils.valorMapa(params, "fechaRegistroFinal"), this.propertiesUtil.getFormatoFechaBusqueda());
                if(fin.before(ini)){
                       busquedaCriterioResponse.setDescripcion("FECHA FINAL ES MAYOR A FECHA INICIAL");
                       busquedaCriterioResponse.setEstado("FALSE");
                       return new ResponseEntity<>(busquedaCriterioResponse, HttpStatus.BAD_REQUEST);
                }
             }
            
            
            
            
            
             
             
             
            
                              
                              
                   busquedaCriterioResponse = gestionPsicologoService.busquedaPorCriterio(utils.uuid(),
                           utils.valorMapa(params, "fechaRegistroInicial"),
                           utils.valorMapa(params, "fechaRegistroFinal"),
                           utils.valorMapa(params, "numeroDocumento"),
                           utils.valorMapa(params, "nombre"),
                           utils.valorMapa(params, "celular"),
                           utils.valorMapa(params, "correo"),
                           utils.valorMapa(params, "estado"),
                           utils.valorMapa(params, "userLogin")
                           );
                     
                   
                   return new ResponseEntity<>(busquedaCriterioResponse, HttpStatus.OK);
            
             
          
         
       } catch (Exception ex) {
          return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
      }
       
   }
   
    
   @RequestMapping(value="/estados", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody  
   @CrossOrigin
   public ResponseEntity<BuscarEstadosResponse> estados(@RequestParam Map<String, String> params){
         
       
       
      try {
          if(utils.validateCriterios(utils.valorMapa(params, "userLogin"))){
              return new ResponseEntity<>(gestionPsicologoService.estados(utils.uuid(),utils.valorMapa(params, "userLogin")), HttpStatus.OK);
          }else{
              BuscarEstadosResponse response= new BuscarEstadosResponse();
              response.setDescripcion("Falta paramero UserLogin");
              response.setEstado("FALSE");
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
          }
          
      } catch (Exception ex) {
         return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
      }
       
       
   }  
   
   
}
