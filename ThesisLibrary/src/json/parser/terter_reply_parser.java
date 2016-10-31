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
        Reply_Parser rp = new Reply_Parser("{Token: \"2b2c5f9e6655ce42740584f4c25c85b6\",B.Service: {{ServiceName: kelu, value: 13101206}{ServiceName: light, value: 10}}}");
        rp.getIt();
    }
}
