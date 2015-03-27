/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adsit
 */
@Entity
@Table(name = "movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m"),
    @NamedQuery(name = "Movimientos.findByIdMovimiento", query = "SELECT m FROM Movimientos m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "Movimientos.findByFechaHoraMovimiento", query = "SELECT m FROM Movimientos m WHERE m.fechaHoraMovimiento = :fechaHoraMovimiento"),
    @NamedQuery(name = "Movimientos.findByValorMovimiento", query = "SELECT m FROM Movimientos m WHERE m.valorMovimiento = :valorMovimiento"),
    @NamedQuery(name = "Movimientos.findByDescripcionFactura", query = "SELECT m FROM Movimientos m WHERE m.descripcionFactura = :descripcionFactura")})
public class Movimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_movimiento")
    private double valorMovimiento;
    @Size(max = 200)
    @Column(name = "descripcion_factura")
    private String descripcionFactura;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Movimientos() {
    }

    public Movimientos(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimientos(Integer idMovimiento, Date fechaHoraMovimiento, double valorMovimiento) {
        this.idMovimiento = idMovimiento;
        this.fechaHoraMovimiento = fechaHoraMovimiento;
        this.valorMovimiento = valorMovimiento;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFechaHoraMovimiento() {
        return fechaHoraMovimiento;
    }

    public void setFechaHoraMovimiento(Date fechaHoraMovimiento) {
        this.fechaHoraMovimiento = fechaHoraMovimiento;
    }

    public double getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(double valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public String getDescripcionFactura() {
        return descripcionFactura;
    }

    public void setDescripcionFactura(String descripcionFactura) {
        this.descripcionFactura = descripcionFactura;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bingomipueblo.jpa.entities.Movimientos[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
