package com.cours.allo.docteur.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TokenList
 */
public class TokenList {
    private static final TokenList instance = new TokenList();
    private static List<String> tokenList = new ArrayList<>();

    public static TokenList getInstance() {
        return instance;
    }

    public void addToken(String token) {
        tokenList.add(token);
    }

    public boolean hasToken(String token) {
        Iterator<String> it;

        it = tokenList.iterator();

        while (it.hasNext()) {
            if (it.next().equals(token))
                return true;
        }

        return false;
    }

    private TokenList() {
    }

}