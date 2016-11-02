/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

/**
 *
 * @author Koushik
 */
public class terter_reply_parser {

    public static void main(String args[]) {
        String JSON= "{\"Token\": \"2b2c5f9e6655ce42740584f4c25c85b6\", \"B_Service\": [{\"ServiceName\": \"kelu\", \"Value\": \"13101206\"},{\"ServiceName\": \"light\", \"Value\": \"10\"}]}";
        Reply_Parser rp = new Reply_Parser(JSON);
        rp=rp.getIt();
        System.out.println(rp.B_Service[0].ServiceName);
        System.out.println(rp.B_Service[0].Value);
        
        System.out.println(rp.B_Service.length);
    }
}
