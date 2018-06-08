/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.dao.manual.list.impl.ManualListAdresseDao;
import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListUtilisateurDao;

/**
 *
 * @author ElHadji
 */
public class ManualListDaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new ManualListUtilisateurDao();
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return new ManualListAdresseDao();
    }
}
