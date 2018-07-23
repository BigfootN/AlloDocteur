package com.cours.allo.docteur.dao;

import java.time.LocalTime;
import java.util.List;

import com.cours.allo.docteur.dao.entities.RendezVous;

/**
 * IRendezVousDao
 */
public interface IRendezVousDao {

	public List<RendezVous> findAll();

	public RendezVous findRendezVousById(Integer idRendezVous);

	public List<RendezVous> findRendezVousByJour(LocalTime time);

	public Long chiffreAffaire();

	public boolean deleteRendezVous(RendezVous appt);

	public RendezVous updateRendezVous(RendezVous appt);

	public RendezVous createRendezVous(RendezVous appt);
}