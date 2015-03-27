/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingomipueblo.jsf.controller;

import com.bingomipueblo.jpa.entities.Ciudad;
import com.bingomipueblo.jpa.entities.Premio;
import com.bingomipueblo.jpa.entities.Rol;
import com.bingomipueblo.jpa.entities.Sexo;
import com.bingomipueblo.jpa.entities.TipoDocumento;
import com.bingomipueblo.jpa.entities.Usuario;
import com.bingomipueblo.jpa.session.CiudadFacade;
import com.bingomipueblo.jpa.session.PremioFacade;
import com.bingomipueblo.jpa.session.RolFacade;
import com.bingomipueblo.jpa.session.SexoFacade;
import com.bingomipueblo.jpa.session.TipoDocumentoFacade;
import com.bingomipueblo.jpa.session.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Adsit
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuarioActual;
    private List<Usuario> listaUsuarios = null;
    private List<Rol> listaRoles = null;
    private List<Premio> listaPremios = null;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TipoDocumentoFacade tipoDocumento;
    @EJB
    private SexoFacade sexo;
    @EJB
    private CiudadFacade ciudad;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private PremioFacade premioFacade;
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public Usuario getUsuarioActual() {
        if (usuarioActual == null) {
            usuarioActual = new Usuario();
        }
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public TipoDocumentoFacade getTipoDocumentoFacade() {
        return tipoDocumento;
    }

    public SexoFacade getSexoFacade() {
        return sexo;
    }

    public CiudadFacade getCiudadFacade() {
        return ciudad;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }
    
    public List<Sexo> getListaGenerosSelectOne() {
        return getSexoFacade().findAll();
    }

    public PremioFacade getPremioFacade() {
        return premioFacade;
    }

    

    
    
    public List<TipoDocumento> getListaTipoDocumentoSelectOne() {
        return getTipoDocumentoFacade().findAll();

    }

    
     public List<Rol> getListaRolSelectOne() {
        return getRolFacade().findAll();

    }
    
    public List<Ciudad> getListCiudadesAutocomplete(String query) {
        try {
            return getCiudadFacade().finByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    
    
    
    public List<Usuario> getListaUsuario() {

        if (listaUsuarios == null) {
            try {
                listaUsuarios = getUsuarioFacade().findAll();
            } catch (Exception e) {

                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            }
        }
        return listaUsuarios;
    }

    public List<Premio> getListaPremios() {
         if (listaPremios == null) {
            try {
                listaPremios = getPremioFacade().findAll();
            } catch (Exception e) {

                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            }
        }
        return listaPremios;
    }

    public void setListaPremios(List<Premio> listaPremios) {
        this.listaPremios = listaPremios;
    }
    

    
    public String prepareCreate() {
        usuarioActual = new Usuario();
        listaRoles=new ArrayList<>();   //Agregamos la lista de roles
        return "/users/unReg/registro";
    }
    
    public String prepareadmin(){
     usuarioActual = new Usuario();
        listaRoles=new ArrayList<>();   //Agregamos la lista de roles
        return "/admin/adm";   
    }

    private void recargarLista() {
        listaUsuarios = null;
    }
    
    public String admini(){
        return "/admin/Administrador";
    }

    
    public String prepareList() {
        recargarLista();
        return "/admin/List";
    }
    
    public String prepareListPremio(){
    recargarLista();
    return "/admin/listPremio";
    }

    
    public String prepareEdit() {
        return "Edit";
    }

    
    public String prepareView() {
        return "View";
    }
    public String prepareVolver(){
        return "../index";
    }

    public String confiUsuario(){
        return "perfil";
    }
    
    
    public void validarDocumento(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        Usuario usuarioConsulta=getUsuarioFacade().findByNumeroDocumento((String) value);
        
        if(usuarioConsulta != null){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumnetoTitle"),
            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumento")));
        }else{
            String documento= (String) value;
            usuarioActual.setNumeroDocumento(documento);
        }
    }
        
    public String addUsuario() {
        try {
            usuarioActual.setFechacreacionusuario(new Date());
            usuarioActual.setEstado(true);
            if(listaRoles == null || listaRoles.isEmpty()){
                listaRoles.add(new Rol("2","Jugador"));
                usuarioActual.setRolList(listaRoles);
            }else {
                usuarioActual.setRolList(listaRoles);
            }
            
            getUsuarioFacade().create(usuarioActual);
            addSuccesMessage("Usuario Creado Exitosamente", "Crear Usuario");
            recargarLista();
            return "";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            return null;
        }
    }

    public String updateUsuario() {
        try {
            getUsuarioFacade().edit(usuarioActual);
            addSuccesMessage("Actualizar  Usuarios", "Usuario Actualizado Exitosamente");
            return "List";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            return null;
        }
    }

    public String deleteteEmployee() {
        try {
            getUsuarioFacade().remove(usuarioActual);
            addSuccesMessage("Eliminar  Empleado", "Usuario Eliminado Exitosamente");
            recargarLista();

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
        return "List";
    }
    public void validarEdad(FacesContext context, UIComponent component, Object o) throws ValidatorException {
        Date f = (Date) o;
        Date s = new Date();
        if ((s.getYear() - f.getYear()) < 18) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "XXXX", "No cumples con el requisito de edad necesario para registrarte."));
        } else {
            getUsuarioActual().setFechaNacimientoUsuario(f);
        }
    }



    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccesMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage("SuccesInfo", facesMsg);
    }

}
