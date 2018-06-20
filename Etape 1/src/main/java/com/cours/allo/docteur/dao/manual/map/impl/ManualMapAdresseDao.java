/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.map.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.DaoHelper;
import java.util.ArrayList;
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
        return new ArrayList<>(mapAdressesOfDataSource.values());
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        log.debug("Entree de la methode");
        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (entry.getValue().getIdAdresse().equals(idAdresse))
                return entry.getValue();
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        List<Adresse> ret;

        ret = new ArrayList<>();

        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (entry.getValue().getVille().equals(ville))
                ret.add(entry.getValue());
        }

        if (ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        List<Adresse> ret;

        ret = new ArrayList<>();

        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (entry.getValue().getCodePostal().equals(codePostal))
                ret.add(entry.getValue());
        }

        if (ret.size() == 0)
            ret = null;

        return ret;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        Adresse ret;
        Integer newId = 0;

        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (newId < entry.getValue().getIdAdresse()) {
                newId = entry.getValue().getIdAdresse();
            }
        }

        newId++;

        ret = new Adresse(newId, adresse.getRue(), adresse.getCodePostal(), adresse.getVille(), adresse.getPays(),
                adresse.getIdUtilisateur());

        ret.setIdUtilisateur(newId);

        mapAdressesOfDataSource.put(newId, ret);

        log.debug("Sortie de la methode");
        return ret;

    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        Adresse ret;

        ret = null;

        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (entry.getValue().getIdAdresse().equals(adresse.getIdAdresse())) {
                adresse.setVersion(adresse.getVersion() + 1);
                entry.setValue(adresse);
                ret = adresse;
                break;
            }
        }
        log.debug("Sortie de la methode");
        return ret;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        boolean ret;

        ret = false;

        for (Map.Entry<Integer, Adresse> entry : mapAdressesOfDataSource.entrySet()) {
            if (entry.getValue().equals(adresse)) {
                mapAdressesOfDataSource.remove(entry.getKey(), entry.getValue());
                ret = true;
                break;
            }
        }
        log.debug("Sortie de la methode");
        return ret;

    }
}
