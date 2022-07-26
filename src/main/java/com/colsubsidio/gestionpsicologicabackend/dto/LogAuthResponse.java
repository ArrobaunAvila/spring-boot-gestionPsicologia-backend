package com.colsubsidio.gestionpsicologicabackend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LogAuthResponse implements Serializable {
	private static final long serialVersionUID = 3156912672542795118L;
	
	private String access_token;
	private String userName;
	private String type;
	private Long issued_at;
	private String expires_in;

}
