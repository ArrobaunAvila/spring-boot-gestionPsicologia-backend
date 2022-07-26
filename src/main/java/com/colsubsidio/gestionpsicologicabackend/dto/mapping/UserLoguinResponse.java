/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.mapping;

import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class UserLoguinResponse {
    
    private String estado;
    
    private String descripcion;
    
    private GestionUsuarioDTO usuario;
    
    
    
}
