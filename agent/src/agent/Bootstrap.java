/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import java.security.NoSuchAlgorithmException;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;

/**
 *
 * @author NewUsername
 */
public class Bootstrap {

    private final M2M_Request req;

    public Bootstrap(M2M_Request req) {
        this.req = req;
    }

    // This compile method is the driver for the Bootstrap class . 
    // It handles all the things. 
    // Including the main SOA class and the leech class.
    public M2M_Response compile() throws NoSuchAlgorithmException {
        // If it is REQ then I know The request has come and M2M Device 
        // In other words it is a normal REQUEST.
        // Now I need to call the SOA class
        if (req.TYPE.equalsIgnoreCase("REQ")) {
            // Do your Logic Here
        } else if (req.TYPE.equalsIgnoreCase("UPDATE")) {
            // Do your Logic Here
        }

        return null;
    }
}// End of Bootstrap class
