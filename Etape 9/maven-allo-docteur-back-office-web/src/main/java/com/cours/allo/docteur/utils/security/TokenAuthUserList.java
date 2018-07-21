package com.cours.allo.docteur.utils.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public boolean hasToken(String token) {
        if (tokenUserAuthMap.get(token) == null)
            return false;
        else
            return true;
    }

    public Integer getUserId(String token) {
        return tokenUserAuthMap.get(token);
    }

    private TokenAuthUserList() {
    }

}