/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder.objects;

/**
 *
 * @author Koushik
 */
public class SOA_server {

    public String Token;
    public Complex_Service[] C_Service;
    public Simple_Service[] B_Service;

    public class Complex_Service {

        public String csid;
        public String cs_name;
        public String cs_provider;

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
