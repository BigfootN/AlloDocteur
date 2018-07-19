package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * ServiceFacade
 */
@Service
public class ServiceFacade implements IServiceFacade {
	@Autowired
	@Qualifier("adresseDao")
	private IAdresseDao aDao;

	@Autowired
	@Qualifier("utilisateurDao")
	private IUtilisateurDao uDao;

	@Autowired
	@Qualifier("patientDao")
	private IPatientDao pDao;

	@Autowired
	@Qualifier("medecinDao")
	private IMedecinDao mDao;

	@Autowired
	@Qualifier("creneauDao")
	private ICreneauDao cDao;

	@Autowired
	@Qualifier("rendezVousDao")
	private IRendezVousDao rvDao;

	public ServiceFacade() {
		System.out.println("instancie service facade");
	}

	public IAdresseDao getAdresseDao() {
		return aDao;
	}

	@Override
	public IUtilisateurDao gUtilisateurDao() {
		return uDao;
	}

	@Override
	public IRendezVousDao getRendezVousDao() {
		return rvDao;
	}

	@Override
	public IMedecinDao getMedecinDao() {
		return mDao;
	}

	@Override
	public ICreneauDao getCreneauDao() {
		return cDao;
	}

	@Override
	public IPatientDao getPatientDao() {
		return pDao;
	}

}