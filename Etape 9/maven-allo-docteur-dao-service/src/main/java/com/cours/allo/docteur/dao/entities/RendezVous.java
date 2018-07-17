package com.cours.allo.docteur.dao.entities;

import java.time.LocalTime;

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
@Table(name = "RendezVous")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r"),
				@NamedQuery(name = "RendezVous.findById",
							query = "SELECT r FROM RendezVous r.idRendezVous = :idRendezVous"),
				@NamedQuery(name = "RendezVous.findByJour",
							query = "SELECT r FROM RendezVous r WHERE r.jour = :jour") })
public class RendezVous {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRendezVous")
	private Integer idRendezVous;

	@Column(name = "jour")
	private LocalTime jour;

	@Column(name = "prixConsultationDouble")
	private Double prixConsultationDouble;

	@Column(name = "present")
	private Boolean present;

	@Version
	@Column(name = "version")
	private Integer version;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idPatient", name = "idPatient")
	private Patient patientRdv;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "idCreneau", name = "idCreneau")
	private Creneau creneauRdv;
}