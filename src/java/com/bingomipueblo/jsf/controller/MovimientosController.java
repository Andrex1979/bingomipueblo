/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jsf.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Adsit
 */
@Named(value = "movimientosController")
@SessionScoped
public class MovimientosController implements Serializable {

    /**
     * Creates a new instance of MovimientosController
     */
    public MovimientosController() {
    }
    
}
