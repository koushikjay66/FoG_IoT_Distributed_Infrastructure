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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public class mysql {

    private Connection conn;
    private Statement st;
    public  ResultSet result;
    private int changed=-1;

    /**
     * This Constructor is only initializes connection
     */
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
                
                result=st.getResultSet();
            
        }

      
    }
    
    private void executeOthers(String sql) throws SQLException{
        
         st = conn.createStatement();
        if (st.execute(sql)) {
                
                changed=st.getUpdateCount();
            
        }


    }
    
    private void processQuery(String sql, String QueryType) throws SQLException{
        if(QueryType.equalsIgnoreCase("SELECT")){
            executeSelect(sql);
        }else{
            executeOthers(sql);
        }
            
    }

}
