/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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
	private MysqlDataSource dataSource = null;

	/**
	 * Constructeur privé
	 */
	private MySqlSingleton() {
		String methodName = "MySqlSingleton";

		System.out.println(className + " --> " + methodName + "--> Nouvelle Instance de DataSource ");

		try {
			dataSource = new MysqlConnectionPoolDataSource();
			dataSource.setUrl(Constants.DATABASE_URL);
			dataSource.setUser(Constants.DATABASE_USER);
			dataSource.setPassword(Constants.DATABASE_PASSWORD);
			dataSource.setDatabaseName(Constants.DATABASE_NAME);

		} catch (Exception e) {
			dataSource = null;
		}
	}

	public MysqlDataSource getDataSource() {
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
	public static synchronized MySqlSingleton getInstance() {
		String methodName = "MySqlSingleton";

		return SingletonHolder.instance;
	}

}