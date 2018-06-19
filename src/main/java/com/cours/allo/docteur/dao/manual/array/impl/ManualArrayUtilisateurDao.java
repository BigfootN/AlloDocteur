/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.array.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.exception.CustomException;
import com.cours.allo.docteur.utils.DaoHelper;

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
	public Utilisateur findUtilisateurById(int idUtilisateur) throws CustomException {
		Utilisateur ret;
		int idx;
		int size;

		size = arrayUtilisateursOfDataSource.length;
		idx = 0;
		ret = null;

		while (idx < size && ret == null) {
			if (arrayUtilisateursOfDataSource[idx].getIdUtilisateur().equals(idUtilisateur))
				ret = arrayUtilisateursOfDataSource[idx];

			idx++;
		}

		if (ret == null)
			throw new CustomException(
					  "L'utilisateur avec l'id " + idUtilisateur + " n'a pas pu etre trouve",
					  CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) throws CustomException {
		Utilisateur[] tmp;
		Utilisateur[] ret;
		int idx;
		int size;

		size = 0;
		idx = 0;
		tmp = new Utilisateur[arrayUtilisateursOfDataSource.length];

		while (idx < arrayUtilisateursOfDataSource.length) {
			if (arrayUtilisateursOfDataSource[idx].getPrenom().equals(prenom)) {
				tmp[size] = arrayUtilisateursOfDataSource[idx];
				size++;
			}

			idx++;
		}

		if (size == 0)
			throw new CustomException(
					  "L'utilisateur portant le prenom " + prenom + " n'a pas pu etre trouve",
					  CustomException.FIND_ERROR);

		ret = new Utilisateur[size];
		System.arraycopy(tmp, 0, ret, 0, size);

		return Arrays.asList(ret);
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) throws CustomException {
		Utilisateur[] tmp;
		Utilisateur[] ret;
		int idx;
		int size;

		size = 0;
		idx = 0;
		tmp = new Utilisateur[arrayUtilisateursOfDataSource.length];

		while (idx < arrayUtilisateursOfDataSource.length) {
			if (arrayUtilisateursOfDataSource[idx].getNom().equals(nom)) {
				tmp[size] = arrayUtilisateursOfDataSource[idx];
				size++;
			}

			idx++;
		}

		if (size == 0)
			throw new CustomException(
					  "L'utilisateur portant le nom " + nom + " n'a pas pu etre trouve",
					  CustomException.FIND_ERROR);

		ret = new Utilisateur[size];
		System.arraycopy(tmp, 0, ret, 0, size);

		return Arrays.asList(ret);
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) throws CustomException
	{
		Utilisateur[] tmp;
		Utilisateur[] ret;
		Iterator<Adresse> itAddr;
		boolean addrFound;
		int idx;
		int sizeTmp;
		int sizeArray;

		sizeTmp = 0;
		idx = 0;
		sizeArray = arrayUtilisateursOfDataSource.length;
		tmp = new Utilisateur[arrayUtilisateursOfDataSource.length];

		while (idx < sizeArray) {
			addrFound = false;
			itAddr = arrayUtilisateursOfDataSource[idx].getAdresses().iterator();

			while (itAddr.hasNext() && !addrFound) {
				if (itAddr.next().getCodePostal().equals(codePostal)) {
					tmp[sizeTmp] = arrayUtilisateursOfDataSource[idx];
					sizeTmp++;
					addrFound = true;
				}
			}

			idx++;
		}

		if (sizeTmp == 0)
			throw new CustomException(
					  "L'utilisateur habitant a " + codePostal + " n'a pas pu etre trouve",
					  CustomException.FIND_ERROR);

		ret = new Utilisateur[sizeTmp];
		System.arraycopy(tmp, 0, ret, 0, sizeTmp);

		return Arrays.asList(ret);
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Utilisateur[] ret;
		int lastId;
		int idx;
		int size;

		idx = 0;
		size = arrayUtilisateursOfDataSource.length;

		lastId =
			arrayUtilisateursOfDataSource[arrayUtilisateursOfDataSource.length -
										  1].getIdUtilisateur();

		ret = new Utilisateur[arrayUtilisateursOfDataSource.length + 1];
		System.arraycopy(arrayUtilisateursOfDataSource,
						 0,
						 ret,
						 0,
						 arrayUtilisateursOfDataSource.length);

		user.setIdUtilisateur(lastId + 1);
		user.setDateModification(new Date());
		user.setDateCreation(new Date());

		ret[arrayUtilisateursOfDataSource.length] = user;

		arrayUtilisateursOfDataSource = ret.clone();

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) throws CustomException {
		List<Utilisateur> tmp;
		boolean found;
		Utilisateur ret;
		Utilisateur curUser;
		ListIterator<Utilisateur> it;
		int idx;
		int size;

		idx = 0;
		tmp = Arrays.asList(arrayUtilisateursOfDataSource);
		ret = null;
		found = false;
		it = tmp.listIterator();
		size = arrayUtilisateursOfDataSource.length;

		while (idx < size && !found) {
			if (arrayUtilisateursOfDataSource[idx].getIdUtilisateur().equals(user.getIdUtilisateur()))
			{
				arrayUtilisateursOfDataSource[idx] = user;
				arrayUtilisateursOfDataSource[idx].setVersion(user.getVersion() + 1);
				arrayUtilisateursOfDataSource[idx].setDateModification(new Date());

				found = true;
			}

			idx++;
		}

		if (!found) {
			throw new CustomException(
					  "L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas",
					  CustomException.UPDTAE_ERROR);
		}

		return arrayUtilisateursOfDataSource[idx--];
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) throws CustomException {
		int idxArray;
		int idxTmp;
		int size;
		Utilisateur[] tmp;
		boolean found;

		found = false;
		idxArray = 0;
		idxTmp = 0;
		size = arrayUtilisateursOfDataSource.length;
		tmp = new Utilisateur[size - 1];

		while (idxArray < size) {
			if (arrayUtilisateursOfDataSource[idxArray].getIdUtilisateur().equals(user.
																				  getIdUtilisateur()))
			{
				found = true;
			} else {
				tmp[idxTmp] = arrayUtilisateursOfDataSource[idxArray];
				idxTmp++;
			}

			idxArray++;
		}

		arrayUtilisateursOfDataSource = new Utilisateur[size - 1];
		System.arraycopy(tmp, 0, arrayUtilisateursOfDataSource, 0, size - 1);

		return found;
	}

}