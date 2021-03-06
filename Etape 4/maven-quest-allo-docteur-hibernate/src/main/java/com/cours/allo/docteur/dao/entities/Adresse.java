/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author elhad
 */

@Entity
@Table(name="Adresse")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name="Adresse.findAll", query="SELECT u FROM Adresse u"),
        @NamedQuery(name="Adresse.findById", query="SELECT u FROM Adresse u WHERE u.idAdresse = :idAdresse"),
        @NamedQuery(name="Adresse.findByVille", query="SELECT u FROM Adresse u WHERE u.ville = :ville"),
        @NamedQuery(name="Adresse.findByCodePostal", query="SELECT u FROM Adresse u WHERE u.codePostal = :codePostal"),
})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idAdresse")
    //@Basic(optional=false)
    private Integer idAdresse;

    @Column(name="rue")
    private String rue;

    @Column(name="codePostal")
    private String codePostal;

    @Column(name="ville")
    private String ville;

    @Column(name="pays")
    private String pays;

    @Column(name="principale")
    private boolean principale;

    @Column(name="version")
    @Version
    private Integer version;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName="idUtilisateur", name="idUtilisateur")
    private Utilisateur addrOwner;

    public Adresse() {
    }

    public Adresse(String rue, String codePostal, String ville, String pays, Integer idUtilisateur) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        //this.idUtilisateur = idUtilisateur;
    }

    public Adresse(String rue, String codePostal, String ville, String pays) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Adresse(String rue, String codePostal, String ville, String pays, Boolean principale) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.principale = principale;
    }

    public Adresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Adresse(String rue, String codePostal, String ville, String pays, Utilisateur userCRUD) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.addrOwner = userCRUD;
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

    public Boolean getPrincipale() {
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
    
    public Utilisateur getAddrOwner() {
		return addrOwner;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("\n[idAdresse=%s , rue=%s , ville=%s , codePostal=%s , pays=%s , principale=%s , version=%s, idUtilisateur=%s]\n", idAdresse, rue, ville, codePostal, pays, principale, version, addrOwner.getIdUtilisateur());
    }

}
