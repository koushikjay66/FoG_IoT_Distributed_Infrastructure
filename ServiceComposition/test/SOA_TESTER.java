
import java.sql.SQLException;
import json.Builder.Builder;
import json.Builder.objects.M2M_Response;
import soa.SOA;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Koushik
 */
public class SOA_TESTER {

    public static void main(String args[]) throws SQLException {
        SOA soa = new SOA("env");
        M2M_Response mr= soa.search();
        if ( mr== null) {
            System.out.println("Found Nothing");
        }else{
            mr.Token="sfjsouf0w9ur9s87f";
            System.out.println(Builder.compile(mr));
        }
    }
}
