package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Patient;

/**
 * IPatientDao
 */
public interface IPatientDao {
	public List<Patient> findAll();

	public Patient findPatientByNumeroSecuriteSocial(String numeroSecuriteSocial);

	public Patient findPatientByNumeroTelephone(String numeroTelephone);

	public Patient createPatient(Patient patient);

	public Patient updatePatient(Patient patient);

	public boolean deletePatient(Patient patient);
}