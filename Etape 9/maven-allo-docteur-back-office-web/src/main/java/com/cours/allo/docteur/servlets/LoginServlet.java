package com.cours.allo.docteur.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.cours.allo.docteur.utils.security.Token;
import com.cours.allo.docteur.utils.security.TokenAuthUserList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private ApplicationContext ctx;
	private static final Log log = LogFactory.getLog(HomePageServlet.class);

	private IServiceFacade serviceFacade;

	@Override
	public void init() throws ServletException {
		log.debug("Dans init de LoginServlet");
		try {
			ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			serviceFacade = (IServiceFacade) ctx.getBean("serviceFacade");
		} catch (Exception e) {
			serviceFacade = null;
			log.error(e.toString());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		String pwd;
		String mail;
		IMedecinDao mDao;
		Medecin foundDoctor;
		Cookie cookie;
		String generatedToken;

		pwd = req.getParameter("password");
		mail = req.getParameter("login");

		mDao = serviceFacade.getMedecinDao();
		foundDoctor = mDao.findMedecinByPwdAndId(pwd, mail);

		if (foundDoctor != null) {
			generatedToken = Token.generateRandomToken(200);
			cookie = new Cookie(Constants.TOKEN_ACCESS_KEY_NAME, generatedToken);
			cookie.setMaxAge(3000);
			resp.addCookie(cookie);
			TokenAuthUserList.getInstance().addUserAuthToken(generatedToken,
															 foundDoctor.getIdMedecin());
			resp.sendRedirect("./home");
		} else {
			resp.sendRedirect("./login");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		req.getRequestDispatcher("./login.jsp").forward(req, resp);
	}

}