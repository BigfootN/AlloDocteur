package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;

import org.springframework.stereotype.Service;

public interface IServiceFacade {
    public IAdresseDao getAdresseDao();

    public IUtilisateurDao gUtilisateurDao();

    public IRendezVousDao getRendezVousDao();

    public IMedecinDao getMedecinDao();

    public ICreneauDao getCreneauDao();

    public IPatientDao getPatientDao();
}