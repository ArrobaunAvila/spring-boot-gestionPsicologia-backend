package com.colsubsidio.gestionpsicologicabackend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource("file:config/general.properties")
@Data
public class PropertiesUtil {

    @Value("${kibana.log.index}")
    private String kibanaIndex;

    @Value("${kibana.log.side.buscar.pendientes}")
    private String kibanaSidePendientes;

    @Value("${kibana.log.side.registrar.gestion}")
    private String kibanaSideRegistrar;

    @Value("${kibana.log.side.actualizar.gestion}")
    private String kibaSideActualizar;

    @Value("${kibana.log.side.buscar.criterios}")
    private String kibaSideBuscarPorCriterios;

    @Value("${kibana.log.side.crear.usuario}")
    private String kibaSideCrearUsuario;

    @Value("${kibana.log.side.login}")
    private String kibaSideLoguin;
    
    @Value("${kibana.log.side.cron}")
    private String kibaSideCron;

    @Value("${kibana.log.type}")
    private String kibanaType;

    @Value("${retorna.registros.query}")
    private String retornaDatosEnGestionQuery;

    @Value("${rest.client.conn.max.idle.time}")
    private long maxIdleTime;

    @Value("${token.logger}")
    private String tokenLogger;

    @Value("${retorna.por.criterios}")
    private String queriePorCriterios;

    @Value("${pattern.date}")
    private String validateFormat;

    @Value("${mansaje.validacion.fecha}")
    private String validateDateMsj;

    @Value("${pattern.validar.pass}")
    private String validatePass;

    @Value("${elastic.search.gestion}")
    private String elasticSearchQuery;

    @Value("${elastic.endpoint}")
    private String endpointElasticService;

    @Value("${elastic.user}")
    private String kibanaUser;

    @Value("${elastic.password}")
    private String kibanaPassword;
    
    @Value("${id.estado.sesion}")
    private Short idEstadoSesion;
    
    @Value("${formato.de.fecha.busqueda}")
    private String formatoFechaBusqueda;
}
