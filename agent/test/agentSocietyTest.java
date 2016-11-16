
import agent.AgentSociety;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NewUsername
 */
public class agentSocietyTest {
    public static void main(String [] args){
       System.out.println("ami m2m");
        M2M_Request mr = new M2M_Request("REQ");
        mr.build("kalaPocha", "amiKalu", "env12");

        System.out.println(Builder.compile(mr));
        AgentSociety as = new AgentSociety(mr);
        System.out.println(Builder.compile(as.compile()));
    }
}
