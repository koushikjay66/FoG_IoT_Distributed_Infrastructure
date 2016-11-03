/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import database.mysql;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import service.Service;
import json.Builder.GenericReply;
import json.Builder.objects.SOA_server;
import json.Builder.objects.SOA_server.Complex_Service;
import responseResult.RespResult;

/**
 *
 * @author arsha
 */
public class ReplyAgent {

    private SOA_server sr;
    private final mysql result;
    private Map<String, String> valuemap = new HashMap();
    public Object[] serviceResult;
    private String serviceName;

    public ReplyAgent(Service s) throws Exception {
        result = new mysql();
        this.serviceName = s.name;
        sr = new SOA_server();
        updateAgent ua = new updateAgent();
        getComplexServiceValues(serviceName);
        increaseUpdateCount(serviceName);
//        ua.increasePriority(serviceName); // increasePriority

        serviceResult = new Object[valuemap.size()];
        int k = 0;
        if (valuemap.keySet() != null) {
            for (Object i : valuemap.keySet()) {
                GenericReply bulala = new GenericReply(i.toString(), valuemap.get(i));
                System.out.println(valuemap.get(i));
                serviceResult[k] = bulala;
                k++;
            }
        }
    }// End of function constructor

    private void increaseUpdateCount(String serviceName) {

        String sql = "UPDATE complex_service SET cs_update_count = cs_update_count + 1 WHERE cs_name=\"" + serviceName + "\"";
        mysql result = new mysql();
    }

    /**
     *
     * @param serviceName - the required service name
     */
    // this searches for the complex service
    private void getComplexServiceValues(String serviceName) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        String sql = "SELECT ss_name FROM complex_service, simple_service, service_relation "
//                + "WHERE cs_name=\"" + serviceName + "\" AND complex_service.csid=service_relation.csid AND "
//                + "service_relation.ssid=simple_service.ssid";
//
//       
//        HashMap res = (HashMap) result.processQuery(sql);
//        if (res.get("ss_name").toString().equals("[]")) {
//            String str = getsimpleService(serviceName);
//            System.out.println(str);
//            if (str != null) {
//                valuemap.put(serviceName, str);
//            }
//        } else {
//            for (Object i : res.keySet()) {
//                String basics = res.get(i).toString().substring(1, res.get(i).toString().length() - 1);
//                String[] basicServices = basics.split(", ");// divide every 
//                for (int j = 0; j < basicServices.length; j++) {
//                    valuemap.put(basicServices[j], getsimpleService(basicServices[j]));
//                }
//
//            }// End of for loop 
//
//        }
        String sql = "SELECT * from complex_service WHERE cs_name=\"" + serviceName + "\"";
        HashMap res = (HashMap) result.processQuery(sql);
        int arcount = 0;
        if (res.get("cs_name").toString().equals("[]")) {

        } else {
            RespResult rr = new RespResult();
            Complex_Service scs = (new SOA_server()).new Complex_Service();
            
            ArrayList<String> arrL = rr.replyFormat("json.Builder.objects.SOA_server$Complex_Service");

            for (Object i : res.keySet()) {
                if (arrL.contains(i)) {
                    String temp = res.get(i).toString().substring(1, res.get(i).toString().length() - 1);
                    scs.getClass().getDeclaredField(i.toString()).set(scs,temp);
                    System.out.println(scs.getClass().getDeclaredField(i.toString()).get(scs));
                   
                }
            }
            sr.setC_Service(1);
            sr.C_Service[0]=scs;
        }
    }

    //This returns the basic service value as a string "serviceName,Value"
    private void getsimpleService(String serviceName) throws SQLException {

        String sql = "SELECT ss_value FROM simple_service WHERE ss_name =\"" + serviceName + "\"";
        HashMap res = (HashMap) result.processQuery(sql);
        String value = null;
//        if (!res.get("ss_value").toString().equals("[]")) {
//            for (Object i : res.keySet()) {
//                if (i.equals("ss_value")) {
//                    value = res.get(i).toString().substring(1, res.get(i).toString().length() - 1);
//                }
//            }
//        }
//        return value;
    }// End of method getSimpleService

    public int resultLength() {
        return serviceResult.length;
    }

    public Object[] result() {
        return serviceResult;
    }

}

