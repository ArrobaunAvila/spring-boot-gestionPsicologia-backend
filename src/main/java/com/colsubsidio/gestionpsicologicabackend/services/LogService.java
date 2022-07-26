package com.colsubsidio.gestionpsicologicabackend.services;





import com.colsubsidio.gestionpsicologicabackend.client.LogClientRest;
import com.colsubsidio.gestionpsicologicabackend.dto.GestionPsicologicaKibana;
import com.colsubsidio.gestionpsicologicabackend.utils.DateUtil;
import com.colsubsidio.gestionpsicologicabackend.utils.PropertiesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;


@Slf4j
@Service
public class LogService {

   

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private LogClientRest logClient;

    @Autowired
    private PropertiesUtil propertiesUtil;

    public String getAuthToken() {

        return "Bearer" + " " +this.propertiesUtil.getTokenLogger();
    }

    @Async
    public void sendLogToKibana(GestionPsicologicaKibana appoinmentKibana, String transaction,String side) {
		try{
                appoinmentKibana.setIndex(propertiesUtil.getKibanaIndex());
		appoinmentKibana.setType(propertiesUtil.getKibanaType());
		appoinmentKibana.setSide(side);
		appoinmentKibana.setTransaction(transaction);
		appoinmentKibana.setMilliseconds(dateUtil.getMillisecondsFromStartDate(appoinmentKibana.getStartdate()));
		logClient.sendLogtoKibana(appoinmentKibana, getAuthToken());
                }catch(Exception e){
                    e.printStackTrace();
                }
               }
	
	public GestionPsicologicaKibana.Service initLogService(String name, String endPoint, Object request, boolean converResponseToJson) {
		GestionPsicologicaKibana.Service service = new GestionPsicologicaKibana.Service();
		service.setName(name);
		service.setEndPoint(endPoint);
		if(request != null) {
			if(converResponseToJson) {
				try {
					service.setRequest(new ObjectMapper().writeValueAsString(request));
				} catch (Exception e) {}
			} else {
				service.setRequest((String) request);
			}
		}
		service.setDateInitProcess(new Date());
		return service;
	}
	
	public void completeResponseService(GestionPsicologicaKibana.Service service, boolean ok, Object response, boolean converResponseToJson) {
		service.setOk(ok);
		service.setTime(dateUtil.getMillisecondsFromStartDate(service.getDateInitProcess()));
		if(response != null) {
			if(converResponseToJson) {
				try {
					service.setResponse(new ObjectMapper().writeValueAsString(response));
				} catch (JsonProcessingException e) {}
			} else {
				service.setResponse((String)response);
			}
		}
	}
        
         
        
        
        	
}
