package com.cours.allo.docteur.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.entities.Patient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * PatientDao
 */
@Transactional
public class PatientDao implements IPatientDao {
	private static final Log log = LogFactory.getLog(PatientDao.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Patient> findAll() {
		List<Patient> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("Patient.findAll").getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Patient findPatientById(String idPatient) {
		Patient ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Patient) em.createNamedQuery("Patient.findById").setParameter("idPatient", idPatient).getResultList()
					.get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Patient findPatientByNumeroSecuriteSocial(String numeroSecuriteSocial) {
		Patient ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Patient) em.createNamedQuery("Patient.findByNumeroSecuriteSociale")
					.setParameter("numeroSecuriteSociale", numeroSecuriteSocial).getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<Patient> findPatientsByNom(String nom) {
		List<Patient> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("Patient.findByNom").setParameter("nom", nom).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public List<Patient> findPatientsByPrenom(String prenom) {
		List<Patient> ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = em.createNamedQuery("Patient.findByPrenom").setParameter("prenom", prenom).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Patient findPatientByNumeroTelephone(String numeroTelephone) {
		Patient ret;

		log.debug("Entree de la methode");

		ret = null;

		try {
			ret = (Patient) em.createNamedQuery("Patient.findByNumeroTelephone")
					.setParameter("numeroTelephone", numeroTelephone).getResultList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	@Transactional
	public Patient createPatient(Patient patient) {
		log.debug("Entree de la methode");

		try {
			em.persist(patient);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		log.debug("Sortie de la methode");

		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		log.debug("Entree de la methode");

		try {
			patient = em.merge(patient);
		} catch (Exception e) {
		}

		log.debug("Sortie de la methode");

		return patient;
	}

	@Override
	public boolean deletePatient(Patient patient) {
		log.debug("Entree de la methode");

		try {
			em.remove(em.merge(patient));
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}

		log.debug("Sortie de la methode");

		return true;
	}

}