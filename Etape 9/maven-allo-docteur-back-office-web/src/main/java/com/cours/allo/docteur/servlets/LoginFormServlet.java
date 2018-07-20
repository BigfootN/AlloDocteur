package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.cours.allo.docteur.utils.Token;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * LoginServlet
 */
public class LoginFormServlet extends HttpServlet {
    private ApplicationContext ctx;
    private static final Log log = LogFactory.getLog(HomePageServlet.class);

    private IServiceFacade serviceFacade;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pwd;
        String mail;
        IMedecinDao mDao;
        Cookie cookie;

        pwd = request.getParameter("password");
        mail = request.getParameter("login");

        mDao = serviceFacade.getMedecinDao();
        if (mDao.findMedecinByPwdAndId(pwd, mail) != null) {

            cookie = new Cookie(Constants.TOKEN_ACCESS_KEY_NAME, Token.generateRandomToken(200));
            cookie.setMaxAge(3000);
            response.addCookie(cookie);

            response.sendRedirect(getServletContext().getContext("/HomePage").getContextPath());
        }

    }

}