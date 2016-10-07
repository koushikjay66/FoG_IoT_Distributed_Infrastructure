/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;

/**
 *
 * @author arsha
 */
public class maintest {
    public static void main(String args[]) throws SQLException{
        mysql m = new mysql("SELECT * FROM complex_service" , "SELECT");
        //System.out.println(m.result.getString("csid"));;
        
        for (Object i: m.res.keySet()) {
            System.out.println(i+"-> "+m.res.get(i).toString());
        }
        
       
    }
}
