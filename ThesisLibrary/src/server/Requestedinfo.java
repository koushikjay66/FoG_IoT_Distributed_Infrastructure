/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;
import json.Builder.ResponseBuilder;
import json.ReqestedParsedObject;
import json.Requestparser;
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
    public ReqestedParsedObject rpo;

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
        Requestparser p = new Requestparser(requestedString);
        rpo = p.getIt();
        return true;
    }// End of method analizeResult

    public String generateResult() {
        if (!possibleOrNot) {
            return "Invalid Request";
        }
        Service service = null;
        if (rpo.optionalParam.length == 0) {
            service = new Service(rpo.serviceName);
        } else {
            service = new Service(rpo.serviceName, rpo.optionalParam);
        }
        // Service compile if returns nulll then it is time to call the agent
        Object[] ar = service.compile();

        if (ar == null) {
            Leech l = new Leech(rpo);

            return l.startLeeching(requestedString);
        } else {
            ResponseBuilder rb = new ResponseBuilder(, rpo.token);
            return rb.compile();
        }
    }// End of method generateResult
}
