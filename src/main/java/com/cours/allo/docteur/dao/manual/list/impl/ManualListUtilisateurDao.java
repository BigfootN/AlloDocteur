/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.list.impl;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.exception.CustomException;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.utils.DaoHelper;

import java.util.ArrayList;
import java.util.Date;
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
	public static List<Utilisateur> listUtilisateursOfDataSource = DaoHelper.getUtilisateursListDataSource();

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		List<Utilisateur> ret;

		log.debug("Entree de la methode");
		ret = listUtilisateursOfDataSource;
		log.debug("Sortie de la methode");

		return ret;
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) throws CustomException {
		Iterator<Utilisateur> usersIt;
		Utilisateur curUser;

		usersIt = listUtilisateursOfDataSource.iterator();

		while (usersIt.hasNext()) {
			curUser = usersIt.next();

			if (curUser.getIdUtilisateur().equals(idUtilisateur))
				return curUser;
		}

		throw new CustomException("L'utilisateur portant l'idUtilisateur " + idUtilisateur + " n'existe pas",
				CustomException.FIND_ERROR);
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) throws CustomException {
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

		if (ret.size() == 0) {
			throw new CustomException("L'utilisateur portant le prenom " + prenom + " n'existe pas",
					CustomException.FIND_ERROR);
		}

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) throws CustomException {
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

		if (ret.size() == 0) {
			throw new CustomException("Il n'existe aucun utilisateur portant le nom " + nom,
					CustomException.FIND_ERROR);
		}

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) throws CustomException {
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

		if (ret.size() == 0) {
			throw new CustomException("Il n'existe aucun utilisateur ayant comme adresse postale " + codePostal,
					CustomException.FIND_ERROR);
		}

		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) throws CustomException {
		Utilisateur lastUser;
		Iterator<Utilisateur> it;

		lastUser = listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1);
		it = listUtilisateursOfDataSource.iterator();

		while (it.hasNext()) {
			if (it.next().getIdentifiant().equals(user.getIdentifiant()))
				throw new CustomException(
						"L'utilisateur portant l'identitifiant " + user.getIdentifiant() + " existe deja",
						CustomException.CREATE_ERROR);
		}

		listUtilisateursOfDataSource.add(user);
		listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1)
				.setIdUtilisateur(lastUser.getIdUtilisateur() + 1);
		listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1).setDateModification(new Date());
		listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1).setDateCreation(new Date());

		return listUtilisateursOfDataSource.get(listUtilisateursOfDataSource.size() - 1);
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
				user.setDateModification(new Date());
				user.setVersion(user.getVersion() + 1);
				usersIt.set(user);

				ret = user;
				break;
			}
		}

		if (ret == null)
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas",
					CustomException.UPDTAE_ERROR);

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		if (listUtilisateursOfDataSource.remove(user)) {
			return true;
		} else {
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas",
					CustomException.FIND_ERROR);
		}
	}

}