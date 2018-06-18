package com.dao.test;

import com.cours.allo.docteur.dao.manual.map.impl.ManualMapUtilisateurDao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.DaoHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestAlloDocteurMap {

	private static final Log log = LogFactory.getLog(JUnitQuestAlloDocteurMap.class);
	private static IServiceFacade serviceFacade = null;

	@BeforeClass
	public static void init() throws Exception {
		// configuration de l'application
		log.debug("Entree de la methode");
		log.debug("Sortie de la methode");
	}

	@Test
	public void testFindAllUtilisateurs() {
		ManualMapUtilisateurDao users;

		users = new ManualMapUtilisateurDao();

		log.debug("Entree de la methode");
		Assert.assertTrue(
			users.findAllUtilisateurs().size() == DaoHelper.getUtilisateursMapDataSource().size());
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