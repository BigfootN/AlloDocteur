/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.list.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.DaoHelper;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListAdresseDao implements IAdresseDao {

    private static final Log log = LogFactory.getLog(ManualListAdresseDao.class);
    public static List<Adresse> listAdressesOfDataSource = DaoHelper.getAdressesListDataSource();

    @Override
    public List<Adresse> findAllAdresses() {
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        return null;
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        return null;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
        return false;
    }
}
