package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Creneau;

/**
 * ICreneauDao
 */
public interface ICreneauDao {
	public List<Creneau> findAll();

	public List<Creneau> findCreneauxByHeureDebut(Integer heureDebut);

	public List<Creneau> findCreneauxByHeureFin(Integer heureFin);

	public List<Creneau> findCreneauxByMinuteDebut(Integer minuteDebut);

	public List<Creneau> findCreneauxByMinuteFin(Integer minuteFin);

	public Creneau createCreneau(Creneau creneau);

	public boolean deleteCreneau(Creneau creneau);

	public Creneau updateCreneau(Creneau creneau);
}