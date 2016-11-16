/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Iniciar;

/**
 *
 * @author Koushik
 */
public final class mysql {

    /**
     * Final variables will be
     */
    private final String DB_HOST;
    private final String DB_USER;
    private final String DB_PASS;
    private final String DB_NAME;
    private final String DB_URL;

    private Connection conn;
    private Statement st;
    private ResultSet result;

    public mysql() {
        this.DB_HOST = Iniciar.DB_HOST;
        this.DB_USER = Iniciar.DB_USER;
        this.DB_PASS = Iniciar.DB_PASS;
        this.DB_NAME = Iniciar.DB_NAME;
        this.DB_URL = url_builder();
    }// End of no argument Constructor

    public mysql(String host, String user, String pass, String db_name) {
        this.DB_HOST = host;
        this.DB_USER = user;
        this.DB_PASS = pass;
        this.DB_NAME = db_name;
        this.DB_URL = url_builder();
    }// End of constructor

    private String url_builder() {
        return "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;
    }

    private void openConnection()  {
        try {
            this.conn = DriverManager.getConnection(this.DB_URL);
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeConnection()  {
        try {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Object execute(String sql) {
        try {
            openConnection();
            HashMap res = new HashMap<>();
            st = conn.createStatement();
            if (st.execute(sql)) {
                
                result = st.getResultSet();
                if (result != null) {
                    ResultSetMetaData rsmd = result.getMetaData();
                    int total_col = rsmd.getColumnCount();
                    String temp[] = new String[total_col];
                    for (int i = 0; i < total_col; i++) {
                        temp[i] = rsmd.getColumnLabel(i + 1);
                        res.put(temp[i], new ArrayList());
                    }
                    while (result.next()) {
                        for (int i = 0; i < total_col; i++) {
                            ArrayList al = (ArrayList) res.get(temp[i]);
                            al.add(result.getString(temp[i]));
                        }
                    }
                    
                }

            }
            closeConnection();
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    private Object execute(String sql, boolean t)  {
        try {
            int changed = -1;
            openConnection();
            st = conn.createStatement();
            if (st.execute(sql)) {
                changed = st.getUpdateCount();
                
            }
            closeConnection();
            return changed;
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }

    public Object processQuery(String sql)  {
        Object t;
        String QueryType = sql.substring(0, sql.indexOf(" "));
        if (QueryType.equalsIgnoreCase("SELECT") || QueryType.equalsIgnoreCase("SHOW")) {
            t = execute(sql);
        } else {
            t = execute(sql, true);
        }
        return t;
    }// End of method
}// End of class new MySql
