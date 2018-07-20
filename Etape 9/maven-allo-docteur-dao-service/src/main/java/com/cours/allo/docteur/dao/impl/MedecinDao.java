package com.cours.allo.docteur.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.entities.Medecin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MedecinDao
 */
public class MedecinDao implements IMedecinDao {

	@PersistenceContext
	private EntityManager em;
	private static final Log log = LogFactory.getLog(MedecinDao.class);

	@Override
	public List<Medecin> findAll() {
		List<Medecin> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("Medecin.findAll").getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Medecin findMedecinByNumeroAccreditation(String numeroAccreditation) {
		Medecin ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Medecin) em.createNamedQuery("Medecin.findByNumeroAccreditation")
				  .setParameter("numeroAccreditation", numeroAccreditation).getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Medecin findMedecinByNumeroSecuriteSociale(String numeroSecuriteSociale) {
		Medecin ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Medecin) em.createNamedQuery("Medecin.findByNumeroSecuriteSociale")
				  .setParameter("numeroSecuriteSociale", numeroSecuriteSociale).getResultList().get(
				0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Medecin findMedecinByNumeroTelephone(String numeroTelephone) {
		Medecin ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Medecin) em.createNamedQuery("Medecin.findByNumeroTelephone")
				  .setParameter("numeroTelephone", numeroTelephone).getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Medecin findMedecinByPwdAndId(String pwd, String email) {
		Medecin ret;
		Query query;

		log.debug("Entree de la methode");

		ret = null;

		try {
			query = em.createNamedQuery("Medecin.findByMdpIdentifiant");
			query.setParameter("motPasse", pwd);
			query.setParameter("identifiant", email);
			ret = (Medecin) query.getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Medecin createMedecin(Medecin doctor) {
		log.debug("Entree de la methode");

		try {
			em.persist(doctor);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return doctor;
	}

	@Override
	public Medecin updateMedecin(Medecin doctor) {
		log.debug("Entree de la methode");

		Medecin ret;

		ret = null;

		try {
			ret = em.merge(doctor);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public boolean deleteMedecin(Medecin doctor) {
		log.debug("Entree de la methode");

		try {
			em.remove(em.merge(doctor));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return true;
	}

}