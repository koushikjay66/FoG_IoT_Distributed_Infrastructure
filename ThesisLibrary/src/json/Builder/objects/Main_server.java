/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder.objects;

import java.util.ArrayList;

/**
 *
 * @author Koushik Jay
 */
public class Main_server {

    public String Token;
    public Complex_Service C_Service;
    public ArrayList<Simple_Service> B_Service= new ArrayList<>() ;

    public class Complex_Service {

        public String csid;
        public String cs_name;
        public String cs_provider;
        
    }// End of class Complex_Service

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
