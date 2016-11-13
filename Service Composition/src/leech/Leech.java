/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leech;

import com.google.gson.Gson;
import database.mysql;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.SOA_server;
import server.Iniciar;
import server.Requestinfo;
import soa.queries.Queries;

/**
 *
 * @author Koushik
 */
public class Leech {

    private final String main_server = Iniciar.PARENT_SERVER_IP;
    private final String agents[] = Iniciar.SAME_FEATHERS_IP;
    private final M2M_Request req;
    private final M2M_Response res;

    public Leech(M2M_Request req, M2M_Response res) {
        this.req = req;
        this.res = res;
    }

    // Driver class for all partial service request
    public void partial_service() throws NoSuchAlgorithmException {
        String old_data = build_Req(getUnavailable());
        String newData = handleMultiple(old_data);

        // If new Data is null then it is time to call main server
        if (newData == null) {
            Requestinfo rs = new Requestinfo(Iniciar.PARENT_SERVER_IP);
            rs.sendData(newData);
            newData = rs.getReply();

            // New it is time to start parsing . 
        }
        SOA_server soa = parse(newData);
    }// End of partial_service

    private String[] getUnavailable() {

        int number_of_unfound = req.COMPONENTS.size() - res.B_Service.size();
        String unavailable[] = new String[number_of_unfound];
        int size = 0;
        for (int i = 0; i < req.COMPONENTS.size(); i++) {
            if (!req.COMPONENTS.get(i).equals(res.B_Service.get(i).Ss_name)) {
                unavailable[size] = req.COMPONENTS.get(i);
                size++;
            }
        }
        return unavailable;
    }

    private String build_Req(String[] unavailable) throws NoSuchAlgorithmException {
        M2M_Request req = new M2M_Request("REQ");
        String values = "";
        for (int i = 0; i < unavailable.length; i++) {
            values += unavailable[i] + ",";
        }

        return Builder.compile(req.build(this.req.USERID, this.req.PASSWORD, this.req.SERVICE_NAME, values.substring(0, values.length() - 1)));
    }// End of function 

    private String handleMultiple(String JSON) {

        for (int i = 0; i < agents.length; i++) {
            Requestinfo ri = new Requestinfo(agents[i]);
            ri.sendData(JSON);
            String newData = ri.getReply();
            if (!ri.getReply().equals("{}")) {
                return newData;
            }
        }
        return null;
    }// End of handleMultiple

    private SOA_server parse(String newData) {
        Gson g = new Gson();
        SOA_server soa = g.fromJson(newData, SOA_server.class);
        return soa;
    }// End of parseAndUpdateDB

    private void updateDB(SOA_server soa) throws SQLException {
        if (soa.C_Service == null) {
            // I am sure this is not complex service
            if (!soa.B_Service.isEmpty()) {
                update_simple_table(soa.B_Service);
            } else {
                //invalid request (fuck it)

            }
        } else {
            update_complex_services(soa.C_Service);
        }

        String csname = soa.C_Service.cs_name;

    }// End of updateDB

    private String getID(String sql, String column) throws SQLException {
        mysql m = new mysql();

        String id = null;
        ArrayList c = ((ArrayList) ((HashMap) m.processQuery(sql)).get(column));
        if (!c.isEmpty()) {
            id = c.get(0).toString();

        }
        return id;
    }// End of getID

    private void update_simple_table(ArrayList magi) throws SQLException {
        mysql m = new mysql();
        for (int i = 0; i < magi.size(); i++) {
            m.processQuery(Queries.insert_simple_service((SOA_server.Simple_Service) magi.get(i)));
        }
    }// End of update_simple_table

    private void update_simple_table(ArrayList magi, SOA_server.Complex_Service cs) throws SQLException {
        mysql m = new mysql();
        for (int i = 0; i < magi.size(); i++) {
            m.processQuery(Queries.insert_simple_service((SOA_server.Simple_Service) magi.get(i)));
            m.processQuery(Queries.insert_relation((SOA_server.Simple_Service) magi.get(i), cs));
        }
    }// End of update_simple_table

    private void update_complex_services(SOA_server.Complex_Service cs) throws SQLException {
        mysql m = new mysql();
        String temp = getID(Queries.select_from_complex(cs.cs_name), "cs_id");
        if (temp == null) {

            m.processQuery(Queries.insert_complex_service(cs));
        }

    }// End of update_complex_services
}// End of class Leech
