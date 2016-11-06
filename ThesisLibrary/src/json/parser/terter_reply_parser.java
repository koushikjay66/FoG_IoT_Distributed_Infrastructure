/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

import com.google.gson.Gson;
import java.security.NoSuchAlgorithmException;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.Main_server;

/**
 *
 * @author Koushik
 */
public class terter_reply_parser {

    public static void main(String args[]) throws NoSuchAlgorithmException {
        String JSON="\"REQ\" : {\"authentication\" : {\"USERID\": \"koushikjay66\", \"password\": \"Nopassword01\"}, \"token\": \"2b2c5f9e6655ce42740584f4c25c85b6\", \"service\": {\"name\": \"env\", \"components\": \"koushik,arshad,heya,erfan\"}}";
//        Reply_Parser rp = new Reply_Parser(JSON);
//        Object  t= rp.getIt();
//        System.out.println(rp.B_Service[0].ServiceName);
//        System.out.println(rp.B_Service[0].Value);
//        
//        System.out.println(rp.B_Service.length);

//    
//        Gson g = new Gson();
//        json.Builder.objects.M2M_Response m =g.fromJson(JSON, json.Builder.objects.M2M_Response.class);
//        
//        System.out.println(m.B_Service[0].Ss_name);

//            M2M_Request mm = new M2M_Request("REQ").build("koushikjay66", "Nopassword01", "env", "koushik, heya, arshad");
//            Gson g = new Gson();
//            String newJ=g.toJson(mm);
//            
//            M2M_Response m = new M2M_Response();
//            
//            g = new Gson();
//           M2M_Response r = g.fromJson(newJ, M2M_Response.class);
//            System.out.println(r.B_Service[]);
            
    }
}
