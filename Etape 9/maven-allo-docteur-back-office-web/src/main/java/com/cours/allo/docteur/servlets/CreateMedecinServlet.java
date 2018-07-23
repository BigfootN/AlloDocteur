package com.cours.allo.docteur.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Utils;

import org.springframework.context.ApplicationContext;

/**
 * CreateMedecinServlet
 */
public class CreateMedecinServlet extends HttpServlet {
    IServiceFacade serviceFacade;
    ApplicationContext ctx;

    @Override
    public void init() throws ServletException {
        ctx = Utils.initContext(this);
        serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Medecin doctor;
        IMedecinDao mDao;

        mDao = serviceFacade.getMedecinDao();
        doctor = createMedecinFromRequest(req);

        mDao.createMedecin(doctor);

        req.getRequestDispatcher("./parrainer-medecin.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./parrainer-medecin.jsp").forward(req, resp);
    }

    private Medecin createMedecinFromRequest(HttpServletRequest req) {
        Medecin ret;
        Utilisateur user;
        Adresse addr;
        List<Adresse> addrList;
        IUtilisateurDao uDao;
        IAdresseDao aDao;

        uDao = serviceFacade.getUtilisateurDao();
        aDao = serviceFacade.getAdresseDao();

        ret = new Medecin();
        user = new Utilisateur();
        addr = new Adresse();

        ret.setNumeroAccreditation(req.getParameter("numAcreditation"));
        ret.setNumeroTelephone(req.getParameter("tel"));

        user.setPrenom(req.getParameter("firstName"));
        user.setNom(req.getParameter("name"));
        user.setIdentifiant(req.getParameter("identifier"));
        user.setMotPasse(req.getParameter("password"));
        user.setActif(true);
        user.setMarquerEffacer(false);

        addr.setCodePostal(req.getParameter("postaCode"));
        addr.setPrincipale(true);
        addr.setRue(req.getParameter("street"));
        addr.setVille(req.getParameter("city"));
        addr.setPays(req.getParameter("country"));

        user = uDao.createUtilisateur(user);

        System.out.println("iduser = " + user.getIdUtilisateur());
        addrList = new ArrayList<>();
        addrList.add(addr);
        user.setIdUtilisateur(20);
        addr.setIdUtilisateur(user);

        System.out.println("idUserAddr = " + addr.getIdUtilisateur().getIdUtilisateur());

        addr = aDao.createAdresse(addr);

        user.setAdresseSet(addrList);
        ret.setUtilisateur(uDao.createUtilisateur(user));

        return ret;
    }

}