/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sync;

import json.Builder.objects.M2M_Request;
import json.ReqestedParsedObject;
import server.Requestedinfo;
import server.Requestinfo;

/**
 * When a server does not have any particular service information, it may
 * request its parents or its mates. This Leech class provides all the necessary
 * thing for this application . This class is called by the Requested Info class
 * . When the {@link server.Requestedinfo#Requestedinfo(java.net.Socket, java.lang.String, java.lang.String)
 * } method calls it generates the appropriate logic and returns the result.
 *
 * @see server.Requestedinfo
 * @author Koushik
 */
public final class Leech {

    private final String TOKEN;
    private final String serviceName;
    
    public Leech(M2M_Request rpo) {
        this.token = rpo.TOKEN;
        this.serviceName = rpo.serviceName;
    }// End of Constructor 

    public String startLeeching(String requestedString) {
        Requestinfo rf = new Requestinfo();
        rf.sendData(requestedString);
        String data = rf.getReply();
        
        
        return data;
    }// End of method startLeeching

}// End of class Leech
