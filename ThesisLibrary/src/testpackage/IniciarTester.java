/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import server.Iniciar;

/**
 *
 * @author Koushik
 */
public class IniciarTester {
    public static void main(String args[]){
        Iniciar i = new Iniciar("server.Iniciar")
                .parent("172.16.34.2").mates(new String[]{"172", "175"}).database("localhost", "root", "", "chatme");
        if(i.buildServer()){
           i.initiate();
        }
    }
}
