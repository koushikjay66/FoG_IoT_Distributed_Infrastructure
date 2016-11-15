/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa;

import database.mysql;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.M2M_Response.Simple_Service;
import soa.queries.Queries;

/**
 *
 * @author Koushik
 */
public final class SOA {

    private final mysql DB;
    private final String SERVICE_NAME;
    private M2M_Response response;

    /**
     *
     * @param SERVICE_NAME The Service Name that has been Requested By the M2M
     * Device
     */
    public SOA(String SERVICE_NAME) {
        DB = new mysql();
        this.SERVICE_NAME = SERVICE_NAME;

    }// End of constructor 

    // This search Function First searches in the Complex table . 
    // If it does exists then get_complex service returns the csid. O
    // Otherwise it returns null. 
    // When it is null then it will directly call if the simple service of that name exists.
    public M2M_Response search() {

        try {
            String cs_id = get_complex();
            response = new M2M_Response();
            if (cs_id != null) {
                get_simple(cs_id);
            } else {
                get_simple();
                if (response.B_Service.isEmpty()) {
                    return null;
                }
            }
            return response;
        } // End of function search
        catch (SQLException ex) {
            Logger.getLogger(SOA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String get_complex() throws SQLException {

        HashMap res = (HashMap) this.DB.processQuery(Queries.select_from_complex(SERVICE_NAME));

        if (((ArrayList) res.get("csid")).isEmpty()) {
            return null;
        }

        return (String) ((ArrayList) res.get("csid")).get(0);

    }// End of function get_complex

    private void get_simple(String csid) throws SQLException {

        HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple_with_relation(csid));

        int rowLength = ((ArrayList) res.get("ss_name")).size();

        for (int i = 0; i < rowLength; i++) {
            Simple_Service ss = (new M2M_Response()).new Simple_Service();
            ss.Ss_name = (String) ((ArrayList) res.get("ss_name")).get(i);
            ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(i);
            response.B_Service.add(ss);
        }
    }// End of get_simple

    private void get_simple() throws SQLException {
        HashMap res = (HashMap) DB.processQuery(Queries.select_from_simple(SERVICE_NAME));
        if (!((ArrayList) res.get("ss_name")).isEmpty()) {
            int rowLength = ((ArrayList) res.get("ss_name")).size();
            for (int i = 0; i < rowLength; i++) {
                Simple_Service ss = (new M2M_Response()).new Simple_Service();
                ss.Ss_name = (String) ((ArrayList) res.get("ss_name")).get(i);
                ss.ss_value = (String) ((ArrayList) res.get("ss_value")).get(i);
                response.B_Service.add(ss);
            }
        }
    }
}// End of class soa
