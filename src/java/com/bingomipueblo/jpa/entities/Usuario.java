/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.entities;

import com.bingomipueblo.jsf.controllers.util.DigestUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adsit
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNumeroDocumento", query = "SELECT u FROM Usuario u WHERE u.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Usuario.findByPrimerNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.primerNombreUsuario = :primerNombreUsuario"),
    @NamedQuery(name = "Usuario.findBySegundoNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.segundoNombreUsuario = :segundoNombreUsuario"),
    @NamedQuery(name = "Usuario.findByPrimerApellidoUsuario", query = "SELECT u FROM Usuario u WHERE u.primerApellidoUsuario = :primerApellidoUsuario"),
    @NamedQuery(name = "Usuario.findBySegundoApellidoUsuario", query = "SELECT u FROM Usuario u WHERE u.segundoApellidoUsuario = :segundoApellidoUsuario"),
    @NamedQuery(name = "Usuario.findByTelefono1Usuario", query = "SELECT u FROM Usuario u WHERE u.telefono1Usuario = :telefono1Usuario"),
    @NamedQuery(name = "Usuario.findByTelefono2Usuario", query = "SELECT u FROM Usuario u WHERE u.telefono2Usuario = :telefono2Usuario"),
    @NamedQuery(name = "Usuario.findByEmailUsuario", query = "SELECT u FROM Usuario u WHERE u.emailUsuario = :emailUsuario"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByUserName", query = "SELECT u FROM Usuario u WHERE u.userName = :userName"),
    @NamedQuery(name = "Usuario.findByFechaNacimientoUsuario", query = "SELECT u FROM Usuario u WHERE u.fechaNacimientoUsuario = :fechaNacimientoUsuario"),
    @NamedQuery(name = "Usuario.findByDireccionUsuario", query = "SELECT u FROM Usuario u WHERE u.direccionUsuario = :direccionUsuario"),
    @NamedQuery(name = "Usuario.findByFechacreacionusuario", query = "SELECT u FROM Usuario u WHERE u.fechacreacionusuario = :fechacreacionusuario"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "primer_nombre_usuario")
    private String primerNombreUsuario;
    @Size(max = 45)
    @Column(name = "segundo_nombre_usuario")
    private String segundoNombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "primer_apellido_usuario")
    private String primerApellidoUsuario;
    @Size(max = 45)
    @Column(name = "segundo_apellido_usuario")
    private String segundoApellidoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono1_usuario")
    private String telefono1Usuario;
    @Size(max = 50)
    @Column(name = "telefono2_usuario")
    private String telefono2Usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email_usuario")
    private String emailUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento_usuario")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "direccion_usuario")
    private String direccionUsuario;
    @Column(name = "Fecha_creacion_usuario")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacreacionusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinTable(name = "usuario_has_rol", joinColumns = {
        @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "rol_id_rol", referencedColumnName = "id_rol")})
      @ManyToMany
    private List<Rol> rolList;
    
    @ManyToMany(mappedBy = "usuarioList")
    private List<Sorteo> sorteoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioGanador")
    private List<Premio> premioList;
    @JoinColumns({
        @JoinColumn(name = "ciudad_id_ciudad", referencedColumnName = "id_ciudad"),
        @JoinColumn(name = "departamento_id_departamento", referencedColumnName = "departamento_id_departamento")})
    @ManyToOne(optional = false)
    private Ciudad ciudad;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDocumento;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne(optional = false)
    private Sexo idSexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Movimientos> movimientosList;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String numeroDocumento, String primerNombreUsuario, String primerApellidoUsuario, String telefono1Usuario, String emailUsuario, String password, String userName, Date fechaNacimientoUsuario, String direccionUsuario, boolean estado) {
        this.idUsuario = idUsuario;
        this.numeroDocumento = numeroDocumento;
        this.primerNombreUsuario = primerNombreUsuario;
        this.primerApellidoUsuario = primerApellidoUsuario;
        this.telefono1Usuario = telefono1Usuario;
        this.emailUsuario = emailUsuario;
        this.password = password;
        this.userName = userName;
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.estado = estado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombreUsuario() {
        return primerNombreUsuario;
    }

    public void setPrimerNombreUsuario(String primerNombreUsuario) {
        this.primerNombreUsuario = primerNombreUsuario;
    }

    public String getSegundoNombreUsuario() {
        return segundoNombreUsuario;
    }

    public void setSegundoNombreUsuario(String segundoNombreUsuario) {
        this.segundoNombreUsuario = segundoNombreUsuario;
    }

    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }

    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }

    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }

    public String getTelefono1Usuario() {
        return telefono1Usuario;
    }

    public void setTelefono1Usuario(String telefono1Usuario) {
        this.telefono1Usuario = telefono1Usuario;
    }

    public String getTelefono2Usuario() {
        return telefono2Usuario;
    }

    public void setTelefono2Usuario(String telefono2Usuario) {
        this.telefono2Usuario = telefono2Usuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password = DigestUtil.generateDigest(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFechaNacimientoUsuario() {
        return fechaNacimientoUsuario;
    }

    public void setFechaNacimientoUsuario(Date fechaNacimientoUsuario) {
        this.fechaNacimientoUsuario = fechaNacimientoUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public Date getFechacreacionusuario() {
        return fechacreacionusuario;
    }

    public void setFechacreacionusuario(Date fechacreacionusuario) {
        this.fechacreacionusuario = fechacreacionusuario;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @XmlTransient
    public List<Sorteo> getSorteoList() {
        return sorteoList;
    }

    public void setSorteoList(List<Sorteo> sorteoList) {
        this.sorteoList = sorteoList;
    }

    @XmlTransient
    public List<Premio> getPremioList() {
        return premioList;
    }

    public void setPremioList(List<Premio> premioList) {
        this.premioList = premioList;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
    }

    @XmlTransient
    public List<Movimientos> getMovimientosList() {
        return movimientosList;
    }

    public void setMovimientosList(List<Movimientos> movimientosList) {
        this.movimientosList = movimientosList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return userName ;
    }
    
}
