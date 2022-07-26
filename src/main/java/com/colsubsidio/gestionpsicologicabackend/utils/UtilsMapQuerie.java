/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
public class UtilsMapQuerie {
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    
    
    public String queriPorCriterios(String fechaInicial,String fechaFinal,String numDoc,String nombre,String celular,String correo,
            String estado){
        String busqueda=propertiesUtil.getQueriePorCriterios();
        if(!fechaInicial.isEmpty() && !fechaFinal.isEmpty()){
            
            String part="and P.fechaRegistro between CONVERT(DATETIME,'"+fechaInicial+" 00:00',103) and CONVERT(DATETIME,'"+fechaFinal+" 23:59:59',103)";
            busqueda=busqueda.concat(" ").concat(part);
        }if(!numDoc.isEmpty()){
           
           String part="and P.numDocumento='"+numDoc+"' ";
                busqueda=busqueda.concat(" ").concat(part);
            
        }if(!nombre.isEmpty()){
            String part="and  Upper(P.nombre) LIKE upper('%"+nombre+"%') ";
                busqueda=busqueda.concat(" ").concat(part);
        }if(!celular.isEmpty()){
            String part="and P.celular='"+celular+"' ";
                busqueda=busqueda.concat(" ").concat(part);
            
        }if(!correo.isEmpty()){
             String part="and P.correo='"+correo+"' ";
                busqueda=busqueda.concat(" ").concat(part);
            
        }if(!estado.isEmpty()){
             String part="and E.idEstado="+estado+"";
                busqueda=busqueda.concat(" ").concat(part);
        }
        
        
        
        
        return busqueda;
        
    }
    
    
    
    
}
