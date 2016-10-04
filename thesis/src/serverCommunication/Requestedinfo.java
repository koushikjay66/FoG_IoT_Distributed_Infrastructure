/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercommunication;

import java.net.Socket;
import json.ReqestedParsedObject;
import json.parser;
import service.Service;

/**
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
        System.out.println("Requested from " + ip + " MSG : " + requested);
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
        parser p = new parser(requestedString);
        rpo = p.getIt();
        return true;
    }// End of method analizeResult

    public String generateResult() {
        if (!possibleOrNot) {
            return "Invalid Request";
        }
        Service s = null;
        if (rpo.optionalParam.length == 0) {
             s = new Service(rpo.serviceName);
        }else{
             s = new Service(rpo.serviceName, rpo.optionalParam);
        }
        return s.compile();
    }// End of method generateResult
}
