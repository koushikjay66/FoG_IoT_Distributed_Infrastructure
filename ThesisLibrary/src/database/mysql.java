/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public class mysql {

    private Connection conn;
    private Statement st;
    private ResultSet result;
    public int changed = -1;
    public HashMap res;

    /**
     * This Constructor is only initializes connection
     * @param sql
     * @param QueryType
     */
    public mysql(String host, String user, String pass, String db_name){
        try{
            String c = "jdbc:mysql://" + host + "//" + db_name + "?user=" + user + "&password=" + pass;
           conn=DriverManager.getConnection(c);
            System.out.println("Connection to Database Was Successfull. Database also Exists");
        }catch(SQLException e){
            System.out.println(e.getMessage());; 
        }
        finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Cant Close Connection");;
            }
        }
    }
    public mysql(String sql, String QueryType) {
        try {
            conn
                    = DriverManager.getConnection("jdbc:mysql://localhost/services_db?"
                            + "user=root&password=");

            processQuery(sql, QueryType);
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// End of this constructor
    public mysql(String sql, String QueryType, String tableName){
        try {
            conn
                    = DriverManager.getConnection("jdbc:mysql://localhost/"+tableName+"?"
                            + "user=root&password=");

            processQuery(sql, QueryType);
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public mysql(String uri, String user, String password, String db_name, String sql, String QueryType) {

        String c = "jdbc:mysql://" + uri + "//" + db_name + "?user=" + user + "&password=" + password;
        try {
            conn = DriverManager.getConnection(c);
            processQuery(sql, QueryType);
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// End of constructor

    /**
     *
     *
     * @param sql The SQL to execute
     * @return the result set for your query
     * @throws java.sql.SQLException
     */
    private void executeSelect(String sql) throws SQLException {

        st = conn.createStatement();
        if (st.execute(sql)) {

            result = st.getResultSet();
            if (result != null) {
                ResultSetMetaData rsmd = result.getMetaData();
                res= new HashMap<>();
                int total_col = rsmd.getColumnCount();
                String temp[] = new String[total_col];
                for (int i = 0; i < total_col; i++) {
                    
                    temp[i] = rsmd.getColumnName(i + 1);
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
    }

    private void executeOthers(String sql) throws SQLException {

        st = conn.createStatement();
        if (st.execute(sql)) {
            changed = st.getUpdateCount();

        }

    }

    private void processQuery(String sql, String QueryType) throws SQLException {
        if (QueryType.equalsIgnoreCase("SELECT")) {
            executeSelect(sql);
        } else {
            executeOthers(sql);
        }

    }

}
