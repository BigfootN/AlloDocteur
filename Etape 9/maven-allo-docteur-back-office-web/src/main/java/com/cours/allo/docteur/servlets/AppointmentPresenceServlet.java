package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Utils;

import org.springframework.context.ApplicationContext;

/**
 * AppointmentPresenceServlet
 */
public class AppointmentPresenceServlet extends HttpServlet {
	ApplicationContext ctx;
	IServiceFacade serviceFacade;

	@Override
	public void init() throws ServletException {
		ctx = Utils.initContext(this);
		serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		IRendezVousDao dao;
		List<RendezVous> rdv;

		dao = serviceFacade.getRendezVousDao();
		rdv = dao.findAll();

		req.setAttribute("rdvlist", rdv);
		req.getRequestDispatcher("./presence-rendez-vous.jsp").forward(req, resp);
	}

}