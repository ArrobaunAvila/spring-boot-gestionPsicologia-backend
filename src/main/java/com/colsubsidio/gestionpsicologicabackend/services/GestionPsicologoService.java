/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.services;

import com.colsubsidio.gestionpsicologicabackend.dto.ActualizaEstadoGestionRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.ActualizarEstadoGestionResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.BuscarEstadosResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.BusquedaCriterioResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPiscologoRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologoResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionesPendientesResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroGestionPsicologicaDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.SearchResponse;
import com.colsubsidio.gestionpsicologicabackend.model.entities.EstadoGestion;
import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import com.colsubsidio.gestionpsicologicabackend.model.entities.RegistroGestionPsicologica;
import com.colsubsidio.gestionpsicologicabackend.model.entities.UsuarioGestion;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.EstadoGestionRepository;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.GestioPsicologoRepository;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.GestionPsicoRepositoryI;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.RegistroGestionRepository;
import com.colsubsidio.gestionpsicologicabackend.model.repositories.UsuarioGestionRepository;
import com.colsubsidio.gestionpsicologicabackend.utils.Constantes;
import com.colsubsidio.gestionpsicologicabackend.utils.DateUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsMapDto;
import com.colsubsidio.gestionpsicologicabackend.utils.UtilsMapQuerie;
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
public class GestionPsicologoService {
    
@Autowired
private PropertiesUtil propertiesUtil;
    
@Autowired
private GestioPsicologoRepository gestioPsicologoRepository;

@Autowired
private GestionPsicoRepositoryI repoI;

@Autowired
private LogService logService;

@Autowired 
private UsuarioGestionRepository usuarioGestionRepository;

@Autowired 
private RegistroGestionRepository registroGestionRepository;

@Autowired
private UtilsMapDto utilsMapDto;

@Autowired
private EstadoGestionRepository estadoGestionRepository;

@Autowired
private UtilsMapQuerie utilsMapQuerie;

@Autowired
private DateUtil utilDate;

@Autowired
private UpdateKibanaService updateKibanaService;


    
 public GestionesPendientesResponse buscarGestionesPendientes(String uuid,String userLogin) throws Exception{
       GestionesPendientesResponse response= new GestionesPendientesResponse();
       GestionPsicologicaKibana dtoKibana= new GestionPsicologicaKibana();
       List<RegistroGestionPsicologicaDTO> list;
    try {
      
        dtoKibana.setStartdate(new Date());
        dtoKibana.setUserLogin(userLogin);
        dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(),Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
        List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
        dtoKibana.setServices(services);
        list=gestioPsicologoRepository.buscarGestionesPendientes(uuid,dtoKibana);
        if(list!=null && !list.isEmpty()){
            response.setRegistroGestionesPendientes(list);
            response.setDescripcion("busqueda con registros");
            response.setEstado("OK");
        }else{
            response.setDescripcion("busqueda sin registros");
            response.setEstado("OK");
        }
        dtoKibana.setOk(Boolean.TRUE);
    } catch (Exception ex) {
        dtoKibana.setOk(Boolean.FALSE);
        dtoKibana.setError(ex.getMessage());
        
        log.error(uuid+" Excepcion recuperando datos a gestionar", ex);
    }finally{
         logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibanaSidePendientes());
    }
     
