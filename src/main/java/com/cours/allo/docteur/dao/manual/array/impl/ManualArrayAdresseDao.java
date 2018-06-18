/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.array.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.utils.DaoHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualArrayAdresseDao implements IAdresseDao {

    private static final Log log = LogFactory.getLog(ManualArrayAdresseDao.class);
    public static Adresse[] arrayAdressesOfDataSource = DaoHelper.getAdressesArrayDataSource();

    @Override
    public List<Adresse> findAllAdresses() {
        String methodName = "findAllAdresses";
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
        return Arrays.asList(arrayAdressesOfDataSource);
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        List<Adresse> tmp;
        Iterator<Adresse> it;
        boolean found;
        Adresse ret;
        Adresse curAdresse;

        ret = null;
        found = false;
        tmp = Arrays.asList(arrayAdressesOfDataSource);
        it = tmp.iterator();

        while (it.hasNext() && !found) {
            curAdresse = it.next();

            if (curAdresse.getIdAdresse() == idAdresse) {
                found = true;
                ret = curAdresse;
            }
        }

        return ret;
    }

    @Override
    public List<Adresse> findAdressesByVille(String ville) {
        int idx;
        int size;
        List<Adresse> ret;

        ret = new ArrayList<>();
        idx = 0;
        size = 0;

        while (idx < size) {
            if (arrayAdressesOfDataSource[idx].getVille().equals(ville))
                ret.add(arrayAdressesOfDataSource[idx]);

            idx++;
        }

        return ret;
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        int idx;
        int size;
        List<Adresse> ret;

        ret = new ArrayList<>();
        idx = 0;
        size = 0;

        while (idx < size) {
            if (arrayAdressesOfDataSource[idx].getCodePostal().equals(codePostal))
                ret.add(arrayAdressesOfDataSource[idx]);

            idx++;
        }

        return ret;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        List<Adresse> tmp;
        int lastId;
        int size;

        size = arrayAdressesOfDataSource.length;
        tmp = Arrays.asList(arrayAdressesOfDataSource);
        lastId = tmp.get(size - 1).getIdAdresse();
        adresse.setIdAdresse(lastId + 1);
        tmp.add(adresse);
        arrayAdressesOfDataSource = tmp.toArray(new Adresse[size + 1]);

        return adresse;
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        List<Adresse> tmp;
        ListIterator<Adresse> it;
        Adresse curAddr;
        boolean found;

        tmp = Arrays.asList(arrayAdressesOfDataSource);
        it = tmp.listIterator();
        found = false;

        while (it.hasNext() && !found) {
            curAddr = it.next();

            if (curAddr.getIdAdresse() == adresse.getIdAdresse()) {
                it.set(adresse);
                found = true;
            }
        }

        arrayAdressesOfDataSource = tmp.toArray(new Adresse[tmp.size()]);

        return adresse;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        List<Adresse> tmp;
        ListIterator<Adresse> it;
        Adresse curAddr;
        boolean found;

        tmp = Arrays.asList(arrayAdressesOfDataSource);
        it = tmp.listIterator();
        found = false;

        while (it.hasNext() && !found) {
            curAddr = it.next();

            if (curAddr.getIdAdresse() == adresse.getIdAdresse()) {
                it.remove();
                found = true;
            }
        }

        arrayAdressesOfDataSource = tmp.toArray(new Adresse[tmp.size()]);

        return found;
    }

}