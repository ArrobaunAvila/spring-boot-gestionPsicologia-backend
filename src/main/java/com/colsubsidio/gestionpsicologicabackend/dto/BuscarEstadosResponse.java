/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto;

import com.colsubsidio.gestionpsicologicabackend.model.entities.EstadoGestion;
import java.util.List;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class BuscarEstadosResponse {
    
    private List<EstadoGestion> estados;
    
    private String estado;
    
    private String descripcion;
    
    
    
}
