/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainServer;


import database.mysql;
import java.util.HashMap;
import java.util.Map;
import service.Service;
import json.Builder.M2MReply;

/**
 *
 * @author astha
 */
public class mainServer {
    
     public  Map<String, String> valuemap = new HashMap();
    public Object[] serviceResult;
    private String serviceName;

    public mainServer(Service s) {
        this.serviceName = s.name;
        
        getComplexServiceValues(serviceName);

        serviceResult = new Object[valuemap.size()];
        int k = 0;
        if (valuemap.keySet() != null) {
            for (Object i : valuemap.keySet()) {
                M2MReply bulala = new M2MReply(i.toString(), valuemap.get(i).toString());
                serviceResult[k] = bulala;
                k++;
            }
        }
    }// End of function constructor
    
   public  void getComplexServiceValues(String serviceName) {
        String sql = "SELECT ss_name FROM complex_service, simple_service, service_relation "
                + "WHERE cs_name=\"" + serviceName + "\" AND complex_service.csid=service_relation.csid AND "
                + "service_relation.ssid=simple_service.ssid";

        mysql result = new mysql(sql, "SELECT", "main_server_db");

        if (result.res.get("ss_name").toString().equals("[]")) {
            valuemap.put(serviceName, getsimpleService(serviceName));
        } else {
            for (Object i : result.res.keySet()) {
                String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length() - 1);
                String[] basicServices = basics.split(", ");// divide every 
                for (int j = 0; j < basicServices.length; j++) {
                    valuemap.put(basicServices[j], getsimpleService(basicServices[j]));
                }

            }// End of for loop 

        }
    }

    //This returns the basic service value as a string "serviceName,Value"
    public  String getsimpleService(String serviceName) {

        String sql = "SELECT ss_value FROM simple_service WHERE ss_name =\"" + serviceName + "\"";
        mysql result = new mysql(sql, "SELECT", "main_server_db");

        String value = "";
        if (!result.res.get("ss_value").toString().equals("[]")) {
            for (Object i : result.res.keySet()) {
                if (i.equals("ss_value")) {
                    value = result.res.get(i).toString().substring(1, result.res.get(i).toString().length() - 1);
                }
            }
        }
        return value;
    }// End of method getSimpleService


  
}
