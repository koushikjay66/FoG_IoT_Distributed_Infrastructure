/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author Koushik
 */
public class ReqestedParsedObject {
    public String userid;
    public String password;
    public String serviceName;
    public String optionalParam[];
    public String token;
    
    public void params(String params){ 
        optionalParam=params.split(",");
        
    }// End of method params
    
    
    
    
}
