/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class GuardarUsuarioRequest {
    
    private String nombreUsuario;
    
    private String usuarioLogin;
    
    private String password;
    
    private String usuarioSesion;
    
    
    
}
