/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana;


import java.util.Map;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class SearchResponse {
    
     private Map<String, String> datos;
     
     private String idDoc;
    
}
