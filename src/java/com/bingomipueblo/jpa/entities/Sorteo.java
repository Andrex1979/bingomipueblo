/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresM
 */
@Entity
@Table(name = "sorteo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sorteo.findAll", query = "SELECT s FROM Sorteo s"),
    @NamedQuery(name = "Sorteo.findByIdSorteo", query = "SELECT s FROM Sorteo s WHERE s.idSorteo = :idSorteo"),
    @NamedQuery(name = "Sorteo.findByFechaInicioSorteo", query = "SELECT s FROM Sorteo s WHERE s.fechaInicioSorteo = :fechaInicioSorteo"),
    @NamedQuery(name = "Sorteo.findByFechaFinSorteo", query = "SELECT s FROM Sorteo s WHERE s.fechaFinSorteo = :fechaFinSorteo")})
public class Sorteo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sorteo")
    private Integer idSorteo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_sorteo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioSorteo;
    @Column(name = "fecha_fin_sorteo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinSorteo;
    @ManyToMany(mappedBy = "sorteoList")
    private List<Premio> premioList;
    @JoinTable(name = "usuario_has_sorteo", joinColumns = {
        @JoinColumn(name = "sorteo_id_sorteo", referencedColumnName = "id_sorteo")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinTable(name = "sorteo_has_tabla", joinColumns = {
        @JoinColumn(name = "sorteo_id_sorteo", referencedColumnName = "id_sorteo")}, inverseJoinColumns = {
        @JoinColumn(name = "tabla_id_tabla", referencedColumnName = "id_tabla")})
    @ManyToMany
    private List<Tabla> tablaList;
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @ManyToOne(optional = false)
    private Sala idSala;

    public Sorteo() {
    }

    public Sorteo(Integer idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Sorteo(Integer idSorteo, Date fechaInicioSorteo) {
        this.idSorteo = idSorteo;
        this.fechaInicioSorteo = fechaInicioSorteo;
    }

    public Integer getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Integer idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Date getFechaInicioSorteo() {
        return fechaInicioSorteo;
    }

    public void setFechaInicioSorteo(Date fechaInicioSorteo) {
        this.fechaInicioSorteo = fechaInicioSorteo;
    }

    public Date getFechaFinSorteo() {
        return fechaFinSorteo;
    }

    public void setFechaFinSorteo(Date fechaFinSorteo) {
        this.fechaFinSorteo = fechaFinSorteo;
    }

    @XmlTransient
    public List<Premio> getPremioList() {
        return premioList;
    }

    public void setPremioList(List<Premio> premioList) {
        this.premioList = premioList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Tabla> getTablaList() {
        return tablaList;
    }

    public void setTablaList(List<Tabla> tablaList) {
        this.tablaList = tablaList;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSorteo != null ? idSorteo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sorteo)) {
            return false;
        }
        Sorteo other = (Sorteo) object;
        if ((this.idSorteo == null && other.idSorteo != null) || (this.idSorteo != null && !this.idSorteo.equals(other.idSorteo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.Sorteo[ idSorteo=" + idSorteo + " ]";
    }
    
}
