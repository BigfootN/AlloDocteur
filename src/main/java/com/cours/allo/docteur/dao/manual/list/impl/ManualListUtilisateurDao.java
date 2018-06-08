/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.list.impl;

import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.utils.DaoHelper;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    public static List<Utilisateur> listUtilisateursOfDataSource = DaoHelper.getUtilisateursListDataSource();

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        return null;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        return false;
    }
}
