/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import java.util.List;

import com.cours.allo.docteur.dao.entities.Utilisateur;

/**
 *
 * @author ElHadji
 */
public interface IUtilisateurDao {

	public List<Utilisateur> findAllUtilisateurs();

	public Utilisateur findUtilisateurById(int idUtilisateur);

	public Utilisateur authenticate(String identifiant, String pwd);

	public List<Utilisateur> findUtilisateursByPrenom(String prenom);

	public List<Utilisateur> findUtilisateursByNom(String nom);

	public List<Utilisateur> findUtilisateursByCodePostal(String codePostal);

	public Utilisateur createUtilisateur(Utilisateur user);

	public Utilisateur updateUtilisateur(Utilisateur user);

	public boolean deleteUtilisateur(Utilisateur user);

	public void deleteUtilisateur(int id);
}