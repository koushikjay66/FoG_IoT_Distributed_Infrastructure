/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCompositionSoa;

import database.mysql;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import service.Service;
import java.util.LinkedHashSet;
/**
 *input: service object
 * todo: get params and requested service name
 * check if requested complex service exists in the db
 * if exists then pull corresponding basic service info from db
 *      (*)call corresponding basic services to get the results of each basic services
 *      if any service doesn't exist then request for that specific not existing basic service to agent 
 *          and wait for its reply and when replied then add that to the database for future ref.
 *      else pass these basic service results to the Composer
 * else if the basic services (supplied as the optional parameters defining basic services dependencies)
 *      exists in the database then call those and then follow step (*)
 * else send the whole Service object to agent based node for further processing, when the agent based 
 *      node responses with the new Service and its dependencies, save it in the database for future use.
 *      (Here, the exact result will be save here in the db with a TTL or Validity time in order to use 
 *      it for latest requests And also save the dependencies (URL to the service) in order to 
 *      dynamically fetch result when TTL of the saved results has expired)
 * 
 * @author arsha
 */
public class ServiceBootstrap {
    public static Map<String,ArrayList<String>> r = new HashMap();
    public static ArrayList<String> al;
    public static void getComplexServiceValues(String serviceName){
        mysql result = new mysql("SELECT ss_name FROM complex_service, simple_service, service_relation "
                + "WHERE cs_name=\""+serviceName+"\" AND complex_service.csid=service_relation.csid AND "
                + "service_relation.ssid=simple_service.ssid" , "SELECT");
        
        for (Object i: result.res.keySet()) {
            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            String [] basicServices = basics.split(", ");
            
            for (int j = 0; j < basicServices.length; j++) {
//                System.out.println(basicServices[j]);
               al = getsimpleServiceValues(basicServices[j]);
               r.put(basicServices[j],al);
                
            }
            
//            System.out.println(i+"-> "+result.res.get(i).toString());
        }
    }
    
    public static ArrayList<String> getsimpleServiceValues(String serviceName){
        mysql result = new mysql("SELECT * FROM simple_service WHERE ss_name =\""+serviceName+"\"", "SELECT");
        ArrayList<String> vals= new ArrayList<String>();
        for (Object i: result.res.keySet()) {
            vals.add(i+"-> "+result.res.get(i).toString());
//            System.out.println(i+"-> "+result.res.get(i).toString());
        }
        return vals;
    }
    
    
    
    public static void main(String[] args){
        getComplexServiceValues("env");
        for (Object i: r.keySet()) {
            System.out.println(i.toString()+"->"+r.get(i).toString());
        }
    }
    
}
