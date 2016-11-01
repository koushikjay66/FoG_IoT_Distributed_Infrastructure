/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Koushik
 */
public class Reply_Parser {
    
    public String token;
    private final String JSON;
    

    public Reply_Parser(String JSON) {
        if (JSON.equals(null)) {
            throw new NullPointerException("JSON is empty");
        }
        this.JSON = JSON;
    }// End of constructor

    public Reply_Parser getIt(){
        
        final String pattern;
        pattern = "^\\{(Token): (\"(\\S)*\"),"
                + "(B.Service): \\{((\\{"
                + "((ServiceName): ([\\S]*), (value): ((\\S)*))"
                + "\\})*)\\}\\}$";
        
        Pattern p = Pattern.compile(pattern);
        
        Matcher m = p.matcher(this.JSON);
        
        while(m.find()){
            if(m.group(1).equals("Token")){
                this.token=m.group(3);
                System.out.println(this.token);
            }
            
        }
//        while(m.find()){
//            System.out.println(m.group(2));
//        }
        return this;
    
    }// End of method
    
}
