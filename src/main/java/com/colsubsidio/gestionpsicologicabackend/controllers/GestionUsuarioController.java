/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.controllers;


import com.colsubsidio.gestionpsicologicabackend.dto.GuardarUsuarioRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.GuardarUsuarioResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.LoguinUsuarioRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.UserLoguinResponse;
import com.colsubsidio.gestionpsicologicabackend.services.GestionUsuarioService;
import com.colsubsidio.gestionpsicologicabackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api/user")
public class GestionUsuarioController {
    
    @Autowired
    private GestionUsuarioService gestionUsuarioService;
    
    @Autowired
    private Utils utils;
    
    
    @RequestMapping(value="/crear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<GuardarUsuarioResponse> crear(@RequestBody GuardarUsuarioRequest request) {
            
        try {
            
             if(utils.validateCriterios(request.getNombreUsuario())
                && utils.validateCriterios(request.getPassword())     
                && utils.validateCriterios(request.getUsuarioSesion())
                && utils.validateCriterios(request.getNombreUsuario())){
                
                    return new ResponseEntity<>(gestionUsuarioService.save(utils.uuid(),request), HttpStatus.OK);
                
                
                }else{
                     GuardarUsuarioResponse response = new GuardarUsuarioResponse();
                     response.setEstado("FALSE");
                     response.setDescripcion("Los valores : usuarioSesion, NombreUsuario,userLoguin, password son requeridos");
                     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                 }
            
            
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    
    @RequestMapping(value="/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<UserLoguinResponse> login(@RequestBody LoguinUsuarioRequest request) {
            
        try {
            
             if(utils.validateCriterios(request.getPassword())
                && utils.validateCriterios(request.getUserLogin())     
                ){
                
                    return new ResponseEntity<>(gestionUsuarioService.loguin(utils.uuid(),request), HttpStatus.OK);
                
                
                }else{
                     UserLoguinResponse response = new UserLoguinResponse();
                     response.setEstado("FALSE");
                     response.setDescripcion("Los valores : userLoguin, password son requeridos");
                     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                 }
            
            
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    
}
