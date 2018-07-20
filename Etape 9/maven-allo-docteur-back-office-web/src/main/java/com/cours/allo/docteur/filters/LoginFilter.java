package com.cours.allo.docteur.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cours.allo.docteur.utils.Constants;
import com.cours.allo.docteur.utils.TokenList;

import org.springframework.http.HttpRequest;

/**
 * LoginFilter
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest;
        HttpServletResponse httpResponse;
        Cookie[] cookies;
        String token;
        Integer idx;
        String uri;

        httpRequest = (HttpServletRequest) request;
        httpResponse = (HttpServletResponse) response;
        cookies = httpRequest.getCookies();
        uri = httpRequest.getRequestURI();
        idx = 0;

        if (cookies == null) {
            httpResponse.sendRedirect("login");
            return;
        }

        if (uri.endsWith("/login")) {
            chain.doFilter(request, response);
        } else if (uri.indexOf("/assets") > 0) {
            chain.doFilter(request, response);
        }

        while (idx < cookies.length) {
            if (cookies[idx].getName().equals(Constants.TOKEN_ACCESS_KEY_NAME)) {
                token = cookies[idx].getValue();
                if (TokenList.getInstance().hasToken(token)) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            idx++;
        }

        httpResponse.sendRedirect("login");
    }

    @Override
    public void destroy() {
    }

}