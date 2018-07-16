package com.cours.allo.docteur.dao.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Medecin")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
        @NamedQuery(name = "Medecin.findById", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
        @NamedQuery(name = "Medecin.findByNumeroAccreditation", query = "SELECT m FROM Medecin m WHERE m.numeroAccreditation = :numeroAccreditation") })
public class Medecin {

}