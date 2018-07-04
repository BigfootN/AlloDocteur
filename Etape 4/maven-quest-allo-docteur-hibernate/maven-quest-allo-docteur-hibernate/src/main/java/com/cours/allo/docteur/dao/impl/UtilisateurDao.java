/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.utils.Constants;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTANCE_UNIT);

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        String methodName = "findUtilisateurById";

        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        return false;
    }
}
