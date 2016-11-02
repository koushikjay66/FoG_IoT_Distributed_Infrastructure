/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.parser;

/**
 *
 * @author Arshad Hossain
 */
public class main {

    public String Token;
    public Complex_Service[] C_Service;
    public Simple_Service[] B_Service;

    public class Complex_Service {

        public String ServiceName;
        public String Provider;
    }// End of class COmplex_Service

    public class Simple_Service {

        public String Ss_id;
        public String Ss_name;
        public String ss_value;
        public String ss_protocal;
        public String ss_url;
        public String ss_ttl;
        public String ss_timestamp;

    }
}