    return response;
     
 }
    
 public GestionPsicologoResponse registrar(String uuid,GestionPiscologoRequest registrarGestionPiscologo) throws Exception{
     GestionPsicologoResponse response= new GestionPsicologoResponse();
     GestionPsicologicaKibana dtoKibana= new GestionPsicologicaKibana();
     List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
     dtoKibana.setServices(services);
     dtoKibana.setStartdate(new Date());
     dtoKibana.setUserLogin(registrarGestionPiscologo.getUserLogin());
     dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
     GestionPsicologicaKibana.Service buscarGestiones = logService.initLogService("guardar-gestion", 
                 "", registrarGestionPiscologo, true);
     dtoKibana.getServices().add(buscarGestiones);
        
         
         GestionPsicologicaKibana.Service buscarUsuario = logService.initLogService("busca-usuario-gestion", 
                 "", "userLogin: "+registrarGestionPiscologo.getUserLogin(), true);
         dtoKibana.getServices().add(buscarUsuario);
         try {
             List<UsuarioGestion> list = usuarioGestionRepository.usuarioPorLogin(registrarGestionPiscologo.getUserLogin());
             RegistroGestionPsicologica find=registroGestionRepository.findById(Integer.valueOf(registrarGestionPiscologo.getIdGestion())).get();
              EstadoGestion estadoGestion=estadoGestionRepository.getOne(this.propertiesUtil.getIdEstadoSesion());
                        
             if (list != null && !list.isEmpty()) {
                 UsuarioGestion usuario=list.get(0);
                 logService.completeResponseService(buscarUsuario, true, usuario, true);
                 GestionPsicologo object = new GestionPsicologo();
                 object.setComentarioGestion(registrarGestionPiscologo.getComentarioGestion());
                 object.setFechaGestion(new Date());
                 object.setIdRegistroGestion(new RegistroGestionPsicologica(Integer.valueOf(registrarGestionPiscologo.getIdGestion())));
                 object.setIdUsuarioGestiona(new UsuarioGestion(usuario.getIdUsuario()));
                 object.setEnvioKibana(Boolean.FALSE);
                 object.setIdEstadoGestion(estadoGestion.getIdEstado().intValue());
                 if(find!=null && find.getGestionPsicologoList()!=null&&!find.getGestionPsicologoList().isEmpty()){
                      object.setNumeroGestion(find.getGestionPsicologoList().size()+1);
                 }else{
                     object.setNumeroGestion(1);   
                 }
                
                 try{
                     object=repoI.saveAndFlush(object);
                     logService.completeResponseService(buscarGestiones, true, "guardado con exito", false);
                     response.setDescripcion("guardado con exito");
                     response.setEstado("OK");
                     if(find!=null){
                         find.setIdEstadoGestion(estadoGestion);
                     
                     }
                     
                     if(find!=null&&(find.getGestionPsicologoList()==null || find.getGestionPsicologoList().isEmpty())){
                         find.setCantidadGestiones(1);
                       
                     }else{
                         find.setCantidadGestiones(find.getGestionPsicologoList().size()+1);
                     }
                     find=registroGestionRepository.saveAndFlush(find);
                     response.setRegistro(utilsMapDto.registroDto(find));
                     response.setGestionPsicologo(utilsMapDto.gestionPsicologoDTO(object));
                     response.getGestionPsicologo().setUsuarioNombre(usuario.getNombreUsuario());
                     logService.completeResponseService(buscarGestiones, true, response, true);
                     dtoKibana.setGestionPsicologo(response.getGestionPsicologo());
                     dtoKibana.setRegistroGestion(response.getRegistro());
                     
                 }catch(Exception e){
                     log.error(uuid+" Excepcion guardando datos a gestionar", e);
                     logService.completeResponseService(buscarGestiones, false, e.getMessage(), false);
                     response.setDescripcion("Excepcion guardando datos a gestionar: "+e.getMessage());
                     response.setEstado("ERROR");
                 }
                
             
             } else {
                 response.setDescripcion("Excepcion guardando datos a gestionar: No existe usuario "+registrarGestionPiscologo.getUserLogin());
                 response.setEstado("ERROR");
                 logService.completeResponseService(buscarUsuario, true, "No se encontraron registros de usuario: "
                         + registrarGestionPiscologo.getUserLogin(), true);

             }
             dtoKibana.setOk(Boolean.TRUE);
         } catch (Exception e) {
             log.error(uuid + " Excepcion al buscar usuarios :" + registrarGestionPiscologo.getUserLogin());
             logService.completeResponseService(buscarUsuario, false, e.getMessage(), false);
             dtoKibana.setOk(Boolean.FALSE);
             dtoKibana.setError(e.getMessage());
             response.setDescripcion("Excepcion guardando datos a gestionar: "+e.getMessage());
             response.setEstado("ERROR");
         }finally{
             logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibanaSideRegistrar());
             SearchResponse responseUpdate=updateKibanaService.searchDatos(uuid, registrarGestionPiscologo.getIdGestion(), dtoKibana);
             if(responseUpdate!=null && responseUpdate.getDatos()!=null){
                  responseUpdate.getDatos().put("cantidadDeGestiones",response.getRegistro().getCantidadGestiones());
                  responseUpdate.getDatos().put("estadoGestion", response.getRegistro().getEstado());
                  updateKibanaService.actualizarElastic(uuid, dtoKibana, responseUpdate.getDatos(), null, responseUpdate.getIdDoc(),response.getRegistro().getEstado());
        
             }
            
         }
        
         
         
         
        
        
     
     
      return response;
 
 }   
   

 public ActualizarEstadoGestionResponse actualizaEstado(String uuid, ActualizaEstadoGestionRequest request) throws Exception{
     GestionPsicologicaKibana dtoKibana= new GestionPsicologicaKibana();
     List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
     dtoKibana.setServices(services);
     dtoKibana.setStartdate(new Date());
     dtoKibana.setUserLogin(request.getUserLogin());
     dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
     GestionPsicologicaKibana.Service actualizarEstado = logService.initLogService("actualizar-estado-gestion", 
                 "", request, true);
     dtoKibana.getServices().add(actualizarEstado);
     ActualizarEstadoGestionResponse response=new ActualizarEstadoGestionResponse();
     try{
         
         RegistroGestionPsicologica registro=registroGestionRepository.findById(Integer.valueOf(request.getIdGestion())).get();
         EstadoGestion estadoGestion=estadoGestionRepository.getOne(Short.valueOf(request.getIdEstado()));
         registro.setIdEstadoGestion(estadoGestion);
         
         registro = registroGestionRepository.saveAndFlush(registro);
         SearchResponse responseUpdate=updateKibanaService.searchDatos(uuid, registro.getIdGestion().toString(), dtoKibana);
         if(responseUpdate!=null && responseUpdate.getDatos()!=null){
             responseUpdate.getDatos().put("estadoGestion",registro.getIdEstadoGestion().getNombre());
             updateKibanaService.actualizarElastic(uuid, dtoKibana, responseUpdate.getDatos(), null, responseUpdate.getIdDoc(),estadoGestion.getNombre());
         
         }
         
         response.setDescripcion("guardado con exito");
         response.setEstado("OK");
         RegistroGestionPsicologicaDTO dto=utilsMapDto.registroGestionPsicologicaDTO(registro);
         List<GestionPsicologo> listGestion=repoI.cargarGestionesRegistro(registro.getIdGestion());
         dto.setGestionPsicologo(utilsMapDto.listGestionPsicologoDTO(listGestion));
         
         response.setRegistroGestionPsicologica(dto);
         logService.completeResponseService(actualizarEstado, true, "actualizado con exito", false);
         dtoKibana.setOk(Boolean.TRUE);
     }catch(Exception ex){
             log.error(uuid + " Excepcion al actualizar estado gestion ID :" + request.getIdGestion());
             logService.completeResponseService(actualizarEstado, false, ex.getMessage(), false);
             dtoKibana.setOk(Boolean.FALSE);
             dtoKibana.setError(ex.getMessage());
             response.setDescripcion("Excepcion guardando datos a gestionar: "+ex.getMessage());
             response.setEstado("ERROR");
          
     }finally{
         logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibaSideActualizar());
    }
      return response;
     
 }
 
 
    public BusquedaCriterioResponse busquedaPorCriterio(String uuid, String fechaInicial,String fechaFinal, String numDoc, String nombre, String celular, String correo,String estado,String userLogin) {

        GestionPsicologicaKibana dtoKibana = new GestionPsicologicaKibana();
        List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
        dtoKibana.setServices(services);
        dtoKibana.setStartdate(new Date());
        GestionPsicologicaKibana.Service generandoCOnsulta = logService.initLogService("generar-querie",
                "", "", false);
         dtoKibana.getServices().add(generandoCOnsulta);
        String consulta = utilsMapQuerie.queriPorCriterios(fechaInicial,fechaFinal, numDoc, nombre, celular, correo,estado);
        logService.completeResponseService(generandoCOnsulta, true, consulta, false);
        dtoKibana.setUserLogin(userLogin);
        dtoKibana.setFechaHoraOperacion(utilDate.dateToString(dtoKibana.getStartdate(), Constantes.FORMAT_DD_MM_YYYY_HH_MM_SS));
        BusquedaCriterioResponse response=new BusquedaCriterioResponse();
        GestionPsicologicaKibana.Service consultando = logService.initLogService("consulta-criterios",
                "", consulta, false);
         dtoKibana.getServices().add(consultando);
        try {
            List<RegistroGestionPsicologicaDTO> lista= gestioPsicologoRepository.buscarGestionesPorCriterio(uuid, dtoKibana, consulta);
            if(lista!=null&&!lista.isEmpty()){
                response.setDescripcion("busqueda con registros");
                response.setEstado("OK");
                response.setRegistroGestionPsicologicaList(lista);
                logService.completeResponseService(generandoCOnsulta, true, response, true);
            }else{
                response.setDescripcion("busqueda sin registros");
                response.setEstado("OK");
                logService.completeResponseService(generandoCOnsulta, true, response, true);
            }
            dtoKibana.setOk(Boolean.TRUE);
        } catch (Exception ex) {
              dtoKibana.setOk(Boolean.FALSE);
              dtoKibana.setError(ex.getMessage());
              response.setDescripcion(ex.getMessage());
              response.setEstado("ERROR");
             log.error(uuid+" Excepcion recuperando datos a gestionar", ex);
             logService.completeResponseService(generandoCOnsulta, false, ex.getMessage(), false);
        }finally{
         logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibaSideBuscarPorCriterios());
    }
        return response;
    }
    
    
    public BuscarEstadosResponse estados(String uuid,String userLogin){
       
        GestionPsicologicaKibana dtoKibana= new GestionPsicologicaKibana();
        BuscarEstadosResponse response= new BuscarEstadosResponse();
         GestionPsicologicaKibana.Service buscarEstados = logService.initLogService("buscarEstados",
                "", "", false);
        try{ 
             List<GestionPsicologicaKibana.Service> services = new ArrayList<>(0);
             dtoKibana.setServices(services);
             dtoKibana.setStartdate(new Date());
            
             dtoKibana.getServices().add(buscarEstados); 
             List<EstadoGestion> estados=estadoGestionRepository.findAll();
             if(estados!=null && !estados.isEmpty()){
                response.setDescripcion("busqueda con registros");
                response.setEstado("OK");
                response.setEstados(estados);
                logService.completeResponseService(buscarEstados, true, response, true);
                 
             }else{
                response.setEstado("OK");
               response.setDescripcion("busqueda sin registros");
                logService.completeResponseService(buscarEstados, true, response, false);
             }
        }catch(Exception e){
              dtoKibana.setOk(Boolean.FALSE);
              dtoKibana.setError(e.getMessage());
              response.setDescripcion(e.getMessage());
              response.setEstado("ERROR");
             log.error(uuid+" Excepcion recuperando datos a gestionar", e);
             logService.completeResponseService(buscarEstados, false, e.getMessage(), false);
        }finally{
         logService.sendLogToKibana(dtoKibana, uuid,this.propertiesUtil.getKibaSideBuscarPorCriterios());
       }
        
        
        
        return response;
    }
    
    

}
