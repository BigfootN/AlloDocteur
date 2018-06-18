package com.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.manual.array.impl.ManualArrayAdresseDao;
import com.cours.allo.docteur.dao.manual.array.impl.ManualArrayUtilisateurDao;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListAdresseDao;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.DaoHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class JUnitQuestAlloDocteurArray {

    private static final Log log = LogFactory.getLog(JUnitQuestAlloDocteurArray.class);
    private static IServiceFacade serviceFacade = null;

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade();
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindAllUtilisateurs() {
        ManualArrayUtilisateurDao users;

        users = new ManualArrayUtilisateurDao();

        log.debug("Entree de la methode");
        Assert.assertTrue(users.findAllUtilisateurs().size() == DaoHelper.getUtilisateursArrayDataSource().length);
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindAllAdresses() {
        ManualArrayAdresseDao addresses;

        addresses = new ManualArrayAdresseDao();
        log.debug("Entree de la methode");
        Assert.assertTrue(addresses.findAllAdresses().size() == DaoHelper.getAdressesArrayDataSource().length);
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        ManualArrayAdresseDao listAddr;
        ManualArrayUtilisateurDao listUser;

        listAddr = new ManualArrayAdresseDao();
        listUser = new ManualArrayUtilisateurDao();

        log.debug("Entree de la methode");
        log.debug("recherche par id...");
        Assert.assertTrue(listAddr.findAdresseById(2).getIdAdresse() == 2);
        Assert.assertTrue(listUser.findUtilisateurById(2).getIdUtilisateur() == 2);
        log.debug("recherche par ville...");
        Assert.assertTrue(listAddr.findAdressesByVille("Paris").size() == 27);
        log.debug("recherche par adresse code postal...");
        Assert.assertTrue(listAddr.findAdressesByCodePostal("35000").size() == 28);
        log.debug("recherche par utilisateur code postal...");
        Assert.assertTrue(listUser.findUtilisateursByCodePostal("75000").size() == 24);
        log.debug("recherche utilisateur par nom...");
        Assert.assertTrue(listUser.findUtilisateursByNom("Dupond").size() == 1);
        log.debug("recherche utilisateur par prenom...");
        Assert.assertTrue(listUser.findUtilisateursByPrenom("Jerome").size() == 2);
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        ManualArrayUtilisateurDao listUser;
        Utilisateur userTest;
        Utilisateur returnUser;

        listUser = new ManualArrayUtilisateurDao();
        userTest = new Utilisateur(1, "Mr", "Jean", "Dupoound", "id_dupond", "mdp", new Date(), true, true,
                Arrays.asList(new Adresse(1)));

        log.debug("Entree de la methode");
        log.debug("Entree de createUtilisateur");
        returnUser = listUser.createUtilisateur(userTest);
        log.debug("Sortie de createUtilisateur");
        log.debug("Entree getIdUtilisateur");
        Assert.assertTrue(returnUser.getIdUtilisateur() == 29);
        log.debug("Sortie getIdUtilisateur");
        log.debug("Entre de FindAllUtilisateurs");
        Assert.assertTrue(listUser.findAllUtilisateurs().size() == DaoHelper.getUtilisateursArrayDataSource().length);
        log.debug("Sortie de FindAllUtilisateurs");
        listUser.deleteUtilisateur(userTest);
        Assert.assertTrue(listUser.findAllUtilisateurs().size() == 28);
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteAdresse() {
        ManualArrayAdresseDao listUser;
        Adresse addrTest;
        Adresse returnAddr;
        Integer size;

        listUser = new ManualArrayAdresseDao();
        addrTest = new Adresse(1, "Rue des pigeons", "38474", "Pigeon", "Pays");
        size = listUser.findAllAdresses().size();

        log.debug("Entree de la methode");
        log.debug("Entree de createAdresse");
        returnAddr = listUser.createAdresse(addrTest);
        log.debug("Sortie de createAdresse");
        log.debug("Entree getIdUtilisateur");
        //log.debug(returnAddr.getIdAdresse());
        Assert.assertTrue(returnAddr.getIdAdresse() == 83);
        log.debug("Sortie getIdUtilisateur");
        log.debug("Entree de findAllAdresses");
        Assert.assertTrue(listUser.findAllAdresses().size() == DaoHelper.getAdressesArrayDataSource().length);
        log.debug("Sortie de findAllAdresses");
        log.debug("Entree de deleteAdresses");
        listUser.deleteAdresse(addrTest);
        log.debug("Sortie de deleteAdresses");
        Assert.assertTrue(listUser.findAllAdresses().size() == size);
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        log.debug("Sortie de la methode");
    }
}
