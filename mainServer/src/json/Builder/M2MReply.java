/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder;

/**
 *
 * @author Koushik
 */
public class M2MReply {
    public String serviceName;
    public String serviceValue;
    
    public M2MReply(String serviceName,String serviceValue){
        
        this.serviceName=serviceName;
        this.serviceValue=serviceValue;
    }
}
