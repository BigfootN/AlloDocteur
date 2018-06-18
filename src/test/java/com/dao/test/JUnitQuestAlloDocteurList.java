package com.dao.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListAdresseDao;
import com.cours.allo.docteur.dao.manual.list.impl.ManualListUtilisateurDao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.DaoHelper;

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
		ManualListUtilisateurDao users;

		users = new ManualListUtilisateurDao();

		log.debug("Entree de la methode");
		Assert.assertTrue(users.findAllUtilisateurs().size() == DaoHelper.getUtilisateursListDataSource().size());
		log.debug("Sortie de la methode");
	}

	@Test
	public void testFindAllAdresses() {
		ManualListAdresseDao addresses;

		addresses = new ManualListAdresseDao();
		log.debug("Entree de la methode");
		Assert.assertTrue(addresses.findAllAdresses().size() == DaoHelper.getAdressesListDataSource().size());
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
		ManualListUtilisateurDao listUser;
		Utilisateur userTest;
		Utilisateur returnUser;

		listUser = new ManualListUtilisateurDao();
		userTest = new Utilisateur(1, "Mr", "Jean", "Dupoound", "id_dupond", "mdp", new Date(), true, true,
				Arrays.asList(new Adresse(1)));

		log.debug("Entree de la methode");
		returnUser = listUser.createUtilisateur(userTest);
		Assert.assertTrue(returnUser.getIdUtilisateur() == 29);
		Assert.assertTrue(listUser.findAllUtilisateurs().size() == 29);
		listUser.deleteUtilisateur(userTest);
		Assert.assertTrue(listUser.findAllUtilisateurs().size() == 28);
		log.debug("Sortie de la methode");
	}

	@Test
	public void testCreateUpdateDeleteAdresse() {
		ManualListAdresseDao listUser;
		Adresse addrTest;
		Adresse returnAddr;
		Integer size;

		listUser = new ManualListAdresseDao();
		addrTest = new Adresse(1, "Rue des pigeons", "38474", "Pigeon", "Pays");
		size = listUser.findAllAdresses().size();

		log.debug("Entree de la methode");
		returnAddr = listUser.createAdresse(addrTest);
		Assert.assertTrue(listUser.findAllAdresses().size() == (size + 1));
		listUser.deleteAdresse(addrTest);
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