/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ConnectionHelper {

    private static final Log log = LogFactory.getLog(ConnectionHelper.class);
    public final static String className = ConnectionHelper.class.getName();

    public static void closeSqlResources(Connection connection, PreparedStatement preparedStatement, ResultSet result) {
        closeConnection(connection);
        closePreparedStatement(preparedStatement);
        closeResultSet(result);
    }

    public static Connection getConnection() {
        try {
            return MySqlSingleton.getInstance().getDataSource().getConnection();
        } catch (Exception e) {
            return null;
        }
    }

    private static void closeResultSet(ResultSet result) {
        try {
            if (result != null)
                result.close();
        } catch (Exception e) {
        }
    }

    private static void closePreparedStatement(PreparedStatement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (Exception e) {
        }
    }

    private static void closeConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
        }
    }

}