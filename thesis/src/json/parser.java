package json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Koushik
 */
public class parser {
    
    private final String JSON;
    
    public parser(String JSON){
        if(JSON.equals(null)){
            throw new NullPointerException("JSON is empty");
        }
        this.JSON=JSON;
       
    }// End of constructor parser
    
    public ReqestedParsedObject getIt(){
        Pattern p = Pattern.compile("(^(\"(REQ|RES)\") : \\{(\"([a-z]*)\") : \\{(\"([\\w]*)\"): (\"([a-z|A-Z|0-9]*)\"), "
                + "(\"([a-z|A-Z|0-9]*)\"): (\"([a-z|A-Z|0-9]*)\")\\}, "
                + "(\"([a-z]*)\"): (\"([a-z|A-Z|0-9]*)\"), (\"([a-z]*)\"): \\{"
                + "(\"([a-z|A-Z|0-9]*)\"): (\"([a-z|A-Z|0-9]*)\"), (\"([a-z|A-Z|0-9]*)\"): (\"([^\\s]*)\")\\}\\}"
                + ")");
        Matcher m = p.matcher(JSON);

        if( m.find(0) && m.group(3).equals("REQ")){
            int i = 9;
            ReqestedParsedObject rpo = new ReqestedParsedObject();
            rpo.userid=m.group(i);
            rpo.password=m.group(i+4);
            rpo.token=m.group(i+8);
            rpo.serviceName=m.group(i+14);
            rpo.params(m.group(i+18));
            return rpo;
        }else{
            throw new IncompatibleClassChangeError("Invalid format for JSON");
        }
    }
    
    
    
    
    
    
}
