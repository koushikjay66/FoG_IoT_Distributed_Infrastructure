/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Koushik
 */
public class mysql {
    Connection conn;
    /**
     * This Constructor is only initializes connection 
     */
    public mysql(){
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/services_db?" +
                            "user=root&password=");
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Saves the 
     * @param sql The SQL to execute 
     * @return if the query is successfull then it will return true or false .
     */
    public boolean  execute(String sql){
    
        return true;
    }
    
    
}
