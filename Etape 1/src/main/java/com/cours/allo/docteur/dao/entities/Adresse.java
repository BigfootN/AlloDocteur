/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

/**
 *
 * @author elhad
 */
public class Adresse {

    private static final long serialVersionUID = 1L;
    private Integer idAdresse;
    private String rue;
    private String codePostal;
    private String ville;
    private String pays;
    private Boolean principale = false;
    private Integer version = 0;
    private Integer idUtilisateur;

    public Adresse() {
    }

    public Adresse(Integer idAdresse, String rue, String codePostal, String ville, String pays, Boolean principale, Integer idUtilisateur) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.principale = principale;
        this.idUtilisateur = idUtilisateur;
    }

    public Adresse(Integer idAdresse, String rue, String codePostal, String ville, String pays) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Adresse(Integer idAdresse, String rue, String codePostal, String ville, String pays, Integer idUtilisateur) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.idUtilisateur = idUtilisateur;
    }

    public Adresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Boolean isPrincipale() {
        return principale;
    }

    public void setPrincipale(Boolean principale) {
        this.principale = principale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Adresse)) {
            return false;
        }

        Adresse adresse = (Adresse) obj;

        if(adresse.idAdresse != this.idAdresse) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String ret;

        ret = "idAdresse = " + this.idAdresse.toString() + ",";
        ret += "rue = " + this.rue + ",";
        ret += "code postal = " + this.codePostal + ",";
        ret += "ville = " + this.ville + ",";
        ret += "pays = " + this.pays + ",";
        ret += "principale = " + this.principale.toString() + ",";
        ret += "version = " + this.version.toString() + ",";
        ret += "idUtilisateur = " + this.idUtilisateur.toString();

        return ret;
    }

    @Override
    public int hashCode() {
        return this.idAdresse;
    }
}
