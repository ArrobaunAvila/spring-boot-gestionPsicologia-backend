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
public class RegistroPsicologiaDTO {
    
    
   
   
    
    private Integer idGestion;
    
    private Date fechaRegistro;
    
    private String numDocumento;
    
    private String nombre;
    
    private String celular;
    
    private String correo;
    
    private boolean recibioApoyoPsicologico;
    
    private boolean envioKibana;
    
    private String dataKibana;
    
    private String estado;
    
    private String cantidadGestiones;
    
}
