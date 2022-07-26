/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.mapping;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class GestionUsuarioDTO {
    
    
    
    private Integer idUsuario;
   
    private String usuarioLogin;
    
    private String password;
    
    private String nombreUsuario;
    
    private Date fechaIngreso;
    
}
