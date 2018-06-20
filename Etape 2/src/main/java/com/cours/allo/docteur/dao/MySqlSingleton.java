/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class MySqlSingleton {

    private static final Log log = LogFactory.getLog(MySqlSingleton.class);
    public final static String className = MySqlSingleton.class.getName();
    // Objet DataSource
    private DataSource dataSource = null;

    /**
     * Constructeur privé
     */
    private MySqlSingleton() {
        String methodName = "MySqlSingleton";

        System.out.println(className + " --> " + methodName + "--> Nouvelle Instance de DataSource ");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Holder
     */
    private static class SingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static MySqlSingleton instance = new MySqlSingleton();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static MySqlSingleton getInstance() {
        String methodName = "MySqlSingleton";

        return null;
    }
}
