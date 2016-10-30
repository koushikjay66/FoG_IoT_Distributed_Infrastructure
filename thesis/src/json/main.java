package json;


import java.security.NoSuchAlgorithmException;
import json.Builder.RequestBuilder;
import servercommunication.Requestinfo;


/**
 *
 * @author Koushik
 */
public class main {
     public static void main(String args[]) throws NoSuchAlgorithmException{
        RequestBuilder b = new RequestBuilder(true);
        b.authentication("koushikjay66", "Nopassword01");
        b.token();
        b.service("env", new String[]{"koushik", "arshad", "heya", "erfan"});
         System.out.println(b.compile());
         Requestinfo rs= new Requestinfo("172.16.34.39");
         rs.sendData(b.compile());
         System.out.println(rs.getReply());

//        parser p = new parser(b.compile());
//        p.getIt();
         
         
       // parser p = new parser(b.compile());
        
    }
}
