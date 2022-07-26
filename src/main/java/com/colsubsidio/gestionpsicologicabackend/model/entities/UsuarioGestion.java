/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "usuarioGestion")
@NamedQueries({
    @NamedQuery(name = "UsuarioGestion.findAll", query = "SELECT u FROM UsuarioGestion u")})
public class UsuarioGestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "usuarioLogin")
    private String usuarioLogin;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioGestiona")
    private List<GestionPsicologo> gestionPsicologoList;

    public UsuarioGestion() {
    }

    public UsuarioGestion(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioGestion(Integer idUsuario, String usuarioLogin, String password, String nombreUsuario, Date fechaIngreso) {
        this.idUsuario = idUsuario;
        this.usuarioLogin = usuarioLogin;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
        this.fechaIngreso = fechaIngreso;
      
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

   

    public List<GestionPsicologo> getGestionPsicologoList() {
        return gestionPsicologoList;
    }

    public void setGestionPsicologoList(List<GestionPsicologo> gestionPsicologoList) {
        this.gestionPsicologoList = gestionPsicologoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioGestion)) {
            return false;
        }
        UsuarioGestion other = (UsuarioGestion) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.morellana.registro.model.entity.newpackage.UsuarioGestion[ idUsuario=" + idUsuario + " ]";
    }
    
}
