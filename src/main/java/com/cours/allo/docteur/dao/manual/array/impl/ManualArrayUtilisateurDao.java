/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.array.impl;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.utils.DaoHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualArrayUtilisateurDao implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(ManualArrayUtilisateurDao.class);
	public static Utilisateur[] arrayUtilisateursOfDataSource =
		DaoHelper.getUtilisateursArrayDataSource();

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		log.debug("Entree de la methode");
		log.debug("Sortie de la methode");
		return Arrays.asList(arrayUtilisateursOfDataSource);
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		List<Utilisateur> tmp;
		boolean found;
		Utilisateur ret;
		Utilisateur curUser;
		Iterator<Utilisateur> it;

		ret = null;
		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		found = false;
		it = tmp.iterator();

		while (it.hasNext() && !found) {
			curUser = it.next();

			if (curUser.getIdUtilisateur() == idUtilisateur) {
				found = true;
				ret = curUser;
			}
		}

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		List<Utilisateur> tmp;
		List<Utilisateur> ret;
		Utilisateur curUser;
		Iterator<Utilisateur> it;

		ret = new ArrayList<>();
		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		it = tmp.iterator();

		while (it.hasNext()) {
			curUser = it.next();

			if (curUser.getPrenom().equals(prenom)) {
				ret.add(curUser);
			}
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		List<Utilisateur> tmp;
		List<Utilisateur> ret;
		Utilisateur curUser;
		Iterator<Utilisateur> it;

		ret = new ArrayList<>();
		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		it = tmp.iterator();

		while (it.hasNext()) {
			curUser = it.next();

			if (curUser.getNom().equals(nom)) {
				ret.add(curUser);
			}
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
		List<Utilisateur> tmp;
		List<Utilisateur> ret;
		Utilisateur curUser;
		Iterator<Utilisateur> itUser;
		Iterator<Adresse> itAddr;
		boolean addrFound;

		ret = new ArrayList<>();
		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		itUser = tmp.iterator();

		while (itUser.hasNext()) {
			curUser = itUser.next();
			itAddr = curUser.getAdresses().iterator();
			addrFound = false;

			while (itAddr.hasNext() && !addrFound) {
				if (itAddr.next().getCodePostal().equals(codePostal)) {
					addrFound = true;
					ret.add(curUser);
				}
			}
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		List<Utilisateur> tmp;
		int size;
		int lastId;

		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		size = tmp.size();
		lastId = tmp.get(size - 1).getIdUtilisateur();
		user.setIdUtilisateur(lastId + 1);
		tmp.add(user);

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		List<Utilisateur> tmp;
		boolean found;
		Utilisateur ret;
		Utilisateur curUser;
		ListIterator<Utilisateur> it;

		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		ret = null;
		found = false;
		it = tmp.listIterator();

		while (it.hasNext() && !found) {
			curUser = it.next();

			if (curUser.getIdUtilisateur() == user.getIdUtilisateur()) {
				it.set(user);
				found = true;
				ret = user;
			}
		}

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		List<Utilisateur> tmp;
		boolean ret;
		Utilisateur curUser;
		ListIterator<Utilisateur> it;

		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		ret = false;
		it = tmp.listIterator();

		while (it.hasNext() && !ret) {
			curUser = it.next();

			if (curUser.getIdUtilisateur() == user.getIdUtilisateur()) {
				it.remove();
				ret = true;
			}
		}

		return ret;
	}

}