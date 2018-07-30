package com.cours.allo.docteur.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.AppointmentsJson;
import com.cours.allo.docteur.utils.Utils;

import org.springframework.context.ApplicationContext;

/**
 * DownloadAppointmentsJsonServlet
 */
public class DownloadAppointmentsJsonServlet extends HttpServlet {
    ApplicationContext ctx;
    IServiceFacade serviceFacade;

    @Override
    public void init() throws ServletException {
        ctx = Utils.initContext(this);
        serviceFacade = Utils.getBeanServiceFacade(ctx, "serviceFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppointmentsJson apptJson;
        List<RendezVous> appts;
        IRendezVousDao rDao;
        File jsonFile;
        OutputStream out;
        FileInputStream in;
        byte[] buffer;
        int length;

        resp.setContentType("application/json");
        resp.setHeader("Content-disposition", "attachement; filename=rendezvous.json");

        rDao = serviceFacade.getRendezVousDao();
        appts = rDao.findAll();
        apptJson = new AppointmentsJson();
        jsonFile = apptJson.appointmentsToJson(appts);

        out = resp.getOutputStream();
        in = new FileInputStream(jsonFile);
        buffer = new byte[4096];

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        in.close();
        out.flush();
    }

}