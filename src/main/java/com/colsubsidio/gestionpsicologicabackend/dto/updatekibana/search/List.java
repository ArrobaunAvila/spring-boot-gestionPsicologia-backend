/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 *
 * @author Anderson
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class List {
    
    private Total total;
    java.util.List<Hits> hits;
    
}
