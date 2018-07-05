/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.allo.docteur.dao.entities.Utilisateur;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);
    private static final String persistenceUnitName = "QuestAlloDocteurPU";

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	List<Utilisateur> listUser;
    	EntityManager em;
    	EntityManagerFactory emf;
    	
    	emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    	em = emf.createEntityManager();
    	
    	listUser = em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
}
