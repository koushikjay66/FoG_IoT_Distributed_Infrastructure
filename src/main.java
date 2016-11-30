
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;
import server.Requestinfo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Koushik
 */
public class main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("Enter SOA IP");
        Scanner lb = new Scanner(System.in);

         String temp_ip =lb.next();
        //String temp_ip = "172.16.34.38";        

        Requestinfo rs = new Requestinfo(temp_ip);
        
        
        M2M_Request req= new M2M_Request("REQ").build("koushikjay66", "Nopassword01", "k", "13101200,13101201");
        
        String reqJson = Builder.compile(req);
        
        
        System.out.println("Requested\n"+reqJson);
        
        rs.sendData(reqJson);
        System.out.println(rs.getReply());
    }
}
