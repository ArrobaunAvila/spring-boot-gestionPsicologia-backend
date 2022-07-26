/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


import com.colsubsidio.gestionpsicologicabackend.config.FooClientConfig;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.UpdateAppointmentRequest;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.UpdateAppointmentResponse;
import com.colsubsidio.gestionpsicologicabackend.dto.updatekibana.search.Principal;


import feign.Headers;

/**
 *
 * @author Anderson
 */
@FeignClient(name = "elasticoperation", url = "${elastic.endpoint}", configuration = FooClientConfig.class)
@Headers("Accept: application/json")
public interface KibbanaClientRest {

    @GetMapping(value = "${elastic.method.search}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Principal getDatos(@RequestHeader("Authorization") String header, @RequestBody String request);

    @PostMapping(value = "${elastic.method.update}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UpdateAppointmentResponse updateAppointment(@RequestHeader("Authorization") String header,
            @RequestBody UpdateAppointmentRequest request);


   
        

}
