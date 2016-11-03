/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

import com.google.gson.Gson;
import json.Builder.Builder;
import json.Builder.objects.Main_server;

/**
 *
 * @author Koushik
 */
public class terter_reply_parser {

    public static void main(String args[]) {
        String JSON="{\n" +
"    \"Token\" : \"Here will go the token \", \n" +
"    \"C_Service\" :[\n" +
"                    {\"csid\" : \"csid\", \"cs_name\" : \"CS NAME HERE\" , \"cs_provider\" : \"provider name here\"\n" +
"                    }       \n" +
"                ],\n" +
"    \"B_Service\" : [\n" +
"                    {\n" +
"                        \"Ss_id\": \"13101206\", \"Ss_name\" : \"koushik\", \"ss_value\": \"bulala\", \"ss_protocal\": \"http\", \"ss_url\": \"http://google.com\", \"ss_ttl\": \"1306\", \"ss_timestamp\": \"timestamp here\"\n" +
"                    },\n" +
"                    {\n" +
"                        \"Ss_id\": \"13101209\", \"Ss_name\" : \"koushik\", \"ss_value\": \"bulala\", \"ss_protocal\": \"http\", \"ss_url\": \"http://google.com\", \"ss_ttl\": \"1306\", \"ss_timestamp\": \"timestamp here\"\n" +
"                    }\n" +
"                  ]\n" +
"\n" +
"}";
//        Reply_Parser rp = new Reply_Parser(JSON);
//        Object  t= rp.getIt();
//        System.out.println(rp.B_Service[0].ServiceName);
//        System.out.println(rp.B_Service[0].Value);
//        
//        System.out.println(rp.B_Service.length);

    
        Gson g = new Gson();
        Main_server m =g.fromJson(JSON, json.Builder.objects.Main_server.class);
        
        System.out.println(Builder.compile(m));
    
    }
}
