package com.cours.allo.docteur.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.Connection;

/**
 * SQLFile
 */
public class SQLScriptFile {
    private Connection conn;
    private Scanner scanner;

    public SQLScriptFile(Connection conn, String filePath) {
        InputStream inStream;

        this.conn = conn;

        try {
            inStream = new FileInputStream(filePath);
            scanner = new Scanner(inStream);
            scanner.useDelimiter("(;(\r)?\n)|(--\n)");
        } catch (Exception e) {
        }
    }

    public void run() {
        Statement st = null;
        String curLine;
        int idxEoCmt;

        try {
            st = conn.createStatement();

            while (scanner.hasNext()) {
                curLine = scanner.next();

                if (curLine.startsWith("/*!") && curLine.endsWith("*/")) {
                    idxEoCmt = curLine.indexOf(' ');
                    curLine = curLine.substring(idxEoCmt + 1, curLine.length() - "*/".length());
                }

                if (curLine.trim().length() > 0) {
                    st.execute(curLine);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (Exception e) {
            }
        }
    }

}