/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.map.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class ManualMapUtilisateurDao implements IUtilisateurDao {

	private static final Log log = LogFactory.getLog(ManualMapUtilisateurDao.class);
	public static Map<Integer, Utilisateur> mapUtilisateursOfDataSource = DaoHelper.getUtilisateursMapDataSource();

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		return new ArrayList<Utilisateur>(mapUtilisateursOfDataSource.values());
	}

	@Override
	public Utilisateur findUtilisateurById(int idUtilisateur) {
		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdUtilisateur() == idUtilisateur)
				return entry.getValue();
		}

		throw new CustomException("L'utilisateur portant l'idUtilisateur " + idUtilisateur + " n'existe pas", CustomException.FIND_ERROR);
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
		List<Utilisateur> ret;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getPrenom() == prenom)
				ret.add(entry.getValue());
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le prenom " + prenom + " sont introuvable", CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) {
		List<Utilisateur> ret;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getNom() == nom)
				ret.add(entry.getValue());
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le nom " + nom + " sont introuvable", CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) {
		List<Utilisateur> ret;
		Iterator<Adresse> addresseIt;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			addresseIt = entry.getValue().getAdresses().iterator();

			while (addresseIt.hasNext()) {
				if (addresseIt.next().getCodePostal() == codePostal) {
					ret.add(entry.getValue());
					break;
				}
			}
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le codePostal " + codePostal + " sont introuvable", CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Utilisateur ret;
		Utilisateur lastUser;
		Integer newId;


		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdentifiant() == user.getIdentifiant())
				throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " existe deja", CustomException.INSERT_ERROR);
		}

		newId = mapUtilisateursOfDataSource.size() + 1;

		ret = new Utilisateur(newId, user.getCivilite(), user.getPrenom(), user.getNom(), user.getIdentifiant(),
				user.getMotPasse(), user.getDateNaissance(), user.isActif(), user.isMarquerEffacer(),
				user.getAdresses());

		ret.setIdUtilisateur(newId);
		ret.setVersion(user.getVersion() + 1);

		mapUtilisateursOfDataSource.put(newId, ret);

		return ret;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) {
		Utilisateur ret;

		ret = null;

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdUtilisateur() == user.getIdUtilisateur()) {
				user.setVersion(user.getVersion() + 1);
				entry.setValue(user);
				ret = user;
				break;
			}
		}
		if (ret == null){
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas", CustomException.UPDTAE_ERROR);
		}

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) {
		boolean ret;

		ret = false;

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().equals(user)) {
				mapUtilisateursOfDataSource.remove(entry.getKey(), entry.getValue());
				ret = true;
				break;
			}
		}

		if (ret == false){
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas", CustomException.FIND_ERROR);
		}


		return ret;
	}

}