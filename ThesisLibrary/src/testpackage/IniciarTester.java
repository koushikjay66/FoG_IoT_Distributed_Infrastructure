/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.io.File;
import java.util.Scanner;
import server.Iniciar;

/**
 *
 * @author Koushik
 */
public class IniciarTester {
    public static void main(String args[]){
        Scanner lab = new Scanner(System.in);
        System.out.print("Who is my parent : ");
        String parent= lab.next();
        System.out.print("\nWho are my friends(seperate with comma): ");
        String mates[]=lab.next().split(",");
         System.out.print("\nWhat is my db host: ");
        String db_host=lab.next();
        System.out.print("\nDB_USER: ");
        String db_user= lab.next();
        System.out.print("\nDB_Pass: ");
        String db_pass=System.console().readPassword().toString();
        System.out.print("\nDB_Name: ");
        String db_name=lab.next();
        Iniciar i = new Iniciar("server.Iniciar")
                .parent(parent).mates(mates).database(db_name, db_user, db_pass, db_name);
        File f = new File("log/tuni.txt");
        
        if(i.buildServer()){
           i.initiate();
        }
    }
}
