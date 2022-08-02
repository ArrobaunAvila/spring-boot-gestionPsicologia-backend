/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "gestionPsicologo")
@NamedQueries({
    @NamedQuery(name = "GestionPsicologo.findAll", query = "SELECT g FROM GestionPsicologo g")})
public class GestionPsicologo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGestionPsicologo")
    private Integer idGestionPsicologo;
    @Basic(optional = false)
    @Column(name = "fechaGestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGestion;
    @Basic(optional = false)
    @Column(name = "comentarioGestion")
    private String comentarioGestion;
    @JoinColumn(name = "idRegistroGestion", referencedColumnName = "idGestion")
    @ManyToOne(optional = false)
    private RegistroGestionPsicologica idRegistroGestion;
    @Basic(optional = false)
    @Column(name = "envioKibana")
    private boolean envioKibana;
    @Basic(optional = false)
    @Column(name = "numeroGestion")
    private Integer numeroGestion;
    @JoinColumn(name = "idUsuarioGestiona", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private UsuarioGestion idUsuarioGestiona;
    @Column(name = "idEstadoGestion")
    private Integer idEstadoGestion;

    public GestionPsicologo() {
    }

    public GestionPsicologo(Integer idGestionPsicologo) {
        this.idGestionPsicologo = idGestionPsicologo;
    }

    public GestionPsicologo(Integer idGestionPsicologo, Date fechaGestion, String comentarioGestion) {
        this.idGestionPsicologo = idGestionPsicologo;
        this.fechaGestion = fechaGestion;
        this.comentarioGestion = comentarioGestion;
        
    }

    public Integer getIdGestionPsicologo() {
        return idGestionPsicologo;
    }

    public void setIdGestionPsicologo(Integer idGestionPsicologo) {
        this.idGestionPsicologo = idGestionPsicologo;
    }

    public Date getFechaGestion() {
        return fechaGestion;
    }

    public void setFechaGestion(Date fechaGestion) {
        this.fechaGestion = fechaGestion;
    }

    public String getComentarioGestion() {
        return comentarioGestion;
    }

    public void setComentarioGestion(String comentarioGestion) {
        this.comentarioGestion = comentarioGestion;
    }

    public Integer getIdEstadoGestion() {
        return idEstadoGestion;
    }

    public void setIdEstadoGestion(Integer idEstadoGestion) {
        this.idEstadoGestion = idEstadoGestion;
    }

   

    public RegistroGestionPsicologica getIdRegistroGestion() {
        return idRegistroGestion;
    }

    public void setIdRegistroGestion(RegistroGestionPsicologica idRegistroGestion) {
        this.idRegistroGestion = idRegistroGestion;
    }

    public UsuarioGestion getIdUsuarioGestiona() {
        return idUsuarioGestiona;
    }

    public void setIdUsuarioGestiona(UsuarioGestion idUsuarioGestiona) {
        this.idUsuarioGestiona = idUsuarioGestiona;
    }

    public boolean isEnvioKibana() {
        return envioKibana;
    }

    public void setEnvioKibana(boolean envioKibana) {
        this.envioKibana = envioKibana;
    }

    public Integer getNumeroGestion() {
        return numeroGestion;
    }

    public void setNumeroGestion(Integer numeroGestion) {
        this.numeroGestion = numeroGestion;
    }

    

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGestionPsicologo != null ? idGestionPsicologo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GestionPsicologo)) {
            return false;
        }
        GestionPsicologo other = (GestionPsicologo) object;
        if ((this.idGestionPsicologo == null && other.idGestionPsicologo != null) || (this.idGestionPsicologo != null && !this.idGestionPsicologo.equals(other.idGestionPsicologo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.morellana.registro.model.entity.newpackage.GestionPsicologo[ idGestionPsicologo=" + idGestionPsicologo + " ]";
    }
    
}
