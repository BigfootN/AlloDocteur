/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author elhad
 */
public class Utilisateur {

	private static final long serialVersionUID = 1L;
	private Integer idUtilisateur;
	private String civilite;
	private String prenom;
	private String nom;
	private String identifiant;
	private String motPasse;
	private Date dateNaissance;
	private Date dateCreation;
	private Date dateModification;
	private Boolean actif = true;
	private Boolean marquerEffacer = false;
	private Integer version = 0;
	private List<Adresse> adresses;

	public Utilisateur() {}

	public Utilisateur(Integer idUtilisateur,
					   String civilite,
					   String prenom,
					   String nom,
					   String identifiant,
					   String motPasse,
					   Date dateCreation,
					   Date dateModification,
					   Boolean actif,
					   Boolean marquerEffacer,
					   Integer version,
					   List<Adresse> adresses) {
		this.idUtilisateur = idUtilisateur;
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
		this.identifiant = identifiant;
		this.motPasse = motPasse;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.actif = actif;
		this.marquerEffacer = marquerEffacer;
		this.version = version;
		this.adresses = adresses;

	}

	public Utilisateur(Integer idUtilisateur,
					   String civilite,
					   String prenom,
					   String nom,
					   String identifiant,
					   String motPasse,
					   Date dateNaissance,
					   Date dateCreation,
					   Date dateModification,
					   Boolean actif,
					   Boolean marquerEffacer,
					   Integer version,
					   List<Adresse> adresses) {
		this.idUtilisateur = idUtilisateur;
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
		this.identifiant = identifiant;
		this.motPasse = motPasse;
		this.dateNaissance = dateNaissance;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.actif = actif;
		this.marquerEffacer = marquerEffacer;
		this.version = version;
		this.adresses = adresses;

		Iterator<Adresse> it;
	}

	public Utilisateur(Integer idUtilisateur,
					   String civilite,
					   String prenom,
					   String nom,
					   String identifiant,
					   String motPasse,
					   Date dateNaissance,
					   Date dateCreation,
					   Date dateModification,
					   Boolean actif,
					   Boolean marquerEffacer,
					   Integer version) {
		this.idUtilisateur = idUtilisateur;
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
		this.identifiant = identifiant;
		this.motPasse = motPasse;
		this.dateNaissance = dateNaissance;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.actif = actif;
		this.marquerEffacer = marquerEffacer;
		this.version = version;
	}

	public Utilisateur(String civilite,
					   String prenom,
					   String nom,
					   String identifiant,
					   String motPasse,
					   Date dateNaissance) {
		this.civilite = civilite;
		this.prenom = prenom;
		this.nom = nom;
		this.identifiant = identifiant;
		this.motPasse = motPasse;
		this.dateNaissance = dateNaissance;
	}

	public Utilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Boolean isActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Boolean isMarquerEffacer() {
		return marquerEffacer;
	}

	public void setMarquerEffacer(Boolean marquerEffacer) {
		this.marquerEffacer = marquerEffacer;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public String toJson() {
		JsonArray array;
		DateFormat df;
		Adresse addrMain;
		Gson gson;

		df = new SimpleDateFormat("dd/MM/yyyy");
		array = new JsonArray();
		addrMain = getAdressePrincipale();

		array.add(new JsonPrimitive(nom));
		array.add(new JsonPrimitive(prenom));
		array.add(new JsonPrimitive(civilite));
		array.add(new JsonPrimitive(addrMain.getRue() + "," + addrMain.getCodePostal() + "," +
									addrMain.getPays()));

		gson = new Gson();

		return gson.toJson(array);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Utilisateur)) {
			return false;
		}
		Utilisateur other = (Utilisateur) object;
		if ((this.idUtilisateur == null && other.idUtilisateur != null)
			|| (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
			"\n[idUtilisateur=%s, civilite=%s, prenom=%s, nom=%s, identifiant=%s, motPasse=%s, dateNaissance=%s, dateCreation=%s, dateModification=%s, actif=%s, marquerEffacer=%s ,version=%s]\n",
			idUtilisateur,
			civilite,
			prenom,
			nom,
			identifiant,
			motPasse,
			dateNaissance,
			dateCreation,
			dateModification,
			actif,
			marquerEffacer,
			version);
	}

	private Adresse getAdressePrincipale() {
		Iterator<Adresse> it;
		Adresse curAddr;

		it = adresses.iterator();

		while (it.hasNext()) {
			curAddr = (Adresse) it;
			if (curAddr.isPrincipale())
				return curAddr;
		}

		return null;
	}

}