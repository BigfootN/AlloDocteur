package com.cours.allo.docteur.dao.impl;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.entities.RendezVous;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * RendezVous
 */
public class RendezVousDao implements IRendezVousDao {

	@PersistenceContext
	private EntityManager em;
	private static final Log log = LogFactory.getLog(UtilisateurDao.class);

	@Override
	public List<RendezVous> findAll() {
		List<RendezVous> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("RendezVous.findAll").getResultList();
		} catch (Exception e) {
			log.error("Erreur findall Rdv:" + e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public RendezVous findRendezVousById(Integer idRendezVous) {
		RendezVous ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (RendezVous) em.createNamedQuery("RendezVous.findById").setParameter(
				"idRendezVous",
				idRendezVous)
				  .getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<RendezVous> findRendezVousByIdMedecin(Integer idMedecin) {
		List<RendezVous> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("RendezVous.findByIdMedecin").setParameter("idMedecin",
																				 idMedecin)
				  .getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Long chiffreAffaire() {
		Long ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Long) em.createNamedQuery("RendezVous.Revenue").getSingleResult();
		} catch (Exception e) {
			ret = null;
			log.error(e.getMessage());
		}

		if (ret == null)
			ret = 0L;

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<RendezVous> findRendezVousByJourAndIdMedecin(Integer idMedecin, Date time) {
		List<RendezVous> ret;
		Query query;

		log.debug("Entree de la methode");

		ret = null;

		try {
			query = em.createNamedQuery("RendezVous.findRendezVousByJourAndIdMedecin");
			query.setParameter("idMedecin", idMedecin);
			query.setParameter("jour", time);
			ret = query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<RendezVous> findRendezVousByJour(Date time) {
		List<RendezVous> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret =
					em.createNamedQuery("RendezVous.findByJour").setParameter("jour",
							time).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<RendezVous> findAllFuturRendezVous(Date time) {
		List<RendezVous> ret;
		DateFormat df;
		String dateApptStr;

		log.debug("Entree de la methode");

		ret = null;
		dateApptStr = null;

		try {
			df = new SimpleDateFormat("yyyy-MM-dd");
			dateApptStr = df.format(time);
			System.out.println("dateappstr = " + dateApptStr);
			ret =
				em.createNamedQuery("RendezVous.findAllFutur").setParameter("jour",
																			time).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage() + " lmessage: " + dateApptStr);
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public boolean deleteRendezVous(RendezVous appt) {
		log.debug("Entree de la methode");

		try {
			em.remove(em.merge(appt));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		log.debug("Sortie de la methode");

		return true;
	}

	@Override
	public RendezVous updateRendezVous(RendezVous appt) {
		log.debug("Entree de la methode");

		RendezVous ret;

		ret = null;

		try {
			ret = em.merge(appt);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");
		return ret;
	}

	@Override
	public RendezVous createRendezVous(RendezVous appt) {
		log.debug("Entree de la methode");

		try {
			em.persist(appt);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return appt;
	}

}