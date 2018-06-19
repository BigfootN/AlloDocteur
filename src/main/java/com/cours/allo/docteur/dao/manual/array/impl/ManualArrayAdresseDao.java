/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.manual.array.impl;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.exception.CustomException;
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
public class ManualArrayAdresseDao implements IAdresseDao {

	private static final Log log = LogFactory.getLog(ManualArrayAdresseDao.class);
	public static Adresse[] arrayAdressesOfDataSource = DaoHelper.getAdressesArrayDataSource();

	@Override
	public List<Adresse> findAllAdresses() {
		String methodName = "findAllAdresses";
		log.debug("Entree de la methode");
		log.debug("Sortie de la methode");
		return Arrays.asList(arrayAdressesOfDataSource);
	}

	@Override
	public Adresse findAdresseById(int idAdresse) throws CustomException {
		Adresse ret;
		ret = null;

<<<<<<< HEAD
			if (curAdresse.getIdAdresse().equals(idAdresse)) {
				found = true;
				ret = curAdresse;
=======
		for(int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if(arrayAdressesOfDataSource[i].getIdAdresse().equals(idAdresse)){
				ret =arrayAdressesOfDataSource[i];
>>>>>>> 78d670dc0365b3378aa235358dc1d7f29a76d36c
			}
		}

		if (ret == null)
			throw new CustomException("Aucune adresse a l'" + idAdresse,
					CustomException.FIND_ERROR);

		return ret;
	}

	@Override
	public List<Adresse> findAdressesByVille(String ville) throws CustomException {

		Adresse[] ret = new Adresse[arrayAdressesOfDataSource.length];
		int size = 0;

		for(int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if(arrayAdressesOfDataSource[i].getVille().equals(ville)){
				ret[size] = arrayAdressesOfDataSource[i];
				size++;
			}
		}

		if (ret == null)
			throw new CustomException("Aucune adresse a l'" + ville,
					CustomException.FIND_ERROR);

		Adresse[] array = new Adresse[size];
		System.arraycopy(ret, 0, array, 0, array.length);

		return Arrays.asList(array);
	}

	@Override
	public List<Adresse> findAdressesByCodePostal(String codePostal) throws CustomException {

		Adresse[] ret = new Adresse[arrayAdressesOfDataSource.length];;
		int size = 0;

		for(int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if(arrayAdressesOfDataSource[i].getCodePostal().equals(codePostal)){
				ret[size] = arrayAdressesOfDataSource[i];
				size++;
			}
		}

		if (ret == null)
			throw new CustomException("Aucune adresse au '" + codePostal,
					CustomException.FIND_ERROR);

		Adresse[] array = new Adresse[size];
		System.arraycopy(ret, 0, array, 0, array.length);

		return Arrays.asList(array);
	}

	@Override
	public Adresse createAdresse(Adresse adresse) throws CustomException {

		/*for (int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if (arrayAdressesOfDataSource[i].getIdAdresse().equals(adresse.getIdAdresse())){
				throw new CustomException("Adresse existe deja",
						CustomException.CREATE_ERROR);
			}
		}*/

		Adresse[] tmp = new Adresse[arrayAdressesOfDataSource.length + 1];

		System.arraycopy(arrayAdressesOfDataSource, 0, tmp, 0, arrayAdressesOfDataSource.length);
		int lastId;

		lastId = arrayAdressesOfDataSource[arrayAdressesOfDataSource.length - 1].getIdAdresse();

		adresse.setIdAdresse(lastId + 1);

		tmp[arrayAdressesOfDataSource.length] = adresse;

		arrayAdressesOfDataSource = tmp.clone();

		return adresse;
	}

	@Override
	public Adresse updateAdresse(Adresse adresse) {

		for (int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if (arrayAdressesOfDataSource[i].getIdAdresse().equals(adresse.getIdAdresse())){
				adresse.setVersion(adresse.getVersion() + 1);
				arrayAdressesOfDataSource[i] = adresse;
			}
		}

		return adresse;
	}

	@Override
	public boolean deleteAdresse(Adresse adresse) {

		Adresse[] tmp = new Adresse[arrayAdressesOfDataSource.length - 1];

		boolean find = false;

		int j = 0;

		for (int i = 0; i < arrayAdressesOfDataSource.length; i++){
			if (arrayAdressesOfDataSource[i].getIdAdresse().equals(adresse.getIdAdresse())){
				find = true;
			}else {
				tmp[j] = arrayAdressesOfDataSource[i];
				j++;
			}
		}

		arrayAdressesOfDataSource = tmp.clone();

		return find;
	}

}