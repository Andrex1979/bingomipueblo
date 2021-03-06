/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingomipueblo.jsf.controller;


import com.bingomipueblo.jpa.entities.Usuario;
import com.bingomipueblo.jpa.session.UsuarioFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String username;
    private String password;
    private Usuario usuario;
    @EJB
    private UsuarioFacade usuarioFacade;

    public LoginController() {
//      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//     if (session != null) {
//        session.invalidate();
//     }
    }

    public String getUsername() {
        return username;
    }

    private UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }
    
    public Usuario getUserLogueado() {
        String user = getLogueado();
        return getUsuarioFacade().findByUserName(user);
       
    }

    public boolean isAdministrador() {
        return getRequest().isUserInRole("webAdmin");
    }

   
    public boolean isUser() {
        return getRequest().isUserInRole("webUser");
    }

    public String login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

            usuario = getUserLogueado();
            limpiar();

//            //Cancela login para usuarios inactivos
            if (!usuario.getEstado()) {
                logout();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
                return "/index";
            }
            //Redirigir a la página de portada
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + usuario.toString(), null));
            return "/users/userReg/bienvenido";

        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Invalida", null));
            return "/index";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            return "/index";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/admin/List";
        }
    }

    private void limpiar() {
        username = "";
        password = "";
    }
}
