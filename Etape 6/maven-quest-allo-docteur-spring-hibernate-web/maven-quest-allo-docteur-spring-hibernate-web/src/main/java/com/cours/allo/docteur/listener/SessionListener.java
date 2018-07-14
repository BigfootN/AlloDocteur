package com.cours.allo.docteur.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener implements HttpSessionListener {

	private static int totalActiveSessions;
	private ApplicationContext ctx;
	private static IServiceFacade serviceFacade;

	public static IServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session created!!!");
		createServiceFacade(arg0);
	}

	private void createServiceFacade(HttpSessionEvent event) {
		HttpSession session;
		ApplicationContext ctx;

		System.out.println("appel de service facade");

		session = event.getSession();
		try {
			ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
			serviceFacade = (ServiceFacade) ctx.getBean("serviceFacade");
			if (serviceFacade != null)
				System.out.println("Service created (Session Listener)");
			else
				System.out.println("Service not created");
		} catch (Exception e) {
			System.out.println("Une exception a ete lancee");
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {}

}