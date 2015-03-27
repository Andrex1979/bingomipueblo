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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_premio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPremio.findAll", query = "SELECT t FROM TipoPremio t"),
    @NamedQuery(name = "TipoPremio.findByIdTipoPremio", query = "SELECT t FROM TipoPremio t WHERE t.idTipoPremio = :idTipoPremio"),
    @NamedQuery(name = "TipoPremio.findByNombreTipoPremio", query = "SELECT t FROM TipoPremio t WHERE t.nombreTipoPremio = :nombreTipoPremio")})
public class TipoPremio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_premio")
    private Short idTipoPremio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nombre_tipo_premio")
    private String nombreTipoPremio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPremio")
    private List<Premio> premioList;

    public TipoPremio() {
    }

    public TipoPremio(Short idTipoPremio) {
        this.idTipoPremio = idTipoPremio;
    }

    public TipoPremio(Short idTipoPremio, String nombreTipoPremio) {
        this.idTipoPremio = idTipoPremio;
        this.nombreTipoPremio = nombreTipoPremio;
    }

    public Short getIdTipoPremio() {
        return idTipoPremio;
    }

    public void setIdTipoPremio(Short idTipoPremio) {
        this.idTipoPremio = idTipoPremio;
    }

    public String getNombreTipoPremio() {
        return nombreTipoPremio;
    }

    public void setNombreTipoPremio(String nombreTipoPremio) {
        this.nombreTipoPremio = nombreTipoPremio;
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
        hash += (idTipoPremio != null ? idTipoPremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPremio)) {
            return false;
        }
        TipoPremio other = (TipoPremio) object;
        if ((this.idTipoPremio == null && other.idTipoPremio != null) || (this.idTipoPremio != null && !this.idTipoPremio.equals(other.idTipoPremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.TipoPremio[ idTipoPremio=" + idTipoPremio ;
    }
    
}
