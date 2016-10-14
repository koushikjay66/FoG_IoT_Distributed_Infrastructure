/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import database.mysql;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/**
 *
 * @author arsha
 */
public class updateAgent {
    public static Map<String,String> ttlmap = new HashMap();
    public static Map<String,String> timestampmap = new HashMap();
    
    public static String [] csname;
    public static ArrayList<String> simples = new ArrayList<>();
    
    
//    public updateAgent(){
//        
//    }
//    
//    public static void updateTable(){
//        //get complex service by priority
//        String sql = "SELECT cs_name FROM complex_service ORDER BY cs_priority ASC";
//        
//         mysql result = new mysql(sql , "SELECT", "agent_lookup_table");
//         
//         
//        for (Object i: result.res.keySet()) {
//            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
//            System.out.println(basics);
//            if (basics.length()!=0) {
//                String [] basicServices = basics.split(", ");
//                if (i.equals("cs_name")) {
//                    csname = new String[basicServices.length];
//                    for (int j = 0; j < csname.length; j++) {
//                        csname[j]=basicServices[j];
//                    }
//                }
//            }else{
//                System.out.println("No service available");
//            }
//        }// finished getting complex service
//        //gradually get every basic service of complex services and update its value
//        for (int i = 0; i < csname.length; i++) {
//            updateComponents(csname[i]);
//        }
//    }
//    public static String [] ssname;
//    public static String [] ssurl;
//    public static String [] ssvalue;
//    public static void updateComponents(String serviceName){
//        
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        List<Future<String>> list = new ArrayList<Future<String>>();// for getting values later from threads
//        
//        String sql= "SELECT ss_name, ss_url FROM complex_service, simple_service, service_relation "
//                + "WHERE cs_name=\""+serviceName+"\" AND complex_service.csid=service_relation.csid AND "
//                + "service_relation.ssid=simple_service.ssid";
//        
//        mysql result = new mysql(sql , "SELECT", "agent_lookup_table");
//        //get all ssnames and url
//        for (Object i: result.res.keySet()) {
//            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
////            System.out.println(basics);
//            if (basics.length()!=0) {
//                String [] basicServices = basics.split(", ");
//                if (i.equals("ss_name")) {
//                    ssname = new String[basicServices.length];
//                    for (int j = 0; j < ssname.length; j++) {
//                        ssname[j]=basicServices[j];
//                    }
//                }
//                else if (i.equals("ss_url")) {
//                    ssurl = new String[basicServices.length];
//                    for (int j = 0; j < ssurl.length; j++) {
//                        ssurl[j]=basicServices[j];
//                    }
//                }
//            }else{
//                System.out.println("No service available");
//            }
//        }//finished getting all ssname and urls
//        //http req for all ssname's new value
//        for (int i = 0; i < ssname.length; i++) {
//              Callable<String> t = new Threads3(ssname[i],ssurl[i]); // Callable and future for threads giving value
//                Future<String> future = executor.submit(t);
//                list.add(future);
//        }
//        ssvalue = new String[ssname.length];
//        for(Future<String> fut : list){
//            try {
//                //print the return value of Future, notice the output delay in console
//                // because Future.get() waits for task to get completed
//                String future = fut.get();
////                System.out.println(future);
//                String [] temp = future.split("--");
//                int index = Arrays.asList(ssname).indexOf(temp[0]);
//                ssvalue[index]=temp[1];
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        executor.shutdown();
//        // http request done and got all new values
//        //update in database
//        for (int i = 0; i < ssname.length; i++) {
//            updateValues(ssname[i],ssvalue[i]);
//        }
//    }
//    public static void updateValues(String serviceName,String serviceValue){
//        String sql= "UPDATE simple_service SET ss_value=\""+serviceValue+"\" AND ss_timestamp= "
//                + "WHERE ss_name=\""+serviceName+"\"";
//        
//        mysql result = new mysql(sql, "UPDATE", "agent_lookup_table");
//    }
    
    String [] prionames;
    public void increasePriority(String serviceName){
        String sql = "SELECT cs_name FROM complex_service ORDER BY cs_update_count DESC";
        mysql result = new mysql(sql , "SELECT", "agent_lookup_table");
        
        for (Object i: result.res.keySet()) {
            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            prionames = basics.split(", ");
        }
        for (int i = 0; i < prionames.length; i++) {
            changePriority(prionames[i],i+1);
        }
    }// increasePriority di=one
    
    
    
    public static void changePriority(String serviceName, int i){
        String sql = "UPDATE complex_service SET cs_priority="+i+"WHERE cs_name=\""+serviceName+"\"";
         mysql result = new mysql(sql, "UPDATE", "agent_lookup_table");
    }//End of changePriority
    
}