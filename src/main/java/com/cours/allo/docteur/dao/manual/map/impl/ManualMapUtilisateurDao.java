/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.map.impl;

import java.util.ArrayList;
import java.util.Date;
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
	public Utilisateur findUtilisateurById(int idUtilisateur) throws CustomException {
		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdUtilisateur().equals(idUtilisateur))
				return entry.getValue();
		}

		throw new CustomException("L'utilisateur portant l'idUtilisateur " + idUtilisateur + " n'existe pas",
				CustomException.FIND_ERROR);
	}

	@Override
	public List<Utilisateur> findUtilisateursByPrenom(String prenom) throws CustomException {
		List<Utilisateur> ret;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getPrenom().equals(prenom))
				ret.add(entry.getValue());
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le prenom " + prenom + " sont introuvable",
					CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByNom(String nom) throws CustomException {
		List<Utilisateur> ret;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getNom().equals(nom))
				ret.add(entry.getValue());
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le nom " + nom + " sont introuvable",
					CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal) throws CustomException {
		List<Utilisateur> ret;
		Iterator<Adresse> addresseIt;

		ret = new ArrayList<>();

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			addresseIt = entry.getValue().getAdresses().iterator();

			while (addresseIt.hasNext()) {
				if (addresseIt.next().getCodePostal().equals(codePostal)) {
					ret.add(entry.getValue());
					break;
				}
			}
		}

		if (ret.size() == 0)
			throw new CustomException("Les utilisateurs portant le codePostal " + codePostal + " sont introuvable",
					CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur user) {
		Integer newIdUser;
		Integer startIdAddr;
		Iterator<Adresse> itAddr;

		newIdUser = 0;
		startIdAddr = 0;

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdUtilisateur() > newIdUser)
				newIdUser = entry.getValue().getIdUtilisateur();
		}

		startIdAddr = user.getAdresses().get(user.getAdresses().size() - 1).getIdAdresse() + 1;
		itAddr = user.getAdresses().listIterator();

		while (itAddr.hasNext()) {
			itAddr.next().setIdAdresse(startIdAddr);
			startIdAddr++;
		}

		user.setDateCreation(new Date());
		user.setDateModification(new Date());
		user.setIdUtilisateur(newIdUser + 1);

		mapUtilisateursOfDataSource.put(newIdUser + 1, user);

		return user;
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user) throws CustomException {
		Utilisateur ret;

		ret = null;

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().getIdUtilisateur().equals(user.getIdUtilisateur())) {
				user.setVersion(user.getVersion() + 1);
				user.setDateModification(new Date());

				entry.setValue(user);
				ret = user;
				break;
			}
		}
		if (ret == null) {
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas",
					CustomException.UPDTAE_ERROR);
		}

		return ret;
	}

	@Override
	public boolean deleteUtilisateur(Utilisateur user) throws CustomException {
		boolean ret;

		ret = false;

		for (Map.Entry<Integer, Utilisateur> entry : mapUtilisateursOfDataSource.entrySet()) {
			if (entry.getValue().equals(user)) {
				mapUtilisateursOfDataSource.remove(entry.getKey(), entry.getValue());
				ret = true;
				break;
			}
		}

		if (ret == false) {
			throw new CustomException("L'utilisateur portant l'identifiant " + user.getIdentifiant() + " n'existe pas",
					CustomException.FIND_ERROR);
		}

		return ret;
	}

}