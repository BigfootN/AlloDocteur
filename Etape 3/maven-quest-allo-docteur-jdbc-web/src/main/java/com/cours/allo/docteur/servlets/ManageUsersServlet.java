/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		this.getServletContext().getRequestDispatcher("/pages/user/allUsers.jsp").forward(request, response);
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

	}

	/**
	 * Méthode appelée lors de la fin de la Servlet
	 */
	@Override
	public void destroy() {
	}

}