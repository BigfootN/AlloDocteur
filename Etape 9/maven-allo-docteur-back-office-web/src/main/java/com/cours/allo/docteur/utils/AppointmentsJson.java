package com.cours.allo.docteur.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import com.cours.allo.docteur.dao.entities.RendezVous;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * AppointmentsJson
 */
public class AppointmentsJson {
	public File appointmentsToJson(List<RendezVous> rdv) {
		File ret;
		Gson obj;
		String jsonStr;
		Iterator<RendezVous> it;
		Integer idMedecin;

		obj = new GsonBuilder().setPrettyPrinting().create();

		try {
			FileWriter fw;
			it = rdv.iterator();
			fw = new FileWriter("rdv.json");

			while (it.hasNext()) {
				jsonStr = appointmentToJson(it.next(), obj);
				fw.write(jsonStr);
				fw.flush();
			}

			fw.flush();
			fw.close();

			ret = new File("rdv.json");
		} catch (Exception e) {
			ret = null;
		}

		return ret;
	}

	private String appointmentToJson(RendezVous rdv, Gson obj) {
		String ret;

		ret = obj.toJson(rdv.getJour());
		ret += obj.toJson(rdv.getPatientRdv().getUserPatient().getNom());
		ret += obj.toJson(rdv.getPatientRdv().getUserPatient().getPrenom());

		return ret;
	}

}