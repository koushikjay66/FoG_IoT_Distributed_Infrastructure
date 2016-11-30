/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import database.mysql;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.Main_server;
import json.Builder.objects.SOA_server.Complex_Service;
import queries.Queries;

/**
 *
 * @author Koushik Jay
 */
public class Main {

    private final mysql DB;
    private final String SERVICE_NAME;
    private Main_server response;

    public Main(String SERVICE_NAME) {
        DB = new mysql();
        this.SERVICE_NAME = SERVICE_NAME;
    }
    // This search Function First searches in the Complex table . 
    // If it does exists then get_complex service returns the csid. O
    // Otherwise it returns null. 
    // When it is null then it will directly call if the simple service of that name exists.

    public Main_server search() {

        try {
            Main_server.Complex_Service cs_id = get_complex();
            response = new Main_server();
            if (cs_id != null) {
                get_simple(cs_id.csid);
            } else {
                get_simple();
                if (response.B_Service.isEmpty()) {
                    return null;
                }
            }
            return response;
        } // End of function search
        catch (SQLException ex) {
            System.out.println("Error in search");
            //return null;
        }
        return null;
    }

    private Main_server.Complex_Service get_complex() throws SQLException {

        HashMap res = (HashMap) this.DB.processQuery(Queries.select_from_complex(SERVICE_NAME));

        if (((ArrayList) res.get("csid")).isEmpty()) {
            return null;
        }
        Main_server.Complex_Service cs = new Main_server().new Complex_Service();
        cs.csid = (String) ((ArrayList) res.get("csid")).get(0);
        cs.cs_name = (String) ((ArrayList) res.get("cs_name")).get(0);
        cs.cs_provider = (String) ((ArrayList) res.get("cs_provider")).get(0);
        return cs;

    }// End of function get_complex

    private void get_simple(String csid) throws SQLException {

        HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple_with_relation(csid));

        int rowLength = ((ArrayList) res.get("ss_name")).size();

        for (int i = 0; i < rowLength; i++) {
            Main_server.Simple_Service ss = (new Main_server()).new Simple_Service();
            ss.Ss_id = (String) ((ArrayList) res.get("ssid")).get(i);
            ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(i);
            ss.Ss_name = (String) ((ArrayList) res.get("ss_name")).get(i);
            ss.ss_protocal = (String) ((ArrayList) res.get("ss_protocol")).get(i);
            ss.ss_timestamp = (String) ((ArrayList) res.get("ss_timestamp")).get(i);
            ss.ss_ttl = (String) ((ArrayList) res.get("ss_TTL")).get(i);
            ss.ss_url = (String) ((ArrayList) res.get("ss_url")).get(i);
            response.B_Service.add(ss);
        }

    }// End of get_simple

    private void get_simple() throws SQLException {
        HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple(SERVICE_NAME));
        System.out.println("Ekhon error khabo");
        if (res.get("ss_name") != null && !((ArrayList) res.get("ss_name")).isEmpty()) {
            int rowLength = ((ArrayList) res.get("ss_name")).size();
            for (int i = 0; i < rowLength; i++) {
                Main_server.Simple_Service ss = (new Main_server()).new Simple_Service();
                ss.Ss_id = (String) ((ArrayList) res.get("ssid")).get(i);
                ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(i);
                ss.Ss_name = (String) ((ArrayList) res.get("ss_name")).get(i);
                ss.ss_protocal = (String) ((ArrayList) res.get("ss_protocol")).get(i);
                ss.ss_timestamp = (String) ((ArrayList) res.get("ss_timestamp")).get(i);
                ss.ss_ttl = (String) ((ArrayList) res.get("ss_TTL")).get(i);
                ss.ss_url = (String) ((ArrayList) res.get("ss_url")).get(i);
                response.B_Service.add(ss);
            }
        }
    }

}
