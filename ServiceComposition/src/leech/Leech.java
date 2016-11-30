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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private M2M_Response result;

    public Leech(M2M_Request req, M2M_Response res) {
        this.req = req;
        this.res = res;
    }

    // Driver class for all partialaservice request
    public void partial_service() throws NoSuchAlgorithmException {
        //System.out.println("Going for partial service to " +agents[0]);
        getUnavailable();

        String old_data = build_Req();
        String newData = null;
        if (req.COMPONENTS.size() == 0) {
            result = res;
        } else {
            newData = handleMultiple(old_data);

            // If new Data is null then it is time to call main server
            if (newData == null) {
                Requestinfo rs = new Requestinfo(Iniciar.PARENT_SERVER_IP);
                rs.sendData(newData);
                newData = rs.getReply();
                // New it is time to start parsing . 
            }
            SOA_server soa = parse(newData);
            try {
                generate_result(soa);
                updateDB(soa);

            } catch (SQLException ex) {
                Logger.getLogger(Leech.class.getName()).log(Level.SEVERE, null, ex);
                result = null;
            }
        }
    }// End of partial_service

    private void getUnavailable() {

        for (int i = 0; i < res.B_Service.size(); i++) {
            if (req.COMPONENTS.contains(res.B_Service.get(i).Ss_name)) {
                req.COMPONENTS.remove(res.B_Service.get(i).Ss_name);
            }
        }

    }// End 

    private String build_Req() throws NoSuchAlgorithmException {
        M2M_Request req = new M2M_Request("REQ");
        String values = "";
        for (int i = 0; i < this.req.COMPONENTS.size(); i++) {
            values += this.req.COMPONENTS.get(i).toString() + ",";
        }
        req.build(this.req.USERID, this.req.PASSWORD, this.req.SERVICE_NAME, values);
        return Builder.compile(req);
        //return Builder.compile(req.build(this.req.USERID, this.req.PASSWORD, this.req.SERVICE_NAME, values.substring(0, values.length() - 1)));
    }// End of function 

    private String handleMultiple(String JSON) {
        for (int i = 0; i < agents.length; i++) {
            System.out.println("Now Requesting to " + agents[i]);
            Requestinfo ri = new Requestinfo(agents[i]);
            ri.sendData(JSON);
            String newData = ri.getReply();
            System.out.println(newData);
            if (!newData.equals("{}")) {
                System.out.println("Returning Now");
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
            System.out.println("This is a complex service");
            update_complex_services(soa.C_Service);
            update_simple_table(soa.B_Service, soa.C_Service);

        }

        String csname = soa.C_Service.cs_name;

    }// End of updateDB

    private String getID(String sql, String column) throws SQLException {
        mysql m = new mysql();

        String id = null;
        System.out.println(column);
        ArrayList c = ((ArrayList) ((HashMap) m.processQuery(sql)).get(column));
        System.out.println(c.toArray());
        if (c != null) {
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
        String temp = getID(Queries.select_from_complex(cs.cs_name), "csid");
        if (temp == null) {

            m.processQuery(Queries.insert_complex_service(cs));
        }
    }// End of update_complex_services

    public void generate_result(SOA_server soa) {
        result = res;
        for (int i = 0; i < soa.B_Service.size(); i++) {
            M2M_Response.Simple_Service ss = new M2M_Response().new Simple_Service();
            ss.Ss_name = soa.B_Service.get(i).ss_name;
            ss.ss_value = soa.B_Service.get(i).ss_value;
            result.B_Service.add(ss);
        }
    }// End of generate_result

    public M2M_Response result() {
        return result;
    }
}// End of class Leech
