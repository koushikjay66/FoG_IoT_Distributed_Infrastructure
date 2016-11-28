/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

/**
 *
 * @author koushik
 */
public final class Queries {
    public static  String get_all_simple_service(){
        return "SELECT DISTINCT ss_id FROM update_agent";
        
    }// End of string 
    public static  String get_simple_s_ip(String ss_id){
        return "SELECT  ip, priority FROM update_agent where ss_id=\""+ss_id+"\" ORDER BY priority DESC";
        
    }// End of string 
    
    public static String decrease_priority(String ss_id, String ip, int p){
        return "UPDATE update_agent SET priority=priority - "+p+" WHERE ss_id=\""+ss_id+"\" AND ip=\""+ip+"\"";
    }
     public static String update_value(String ss_id, String value){
        return "UPDATE simple_service SET ss_value=\""+value+"\" WHERE ssid=\""+ss_id;
    }
}
