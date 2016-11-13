/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leech;

import com.google.gson.Gson;
import database.mysql;
import java.security.NoSuchAlgorithmException;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.SOA_server;
import json.parser.Reply_Parser;
import server.Iniciar;
import server.Requestinfo;

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
        SOA_server soa= parse(newData);
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
        SOA_server soa= g.fromJson(newData, SOA_server.class);
        return soa;
    }// End of parseAndUpdateDB
    
    private boolean updateDB(SOA_server soa){
        mysql m = new mysql();
        
        
    }// End of updateDB
}// End of class Leech
