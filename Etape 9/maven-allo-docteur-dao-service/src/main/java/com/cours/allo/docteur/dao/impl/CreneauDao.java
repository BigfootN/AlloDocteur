package com.cours.allo.docteur.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.entities.Creneau;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CreneauDao
 */
public class CreneauDao implements ICreneauDao {
	private static final Log log = LogFactory.getLog(AdresseDao.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Creneau> findAll() {
		log.debug("Entree de la methode");
		List<Creneau> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Creneau.findAll").getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Creneau> findCreneauxByHeureDebut(Integer heureDebut) {
		log.debug("Entree de la methode");
		List<Creneau> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Creneau.findByHeureDebut").setParameter("heureDebut", heureDebut)
					.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Creneau> findCreneauxByHeureFin(Integer heureFin) {
		log.debug("Entree de la methode");
		List<Creneau> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Creneau.findByHeureFin").setParameter("heureFin", heureFin).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Creneau> findCreneauxByMinuteDebut(Integer minuteDebut) {
		log.debug("Entree de la methode");
		List<Creneau> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Creneau.findByMinuteDebut").setParameter("minuteDebut", minuteDebut)
					.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public List<Creneau> findCreneauxByMinuteFin(Integer minuteFin) {
		log.debug("Entree de la methode");
		List<Creneau> ret;

		ret = null;

		try {
			ret = em.createNamedQuery("Creneau.findByMinuteFin").setParameter("minuteFin", minuteFin).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public Creneau createCreneau(Creneau creneau) {
		log.debug("Entree de la methode");

		try {
			em.persist(creneau);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return creneau;
	}

	@Override
	public boolean deleteCreneau(Creneau creneau) {
		log.debug("Entree de la methode");

		try {
			em.remove(em.merge(creneau));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		log.debug("Sortie de la methode");
		return true;
	}

	@Override
	public Creneau updateCreneau(Creneau creneau) {
		log.debug("Entree de la methode");
		Creneau ret;

		ret = null;

		try {
			ret = em.merge(creneau);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

}