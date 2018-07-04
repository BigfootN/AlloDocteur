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

    public Json(List<Utilisateur> user){
        System.out.println("Json");
        this.user = user;
    }

    public File writeJson(){
        Gson obj = new GsonBuilder().create();
        //JsonObject obj = new JsonObject();
        System.out.println("writeJson");
        JsonElement jsonElement = obj.toJsonTree(this.user);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("utilisateur", jsonElement);
        System.out.println(jsonObject);

        //obj.toJson(this.user.toString());
        //obj.
       /* for(int i = 0; i < this.user.size(); i++){

            obj.addProperty("Prenom", this.user.get(i).getPrenom());
            obj.addProperty("Nom", this.user.get(i).getNom());
            obj.addProperty("Civilite", this.user.get(i).getCivilite());
            obj.addProperty("Identifiant", this.user.get(i).getIdentifiant());
            obj.addProperty("Date naissance", this.user.get(i).getDateNaissance().toString());
        }*/

        try {
            FileWriter fileWriter = new FileWriter("fileWriter.json");
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
