/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search;


import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author Anderson
 */
@Data
public class _source {
    
    @SerializedName("datos")
    Map<String, String> datos;
    
    
}
