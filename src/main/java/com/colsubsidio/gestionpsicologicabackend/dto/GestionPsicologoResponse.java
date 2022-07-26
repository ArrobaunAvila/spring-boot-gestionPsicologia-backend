/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto;

import com.colsubsidio.gestionpsicologicabackend.dto.mapping.GestionPsicologoDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroPsicologiaDTO;

import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class GestionPsicologoResponse {
    
    private RegistroPsicologiaDTO registro;
    
    private GestionPsicologoDTO gestionPsicologo;
    
    private String estado;
    
    private String descripcion;
    
    
    
}
