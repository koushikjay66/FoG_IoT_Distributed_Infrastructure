/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import java.net.Socket;
import json.Builder.ResponseBuilder;
import json.Builder.objects.M2M_Request;
import server.sync.Leech;
import service.Service;

/**
 * 0
 *
 * @author Arshad
 */
public class Requestedinfo {

    public String ip;
    public Socket s;
    public String requestedString;
    public boolean possibleOrNot;
    private M2M_Request req;

    public Requestedinfo(Socket s, String ip, String requested) {
        this.ip = ip;
        this.s = s;
        this.requestedString = requested;

        possibleOrNot = analizeRequested();

    }// End of constructor

    /**
     * This method analyzes the requested String. if The request result can be
     * found in this server database or any other medium, Then returns true.
     * Implementation of this method is server Specific . For example , Level -
     * 1/ The main server will be able to give reply for all sorts of problems .
     * But level 2 can not . Another good example can be , suppose this server
     * does not know how to a process a specific service request. It may now can
     * redirect the requested IP to another server. Which may be done by
     * generateResult method.
     *
     * @return true/ false depending on the things .
     */
    public boolean analizeRequested() {
        Gson g = new Gson();
        req = g.fromJson(requestedString, M2M_Request.class);
        return true;
    }// End of method analizeResult

    public String generateResult() throws ClassNotFoundException {
        if (!possibleOrNot) {
            return "Invalid Request";
        }
        Service service = new Service(req);

        // Service compile if returns nulll then it is time to call the agent
        Object ar = service.compile();
        // If the Object is totally Null then I will request it 
        // To my parent or my siblings. 
        if (ar == null) {
            Leech l = new Leech(req);
            return l.startLeeching(requestedString);
        } // If optional parameters is present and I have not got the full things I will also request 
        // the optional things on the server. 
        else {
            ResponseBuilder rb = new ResponseBuilder(ar);
            return rb.compile();
        }// End of else
    }// End of method generateResult
}// End of class 
