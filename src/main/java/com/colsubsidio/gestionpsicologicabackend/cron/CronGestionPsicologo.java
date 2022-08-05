/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.cron;

import com.colsubsidio.gestionpsicologicabackend.services.TareasCronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author HP
 */
@Component
@Slf4j
public class CronGestionPsicologo {
    
    
    @Autowired
    private TareasCronService cronservice;
    
    @Scheduled(cron = "${cron.expresion.send.gestion.psicologo}")
    public void sendKibanaGestionesPsicologo(){
        
        
        cronservice.process();
        
        
    }
    
}
