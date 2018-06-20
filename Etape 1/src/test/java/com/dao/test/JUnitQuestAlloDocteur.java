package com.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestAlloDocteur {

    private static final Log log = LogFactory.getLog(JUnitQuestAlloDocteur.class);

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
    }

    @Test
    public void callAllTests() {
        JUnitQuestAlloDocteurArray junitArray = new JUnitQuestAlloDocteurArray();
        JUnitQuestAlloDocteurMap junitMap = new JUnitQuestAlloDocteurMap();
        JUnitQuestAlloDocteurList junitList = new JUnitQuestAlloDocteurList();

        junitArray.testFindAllAdresses();
        junitArray.testFindAllUtilisateurs();
        junitArray.testFindByCriteria();
        junitArray.testCreateUpdateDeleteAdresse();
        junitArray.testCreateUpdateDeleteUtilisateur();

        junitMap.testFindAllAdresses();
        junitMap.testFindAllUtilisateurs();
        junitMap.testFindByCriteria();
        junitMap.testCreateUpdateDeleteAdresse();
        junitMap.testCreateUpdateDeleteUtilisateur();

        junitList.testFindAllAdresses();
        junitList.testFindAllUtilisateurs();
        junitList.testFindByCriteria();
        junitList.testCreateUpdateDeleteAdresse();
        junitList.testCreateUpdateDeleteUtilisateur();
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        log.debug("Sortie de la methode");
    }

}