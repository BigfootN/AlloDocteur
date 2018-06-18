package com.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.allo.docteur.service.IServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestAlloDocteurArray {

    private static final Log log = LogFactory.getLog(JUnitQuestAlloDocteurArray.class);
    private static IServiceFacade serviceFacade = null;

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindAllUtilisateurs() {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindAllAdresses() {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        log.debug("Entree de la methode");

        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteAdresse() {
        log.debug("Entree de la methode");

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
