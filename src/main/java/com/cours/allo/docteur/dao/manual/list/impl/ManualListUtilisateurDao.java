/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.list.impl;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.utils.DaoHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
	public static List<Utilisateur> listUtilisateursOfDataSource =
		DaoHelper.getUtilisateursListDataSource();

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		List<Utilisateur> ret;

		log.debug("Entree de la methode");
		ret = listUtilisateursOfDataSource;
		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		Iterator<Utilisateur> usersIt;
		Utilisateur curUser;

		usersIt = listUtilisateursOfDataSource.iterator();

		while (usersIt.hasNext()) {
			curUser = usersIt.next();

			if (curUser.getIdUtilisateur() == idUtilisateur)
				return curUser;
		}

		return null;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		Iterator<Utilisateur> usersIt;
		List<Utilisateur> ret;
		Utilisateur curUser;

		usersIt = listUtilisateursOfDataSource.iterator();
		ret = new ArrayList<>();

		while (usersIt.hasNext()) {
			curUser = usersIt.next();

			if (curUser.getPrenom().equals(prenom))
				ret.add(curUser);
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		Iterator<Utilisateur> usersIt;
		List<Utilisateur> ret;
		Utilisateur curUser;

		usersIt = listUtilisateursOfDataSource.iterator();
		ret = new ArrayList<>();

		while (usersIt.hasNext()) {
			curUser = usersIt.next();

			if (curUser.getNom().equals(nom))
				ret.add(curUser);
		}

		if (ret.size() == 0)
			ret = null;

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
		Iterator<Utilisateur> usersIt;
		Iterator<Adresse> addrIt;
		List<Utilisateur> ret;
		boolean userHasPostalCode;
		Utilisateur curUser;

		usersIt = listUtilisateursOfDataSource.iterator();
		ret = new ArrayList<>();

		while (usersIt.hasNext()) {
			curUser = usersIt.next();
			addrIt = curUser.getAdresses().iterator();
			userHasPostalCode = false;

			while (addrIt.hasNext() && userHasPostalCode == false) {
				userHasPostalCode = addrIt.next().getCodePostal().equals(codePostal);

				if (userHasPostalCode) {
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
		Utilisateur ret;
		Utilisateur lastUser;

		lastUser = listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1);

		ret = new Utilisateur(lastUser.getIdUtilisateur() + 1,
							  user.getCivilite(),
							  user.getPrenom(),
							  user.getNom(),
							  user.getIdentifiant(),
							  user.getMotPasse(),
							  user.getDateNaissance(),
							  user.isActif(),
							  user.isMarquerEffacer(),
							  user.getAdresses());

		ret.setVersion(user.getVersion() + 1);
		listUtilisateursOfDataSource.add(ret);

		return ret;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		ListIterator<Utilisateur> usersIt;
		Utilisateur curUser;
		Utilisateur ret;

		usersIt = listUtilisateursOfDataSource.listIterator();
		ret = null;

		while (usersIt.hasNext()) {
			curUser = usersIt.next();

			if (curUser.getIdUtilisateur() == user.getIdUtilisateur()) {
				user.setVersion(user.getVersion() + 1);
				usersIt.set(user);
				ret = user;
				break;
			}
		}

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		return listUtilisateursOfDataSource.remove(user);
	}

}