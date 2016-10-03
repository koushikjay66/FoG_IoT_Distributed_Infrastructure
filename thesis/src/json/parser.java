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
        getIt();
    }// End of constructor parser
    
    public void getIt(){
        Pattern p = Pattern.compile("(^(\"(REQ|RES)\") : \\{(\"([a-z]*)\") : \\{(\"([\\w]*)\"): (\"([a-z|A-Z|0-9]*)\"), "
                + "(\"([a-z|A-Z|0-9]*)\"): (\"([a-z|A-Z|0-9]*)\")\\}, "
                + "(\"([a-z]*)\"): (\"([a-z|A-Z|0-9]*)\"), (\"([a-z]*)\"): \\{"
                + "(\"([a-z|A-Z|0-9]*)\"): (\"([a-z|A-Z|0-9]*)\"), (\"([a-z|A-Z|0-9]*)\"): (\"([^\\s]*)\")\\}\\}"
                + ")");
        Matcher m = p.matcher(JSON);

        while(m.find()){
            System.out.println(m.group(27));
        }
       
    }
    
    
    
    
    
    
}
