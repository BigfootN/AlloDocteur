/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.manual.map.impl.ManualMapAdresseDao;
import com.cours.allo.docteur.dao.manual.map.impl.ManualMapUtilisateurDao;

/**
 *
 * @author ElHadji
 */
public class ManualMapDaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new ManualMapUtilisateurDao();
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return new ManualMapAdresseDao();
    }
}
