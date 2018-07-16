package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Medecin;

/**
 * IMedecinDao
 */
public interface IMedecinDao {
    public List<Medecin> findAll();
}