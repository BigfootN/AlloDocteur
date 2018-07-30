package com.dao.cours.test;
/**
 * TestDao
 */

import java.util.List;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.ServiceFacade;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDao {

	@Test
	public void testAddr() {
		IAdresseDao dao;
		Adresse addr;
		Utilisateur user;
		List<Adresse> list;
		ApplicationContext ctx;
		ServiceFacade serviceFacade;

		ctx = new ClassPathXmlApplicationContext("file:src/main/resources/applicationContext.xml");
		serviceFacade = (ServiceFacade) ctx.getBean("serviceFacade");
		dao = serviceFacade.getAdresseDao();
		addr = new Adresse();
		user = new Utilisateur();
		user.setIdUtilisateur(20);
		addr.setIdUtilisateur(user);
		dao.createAdresse(addr);
		System.out.println("et l'adresse est cree");
	}

}