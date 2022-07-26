/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.dto;

import com.colsubsidio.gestionpsicologicabackend.dto.mapping.GestionPsicologoDTO;
import com.colsubsidio.gestionpsicologicabackend.dto.mapping.RegistroPsicologiaDTO;
import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class GestionPsicologicaKibana {
    
    private static final long serialVersionUID = 7238438690524830007L;
	
	private String index;
	private String type;
	private String side;
	private String transaction;
	private Date startdate;
	private String messageText;
	private Boolean ok ;
	private String error;
	private Long milliseconds;
	private List<Service> services;
	private Integer messages;
        private GestionPsicologoDTO gestionPsicologo;
        private RegistroPsicologiaDTO registroGestion;
        
        private String userLogin;
        private String fechaHoraOperacion;
        
        
        @Data
	public static class Service implements Serializable {
		private static final long serialVersionUID = -1603769902670845023L;
		private String name;
		private String endPoint;
		private Date dateInitProcess;
		private Long time;
		private Boolean ok;
		private String request;
		private String response;
	}
    
    
}
