/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder.objects;

import json.parser.Parser;

/**
 *
 * @author Koushik
 */
public class M2M_Response extends Parser {

    public String Token;
    public Simple_Service[] B_Service;

    public class Simple_Service {

        public String Ss_name;
        public String ss_value;
    }
}// End of class M2M_Response
