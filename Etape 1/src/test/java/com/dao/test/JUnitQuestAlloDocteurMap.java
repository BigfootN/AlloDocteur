package com.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.manual.map.impl.ManualMapAdresseDao;
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
		ManualMapAdresseDao addresses;

		addresses = new ManualMapAdresseDao();

		log.debug("Entree de la methode");
		Assert.assertTrue(
			addresses.findAllAdresses().size() == DaoHelper.getAdressesMapDataSource().size());
		log.debug("Sortie de la methode");
	}

	@Test
	public void testFindByCriteria() {
		ManualMapUtilisateurDao users;
		ManualMapAdresseDao addresses;

		users = new ManualMapUtilisateurDao();
		addresses = new ManualMapAdresseDao();

		log.debug("Entree de la methode");
		log.debug("Recherche par prenom...");
		Assert.assertTrue(users.findUtilisateursByPrenom("Jerome").size() == 2);
		log.debug("Recherche par nom...");
		Assert.assertTrue(users.findUtilisateursByNom("Collard").size() == 1);
		log.debug("Recherche par code postal...");
		Assert.assertTrue(users.findUtilisateursByCodePostal("76000").size() == 5);
		Assert.assertTrue(addresses.findAdressesByCodePostal("35000").size() == 28);
		log.debug("Recherche par ville...");
		Assert.assertTrue(addresses.findAdressesByVille("Rouen").size() == 5);
		log.debug("Recherche par id...");
		Assert.assertTrue(addresses.findAdresseById(3).getRue().equals("66 rue des deportés"));
		Assert.assertTrue(users.findUtilisateurById(4).getPrenom().equals("Dimitry"));
		log.debug("Sortie de la methode");
	}

	@Test
	public void testCreateUpdateDeleteUtilisateur() {
		ManualMapUtilisateurDao users;
		Utilisateur testUser;
		int nbUsers;

		testUser = new Utilisateur(3, "Mme", "Deuiu", "Jajer", "identifiant", "mdp",
								   new Date(System.currentTimeMillis()),
								   new ArrayList<Adresse>(
									   Arrays.asList(new Adresse(1, "5 rue du Paradis", "75000",
																 "Paris", "France", true, 1),
													 new Adresse(2, "61 rue des faveurs", "44000",
																 "Nantes", "France", 1))));
		users = new ManualMapUtilisateurDao();
		nbUsers = users.findAllUtilisateurs().size();

		log.debug("Entree de la methode");
		log.debug("ajout de l'utilisateur...");
		Assert.assertTrue(users.createUtilisateur(testUser).getIdUtilisateur().equals(29));
		Assert.assertTrue(users.findAllUtilisateurs().size() == (nbUsers + 1));
		log.debug("suppression de l'utilisateur");
		users.deleteUtilisateur(testUser);
		Assert.assertTrue(users.findAllUtilisateurs().size() == nbUsers);
		log.debug("Sortie de la methode");
	}

	@Test
	public void testCreateUpdateDeleteAdresse() {
		ManualMapAdresseDao addresses;
		Adresse testAddr;

		testAddr = new Adresse(1, "5 rue du Paradis", "75000", "Paris", "France", true, 1);
		addresses = new ManualMapAdresseDao();

		log.debug("Entree de la methode");
		log.debug("ajout de l'utilisateur...");
		// log.debug(addresses.createAdresse(testAddr).getIdAdresse());
		Assert.assertTrue(addresses.createAdresse(testAddr).getIdAdresse() == 83);
		/*
		 * log.debug(addresses.findAllAdresses().size());
		 * log.debug(DaoHelper.getAdressesMapDataSource().size());
		 */
		Assert.assertTrue(addresses.findAllAdresses().size() ==
						  (DaoHelper.getAdressesMapDataSource().size() + 1));
		log.debug("suppression de l'addresse...");
		addresses.deleteAdresse(testAddr);
		Assert.assertTrue(
			addresses.findAllAdresses().size() == DaoHelper.getAdressesMapDataSource().size());
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