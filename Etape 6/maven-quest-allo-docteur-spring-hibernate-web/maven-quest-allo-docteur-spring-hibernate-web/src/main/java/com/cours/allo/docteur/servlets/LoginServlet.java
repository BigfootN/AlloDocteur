/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.faces.application.Application;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;

/**
 *
 * @author elhad
 */
public class LoginServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private IServiceFacade serviceFacade = null;
	private ApplicationContext ctx;

	@Override
	public void init() throws ServletException {
		try {
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			serviceFacade = (ServiceFacade) ctx.getBean("serviceFacade");
		} catch (Exception e) {
			serviceFacade = null;
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		Utilisateur user;

		try {
			if (serviceFacade != null)
				System.out.println("Service created (get)");
			else
				System.out.println("Service not created (get)");
		} catch (Exception e) {
			serviceFacade = null;
			System.out.println(e.getMessage());
		}

		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request,
																						response);
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

		// user = dao.authenticate(mail, pwd);

		if (true)
			response.sendRedirect(this.getServletContext().getContextPath() +
								  "/ManageUsersServlet");
		else {
			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request,
																							response);
		}
	}

	/**
	 * Méthode appelée lors de la fin de la Servlet
	 */
	@Override
	public void destroy() {}

}