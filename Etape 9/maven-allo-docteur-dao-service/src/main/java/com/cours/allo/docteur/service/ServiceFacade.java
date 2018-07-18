package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * ServiceFacade
 */
@Service
public class ServiceFacade {
    @Autowired
    @Qualifier("adresseDao")
    private IAdresseDao aDao;

    public IAdresseDao getAdresseDao() {
        return aDao;
    }

}