/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updateagent;

import com.google.gson.Gson;
import database.mysql;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.Main_server;
import org.omg.PortableInterceptor.RequestInfo;
import queries.Queries;
import server.Requestinfo;

/**
 *
 * @author koushik
 */
public class UpdateAgent {

    private final mysql db = new mysql("localhost", "root", "", "agent_lookup_table");

    public UpdateAgent() {
        HashMap h = (HashMap) db.processQuery(Queries.get_all_simple_service());
        ArrayList ss_id = ((ArrayList) h.get("ss_id"));
        for (int i = 0; i < ss_id.size(); i++) {
            HashMap ss = (HashMap) db.processQuery(Queries.get_simple_s_ip(ss_id.get(i).toString()));
            ArrayList as = (ArrayList) ss.get("ip");
            for (int j = 0; j < as.size(); j++) {
                System.out.println(as.get(j).toString());

                try {
                    Requestinfo rs = new Requestinfo(as.get(j).toString());

                    rs.sendData(Builder.compile(new Req(ss_id.get(i).toString())));

                    String jsonReply = rs.getReply();
                    Gson g = new Gson();
                    Main_server ms = g.fromJson(jsonReply, Main_server.class);
                    String val = ms.B_Service.get(0).ss_value;
                    if (val == null) {
                        db.processQuery(Queries.decrease_priority(ss_id.get(i).toString(), as.get(j).toString(), 2));
                    } else {
                        db.processQuery(Queries.update_value(ss_id.get(i).toString(), val));
                    }

                } catch (IOException ex) {
                    System.out.println("Server Not Available");
                    db.processQuery(Queries.decrease_priority(ss_id.get(i).toString(), as.get(j).toString(), 1));
                }

            }// End 
        }

    }// ENd 

    public class Req {

        public String SERVICE_NAME;

        public Req(String service_name) {
            this.SERVICE_NAME = service_name;
        }

    }// End of 
}// End of UpdateAgent
