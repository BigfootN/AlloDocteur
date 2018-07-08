/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ElHadji
 */
@Transactional
public class UtilisateurDao implements IUtilisateurDao {

    @PersistenceContext
    private EntityManager em;
    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        List<Utilisateur> ret;

        log.debug("Entree de la methode");
        ret = em.createNamedQuery("Utilisateur.findAll").getResultList();
        log.debug("Sortie de la methode");

        return ret;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {

        List<Utilisateur> ret = null;
        Utilisateur user = null;


        ret = em.createNamedQuery("Utilisateur.findById").setParameter("idUtilisateur", idUtilisateur).getResultList();

        return ret.get(0);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        List<Utilisateur> ret;

        log.debug("Entree de la methode");
        ret = em.createNamedQuery("Utilisateur.findByPrenom").setParameter("prenom", prenom).getResultList();
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        List<Utilisateur> ret;

        log.debug("Entree de la methode");
        ret = em.createNamedQuery("Utilisateur.findByNom").setParameter("nom", nom).getResultList();

        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
        List<Utilisateur> ret;
        log.debug("Entree de la methode");
        ret = em.createNamedQuery("Utilisateur.findByCodePostal").setParameter("codePostal", codePostal).getResultList();

        log.debug("Sortie de la methode");
        return ret;
    }


    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {

        log.debug("Entree de la methode");
        em.persist(user);
        log.debug("Sortie de la methode");
        return user;
    }


    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");

        Utilisateur ret;
        em.merge(user);
        log.debug("Sortie de la methode");
        return user;

    }


    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");

        em.remove(em.merge(user));

        log.debug("Sortie de la methode");

        return true;
    }
}
