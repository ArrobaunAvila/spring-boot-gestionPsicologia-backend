/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.utils;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 *
 * @author HP
 */
@Component
public class Utils {
    
    @Autowired
    private PropertiesUtil propertiesUtil;
    
    public String uuid(){
        return UUID.randomUUID().toString();
    }
    
    
    
    public String valorMapa(Map<String, String> params,String clave){
        if(params.containsKey(clave)){
           return params.get(clave);
        }else{
            return "";
        }
    }
    
    public boolean validateCriterios(String valor){
        if(valor==null || valor.isEmpty()){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }
    
    public boolean validateFecha(String valor){
        if(valor!=null&&!valor.isEmpty()){
            if(valor.matches(this.propertiesUtil.getValidateFormat())){
                return Boolean.TRUE;
            }else{
                return Boolean.FALSE;
            }
        }else{
            return Boolean.TRUE;
        }
    }
    
    
    public boolean validatePass(String valor){
        Pattern p = Pattern.compile(this.propertiesUtil.getValidatePass());
        if(valor!=null&&!valor.isEmpty()){
             Matcher m = p.matcher(valor);
             return m.matches();
        }else{
            return Boolean.FALSE;
        }
    }
    
    
    public String authHeader() {

        String username = propertiesUtil.getKibanaUser();
        String password = propertiesUtil.getKibanaPassword().trim();

        byte[] encodedBytes = Base64Utils.encode((username + ":" + password).getBytes());

        String authHeader = Constantes.BASIC + new String(encodedBytes);

        return authHeader;
        
        
    }
}
