/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.security.NoSuchAlgorithmException;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import json.Builder.objects.M2M_Response;
import json.Builder.objects.M2M_Response.Simple_Service;

/**
 *
 * @author Tasnia
 */
public class testerReplyAgent {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        M2M_Request m = new M2M_Request("REQ").build("koushikjay66", "Nopassword01", "env", "arshad, koushik,erfan");
        System.out.println(Builder.compile(m));

        M2M_Response m2 = new M2M_Response();

        Simple_Service ss = (new M2M_Response()).new Simple_Service();
        m2.Token = m.TOKEN;
        ss.Ss_name = "koushik";
        ss.ss_value = "Got a new CFrusgh";

        m2.B_Service.add(ss);
        ss = (new M2M_Response()).new Simple_Service();
        ss.Ss_name = "erfan";
        ss.ss_value = "G";
        m2.B_Service.add(ss);
        System.out.println(Builder.compile(m2));

    }

}
