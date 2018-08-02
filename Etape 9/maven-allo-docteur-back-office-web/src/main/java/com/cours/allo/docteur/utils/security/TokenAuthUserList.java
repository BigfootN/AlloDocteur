package com.cours.allo.docteur.utils.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.cours.allo.docteur.utils.Constants;

/**
 * TokenList
 */
public class TokenAuthUserList {
    private static final TokenAuthUserList instance = new TokenAuthUserList();
    private static Map<String, Integer> tokenUserAuthMap = new HashMap<>();

    public static TokenAuthUserList getInstance() {
        return instance;
    }

    public void addUserAuthToken(String token, Integer userId) {
        tokenUserAuthMap.put(token, userId);
    }

    public void disconnect(HttpServletRequest req) {
        String token;

        token = getToken(req);
        tokenUserAuthMap.remove(token);
    }

    public boolean hasToken(String token) {
        if (tokenUserAuthMap.get(token) == null)
            return false;
        else
            return true;
    }

    public Integer getUserId(String token) {
        return tokenUserAuthMap.get(token);
    }

    public Integer getUserId(HttpServletRequest req) {
        Integer ret;

        ret = tokenUserAuthMap.get(getCookieToken(req).getValue());

        return ret;
    }

    private String getToken(HttpServletRequest req) {
        String ret;

        ret = getCookieToken(req).getValue();

        return ret;
    }

    private Cookie getCookieToken(HttpServletRequest req) {
        Cookie ret;
        int i;
        Cookie[] cookies;

        i = 0;
        cookies = req.getCookies();
        ret = null;

        while (i < cookies.length && ret == null) {
            if (cookies[i].getName().equals(Constants.TOKEN_ACCESS_KEY_NAME)) {
                ret = cookies[i];
            }

            i++;
        }

        return ret;
    }

    private TokenAuthUserList() {
    }

}