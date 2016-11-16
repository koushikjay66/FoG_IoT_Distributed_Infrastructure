/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercommunication;

import java.net.Socket;
import json.Builder.Builder;
import json.Builder.objects.SOA_server;

/**
 *
 * @author Arshad
 */
public class Requestedinfo {
   public  String ip;
   public Socket s;
   public String requestedString;
   public boolean possibleOrNot;
   
    public Requestedinfo(Socket s,String ip, String requested){
        System.out.println("Requested from "+ip+" MSG : "+requested);
        this.ip=ip;
        this.s=s;
        this.requestedString=requested;
        
        possibleOrNot=analizeRequested();
        
    }// End of constructor
    
    /**
     * This method analyzes the requested String. if The request result can be 
     * found in this server database or any other medium, Then returns true. 
     * Implementation of this method is server Specific . 
     * For example , Level - 1/ The main server will be able to give reply for all 
     * sorts of problems . But level 2 can not . 
     * Another good example can be , suppose this server does not know how to a process a
     * specific service request. It may now can redirect the requested ip to another 
     * server. Which may be done by generateResult method.
     * @return true/ false depending on the things . 
     */
    public boolean  analizeRequested(){
        return true;
    }// End of method analizeResult
    
    
    public String generateResult(){
        if(!possibleOrNot){
            return "Invalid Request";
        }
        SOA_server ss = new SOA_server();
        
        ss.C_Service.cs_name = "env";
        ss.C_Service.cs_provider="emni";
        ss.C_Service.csid="env";
        
        SOA_server.Simple_Service kelu = (new SOA_server()).new Simple_Service();
        SOA_server.Simple_Service hagu = (new SOA_server()).new Simple_Service();
        SOA_server.Simple_Service yo = (new SOA_server()).new Simple_Service();
        
        kelu.ss_id="kelu";
        kelu.ss_name="kelu";
        kelu.ss_protocal="http";
        kelu.ss_timestamp="time";
        kelu.ss_ttl="500";
        kelu.ss_url="www.google.com";
        kelu.ss_value="50";
        
        hagu.ss_id="hagu";
        hagu.ss_name="hagu";
        hagu.ss_protocal="http";
        hagu.ss_timestamp="time";
        hagu.ss_ttl="500";
        hagu.ss_url="www.google.com";
        hagu.ss_value="50";
        
        yo.ss_id="yo";
        yo.ss_name="yo";
        yo.ss_protocal="http";
        yo.ss_timestamp="time";
        yo.ss_ttl="500";
        yo.ss_url="www.google.com";
        yo.ss_value="40";
        
        
        
        ss.B_Service.add(kelu);
        ss.B_Service.add(hagu);
        ss.B_Service.add(yo);
        System.out.println(Builder.compile(ss));
        return Builder.compile(ss);
    }// End of method generateResult
    
    
}
