/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jsf.controller;

import com.bingomipueblo.jpa.entities.Sexo;
import com.bingomipueblo.jpa.session.SexoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Adsit
 */
@Named(value = "sexoController")
@SessionScoped
public class SexoController implements Serializable {
    
    @EJB SexoFacade sexoFacade;
    /**
     * Creates a new instance of SexoController
     */
    public SexoController() {
    }
    
   public SexoFacade getSexoFacade(){
       return sexoFacade;
   }


   public Sexo getSexo(java.lang.Short id) {
        return getSexoFacade().find(id);
    }

    @FacesConverter(forClass = Sexo.class)
    public static class SexoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SexoController controller = (SexoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sexoController");
            return controller.getSexo(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sexo) {
                Sexo o = (Sexo) object;
                return getStringKey(o.getIdSexo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sexo.class.getName());
            }
        }

    }

}



 

