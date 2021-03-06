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

import javax.persistence.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(UtilisateurDao.class);
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
		Constants.PERSISTANCE_UNIT);

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		EntityManager em;
		List<Utilisateur> ret;

		log.debug("Entree de la methode");

		em = emf.createEntityManager();
		try {
			ret = em.createNamedQuery("Utilisateur.findAll").getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		String methodName = "findUtilisateurById";
		EntityManager em;
		List<Utilisateur> ret = null;
		Utilisateur user = null;

		em = emf.createEntityManager();
		try {
			ret = em.createNamedQuery("Utilisateur.findById").setParameter("idUtilisateur",
																		   idUtilisateur).
				  getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		} finally {
			ConnectionHelper.closeSqlResources(em);
		}

		return ret.get(0);
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		List<Utilisateur> ret;
		EntityManager em;

		log.debug("Entree de la methode");
		em = emf.createEntityManager();

		try {
			ret =
				em.createNamedQuery("Utilisateur.findByPrenom").setParameter("prenom",
																			 prenom).getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

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
		try {
			ret =
				em.createNamedQuery("Utilisateur.findByNom").setParameter("nom",
																		  nom).getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			ret = null;
		}

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
		try {

			ret = em.createNamedQuery("Utilisateur.findByCodePostal").setParameter("codePostal",
																				   codePostal).
				  getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {

		log.debug("Entree de la methode");
		EntityManager em;
		EntityTransaction tx;

		user.setDateModification(new Date());
		user.setDateCreation(new Date());
		em = emf.createEntityManager();

		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (Exception e) {
			user = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {

		log.debug("Entree de la methode");

		Utilisateur ret;
		EntityManager em;
		EntityTransaction tx;

		user.setDateModification(new Date());

		em = emf.createEntityManager();

		try {
			tx = em.getTransaction();
			tx.begin();
			ret = em.merge(user);
			tx.commit();
			em.close();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		EntityManager em;

		log.debug("Entree de la methode");
		em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.remove(em.merge(user));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			return false;
		} finally {
			ConnectionHelper.closeSqlResources(em);
		}

		log.debug("Sortie de la methode");

		return true;
	}

}