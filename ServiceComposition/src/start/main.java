/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

/**
 *
 * @author Koushik
 */
import java.util.Scanner;
import server.Iniciar;

public class main {

    public static void main(String args[]) {

        Scanner lab = new Scanner(System.in);
        System.out.print("Who is my Main Server: ");
        String parent = "13.84.221.115";
        System.out.print("\nWho are my agents(seperate with comma): ");
        String mates[] = "".split(",");
        System.out.print("\nWhat is my db host: ");
        String db_host = "localhost";
        System.out.print("\nDB_USER: ");
        String db_user = "root";
        System.out.print("\nDB_Pass: ");
        String db_pass = "";//sXdG160000_6";
        System.out.print("\nDB_Name: ");
        String db_name = "services_db";
        System.out.println("Enter Bootstrap Class Name");
        Iniciar i = new Iniciar("soa.Bootstrap")
                .parent(parent).mates(mates).database(db_host, db_user, db_pass, db_name);

        if (i.buildServer()) {
            i.initiate();
        }
    }
}
