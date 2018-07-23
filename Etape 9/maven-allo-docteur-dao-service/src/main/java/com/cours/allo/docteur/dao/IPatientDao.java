package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Patient;

/**
 * IPatientDao
 */
public interface IPatientDao {
	public List<Patient> findAll();

	public Patient findPatientById(String idPatient);

	public Patient findPatientByNumeroSecuriteSocial(String numeroSecuriteSocial);

	public Patient findPatientByNumeroTelephone(String numeroTelephone);

	public List<Patient> findPatientsByNom(String nom);

	public List<Patient> findPatientsByPrenom(String prenom);

	public Patient createPatient(Patient patient);

	public Patient updatePatient(Patient patient);

	public boolean deletePatient(Patient patient);
}