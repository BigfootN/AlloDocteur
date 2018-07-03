/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;

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
				this.getServletContext().getRequestDispatcher("/pages/user/user.jsp").forward(request, response);
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
			throws IOException, ServletException {
		String attrStr;
		Integer attrInt;
		UtilisateurDao dao;

		dao = new UtilisateurDao();

		attrStr = (String) request.getParameter("id");
		attrInt = Integer.parseInt(attrStr);
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

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attrStr;
		Integer attrInt;
		UtilisateurDao dao;

		dao = new UtilisateurDao();

		attrStr = (String) request.getParameter("id");
		attrInt = Integer.parseInt(attrStr);
		if (dao.deleteUtilisateur(attrInt)) {
			response.setContentType("text/plain");
			response.setStatus(204);
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
		String attrStr;
		Integer attrInt;
		UtilisateurDao dao;
		Utilisateur userUpdated;
		Integer idUser;
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

		System.out.println("SKLADHFKJASHDFKHSKADFHKJASHDFJKAHSDKFJHASKDHF");

		df = new SimpleDateFormat("dd/MM/YYYY");

		name = request.getParameter("firstname");
		lastName = request.getParameter("lastname");
		email = request.getParameter("email");
		birthDateStr = request.getParameter("dteNaiss");
		street = request.getParameter("street");
		postalCode = request.getParameter("postal_code");
		city = request.getParameter("select-city");
		country = request.getParameter("country");

		try {
			birthDate = df.parse(birthDateStr);
		} catch (Exception e) {
			birthDate = null;
		}

		dao = new UtilisateurDao();

		attrStr = (String) request.getParameter("id");
		idUser = Integer.parseInt(attrStr);

		userUpdated = new Utilisateur(idUser);
		userUpdated.setDateNaissance(birthDate);
		userUpdated.setNom(name);
		userUpdated.setPrenom(lastName);
		userUpdated.setIdentifiant(email);

		dao.updateUtilisateur(userUpdated);
		System.out.println("utilisateur inseree");
		request.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp").forward(request, response);
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