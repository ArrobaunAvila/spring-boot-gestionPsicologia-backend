/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.mapping;


import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class RegistroGestionPsicologicaDTO {
    
    
    
    private Integer idGestion;
    
    private Date fechaRegistro;
    
    private String numDocumento;
   
    private String nombre;
    
    private String celular;
    
    private String correo;
    
    private boolean recibioApoyoPsicologico;
   
    private List<GestionPsicologoDTO> gestionPsicologo;
    
    private String estado;
    
    private Integer idEstadoGestion;
    
    
    
}
