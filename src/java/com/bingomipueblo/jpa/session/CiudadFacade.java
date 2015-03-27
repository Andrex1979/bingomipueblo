/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bingomipueblo.jpa.session;

import com.bingomipueblo.jpa.entities.Ciudad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adsit
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "BingoMiPuebloPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    
    public List<Ciudad> finByNombre(String nombre){
       Query q= getEntityManager().createNamedQuery("Ciudad.findByNombreCiudad");
       q.setParameter("nombreCiudad",nombre+ "%");
       return q.getResultList();
    }
    
}
