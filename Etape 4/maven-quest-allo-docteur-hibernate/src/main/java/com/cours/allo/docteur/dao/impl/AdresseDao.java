/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.Constants;
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
public class AdresseDao implements IAdresseDao {

	private static final Log log = LogFactory.getLog(AdresseDao.class);
	public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
		Constants.PERSISTANCE_UNIT);

	@Override
	public List<Adresse> findAllAdresses() {
		log.debug("Entree de la methode");
		EntityManager em;
		List<Adresse> ret;

		em = emf.createEntityManager();

		try {
			ret = em.createNamedQuery("Adresse.findAll").getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			ret = null;
		}
		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Adresse findAdresseById(int idAdresse) {
		log.debug("Entree de la methode");
		EntityManager em;
		List<Adresse> ret;

		em = emf.createEntityManager();

		try {
			ret =
				em.createNamedQuery("Adresse.findById").setParameter("idAdresse",
																	 idAdresse).getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}
		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return ret.get(0);
	}

	@Override
	public List<Adresse> findAdressesByVille(String ville) {
		log.debug("Entree de la methode");
		EntityManager em;
		List<Adresse> ret;

		em = emf.createEntityManager();

		try {

			ret =
				em.createNamedQuery("Adresse.findByVille").setParameter("ville",
																		ville).getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Adresse> findAdressesByCodePostal(String codePostal) {
		log.debug("Entree de la methode");
		EntityManager em;
		List<Adresse> ret;

		em = emf.createEntityManager();

		try {
			ret =
				em.createNamedQuery("Adresse.findByCodePostal").setParameter("codePostal",
																			 codePostal).
				getResultList();
		} catch (Exception e) {
			ret = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Adresse createAdresse(Adresse adresse) {
		log.debug("Entree de la methode");
		EntityManager em;

		em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(adresse);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		log.debug("Sortie de la methode");
		return adresse;
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) {
		Adresse oldAddr;
		EntityManager em;

		log.debug("Entree de la methode");

		em = emf.createEntityManager();

		try {
			oldAddr = em.find(Adresse.class, adresse.getIdAdresse());
			em.getTransaction().begin();
			oldAddr.setCodePostal(adresse.getCodePostal());
			oldAddr.setPays(adresse.getPays());
			oldAddr.setRue(adresse.getRue());
			oldAddr.setVille(adresse.getVille());
			oldAddr.setVersion(oldAddr.getVersion() + 1);
			em.getTransaction().commit();
			log.debug("Sortie de la methode");
		} catch (Exception e) {
			oldAddr = null;
			em.getTransaction().rollback();
		}

		ConnectionHelper.closeSqlResources(em);

		return oldAddr;
	}

	@Override
	public boolean deleteAdresse(Adresse adresse) {
		EntityManager em;

		log.debug("Entree de la methode");
		em = emf.createEntityManager();

		try {
			em.remove(em.find(Adresse.class, adresse.getIdAdresse()));
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