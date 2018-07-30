package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
 * AppointmentsDayServlet
 */

public class AppointmentsDayServlet extends HttpServlet {
	IServiceFacade serviceFacade;
	ApplicationContext ctx;

	@Override
	public void init() throws ServletException {
		ctx = Utils.initContext(this);
		serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		List<RendezVous> rdv;
		IRendezVousDao rDao;

		rDao = serviceFacade.getRendezVousDao();
		rdv = rDao.findRendezVousByJour(new Date(Calendar.getInstance().getTime().getTime()));

		req.setAttribute("rdvList", rdv);

		req.getRequestDispatcher("./rendez-vous-journee.jsp").forward(req, resp);
	}

}