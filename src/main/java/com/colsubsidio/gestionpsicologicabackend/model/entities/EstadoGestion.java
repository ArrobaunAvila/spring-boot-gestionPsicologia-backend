/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "EstadoGestion")
@NamedQueries({
    @NamedQuery(name = "EstadoGestion.findAll", query = "SELECT e FROM EstadoGestion e")})
public class EstadoGestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstado")
    private Short idEstado;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoGestion")
    @JsonIgnore
    private List<RegistroGestionPsicologica> registroGestionPsicologicaList;
    

    public EstadoGestion() {
    }

    public EstadoGestion(Short idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoGestion(Short idEstado, String nombre, Date fechaRegistro) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    public Short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Short idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<RegistroGestionPsicologica> getRegistroGestionPsicologicaList() {
        return registroGestionPsicologicaList;
    }

    public void setRegistroGestionPsicologicaList(List<RegistroGestionPsicologica> registroGestionPsicologicaList) {
        this.registroGestionPsicologicaList = registroGestionPsicologicaList;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoGestion)) {
            return false;
        }
        EstadoGestion other = (EstadoGestion) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.morellana.registro.model.entity.newpackage.EstadoGestion[ idEstado=" + idEstado + " ]";
    }
    
}
