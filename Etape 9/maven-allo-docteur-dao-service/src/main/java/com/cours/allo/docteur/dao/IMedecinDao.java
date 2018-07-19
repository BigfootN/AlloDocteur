package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Medecin;

/**
 * IMedecinDao
 */
public interface IMedecinDao {
	public List<Medecin> findAll();

	public Medecin findMedecinByNumeroAccreditation(String numeroAccreditation);

	public Medecin findMedecinByNumeroSecuriteSociale(String numeroSecuriteSociale);

	public Medecin findMedecinByNumeroTelephone(String numeroTelephone);

	public Medecin findMedecinByPwdAndId(String pwd, String email);

	public Medecin createMedecin(Medecin Medecin);

	public Medecin updateMedecin(Medecin Medecin);

	public boolean deleteMedecin(Medecin Medecin);

}