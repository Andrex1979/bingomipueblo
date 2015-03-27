/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bingomipueblo.jpa.session;

import com.bingomipueblo.jpa.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adsit
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BingoMiPuebloPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findByNumeroDocumento(String query) {
        Query q = getEntityManager().createNamedQuery("Usuario.findByNumeroDocumento");
        q.setParameter("numeroDocumento", query);
        try {
            return (Usuario) q.getSingleResult();

        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }

    }
    
     public Usuario findByUserName(String query) {
        Query q = getEntityManager().createNamedQuery("Usuario.findByUserName");
        q.setParameter("userName", query);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
