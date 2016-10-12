/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import static agent.replyAgent.getsimpleServiceTTL;
import static agent.replyAgent.valuemap;
import database.mysqlAgent;



/**
 *
 * @author arsha
 */
public class updateAgent {
    
    public void updateTable(String serviceName){
         mysqlAgent result = new mysqlAgent("SELECT ss_name, ss_TTL, ss_timestamp FROM complex_service, simple_service, service_relation "
                + "WHERE cs_name=\""+serviceName+"\" AND complex_service.csid=service_relation.csid AND "
                + "service_relation.ssid=simple_service.ssid" , "SELECT");
        for (Object i: result.res.keySet()) {
            String rec = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            
            
        }
    }
    public void increasePriority(String serviceName){
        
    }
    public static void main(String[] args){ // just for checking
//        getComplexServiceValues("env");
//        for (Object i: valuemap.keySet()) {
//            
//            System.out.println(i.toString()+"-->"+valuemap.get(i).toString());
//        }


    }
}
