/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author arsha
 */
public class maintest {
    public static void main(String args[]) throws SQLException{
        mysql m = new mysql("SELECT * FROM COMPLEX_SERVICE" , "SELECT");
       // System.out.println(m.result.getString("csid"));;
        ArrayList <String>ar;
        for (Object i: m.res.keySet()) {
            System.out.println(i+"-> "+m.res.get(i));
        }
        
    }
}
