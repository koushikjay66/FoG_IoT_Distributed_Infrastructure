/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverCommunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Koushik
 */
public  class Iniciar {

    public static String BOOTSTRAP_CLASS_NAME;
    public static String HELPER_SERVER;
    public static String HELPER_SERVER_IP;

    public Iniciar(String BOOTSTRAP_CLASS_NAME, String HELPER_SERVER, String HELPER_SERVER_IP) {
        
        Iniciar.BOOTSTRAP_CLASS_NAME = BOOTSTRAP_CLASS_NAME;
        Iniciar.HELPER_SERVER = HELPER_SERVER;
        Iniciar.HELPER_SERVER_IP = HELPER_SERVER_IP;
        
    }//End of Constructor 

    public static void initiate() {
        try {
            ServerSocket ss = new ServerSocket(1140);

            while (true) {
                Socket s = ss.accept();
                Threads t = new Threads(s.getRemoteSocketAddress().toString(), s);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// End of class initiate
}// End of class Iniciar
