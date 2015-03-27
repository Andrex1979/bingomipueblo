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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresM
 */
@Entity
@Table(name = "balota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Balota.findAll", query = "SELECT b FROM Balota b"),
    @NamedQuery(name = "Balota.findByIdBalota", query = "SELECT b FROM Balota b WHERE b.idBalota = :idBalota"),
    @NamedQuery(name = "Balota.findByNumeroBalota", query = "SELECT b FROM Balota b WHERE b.numeroBalota = :numeroBalota")})
public class Balota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_balota")
    private Integer idBalota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_balota")
    private int numeroBalota;
    @JoinTable(name = "tabla_has_balota", joinColumns = {
        @JoinColumn(name = "balota_id_balota", referencedColumnName = "id_balota")}, inverseJoinColumns = {
        @JoinColumn(name = "tabla_id_tabla", referencedColumnName = "id_tabla")})
    @ManyToMany
    private List<Tabla> tablaList;

    public Balota() {
    }

    public Balota(Integer idBalota) {
        this.idBalota = idBalota;
    }

    public Balota(Integer idBalota, int numeroBalota) {
        this.idBalota = idBalota;
        this.numeroBalota = numeroBalota;
    }

    public Integer getIdBalota() {
        return idBalota;
    }

    public void setIdBalota(Integer idBalota) {
        this.idBalota = idBalota;
    }

    public int getNumeroBalota() {
        return numeroBalota;
    }

    public void setNumeroBalota(int numeroBalota) {
        this.numeroBalota = numeroBalota;
    }

    @XmlTransient
    public List<Tabla> getTablaList() {
        return tablaList;
    }

    public void setTablaList(List<Tabla> tablaList) {
        this.tablaList = tablaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBalota != null ? idBalota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balota)) {
            return false;
        }
        Balota other = (Balota) object;
        if ((this.idBalota == null && other.idBalota != null) || (this.idBalota != null && !this.idBalota.equals(other.idBalota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.Balota[ idBalota=" + idBalota + " ]";
    }
    
}
