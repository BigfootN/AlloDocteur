/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "ManageUsersServlet", urlPatterns =
// {"/ManageUsersServlet"})
public class ManageUsersServlet extends HttpServlet {
	private IServiceFacade service;

	/**
	 * Méthode d'initialisation de la Servlet
	 *
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		service = ServiceFactory.getDefaultServiceFacade();
	}

	/**
	 * Méthode appelée lors d'une requête HTTP GET
	 *
	 * @param request  L'objet requête contenant les informations de la requête http
	 * @param response L'objet réponse contenant les informations de la réponse http
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getQueryString();

		if (uri != null) {
			if (uri.equals("user")) {
				this.getServletContext().getRequestDispatcher("/pages/user/addUser.jsp").forward(request, response);
			} else if (uri.startsWith("id=")) {
				int idUser = Integer.parseInt(request.getParameter("id"));
				RequestDispatcher dispatcher;

				dispatcher = request.getRequestDispatcher("/pages/user/updateUser.jsp");
				saveUserInRequest(request, idUser);

				dispatcher.forward(request, response);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp").forward(request, response);
		}

		// this.getServletContext().getRequestDispatcher("/pages/user/user.jsp").forward(request,
		// response);
	}

	/**
	 * Méthode appelée lors d'une requête HTTP POST
	 *
	 * @param request  L'objet requête contenant les informations de la requête http
	 * @param response L'objet réponse contenant les informations de la réponse http
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("_method") != null && request.getParameter("_method").equals("put")) {
			doPut(request, response);
			return;
		}

		String test = null;
		Utilisateur utilisateur;
		UtilisateurDao userDao;
		Adresse adresse;
		AdresseDao adresseDao;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		utilisateur = new Utilisateur();
		userDao = new UtilisateurDao();
		adresse = new Adresse();
		adresseDao = new AdresseDao();

		utilisateur.setPrenom(request.getParameter("firstname"));
		utilisateur.setNom(request.getParameter("lastname"));
		utilisateur.setIdentifiant(request.getParameter("email"));
		utilisateur.setMotPasse(request.getParameter("password"));
		if (request.getParameter("sex").equals("male")) {
			utilisateur.setCivilite("Mr");
		} else {
			utilisateur.setCivilite("Mme");
		}
		try {
			Date date = format.parse(request.getParameter("dteNaiss"));
			utilisateur.setDateNaissance(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		adresse.setRue(request.getParameter("street"));
		adresse.setCodePostal(request.getParameter("postal_code"));
		adresse.setVille(request.getParameter("select-city"));
		adresse.setPays(request.getParameter("country"));
		adresse.setPrincipale(true);

		utilisateur = userDao.createUtilisateur(utilisateur);
		adresse.setIdUtilisateur(utilisateur.getIdUtilisateur());
		adresseDao.createAdresse(adresse);

		response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer attrInt;
		String id = null;
		UtilisateurDao dao;
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";

		if (br != null) {
			json = br.readLine();
		}

		id = json.split("=")[1];
		dao = new UtilisateurDao();

		attrInt = Integer.parseInt(id);
		if (dao.deleteUtilisateur(attrInt)) {
			response.setContentType("text/plain");
			response.setStatus(200);
			response.getWriter().write("1");
		} else {
			response.setContentType("text/plain");
			response.setStatus(500);
			response.getWriter().write("0");
		}
	}

	/**
	 * Méthode appelée lors de la fin de la Servlet
	 */
	@Override
	public void destroy() {
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurDao daoUser;
		AdresseDao daoAddr;
		Utilisateur userUpdated;
		Integer idUser;
		Integer idAddr;
		DateFormat df;
		String name;
		String lastName;
		String email;
		String street;
		String postalCode;
		String city;
		String country;
		String birthDateStr;
		Date birthDate;
		Date creationDate;
		Date modificationDate;
		Adresse newAddr;
		Adresse mainAddr;
		String civilite;

		name = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		civilite = request.getParameter("sex");
		email = request.getParameter("email");
		birthDateStr = request.getParameter("dteNaiss");
		street = request.getParameter("street");
		postalCode = request.getParameter("postal_code");
		city = request.getParameter("select-city");
		country = request.getParameter("country");
		creationDate = new Date();
		modificationDate = new Date();

		idUser = Integer.parseInt(request.getParameter("id"));

		try {
			df = new SimpleDateFormat("dd/MM/YYYY");
			birthDate = df.parse(birthDateStr);
		} catch (Exception e) {
			birthDate = new Date();
		}

		daoUser = new UtilisateurDao();
		daoAddr = new AdresseDao();

		userUpdated = new Utilisateur(idUser);

		userUpdated.setDateNaissance(birthDate);
		userUpdated.setNom(name);
		userUpdated.setPrenom(lastName);
		userUpdated.setIdentifiant(email);
		userUpdated.setDateCreation(creationDate);
		userUpdated.setDateModification(modificationDate);
		userUpdated.setCivilite(civilite);
		userUpdated.setActif(true);
		userUpdated.setMarquerEffacer(false);
		userUpdated.setVersion(1);

		mainAddr = daoUser.updateUtilisateur(userUpdated).getAdressePrincipale();

		if (mainAddr != null) {
			idAddr = mainAddr.getIdAdresse();
			System.out.println("main addr not null");
		} else {
			idAddr = 1;
			System.out.println("main addr is null");
		}

		newAddr = new Adresse(idAddr, street, postalCode, city, country, true, 1, idUser);
		daoAddr.updateAdresse(newAddr);

		response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
	}

	private void saveUserInRequest(HttpServletRequest request, int idUser) {
		Utilisateur user;
		UtilisateurDao dao;
		Adresse addr;

		dao = new UtilisateurDao();
		user = dao.findUtilisateurById(idUser);
		addr = user.getAdressePrincipale();

		request.setAttribute("id", idUser);
		request.setAttribute("name", user.getPrenom());
		request.setAttribute("lastName", user.getNom());
		request.setAttribute("email", user.getIdentifiant());

		if (addr != null) {
			request.setAttribute("rue", addr.getRue());
			request.setAttribute("codePostal", addr.getCodePostal());
			request.setAttribute("pays", addr.getPays());
		} else {
			request.setAttribute("rue", "");
			request.setAttribute("codePostal", "");
			request.setAttribute("pays", "");
		}

	}

}