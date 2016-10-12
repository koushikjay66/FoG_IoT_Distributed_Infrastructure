/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import database.mysql;
import database.mysqlAgent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author arsha
 */
public class replyAgent {
    public static Map<String,String> valuemap = new HashMap();
        
    public Map<String,String> replyAgent(String serviceName){
        getComplexServiceValues(serviceName);
//        increasePriority(serviceName); // increasePriority
        return valuemap;  // update needed in SOA class to update SOA database with this new service
    }
    /**
     * 
     * @param serviceName - the required service name 
     */
    // this searches for the complex service
    public static void getComplexServiceValues(String serviceName){ 
        mysqlAgent result = new mysqlAgent("SELECT ss_name FROM complex_service, simple_service, service_relation "
                + "WHERE cs_name=\""+serviceName+"\" AND complex_service.csid=service_relation.csid AND "
                + "service_relation.ssid=simple_service.ssid" , "SELECT");
        
        for (Object i: result.res.keySet()) {
            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            
            if (basics.length()!=0) {
                System.out.println(result.res.get(i));
                String [] basicServices = basics.split( ", ");// divide every 
            
                for (int j = 0; j < basicServices.length; j++) {
                    valuemap.put(basicServices[j],getsimpleServiceTTL(basicServices[j]));
                }
            }else{
                //ask cloud
            }
            
        }
    }
    
    //This returns the basic service value as a string "serviceName,Value"
    public static String getsimpleServiceTTL(String serviceName){
        mysqlAgent result = new mysqlAgent("SELECT ss_name, ss_value FROM simple_service WHERE ss_name =\""+serviceName+"\"", "SELECT");
        String name="",value="";
        for (Object i: result.res.keySet()) {
            if (i.equals("ss_name")) {
                 name = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
//                System.out.println("name: "+name);
            }
            else if (i.equals("ss_value")) {
                 value = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
//                System.out.println("value: "+ value);
            }
//            else if (i.equals("ss_TTL")) {
//                 ttl = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
////                System.out.println("name: "+name);
//            }
//            else if (i.equals("ss_timestamp")) {
//                 time = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
////                System.out.println("value: "+ value);
//            }
        }
        String str = name+","+value;
        return str ;
    }
    
    public static double ttlCount(String name,String timestamp){ //need this fixed calculation not working
        long diff=0;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date parsedDate = dateFormat.parse(timestamp);
            Timestamp timestmp = new java.sql.Timestamp(parsedDate.getTime());
//         System.out.println(timestmp);
            long st = timestmp.getTime();
            long ct = System.currentTimeMillis();
            diff = ct-st;
//            System.out.println(name+" service time: "+st);
//            System.out.println(name+" current time: "+ct);
//            System.out.print(name+" ttl Differance: ");
//            System.out.println(name+ " " +(diff/1000.0)+ " seconds");
        }catch(Exception e){
            System.out.println(name+" Time calculation error");
        }
        return (diff/1000.0);
    }
    
    
    public static void main(String[] args){ // just for checking
        getComplexServiceValues("env");
        for (Object i: valuemap.keySet()) {
            
            System.out.println(i.toString()+"-->"+valuemap.get(i).toString());
        }
    }
    
    
}
