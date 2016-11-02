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
public class ResponseBuilder {
    private String token;
    private Object [] replyObject;
    
    public ResponseBuilder(Object replyObject[], String token){
        this.token=token;
        this.replyObject=replyObject;
    }// End of ResponserBuilder
    
    
    
    public String compile(){
        String temp="{"+
                tokenG()
                +","+basicServicesG()+
                "}";
            
        return temp;
    }//End of compile 
    
    
    /**
     * Generates Token part of the response JSON
     * TOKEN: "sklhs5s4f34sf46s4f3s4f";
     * @return if token is set then it reply with the JSON formatted token  
     */
    private String tokenG(){
        if(this.token!=null){
            return "\"Token\": \""+token+"\"";
        }
        else 
            return null;
    }// End of finction tokenG
    
    private String basicServicesG(){
        String service="\"B_Service\": [";
        for (int i = 0; i <replyObject.length ; i++) {
            service+="{\"ServiceName\": \""+((GenericReply)replyObject[i]).serviceName+"\", ";
            service+="\"Value\": \""+((GenericReply)replyObject[i]).serviceValue+"\"}, ";
        }
        service =service.substring(0, service.lastIndexOf(",")-1);
        return service+"]";
        
    }// End of function basicServices
   
}
