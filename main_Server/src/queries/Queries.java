/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

/**
 *
 * @author NewUsername
 */
import json.Builder.objects.SOA_server;

/**
 *
 * @author Koushik
 */
public abstract class Queries {

    public static String select_from_complex(String cs_name) {
       // System.out.println("SELECT csid FROM `complex_service` where cs_name=\""+cs_name+"\" LIMIT 1");
        return "SELECT * FROM `complex_service` where cs_name=\""+cs_name+"\" LIMIT 1";
    }// End of 
    public static String select_from_simple_with_relation(String cs_id){
        return "SELECT * FROM simple_service WHERE ssid IN(SELECT ssid FROM service_relation WHERE csid =\""+cs_id+"\") ";
    }// End
    public static String select_from_simple(String ss_name){
        //System.out.println("SELECT ssid FROM `simple_service` where ss_name=\""+ss_name+"\" LIMIT 1");
        return "SELECT * FROM `simple_service` where ss_name=\""+ss_name+"\" LIMIT 1";
    }// End 
    
}// End of Class queries
