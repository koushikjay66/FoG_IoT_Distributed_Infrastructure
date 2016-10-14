/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import servercommunication.Threads;

/**
 *
 * @author Arshad
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bootstrap();
    }

    public static void bootstrap() {
        try {
            ServerSocket ss = new ServerSocket(1140);

            while (true) {
                Socket s = ss.accept();
                Threads t = new Threads(s.getRemoteSocketAddress().toString(), s);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// End of bootstrap class

}
