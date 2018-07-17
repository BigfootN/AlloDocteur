package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Creneau;

/**
 * ICreneauDao
 */
public interface ICreneauDao {
	public List<Creneau> findAll();

	public Creneau findCreneauByHeureDebut(Integer heureDebut);

	public Creneau findCreneauByHeureFin(Integer heureFin);

	public Creneau findCreneauByMinuteDebut(Integer minuteDebut);

	public Creneau findCreneauByMinuteFin(Integer minuteFin);

	public Creneau createCreateCreneau(Creneau creneau);

	public boolean deleteCreneau(Creneau creneau);

	public Creneau updateCreneau(Creneau creneau);
}