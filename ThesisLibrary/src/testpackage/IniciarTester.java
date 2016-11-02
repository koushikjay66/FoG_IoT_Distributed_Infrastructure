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
        System.out.print("Who is my First Ex ? RAIYAN !!! =D : ");
        String parent= "172.16.34.2";
        System.out.print("\nWho are my friends(seperate with comma): ");
        String mates[]="sklfhsiofh, ksfhskfhsklfh".split(",");
         System.out.print("\nWhat is my db host: ");
        String db_host="localhost";
        System.out.print("\nDB_USER: ");
        String db_user= "root";
        System.out.print("\nDB_Pass: ");
        String db_pass="";
        System.out.print("\nDB_Name: ");
        String db_name="services_db";
        Iniciar i = new Iniciar("server.Iniciar")
                .parent(parent).mates(mates).database(db_host, db_user, db_pass, db_name);
        
        if(i.buildServer()){
           i.initiate();
        }
    }
}
