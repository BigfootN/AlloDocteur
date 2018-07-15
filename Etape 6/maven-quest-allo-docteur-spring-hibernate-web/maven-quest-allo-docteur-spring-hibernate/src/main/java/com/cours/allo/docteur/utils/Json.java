package com.cours.allo.docteur.utils;

import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Json {
	private List<Utilisateur> user = null;

	public Json(List<Utilisateur> user) {
		System.out.println("Json");
		this.user = user;
	}

	public File writeJson() {
		Gson obj = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = obj.toJsonTree(this.user);
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("utilisateur", jsonElement);

		try {
			FileWriter fileWriter = new FileWriter("file.json");
			String json = jsonObject.toString();
			fileWriter.write(json);
			fileWriter.flush();
			File file = new File("file.json");
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}