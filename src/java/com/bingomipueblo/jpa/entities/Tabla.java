/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresM
 */
@Entity
@Table(name = "tabla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabla.findAll", query = "SELECT t FROM Tabla t"),
    @NamedQuery(name = "Tabla.findByIdTabla", query = "SELECT t FROM Tabla t WHERE t.idTabla = :idTabla"),
    @NamedQuery(name = "Tabla.findByCodigoTabla", query = "SELECT t FROM Tabla t WHERE t.codigoTabla = :codigoTabla")})
public class Tabla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tabla")
    private Integer idTabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "codigo_tabla")
    private String codigoTabla;
    @ManyToMany(mappedBy = "tablaList")
    private List<Sorteo> sorteoList;
    @ManyToMany(mappedBy = "tablaList")
    private List<Balota> balotaList;

    public Tabla() {
    }

    public Tabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public Tabla(Integer idTabla, String codigoTabla) {
        this.idTabla = idTabla;
        this.codigoTabla = codigoTabla;
    }

    public Integer getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public String getCodigoTabla() {
        return codigoTabla;
    }

    public void setCodigoTabla(String codigoTabla) {
        this.codigoTabla = codigoTabla;
    }

    @XmlTransient
    public List<Sorteo> getSorteoList() {
        return sorteoList;
    }

    public void setSorteoList(List<Sorteo> sorteoList) {
        this.sorteoList = sorteoList;
    }

    @XmlTransient
    public List<Balota> getBalotaList() {
        return balotaList;
    }

    public void setBalotaList(List<Balota> balotaList) {
        this.balotaList = balotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTabla != null ? idTabla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabla)) {
            return false;
        }
        Tabla other = (Tabla) object;
        if ((this.idTabla == null && other.idTabla != null) || (this.idTabla != null && !this.idTabla.equals(other.idTabla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.Tabla[ idTabla=" + idTabla + " ]";
    }
    
}
