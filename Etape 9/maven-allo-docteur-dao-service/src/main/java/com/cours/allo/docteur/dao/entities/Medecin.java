package com.cours.allo.docteur.dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
				@NamedQuery(name = "Medecin.findById",
							query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
				@NamedQuery(name = "Medecin.findByNumeroAccreditation",
							query =
								"SELECT m FROM Medecin m WHERE m.numeroAccreditation = :numeroAccreditation"),
				@NamedQuery(name = "Medecin.findByNumeroTelephone",
							query =
								"SELECT m FROM Medecin m WHERE m.numeroTelephone = :numeroTelephone") })
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

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.MERGE, orphanRemoval = true,
			   fetch = FetchType.EAGER)
	private List<Patient> patients;
}