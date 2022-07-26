/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 *
 * @author HP
 */
@EnableCaching
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class GestionPsicologicaMain {
    
    
    
    
    @PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionPsicologicaMain.class, args);
	}
    
}
