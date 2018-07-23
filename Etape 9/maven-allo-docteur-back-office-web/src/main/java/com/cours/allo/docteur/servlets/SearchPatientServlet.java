package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Utils;

import org.springframework.context.ApplicationContext;

/**
 * SearchPatientServlet
 */
public class SearchPatientServlet extends HttpServlet {
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
		IPatientDao dao;
		String searchValue;
		String searchCriteria;
		List<Patient> patients;

		searchCriteria = req.getParameter("searchCriteria");
		searchValue = req.getParameter("searchValue");
		dao = serviceFacade.getPatientDao();

		if (!isSearching(searchCriteria, searchValue))
			patients = dao.findAll();
		else {
			searchValue = searchValue.trim();
			if (searchCriteria.equals("name")) {
				patients = dao.findPatientsByPrenom(searchValue);
			} else if (searchCriteria.equals("lastname")) {
				patients = dao.findPatientsByNom(searchValue);
			} else if (searchCriteria.equals("securityNumber")) {
				patients = new ArrayList<>();
				patients.add(dao.findPatientByNumeroSecuriteSocial(searchValue));
			} else {
				patients = null;
			}
		}

		req.setAttribute("patientlist", patients);
		req.getRequestDispatcher("./recherche-patient.jsp").forward(req, resp);
	}

	private boolean isSearching(String searchCriteria, String searchValue) {
		if (searchCriteria == null || searchCriteria.equals("notset"))
			return false;

		if (searchValue == null || searchValue.trim().length() == 0)
			return false;

		return true;
	}

}