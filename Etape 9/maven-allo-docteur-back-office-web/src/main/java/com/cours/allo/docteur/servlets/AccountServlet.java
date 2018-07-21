package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.cours.allo.docteur.utils.Utils;
import com.cours.allo.docteur.utils.security.TokenAuthUserList;

import org.springframework.context.ApplicationContext;

/**
 * AccountServlet
 */
public class AccountServlet extends HttpServlet {
    private ApplicationContext ctx;
    private IServiceFacade serviceFacade;

    @Override
    public void init() throws ServletException {
        ctx = Utils.initContext(this);
        serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Medecin curDoctor;
        Integer curDoctorId;
        Cookie[] cookies;
        Integer idx;

        idx = 0;
        cookies = req.getCookies();
        curDoctorId = null;

        while (idx < cookies.length) {
            if (cookies[idx].getName().equals(Constants.TOKEN_ACCESS_KEY_NAME)) {
                curDoctorId = TokenAuthUserList.getInstance().getUserId(cookies[idx].getValue());
                break;
            }

            idx++;
        }

        System.out.println("doctor id = " + curDoctorId);
        curDoctor = serviceFacade.getMedecinDao().findMedecinByIdMedecin(curDoctorId);

        insertDoctorIntoRequest(req, curDoctor);
        insertAddressIntoRequest(req, curDoctor);
        req.getRequestDispatcher("/modification-informations-medecin.jsp").forward(req, resp);
    }

    private void insertDoctorIntoRequest(HttpServletRequest request, Medecin doctor) {
        Utilisateur user;

        user = doctor.getUtilisateur();

        request.setAttribute("name", user.getPrenom());
        request.setAttribute("lastName", user.getNom());
        request.setAttribute("accrNumber", doctor.getNumeroAccreditation());
        request.setAttribute("telNumber", doctor.getNumeroTelephone());
        request.setAttribute("email", user.getIdentifiant());
    }

    private void insertAddressIntoRequest(HttpServletRequest request, Medecin doctor) {
        IUtilisateurDao dao;
        Adresse mainAddr;

        dao = serviceFacade.getUtilisateurDao();
        mainAddr = dao.findAdressePrincipale(doctor.getUtilisateur().getIdUtilisateur());

        request.setAttribute("street", mainAddr.getRue());
        request.setAttribute("country", mainAddr.getPays());
        request.setAttribute("postalCode", mainAddr.getCodePostal());
    }

}