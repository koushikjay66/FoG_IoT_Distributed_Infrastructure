/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCompositionSoa;

import database.mysql;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
    /**
    * r - results recieved from the query with key as complexService name and Arraylist of basic services
    *  timestampres - Basic service timestamp values, key as basic ServiceName
    *  ttlres - Basic service ttl differance values, key as basic ServiceName
    *  valueres - Basic service values, key as basic ServiceName
     */
    //donotneed all these maps check later to remove if neccessary
    public static Map<String,ArrayList<String>> r = new HashMap();
    public static Map<String,String> timestampres = new HashMap();
    public static Map<String,Double> ttlres = new HashMap();
    public static Map<String,String> valuesres = new HashMap(); // this is the final map that gets to the composer
    
    public static ArrayList<String> al;
    public static String[] time;// not needed later
    public static int count; // not needed later
    
    
    public static void getComplexServiceValues(String serviceName){
        mysql result = new mysql("SELECT ss_name FROM complex_service, simple_service, service_relation "
                + "WHERE cs_name=\""+serviceName+"\" AND complex_service.csid=service_relation.csid AND "
                + "service_relation.ssid=simple_service.ssid" , "SELECT");
        
        for (Object i: result.res.keySet()) {
            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            String [] basicServices = basics.split(", ");
//            System.out.println(i.toString()+"d"+result.res.get(i).toString());
            for (int j = 0; j < basicServices.length; j++) {
               al = getsimpleServiceValues(basicServices[j]);
               r.put(basicServices[j],al);
            }
            
        }
    }
    
    static double value=0;
    public static ArrayList<String> getsimpleServiceValues(String serviceName){
        mysql result = new mysql("SELECT * FROM simple_service WHERE ss_name =\""+serviceName+"\"", "SELECT");
        ArrayList<String> vals= new ArrayList<String>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<Future<String>>();// for getting values later from threads
        double timeCount=0;// for ttl calculation
        double ttlCount=0;// for ttl calculation
       String url="";
        for (Object i: result.res.keySet()) {
            String rslt=result.res.get(i).toString();
            
            if (i.equals("ss_timestamp")) {
                String str = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
                timeCount =  ttlCount(serviceName,str);
                timestampres.put(serviceName, str);
                System.out.println("Timestamp: "+str);
            }     
            else if (i.equals("ss_url")) {
                url = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
                System.out.println("url: "+url);
            }
            else if (i.equals("ss_TTL")) {
                String str = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
                ttlCount =  Double.parseDouble(str);
                ttlres.put(serviceName, ttlCount);
                
            }else if (i.equals("ss_value")) {
                String str = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
                    
                value =  Double.parseDouble(str);
                valuesres.put(serviceName, str);// str for check only here goes value
                System.out.println("value: "+value);
            }
            vals.add(i+"-> "+rslt);
        }
        
        System.out.println(ttlCount+" - "+timeCount);
            double timeDiff= ttlCount-timeCount;
                if (timeDiff < 0) { //this is to make ttl difference
                    Callable<String> t = new Threads(serviceName,url); // Callable and future for threads giving value
                    Future<String> future = executor.submit(t);
                    list.add(future);
                }
                System.out.println("ttl: "+timeDiff);
        
        for(Future<String> fut : list){
            try {
                //print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                String future = fut.get();
//                System.out.println(future);
                String [] temp = future.split("--");
                valuesres.put(temp[0], temp[1]);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return vals;
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
    
    public static void main(String[] args){ // check only remove later
        getComplexServiceValues("env");
//        for (Object i: r.keySet()) {
//            
//            System.out.println(i.toString()+"->"+r.get(i).toString());
//        }
        for (Object i: valuesres.keySet()) {
            
            System.out.println(i.toString()+"-->"+valuesres.get(i).toString());
        }
        
    }
    
    
}