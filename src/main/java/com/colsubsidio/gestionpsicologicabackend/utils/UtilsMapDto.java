/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.utils;

import com.colsubsidio.gestionpsicologicabackend.dto.mapping.GestionPsicologoDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.GestionUsuarioDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroGestionPsicologicaDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroPsicologiaDTO;
import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import com.colsubsidio.gestionpsicologicabackend.model.entities.RegistroGestionPsicologica;
import com.colsubsidio.gestionpsicologicabackend.model.entities.UsuarioGestion;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
public class UtilsMapDto {
    
    
    
    
    public GestionPsicologoDTO gestionPsicologoDTO(GestionPsicologo object){
        GestionPsicologoDTO dto= new GestionPsicologoDTO();
        dto.setComentarioGestion(object.getComentarioGestion());
        dto.setFechaGestion(object.getFechaGestion());
        dto.setIdGestionPsicologo(object.getIdGestionPsicologo());
        dto.setIdRegistroGestion(object.getIdRegistroGestion().getIdGestion());
        dto.setUsuarioNombre(object.getIdUsuarioGestiona().getNombreUsuario());
        dto.setNumeroDeGestion(object.getNumeroGestion().toString());
        dto.setIdEstadoGestion(object.getIdEstadoGestion());
        return dto;
    }
    
    public RegistroGestionPsicologicaDTO registroGestionPsicologicaDTO(RegistroGestionPsicologica object){
        RegistroGestionPsicologicaDTO dto= new RegistroGestionPsicologicaDTO();
        dto.setCelular(object.getCelular());
        dto.setCorreo(object.getCorreo());
        dto.setEstado(object.getIdEstadoGestion().getNombre());
        dto.setFechaRegistro(object.getFechaRegistro());
        dto.setIdGestion(object.getIdGestion());
        dto.setRecibioApoyoPsicologico(object.getRecibioApoyoPsicologico());
        dto.setEstado(object.getIdEstadoGestion().getNombre());
        dto.setIdEstadoGestion(object.getIdEstadoGestion().getIdEstado().intValue());
        return dto;
        
        
        
    }
    
    public List<GestionPsicologoDTO> listGestionPsicologoDTO(List<GestionPsicologo>gestionList){
        List<GestionPsicologoDTO> list=new ArrayList<>();
        
        if(gestionList!=null && !gestionList.isEmpty()){
            gestionList.forEach(obj -> {
                list.add(gestionPsicologoDTO(obj));
            });
        }
        
        
        return list;
    }
    
    public GestionUsuarioDTO  gestionUsuarioDTO(UsuarioGestion user){
        
        GestionUsuarioDTO dto=new GestionUsuarioDTO();
        dto.setFechaIngreso(user.getFechaIngreso());
        dto.setIdUsuario(user.getIdUsuario());
        dto.setNombreUsuario(user.getNombreUsuario());
        dto.setUsuarioLogin(user.getUsuarioLogin());
        return dto;
        
    } 
    
    public RegistroPsicologiaDTO registroDto(RegistroGestionPsicologica object){
        
        RegistroPsicologiaDTO dto=new RegistroPsicologiaDTO();
        dto.setCelular(object.getCelular());
        dto.setCorreo(object.getCorreo());
        dto.setEstado(object.getIdEstadoGestion().getNombre());
        dto.setFechaRegistro(object.getFechaRegistro());
        dto.setIdGestion(object.getIdGestion());
        dto.setNumDocumento(object.getNumDocumento());
        dto.setRecibioApoyoPsicologico(object.getRecibioApoyoPsicologico());
        dto.setNombre(object.getNombre());
        dto.setCantidadGestiones(object.getCantidadGestiones().toString());
        return dto;
    }
    
}
