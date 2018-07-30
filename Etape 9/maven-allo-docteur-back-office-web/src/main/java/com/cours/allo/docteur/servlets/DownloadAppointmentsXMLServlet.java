package com.cours.allo.docteur.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.AppointmentsXML;
import com.cours.allo.docteur.utils.Utils;
import com.cours.allo.docteur.utils.security.TokenAuthUserList;

import org.springframework.context.ApplicationContext;

/**
 * DownloadAppointmentsXMLServlet
 */
public class DownloadAppointmentsXMLServlet extends HttpServlet {
    ApplicationContext ctx;
    IServiceFacade serviceFacade;

    @Override
    public void init() throws ServletException {
        ctx = Utils.initContext(this);
        serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentsXML apptsXML;
        File xmlFile;
        OutputStream out;
        FileInputStream in;
        byte[] buffer;
        int length;
        IRendezVousDao rDao;
        List<RendezVous> appts;
        Integer idMedecin;

        idMedecin = TokenAuthUserList.getInstance().getUserId(req);

        rDao = serviceFacade.getRendezVousDao();
        appts = rDao.findRendezVousByIdMedecin(idMedecin);
        apptsXML = new AppointmentsXML();
        xmlFile = apptsXML.toXML(appts);

        resp.setContentType("text/xml");
        resp.setHeader("Content-disposition", "attachement; filename=rendezvous.xml");

        out = resp.getOutputStream();
        in = new FileInputStream(xmlFile);
        buffer = new byte[4096];

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
            out.flush();
        }

        in.close();
        out.flush();
    }

}