/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.utils.Constants;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTANCE_UNIT);

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        EntityManager em;
    	List<Utilisateur> ret;
        
    	log.debug("Entree de la methode");
        em = emf.createEntityManager();
        ret = em.createNamedQuery("Utilisateur.findAll").getResultList();
        ConnectionHelper.closeSqlResources(em);
        log.debug("Sortie de la methode");
        
        return ret;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        String methodName = "findUtilisateurById";
        EntityManager em;
        List<Utilisateur> ret;
        
        em = emf.createEntityManager();
        ret = em.createNamedQuery("Utilisateur.findById").setParameter("idUtilisateur", idUtilisateur).getResultList();
        ConnectionHelper.closeSqlResources(em);
        
        return ret.get(0);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        List<Utilisateur> ret;
    	EntityManager em;
        
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	ret = em.createNamedQuery("Utilisateur.findByPrenom").setParameter("prenom", prenom).getResultList();
    	ConnectionHelper.closeSqlResources(em);
    	
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> ret;
    	EntityManager em;
        
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	ret = em.createNamedQuery("Utilisateur.findByNom").setParameter("nom", nom).getResultList();
    	ConnectionHelper.closeSqlResources(em);
    	
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
    	List<Utilisateur> ret;
    	EntityManager em;
        
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	ret = em.createNamedQuery("Utilisateur.findByCodePostal").setParameter("codePostal", codePostal).getResultList();
    	ConnectionHelper.closeSqlResources(em);
    	
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        Utilisateur oldUser;
        EntityManager em;
    	
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	oldUser = em.find(Utilisateur.class, user.getIdUtilisateur());
    	
    	em.getTransaction().begin();
    	em.persist(user);
    	em.getTransaction().commit();
    	
        return user;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	Utilisateur ret;
    	EntityManager em;
    	
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	ret = em.find(Utilisateur.class, user.getIdUtilisateur());
    	
    	em.getTransaction().begin();
    	ret.setAdresseSet(user.getAdresses());
    	ret.setCivilite(user.getCivilite());
    	ret.setDateCreation(user.getDateCreation());
    	ret.setDateModification(new Date());
    	ret.setActif(user.getActif());
    	ret.setMarquerEffacer(user.getMarquerEffacer());
    	ret.setNom(user.getNom());
    	ret.setPrenom(user.getPrenom());
    	ret.setMotPasse(user.getMotPasse());
    	em.getTransaction().commit();
    	
    	ConnectionHelper.closeSqlResources(em);
    	
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	Utilisateur ret;
    	EntityManager em;
    	
    	log.debug("Entree de la methode");
    	em = emf.createEntityManager();
    	ret = em.find(Utilisateur.class, user.getIdUtilisateur());
    	
    	try {
    		em.getTransaction().begin();
        	ret.setAdresseSet(user.getAdresses());
        	ret.setCivilite(user.getCivilite());
        	ret.setDateCreation(user.getDateCreation());
        	ret.setDateModification(new Date());
        	ret.setActif(user.getActif());
        	ret.setMarquerEffacer(user.getMarquerEffacer());
        	ret.setNom(user.getNom());
        	ret.setPrenom(user.getPrenom());
        	ret.setMotPasse(user.getMotPasse());
        	em.getTransaction().commit();
    	} catch (Exception e) {
    		return false;
    	} finally {
    		ConnectionHelper.closeSqlResources(em);
    	}
    	
        log.debug("Sortie de la methode");
        
        return true;
    }
}
