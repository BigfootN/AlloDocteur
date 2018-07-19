package com.cours.allo.docteur.dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Medecin")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
		@NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
		@NamedQuery(name = "Medecin.findByNumeroAccreditation", query = "SELECT m FROM Medecin m WHERE m.numeroAccreditation = :numeroAccreditation"),
		@NamedQuery(name = "Medecin.findByNumeroTelephone", query = "SELECT m FROM Medecin m WHERE m.numeroTelephone = :numeroTelephone"),
		@NamedQuery(name = "Medecin.findByMdpIdentifiant", query = "SELECT m FROM Medecin m INNER JOIN m.userDoctor u WHERE u.identifiant = :identifiant AND u.motPasse = :motPasse") })
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

	@OneToMany(mappedBy = "doctorCreneau", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Creneau> creneaux = new ArrayList<>();
}