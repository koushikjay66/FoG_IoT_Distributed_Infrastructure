/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.security.NoSuchAlgorithmException;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.Main_server;

/**
 *
 * @author Koushik Jay
 */
public class Bootstrap {
    private final M2M_Request req;
    public Bootstrap(M2M_Request req) {
        this.req = req;
    }
    
  public Main_server compile() throws NoSuchAlgorithmException {
        // If it is REQ then I know The request has come and M2M Device 
        // In other words it is a normal REQUEST.
        // Now I need to call the SOA class
        if (req.TYPE.equalsIgnoreCase("REQ")) {
            Main m = new Main(req.SERVICE_NAME);
            return m.search();
        }

        return null;
    }
  
}
