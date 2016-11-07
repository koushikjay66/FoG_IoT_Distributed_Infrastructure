/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCompositionSoa;

import com.sun.jmx.defaults.ServiceName;

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
        System.out.println(serviceName);
        System.out.println(this.serviceValue);
    }
}
