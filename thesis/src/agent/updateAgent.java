/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import database.mysqlAgent;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author arsha
 */
public class updateAgent {
    public static Map<String,String> ttlmap = new HashMap();
    public static Map<String,String> timestampmap = new HashMap();
    
    public static String [] csname;
    public static int [] cspriority;
    public static String [] prio;
    public static void updateTable(){
         mysqlAgent result = new mysqlAgent("SELECT cs_name, cs_priority FROM complex_service" , "SELECT");
        for (Object i: result.res.keySet()) {
            
            String basics = result.res.get(i).toString().substring(1, result.res.get(i).toString().length()-1);
            System.out.println(basics);
            if (basics.length()!=0) {
                String [] basicServices = basics.split(", ");
                
                if (i.equals("cs_name")) {
                    csname = new String[basicServices.length];
                    for (int j = 0; j < csname.length; j++) {
                        csname[j]=basicServices[j];
                    }
                }
                else if (i.equals("cs_priority")) {
                    cspriority = new int[basicServices.length];
                    for (int j = 0; j < cspriority.length; j++) {
                        cspriority[j]=Integer.parseInt(basicServices[j]);
                    }
                }
            }
        }
        prio = new String[cspriority.length];
        for (int i = 0; i < cspriority.length; i++) {
            prio[cspriority[i]-1] = csname[i];
        }
    }
    
    
    public void increasePriority(String serviceName){
        
    }
    public static void main(String[] args){ // just for checking
        updateTable();
        
        for (int i = 0; i < csname.length; i++) {
            System.out.println(csname[i]+" : "+cspriority[i]);
        }
        for (int i = 0; i < prio.length; i++) {
            System.out.println(prio[i]);
        }
    }
}
