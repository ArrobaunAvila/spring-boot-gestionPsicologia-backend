/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.client;


import com.colsubsidio.gestionpsicologicabackend.config.FooClientConfig;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.dto.LogAuthRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.LogAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author Anderson
 */
@FeignClient(name = "elasticsearch", url = "${log.endpoint.url}", configuration = FooClientConfig.class)
@Headers("Accept: application/json")
public interface LogClientRest {

   
    
    @PostMapping("${log.endpoint.oauth.method}")
    public LogAuthResponse getAuthToken(@RequestBody LogAuthRequest authRequest);

    @PostMapping("${log.endpoint.log.method}")
    public ResponseEntity<?> sendLogtoKibana(@RequestBody GestionPsicologicaKibana kibanaDTO, @RequestHeader(name = "Authorization") String authToken);

    

        
        

}
