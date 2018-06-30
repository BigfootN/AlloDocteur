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

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private IServiceFacade serviceFacade = null;

	@Override
	public void init() throws ServletException {
		serviceFacade = ServiceFactory.getDefaultServiceFacade();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user;

		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user;
		String pwd;
		String mail;
		IUtilisateurDao dao;

		mail = request.getParameter("email");
		pwd = request.getParameter("password");
		dao = serviceFacade.getUtilisateurDao();

		user = dao.authenticate(mail, pwd);

		if (true)
			response.sendRedirect(this.getServletContext().getContextPath() + "/ManageUsersServlet");
		else {
			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
		}
	}

	/**
	 * Méthode appelée lors de la fin de la Servlet
	 */
	@Override
	public void destroy() {
	}

}