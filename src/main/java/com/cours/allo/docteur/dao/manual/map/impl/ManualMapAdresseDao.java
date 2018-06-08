/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.map.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.DaoHelper;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapAdresseDao implements IAdresseDao {

    private static final Log log = LogFactory.getLog(ManualMapAdresseDao.class);
    public static Map<Integer, Adresse> mapAdressesOfDataSource = DaoHelper.getAdressesMapDataSource();

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
