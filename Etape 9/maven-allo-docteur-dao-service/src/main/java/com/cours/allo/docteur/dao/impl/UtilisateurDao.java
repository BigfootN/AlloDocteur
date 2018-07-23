/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

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

		ret = null;

		try {
			ret = em.createNamedQuery("Utilisateur.findAll").getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		Utilisateur ret;

		ret = null;

		try {

			ret = (Utilisateur) em.createNamedQuery("Utilisateur.findById").setParameter(
				"idUtilisateur",
				idUtilisateur)
				  .getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return ret;
	}

	@Override
	public Adresse findAdressePrincipale(int idUtilisateur) {
		Adresse ret;

		ret = null;

		try {

			ret = (Adresse) em.createNamedQuery("Utilisateur.findMainAddress")
				  .setParameter("idUtilisateur", idUtilisateur).getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		List<Utilisateur> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret =
				em.createNamedQuery("Utilisateur.findByPrenom").setParameter("prenom",
																			 prenom).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		List<Utilisateur> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret =
				em.createNamedQuery("Utilisateur.findByNom").setParameter("nom",
																		  nom).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
		List<Utilisateur> ret;
		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("Utilisateur.findByCodePostal").setParameter("codePostal",
																				   codePostal)
				  .getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {

		log.debug("Entree de la methode");

		user.setDateCreation(new Date());
		user.setDateModification(new Date());

		try {
			em.persist(user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		log.debug("Entree de la methode");

		Utilisateur ret;

		ret = null;
		user.setDateModification(new Date());

		try {
			ret = em.merge(user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;

	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		log.debug("Entree de la methode");

		try {
			em.remove(em.merge(user));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return true;
	}

}