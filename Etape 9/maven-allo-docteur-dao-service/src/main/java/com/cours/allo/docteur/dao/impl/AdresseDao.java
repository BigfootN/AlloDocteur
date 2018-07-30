/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.lifecycle.Lifecycle;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ElHadji
 */
@Transactional
public class AdresseDao implements IAdresseDao {

	private static final Log log = LogFactory.getLog(AdresseDao.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Adresse> findAllAdresses() {
		log.debug("Entree de la methode");
		List<Adresse> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Adresse.findAll").getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Adresse findAdresseById(int idAdresse) {
		log.debug("Entree de la methode");
		List<Adresse> listAddr;
		Adresse ret;

		ret = null;

		try {
			listAddr = em.createNamedQuery("Adresse.findById").setParameter("idAdresse", idAdresse).getResultList();
			ret = listAddr.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Adresse> findAdressesByVille(String ville) {
		log.debug("Entree de la methode");
		List<Adresse> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Adresse.findByVille").setParameter("ville", ville).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Adresse> findAdressesByCodePostal(String codePostal) {
		log.debug("Entree de la methode");
		List<Adresse> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Adresse.findByCodePostal").setParameter("codePostal", codePostal)
					.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Adresse createAdresse(Adresse adresse) {
		log.debug("Entree de la methode");

		try {
			System.out.println("adresse = " + adresse.getIdUtilisateur().getIdUtilisateur());
			em.persist(adresse);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return adresse;
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) {
		log.debug("Entree de la methode");
		Adresse oldAddr;

		oldAddr = null;

		try {
			oldAddr = em.find(Adresse.class, adresse.getIdAdresse());
			oldAddr.setCodePostal(adresse.getCodePostal());
			oldAddr.setPays(adresse.getPays());
			oldAddr.setRue(adresse.getRue());
			oldAddr.setVille(adresse.getVille());
			oldAddr.setVersion(oldAddr.getVersion() + 1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return oldAddr;
	}

	@Override
	public boolean deleteAdresse(Adresse adresse) {

		log.debug("Entree de la methode");

		try {
			em.remove(em.find(Adresse.class, adresse.getIdAdresse()));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		log.debug("Sortie de la methode");
		return true;
	}

}