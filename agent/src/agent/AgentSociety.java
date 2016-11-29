/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import agent.queries.Queries;
import database.mysql;
import java.util.ArrayList;
import java.util.HashMap;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.SOA_server;
import json.Builder.objects.SOA_server.Complex_Service;
import json.Builder.objects.SOA_server.Simple_Service;

/**
 *
 * @author NewUsername
 */
public class AgentSociety {

    private final M2M_Request req;

    public AgentSociety(M2M_Request req) {
        this.req = req;

    }

    // This compile method is the driver for the Agent Society class . 
    // It handles all the things. 
    public SOA_server compile() {
        CheckerAgent ca;
        if (req.COMPONENTS.isEmpty()) {
            ca = new CheckerAgent(req.SERVICE_NAME);
        } else {
            ca = new CheckerAgent(req.SERVICE_NAME, req.COMPONENTS);
        }
        // If agent has found that in its database then it will reply that to the SOA server
        if (ca.result != null) {
            System.out.println("Ami Null Na"+ ca.result);
            return ca.result;
        }

        return null;
    }

    private class ReplyAgent {
    }// End of replyagent

    private class RequestAgent {
    }// end of request agent

    private class CheckerAgent {

        SOA_server result = null;
        final mysql DB = new mysql("localhost", "root", "", "agent_lookup_table");
        final String SERVICE_NAME;

        CheckerAgent(String serviceName) {
            this.SERVICE_NAME = serviceName;
            get_complex();
            if (getResult() == null) {
                //call get simple with no argument 
                get_simple();

            } else {
                get_simple(result.C_Service.csid);
            }
        }// End of constructor 

        CheckerAgent(String serviceName, ArrayList a) {
            this.SERVICE_NAME = serviceName;
            get_complex();
            if (getResult() != null) {
                get_simple(result.C_Service.csid, a);
            }
        }// End of constructor 

        void get_complex() {

            HashMap res = (HashMap) this.DB.processQuery(Queries.select_from_complex(SERVICE_NAME));

            if (!((ArrayList) res.get("csid")).isEmpty()) {
                result = new SOA_server();
                Complex_Service cs = (new SOA_server()).new Complex_Service();
                cs.cs_name = (String) ((ArrayList) res.get("cs_name")).get(0);
                cs.cs_provider = (String) ((ArrayList) res.get("cs_provider")).get(0);
                cs.csid = (String) ((ArrayList) res.get("csid")).get(0);
                result.C_Service = cs;
            }

        }// End of function get_complex    

        /*
         If there is no complex service assocation with the request this 
        method will be called . This will set the value for every thing 
        
         */
        void get_simple() {
            HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple(SERVICE_NAME));
            if (!((ArrayList) res.get("ss_name")).isEmpty()) {
                result = new SOA_server();
                Simple_Service ss = (new SOA_server()).new Simple_Service();
                ss.ss_id = (String) ((ArrayList) res.get("ss_id")).get(0);
                ss.ss_name = (String) ((ArrayList) res.get("ss_name")).get(0);
                ss.ss_protocal = (String) ((ArrayList) res.get("ss_protocol")).get(0);
                ss.ss_timestamp = (String) ((ArrayList) res.get("ss_timestamp")).get(0);
                ss.ss_url = (String) ((ArrayList) res.get("ss_url")).get(0);
                ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(0);
                result.B_Service.add(ss);
            }
        }// End of get Simple

        void get_simple(String csid) {

            HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple_with_relation(csid));

            int rowLength = ((ArrayList) res.get("ss_name")).size();
            System.out.println(rowLength);
            for (int i = 0; i < rowLength; i++) {
                Simple_Service ss = (new SOA_server()).new Simple_Service();
                ss.ss_id = (String) ((ArrayList) res.get("ssid")).get(i);
                ss.ss_name = (String) ((ArrayList) res.get("ss_name")).get(i);
                ss.ss_protocal = (String) ((ArrayList) res.get("ss_protocol")).get(i);
                ss.ss_timestamp = (String) ((ArrayList) res.get("ss_timestamp")).get(i);
                ss.ss_url = (String) ((ArrayList) res.get("ss_url")).get(i);
                ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(i);
                result.B_Service.add(ss);
            }
        }// End of get_simple

        void get_simple(String csid, ArrayList optionalParam) {

            for (int ii = 0; ii < optionalParam.size(); ii++) {
                HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple_with_optional_param(csid, optionalParam.get(ii).toString()));
                if (res.containsKey("ssid") && !((ArrayList)(res.get("ssid"))).isEmpty()) {
                    System.out.println("Dhukchi");
                    Simple_Service ss = (new SOA_server()).new Simple_Service();
                    ss.ss_id = (String) ((ArrayList) res.get("ssid")).get(0);
                    ss.ss_name = (String) ((ArrayList) res.get("ss_name")).get(0);
                    ss.ss_protocal = (String) ((ArrayList) res.get("ss_protocol")).get(0);
                    ss.ss_timestamp = (String) ((ArrayList) res.get("ss_timestamp")).get(0);
                    ss.ss_url = (String) ((ArrayList) res.get("ss_url")).get(0);
                    ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(0);
                    System.out.println("HAGU");
                    result.B_Service.add(ss);
                }

            }

        }// End of get_simple

        SOA_server getResult() {
            return result;
        }
    }// end of checkeragent

}// End of Bootstrap class
