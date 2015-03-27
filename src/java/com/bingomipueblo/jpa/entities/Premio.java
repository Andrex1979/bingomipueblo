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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adsit
 */
@Entity
@Table(name = "premio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Premio.findAll", query = "SELECT p FROM Premio p"),
    @NamedQuery(name = "Premio.findByIdPremio", query = "SELECT p FROM Premio p WHERE p.idPremio = :idPremio"),
    @NamedQuery(name = "Premio.findByNombrePremio", query = "SELECT p FROM Premio p WHERE p.nombrePremio = :nombrePremio"),
    @NamedQuery(name = "Premio.findByValorPremio", query = "SELECT p FROM Premio p WHERE p.valorPremio = :valorPremio"),
    @NamedQuery(name = "Premio.findByFechaPremioGanado", query = "SELECT p FROM Premio p WHERE p.fechaPremioGanado = :fechaPremioGanado"),
    @NamedQuery(name = "Premio.findByFechaEntregaPremio", query = "SELECT p FROM Premio p WHERE p.fechaEntregaPremio = :fechaEntregaPremio")})
public class Premio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_premio")
    private Integer idPremio;
    @Size(max = 150)
    @Column(name = "nombre_premio")
    private String nombrePremio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_premio")
    private Double valorPremio;
    @Column(name = "fecha_premio_ganado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPremioGanado;
    @Column(name = "fecha_entrega_premio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaPremio;
    @JoinTable(name = "sorteo_has_premio", joinColumns = {
        @JoinColumn(name = "premio_id_premio", referencedColumnName = "id_premio")}, inverseJoinColumns = {
        @JoinColumn(name = "sorteo_id_sorteo", referencedColumnName = "id_sorteo")})
    @ManyToMany
    private List<Sorteo> sorteoList;
    @JoinColumn(name = "id_usuario_ganador", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioGanador;
    @JoinColumn(name = "id_estadopremio", referencedColumnName = "id_estadopremio")
    @ManyToOne(optional = false)
    private Estadopremio idEstadopremio;
    @JoinColumn(name = "id_tipo_premio", referencedColumnName = "id_tipo_premio")
    @ManyToOne(optional = false)
    private TipoPremio idTipoPremio;

    public Premio() {
    }

    public Premio(Integer idPremio) {
        this.idPremio = idPremio;
    }

    public Integer getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(Integer idPremio) {
        this.idPremio = idPremio;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nombrePremio) {
        this.nombrePremio = nombrePremio;
    }

    public Double getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(Double valorPremio) {
        this.valorPremio = valorPremio;
    }

    public Date getFechaPremioGanado() {
        return fechaPremioGanado;
    }

    public void setFechaPremioGanado(Date fechaPremioGanado) {
        this.fechaPremioGanado = fechaPremioGanado;
    }

    public Date getFechaEntregaPremio() {
        return fechaEntregaPremio;
    }

    public void setFechaEntregaPremio(Date fechaEntregaPremio) {
        this.fechaEntregaPremio = fechaEntregaPremio;
    }

    @XmlTransient
    public List<Sorteo> getSorteoList() {
        return sorteoList;
    }

    public void setSorteoList(List<Sorteo> sorteoList) {
        this.sorteoList = sorteoList;
    }

    public Usuario getIdUsuarioGanador() {
        return idUsuarioGanador;
    }

    public void setIdUsuarioGanador(Usuario idUsuarioGanador) {
        this.idUsuarioGanador = idUsuarioGanador;
    }

    public Estadopremio getIdEstadopremio() {
        return idEstadopremio;
    }

    public void setIdEstadopremio(Estadopremio idEstadopremio) {
        this.idEstadopremio = idEstadopremio;
    }

    public TipoPremio getIdTipoPremio() {
        return idTipoPremio;
    }

    public void setIdTipoPremio(TipoPremio idTipoPremio) {
        this.idTipoPremio = idTipoPremio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPremio != null ? idPremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Premio)) {
            return false;
        }
        Premio other = (Premio) object;
        if ((this.idPremio == null && other.idPremio != null) || (this.idPremio != null && !this.idPremio.equals(other.idPremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wizzard.jpa.entities.Premio[ idPremio=" + idPremio ;
    }
    
}
