/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;

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
