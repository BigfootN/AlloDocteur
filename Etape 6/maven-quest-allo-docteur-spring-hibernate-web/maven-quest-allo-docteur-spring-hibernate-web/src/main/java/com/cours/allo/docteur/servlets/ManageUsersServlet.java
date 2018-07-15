/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Json;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.cours.allo.docteur.service.ServiceFacade;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "ManageUsersServlet", urlPatterns =
// {"/ManageUsersServlet"})
public class ManageUsersServlet extends HttpServlet {
	private IServiceFacade service;
	private ApplicationContext ctx;
	private IServiceFacade serviceFacade;

	/**
	 * Méthode d'initialisation de la Servlet
	 *
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		ctx = (ApplicationContext) WebApplicationContextUtils.getWebApplicationContext(
			this.getServletContext());
		serviceFacade = (ServiceFacade) ctx.getBean("serviceFacade");
		if (serviceFacade == null)
			System.out.println("servicefacade est null");
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

		saveUsersInRequestWithMainAddr(request);

		if (uri != null) {
			if (uri.equals("user")) {
				this.getServletContext().getRequestDispatcher("/pages/user/addUser.jsp").forward(
					request,
					response);
			} else if (uri.startsWith("id=")) {
				int idUser = Integer.parseInt(request.getParameter("id"));
				RequestDispatcher dispatcher;

				dispatcher = request.getRequestDispatcher("/pages/user/updateUser.jsp");
				saveUserInRequest(request, idUser);

				dispatcher.forward(request, response);
			} else if (uri.startsWith("json")) {
				IUtilisateurDao utilisateurDao = serviceFacade.getUtilisateurDao();

				Json json = new Json(utilisateurDao.findAllUtilisateurs());
				response.setContentType("Json");
				response.setHeader("Content-disposition", "attachment;filename=file.json");

				OutputStream out = response.getOutputStream();
				FileInputStream in = new FileInputStream(json.writeJson());
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.flush();
			}
		} else {
			request.getRequestDispatcher("/pages/user/allUsers.jsp").forward(request, response);
		}
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

		System.out.println("post");

		if (request.getParameter("_method") != null &&
			request.getParameter("_method").equals("put")) {
			doPut(request, response);
			return;
		}

		Utilisateur utilisateur;
		IUtilisateurDao userDao;
		Adresse adresse;
		IAdresseDao adresseDao;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		utilisateur = new Utilisateur();

		userDao = serviceFacade.getUtilisateurDao();
		adresse = new Adresse();
		adresseDao = serviceFacade.getAdresseDao();

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
		adresse.setIdUtilisateur(utilisateur);
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
		if (dao.deleteUtilisateur(new Utilisateur(attrInt))) {
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
	public void destroy() {}

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
			df = new SimpleDateFormat("dd/MM/yyyy");
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

		mainAddr = daoUser.updateUtilisateur(userUpdated).getMainAddress();

		if (mainAddr != null) {
			idAddr = mainAddr.getIdAdresse();
		} else {
			idAddr = 1;
		}

		newAddr = new Adresse(street, postalCode, city, country, true, idUser);
		daoAddr.updateAdresse(newAddr);

		response.sendRedirect("/maven-quest-allo-docteur-jdbc-web/ManageUsersServlet");
	}

	private void saveUserInRequest(HttpServletRequest request, int idUser) {
		Utilisateur user;
		UtilisateurDao dao;
		Adresse addr;
		DateFormat df;

		df = new SimpleDateFormat("dd/MM/yyyy");
		dao = new UtilisateurDao();
		user = dao.findUtilisateurById(idUser);
		addr = user.getMainAddress();

		request.setAttribute("id", idUser);
		request.setAttribute("name", user.getPrenom());
		request.setAttribute("lastName", user.getNom());
		request.setAttribute("email", user.getIdentifiant());
		request.setAttribute("dteNaissance", df.format(user.getDateNaissance()));

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

	private void saveUsersInRequestWithMainAddr(HttpServletRequest request) {
		List<Utilisateur> users;
		UtilisateurDao dao;

		dao = new UtilisateurDao();
		users = dao.findUtilisateursWithAdressePrincipal();
		request.setAttribute("usersWithMainAddr", users);
	}

}