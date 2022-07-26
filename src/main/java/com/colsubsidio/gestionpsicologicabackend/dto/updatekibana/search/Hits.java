/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 *
 * @author Anderson
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hits {
    
 private String _index;
 private String _type;
 private String _id;
 private float _score;
 
 @SerializedName("_source")
 _source _source;


    
}
