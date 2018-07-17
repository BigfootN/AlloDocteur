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
@Table(name = "Patient")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
				@NamedQuery(name = "Patient.findByNumeroSecuriteSociale",
							query =
								"SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :numeroSecuriteSociale"),
				@NamedQuery(name = "Patient.findByNumeroTelephone",
							query =
								"SELECT p FROM Patient p WHERE p.numeroTelephone = :numeroTelephone") })
public class Patient {
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idMedecin", name = "idMedecin")
	private Medecin doctor;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPatient")
	private Integer idPatient;

	@Column(name = "numeroSecuriteSociale")
	private String numeroSecuriteSociale;

	@Column(name = "numeroTelephone")
	private String numeroTelephone;

	@Column(name = "version")
	@Version
	private Integer version;

	@OneToMany(mappedBy = "rdvPatient", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.EAGER)
	private List<RendezVous> rendezVous = new ArrayList<>();

	@OneToMany(mappedBy = "rdvPatient", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.EAGER)
	private List<Creneau> creneaux = new ArrayList<>();
}