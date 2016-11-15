/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.queries;

import json.Builder.objects.SOA_server;

/**
 *
 * @author Koushik
 */
public abstract class Queries {

    public static String select_from_complex(String cs_name) {
        System.out.println("SELECT csid FROM `complex_service` where cs_name=\""+cs_name+"\" LIMIT 1");
        return "SELECT csid FROM `complex_service` where cs_name=\""+cs_name+"\" LIMIT 1";
    }// End of 
    public static String select_from_simple_with_relation(String cs_id){
        return "SELECT ss_name, ss_value FROM simple_service WHERE ssid IN(SELECT ssid FROM service_relation WHERE csid =\""+cs_id+"\") ";
    }// End
    public static String select_from_simple(String ss_name){
        return "SELECT ssid FROM `simple_service` where ss_name=\""+ss_name+"\" LIMIT 1";
    }// End 
    
    public static String insert_complex_service(SOA_server.Complex_Service soa){
        String sql = "INSERT INTO complex_service VALUES("
                +"\""+soa.csid+"\", "
                +"\""+soa.cs_name+"\", "
                +"\""+soa.cs_provider+"\""
                + ")";
        return sql;
        
    }
    
    public static String insert_simple_service(SOA_server.Simple_Service simple){
       return  "INSERT INTO simple_service VALUES ("
               +"\""+simple.ss_id+"\", "
               +"\""+simple.ss_name+"\", "
               +"\""+simple.ss_value+"\", "
               +"\""+simple.ss_protocal+"\", "
               +"\""+simple.ss_url+"\", "
               +"\""+simple.ss_ttl+"\", "
               +"\""+simple.ss_timestamp+"\", "
               +")";
    }// End of function 
    
     public static String insert_relation(SOA_server.Simple_Service ss, SOA_server.Complex_Service cs){
       return  "INSERT INTO simple_service VALUES ("
               +"\""+cs.csid+"\", "
               +"\""+ss.ss_id+"\", "
               +")";
    }// End of function 
}// End of Class queries
