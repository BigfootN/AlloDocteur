package com.dao.test;

import com.cours.allo.docteur.dao.manual.list.impl.ManualListAdresseDao;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListUtilisateurDao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestAlloDocteurList {

	private static final Log log = LogFactory.getLog(JUnitQuestAlloDocteurList.class);
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
		ManualListAdresseDao listAddr;
		ManualListUtilisateurDao listUser;

		listAddr = new ManualListAdresseDao();
		listUser = new ManualListUtilisateurDao();

		log.debug("Entree de la methode");
		log.debug("recherche par id...");
		Assert.assertTrue(listAddr.findAdresseById(2).getIdAdresse() == 2);
		Assert.assertTrue(listUser.findUtilisateurById(2).getIdUtilisateur() == 2);
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