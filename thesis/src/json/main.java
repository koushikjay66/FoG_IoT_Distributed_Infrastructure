package json;


import json.Builder;
import java.security.NoSuchAlgorithmException;

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
     public static void main(String args[]) throws NoSuchAlgorithmException{
        Builder b = new Builder(true);
        b.authentication("koushikjay66", "Nopassword01");
        b.token();
        b.service("env");
        System.out.println(b.compile());
        
    }
}
