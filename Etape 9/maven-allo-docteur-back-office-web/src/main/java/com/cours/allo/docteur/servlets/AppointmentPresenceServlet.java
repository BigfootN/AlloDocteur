package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Utils;
import com.cours.allo.docteur.utils.security.TokenAuthUserList;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IRendezVousDao dao;
		IUtilisateurDao uDao;
		List<RendezVous> rdv;
		Iterator<RendezVous> it;
		List<Adresse> addrList;

		dao = serviceFacade.getRendezVousDao();
		uDao = serviceFacade.getUtilisateurDao();
		rdv = dao.findRendezVousByIdMedecin(TokenAuthUserList.getInstance().getUserId(req));

		it = rdv.iterator();
		addrList = new ArrayList<>();

		while (it.hasNext()) {
			Utilisateur user;
			Adresse addr;

			user = it.next().getPatientRdv().getUserPatient();
			addrList.add(uDao.findAdressePrincipale(user.getIdUtilisateur()));
		}

		req.setAttribute("addrList", addrList);
		req.setAttribute("rdvlist", rdv);
		req.getRequestDispatcher("./presence-rendez-vous.jsp").forward(req, resp);
	}

}