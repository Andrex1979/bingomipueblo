/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adsit
 */
@Entity
@Table(name = "estadopremio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadopremio.findAll", query = "SELECT e FROM Estadopremio e"),
    @NamedQuery(name = "Estadopremio.findByIdEstadopremio", query = "SELECT e FROM Estadopremio e WHERE e.idEstadopremio = :idEstadopremio"),
    @NamedQuery(name = "Estadopremio.findByNombreEstado", query = "SELECT e FROM Estadopremio e WHERE e.nombreEstado = :nombreEstado")})
public class Estadopremio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estadopremio")
    private Integer idEstadopremio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadopremio")
    private List<Premio> premioList;

    public Estadopremio() {
    }

    public Estadopremio(Integer idEstadopremio) {
        this.idEstadopremio = idEstadopremio;
    }

    public Estadopremio(Integer idEstadopremio, String nombreEstado) {
        this.idEstadopremio = idEstadopremio;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstadopremio() {
        return idEstadopremio;
    }

    public void setIdEstadopremio(Integer idEstadopremio) {
        this.idEstadopremio = idEstadopremio;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @XmlTransient
    public List<Premio> getPremioList() {
        return premioList;
    }

    public void setPremioList(List<Premio> premioList) {
        this.premioList = premioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadopremio != null ? idEstadopremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadopremio)) {
            return false;
        }
        Estadopremio other = (Estadopremio) object;
        if ((this.idEstadopremio == null && other.idEstadopremio != null) || (this.idEstadopremio != null && !this.idEstadopremio.equals(other.idEstadopremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.Estadopremio[ idEstadopremio=" + idEstadopremio + " ]";
    }
    
}
