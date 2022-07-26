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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "registroGestionPsicologica")
@NamedQueries({
    @NamedQuery(name = "RegistroGestionPsicologica.findAll", query = "SELECT r FROM RegistroGestionPsicologica r")})
public class RegistroGestionPsicologica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGestion")
    private Integer idGestion;
    @Basic(optional = false)
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @Column(name = "numDocumento")
    private String numDocumento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "recibioApoyoPsicologico")
    private boolean recibioApoyoPsicologico;
    @Basic(optional = false)
    @Column(name = "envioKibana")
    private boolean envioKibana;
    @Basic(optional = false)
    @Column(name = "dataKibana")
    private String dataKibana;
    @Column(name = "cantidadGestiones")
    private Integer cantidadGestiones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistroGestion")
    @JsonIgnore
    private List<GestionPsicologo> gestionPsicologoList;
    
    @JoinColumn(name = "idEstadoGestion", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    @JsonIgnore
    private EstadoGestion idEstadoGestion;
    
    @Transient
    private String estado;

    public RegistroGestionPsicologica() {
    }

    public RegistroGestionPsicologica(Integer idGestion) {
        this.idGestion = idGestion;
    }

    public RegistroGestionPsicologica(Integer idGestion, Date fechaRegistro, String numDocumento, String nombre, String celular, String correo, boolean recibioApoyoPsicologico, boolean envioKibana, String dataKibana) {
        this.idGestion = idGestion;
        this.fechaRegistro = fechaRegistro;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
        this.recibioApoyoPsicologico = recibioApoyoPsicologico;
        this.envioKibana = envioKibana;
        this.dataKibana = dataKibana;
       
    }

    public Integer getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Integer idGestion) {
        this.idGestion = idGestion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getRecibioApoyoPsicologico() {
        return recibioApoyoPsicologico;
    }

    public void setRecibioApoyoPsicologico(boolean recibioApoyoPsicologico) {
        this.recibioApoyoPsicologico = recibioApoyoPsicologico;
    }

    public boolean getEnvioKibana() {
        return envioKibana;
    }

    public void setEnvioKibana(boolean envioKibana) {
        this.envioKibana = envioKibana;
    }

    public String getDataKibana() {
        return dataKibana;
    }

    public void setDataKibana(String dataKibana) {
        this.dataKibana = dataKibana;
    }

    public Integer getCantidadGestiones() {
        return cantidadGestiones;
    }

    public void setCantidadGestiones(Integer cantidadGestiones) {
        this.cantidadGestiones = cantidadGestiones;
    }

  

    public List<GestionPsicologo> getGestionPsicologoList() {
        return gestionPsicologoList;
    }

    public void setGestionPsicologoList(List<GestionPsicologo> gestionPsicologoList) {
        this.gestionPsicologoList = gestionPsicologoList;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoGestion getIdEstadoGestion() {
        return idEstadoGestion;
    }

    public void setIdEstadoGestion(EstadoGestion idEstadoGestion) {
        this.idEstadoGestion = idEstadoGestion;
    }

    
    
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGestion != null ? idGestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroGestionPsicologica)) {
            return false;
        }
        RegistroGestionPsicologica other = (RegistroGestionPsicologica) object;
        if ((this.idGestion == null && other.idGestion != null) || (this.idGestion != null && !this.idGestion.equals(other.idGestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.morellana.registro.model.entity.newpackage.RegistroGestionPsicologica[ idGestion=" + idGestion + " ]";
    }
    
}
