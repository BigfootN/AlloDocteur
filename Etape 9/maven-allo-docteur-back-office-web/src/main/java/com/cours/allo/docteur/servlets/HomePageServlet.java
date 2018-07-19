package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.service.IServiceFacade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class HomePageServlet extends HttpServlet {
    private ApplicationContext ctx;
    private IServiceFacade serviceFacade;
    private static final Log log = LogFactory.getLog(HomePageServlet.class);

    @Override
    public void init() throws ServletException {
        log.debug("Dans init de HomePageServlet");
        try {
            ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            serviceFacade = (IServiceFacade) ctx.getBean("serviceFacade");
        } catch (Exception e) {
            serviceFacade = null;
            log.error(e.toString());
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
    }

}