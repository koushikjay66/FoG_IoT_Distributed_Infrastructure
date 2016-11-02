/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

import com.google.gson.Gson;
import server.Iniciar;

/**
 * I love Google . GSON is so easy to use and fast . =D =D
 * @author Koushik
 */
public class Reply_Parser {
    private final Gson g;
    private final String JSON;
    
    
    public String Token;
    public B_Service_Object  B_Service[];
    

    public Reply_Parser(String JSON) {
        g= new Gson();
        if (JSON.equals(null)) {
            throw new NullPointerException("JSON is empty");
        }
        this.JSON = JSON;
    }// End of constructor

    public Object getIt(){
        
        return  g.fromJson(JSON, Iniciar.parsing);
    
    }// End of method
    
    
    public class B_Service_Object{
        String ServiceName;
        String Value;
    }// End of class B_Parser
    
}
