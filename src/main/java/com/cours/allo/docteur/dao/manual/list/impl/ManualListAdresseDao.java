/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.list.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.DaoHelper;

import java.util.ArrayList;
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
        return listAdressesOfDataSource;
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        log.debug("Entree de la methode");
        for (Adresse adresse : listAdressesOfDataSource) {
            if (adresse.getIdAdresse().equals(idAdresse)) {
                return adresse;
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        List<Adresse> adressesByVille = null;

        adressesByVille = new ArrayList<>();

        log.debug("Entree de la methode");
        for (Adresse adresse : listAdressesOfDataSource) {
            if (adresse.getVille().equals(ville)) {
                adressesByVille.add(adresse);
            }
        }
        log.debug("Sortie de la methode");
        return adressesByVille;
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        List<Adresse> adressesByCode = null;

        adressesByCode = new ArrayList<>();

        log.debug("Entree de la methode");
        for (Adresse adresse : listAdressesOfDataSource) {
            if (adresse.getCodePostal().equals(codePostal)) {
                adressesByCode.add(adresse);
            }
        }
        log.debug("Sortie de la methode");
        return adressesByCode;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        int idAdresse = 0;
        log.debug("Entree de la methode");
        idAdresse = listAdressesOfDataSource.get(listAdressesOfDataSource.size() - 1).getIdAdresse();
        adresse.setIdAdresse(idAdresse + 1);
        listAdressesOfDataSource.add(adresse);
        log.debug("Sortie de la methode");
        return adresse;
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        for (int i = 0; i < listAdressesOfDataSource.size(); i++) {
            if (listAdressesOfDataSource.get(i).getIdAdresse().equals(adresse.getIdAdresse())) {
                adresse.setVersion(adresse.getVersion() + 1);
                listAdressesOfDataSource.set(i, adresse);
                return adresse;
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        listAdressesOfDataSource.remove(adresse);
        log.debug("Sortie de la methode");
        return false;
    }

}