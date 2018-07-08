/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ElHadji
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Adresse ect
    // ....)
    private IUtilisateurDao utilisateurDao = null;
    private IAdresseDao adresseDao = null;

    public ServiceFacade() {
        ApplicationContext ctx;

        ctx = new ClassPathXmlApplicationContext("file:src/main/resources/applicationContext.xml");

        utilisateurDao = (IUtilisateurDao) ctx.getBean("userDao");
        adresseDao = (IAdresseDao) ctx.getBean("addrDao");

        ((ConfigurableApplicationContext) ctx).close();
    }

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return adresseDao;
    }

}