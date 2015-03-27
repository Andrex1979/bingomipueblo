/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jsf.controller;

import com.bingomipueblo.jpa.entities.Ciudad;
import com.bingomipueblo.jpa.session.CiudadFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author DELL
 */
@ManagedBean
@SessionScoped
public class CiudadController {

    @EJB CiudadFacade ciudad;
    /**
     * Creates a new instance of CiudadController
     */
    public CiudadController() {
    }

    public CiudadFacade getCiudadFacade() {
        return ciudad;
    }
    
    public Ciudad getCiudad(com.bingomipueblo.jpa.entities.CiudadPK id) {
        return getCiudadFacade().find(id);
    }

    @FacesConverter(forClass = Ciudad.class, value="ciudadConverter") //se le agrego el value="ciudadConverter" para hacer al autocompletable
    public static class CiudadControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CiudadController controller = (CiudadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ciudadController");
            return controller.getCiudad(getKey(value));
        }

        com.bingomipueblo.jpa.entities.CiudadPK getKey(String value) {
            com.bingomipueblo.jpa.entities.CiudadPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.bingomipueblo.jpa.entities.CiudadPK();
            key.setIdCiudad(Integer.parseInt(values[0]));
            key.setDepartamentoIdDepartamento(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.bingomipueblo.jpa.entities.CiudadPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdCiudad());
            sb.append(SEPARATOR);
            sb.append(value.getDepartamentoIdDepartamento());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ciudad) {
                Ciudad o = (Ciudad) object;
                return getStringKey(o.getCiudadPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ciudad.class.getName());
            }
        }

    }
    
}
