package com.cours.allo.docteur.utils;

import javax.servlet.http.HttpServlet;

import com.cours.allo.docteur.service.IServiceFacade;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Utils
 */
public class Utils {
    public static ApplicationContext initContext(HttpServlet servlet) {
        return WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
    }

    public static IServiceFacade getBeanServiceFacade(ApplicationContext ctx, String name) {
        IServiceFacade ret;

        try {
            ret = (IServiceFacade) ctx.getBean(name);
        } catch (Exception e) {
            ret = null;
        }

        return ret;
    }
}