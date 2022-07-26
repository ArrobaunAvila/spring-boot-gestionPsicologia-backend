/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.utils;

import java.util.Base64;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
public class UtilsUserGestion {
    
    
    
    
    public String encodePass(String pass){
        String encodedString = Base64.getEncoder().encodeToString(pass.getBytes());
        return encodedString;
    }
}
