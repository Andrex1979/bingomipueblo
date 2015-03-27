/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.session;

import com.bingomipueblo.jpa.entities.Tabla;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andresM
 */
@Stateless
public class TablaFacade extends AbstractFacade<Tabla> {
    @PersistenceContext(unitName = "BingoMiPuebloPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TablaFacade() {
        super(Tabla.class);
    }
    
}
