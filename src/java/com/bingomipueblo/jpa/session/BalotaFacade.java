/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.session;

import com.bingomipueblo.jpa.entities.Balota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andresM
 */
@Stateless
public class BalotaFacade extends AbstractFacade<Balota> {
    @PersistenceContext(unitName = "BingoMiPuebloPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BalotaFacade() {
        super(Balota.class);
    }
    
}
