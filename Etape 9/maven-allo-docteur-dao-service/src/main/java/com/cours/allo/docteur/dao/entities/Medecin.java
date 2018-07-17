package com.cours.allo.docteur.dao.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Medecin")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
		@NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
		@NamedQuery(name = "Medecin.findByNumeroAccreditation", query = "SELECT m FROM Medecin m WHERE m.numeroAccreditation = :numeroAccreditation"),
		@NamedQuery(name = "Medecin.findByNumeroTelephone", query = "SELECT m FROM Medecin m WHERE m.numeroTelephone = :numeroTelephone") })
public class Medecin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMedecin")
	private Integer idMedecin;

	@Column(name = "civilite")
	private String prenom;

	@Column(name = "numeroTelephone")
	private String numeroTelephone;

	@Column(name = "numeroAccreditation")
	private String numeroAccreditation;

	@Column(name = "version")
	@Version
	private int version;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idUtilisateur", name = "idUtilisateur")
	private Utilisateur userDoctor;
}