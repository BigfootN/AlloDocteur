/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.IOException;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;

/**
 *
 * @author elhad
 */
 @WebServlet(name = "ManageUsersServlet", urlPatterns =
 {"/ManageUsersServlet"})
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

        if (uri != null && uri.equals("user")){
            this.getServletContext().getRequestDispatcher("/pages/user/user.jsp").forward(request, response);
        }else{
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response){


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
			if(dao.deleteUtilisateur(attrInt)){
				response.setContentType("text/plain");
				response.setStatus(200);
				response.getWriter().write("1");
			}else {
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

}