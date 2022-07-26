/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.services;

import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.GuardarUsuarioRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.GuardarUsuarioResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.LoguinUsuarioRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.UserLoguinResponse;
import com.colsubsidio.gestionpsicologicabackend.model.entities.UsuarioGestion;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.UsuarioGestionRepository;
import com.colsubsidio.gestionpsicologicabackend.utils.Constantes;
import com.colsubsidio.gestionpsicologicabackend.utils.DateUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.Utils;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsMapDto;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsUserGestion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
@Slf4j
public class GestionUsuarioService {
    
    
    @Autowired
    private UsuarioGestionRepository usuarioGestionRepository;
    
    @Autowired
    private UtilsUserGestion  utilsUserGestion;
    
    @Autowired
    private DateUtil utilDate;
    
    @Autowired
    private UtilsMapDto utilsMap;
    
    @Autowired
    private LogService logService;
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    
    @Autowired
  private Utils utils;
    
    public GuardarUsuarioResponse save(String uuid,GuardarUsuarioRequest request) throws Exception{
        GestionPsicologicaKibana dtoKibana = new GestionPsicologicaKibana();
        List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
        dtoKibana.setServices(services);
        dtoKibana.setStartdate(new Date());
        dtoKibana.setUserLogin(request.getUsuarioSesion());
        dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
        GuardarUsuarioResponse response= new GuardarUsuarioResponse();
        
        String pass=request.getPassword();
        request.setPassword("");
        
        GestionPsicologicaKibana.Service guardarUsuario = logService.initLogService("guardar-usuario",
                "", request, true);
        dtoKibana.getServices().add(guardarUsuario);
        try {
            List<UsuarioGestion> list = usuarioGestionRepository.usuarioPorLogin(request.getUsuarioLogin());
            if(utils.validatePass(pass)){
              if (list != null && !list.isEmpty()) {
                response.setDescripcion("Ya existe usuario con este userLogin");
                response.setEstado("FALSE");
            } else {
                UsuarioGestion object = new UsuarioGestion();
                object.setFechaIngreso(new Date());
                object.setNombreUsuario(request.getNombreUsuario());
                object.setUsuarioLogin(request.getUsuarioLogin());
                object.setPassword(utilsUserGestion.encodePass(pass));
                object = usuarioGestionRepository.saveAndFlush(object);
                response.setDescripcion("guardado con exito");
                response.setEstado("OK");
                response.setUsuario(utilsMap.gestionUsuarioDTO(object));
            }
            }else{
                response.setDescripcion("password debe poseer de 4-8, números y letras mayusculas y minusculas");
                response.setEstado("FALSE");
            }
            


            logService.completeResponseService(guardarUsuario, true, response, true);
        } catch (Exception e) {
            logService.completeResponseService(guardarUsuario, false, e, false);
        } finally {
            logService.sendLogToKibana(dtoKibana, uuid, this.propertiesUtil.getKibaSideCrearUsuario());
        }
        return response;
    }
    
    
    
    public UserLoguinResponse loguin(String uuid,LoguinUsuarioRequest request){
        
        UserLoguinResponse response= new UserLoguinResponse();
        GestionPsicologicaKibana dtoKibana = new GestionPsicologicaKibana();
        List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
        dtoKibana.setServices(services);
        dtoKibana.setStartdate(new Date());
        dtoKibana.setUserLogin(request.getUserLogin());
        dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
        
        String password=request.getPassword();
        request.setPassword("");
        GestionPsicologicaKibana.Service buscarUsuarioService = logService.initLogService("buscar-usuario",
                "", request, true);
        
        dtoKibana.getServices().add(buscarUsuarioService);
        try{
           String pass= utilsUserGestion.encodePass(password);
           List<UsuarioGestion> list= usuarioGestionRepository.usuarioPorLoginPass(request.getUserLogin(),pass);
           
           if(list!=null && !list.isEmpty()){
               response.setDescripcion("usuario encontrado con exito");
               response.setEstado("OK");
               response.setUsuario(utilsMap.gestionUsuarioDTO(list.get(0)));
               
           }else{
               response.setDescripcion("No se encontro usuario");
               response.setEstado("FALSE");
           }
        
           logService.completeResponseService(buscarUsuarioService, true, response, true);
        }catch(Exception e){
             logService.completeResponseService(buscarUsuarioService, false, e, false);
        }finally{
           logService.sendLogToKibana(dtoKibana, uuid, this.propertiesUtil.getKibaSideLoguin());
        }
        
        
        
        return response;
    }
    
    
}
