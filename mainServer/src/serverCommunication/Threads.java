/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercommunication;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arshad
 */
public class Threads extends Thread{
    
    String clientIp;
    Socket s;
    
    public Threads(String clientIp, Socket s){
        this.clientIp=clientIp;
        this.s=s;
    }// End of constuctor
    
    @Override
     public void start() {
        Thread t = new Thread(this, clientIp);
        t.start();
    }// End of start method
     
    @Override
     public void run(){
         String dataInput=readInput();
         Requestedinfo  redi = new Requestedinfo(s, clientIp, dataInput);
         String result=redi.generateResult();
         writeOutput(result);
         
     }// End of method run
     
      private String readInput() {
        String h = "";
        while (true) {
            try {
                h = h + (char) s.getInputStream().read();
                if (s.getInputStream().available() == 0) {
                    break;
                }
            } catch (IOException ex) {
               Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(h);
        return h;
    }// End of method readInput
      
      private void writeOutput(String result){
        try {
            s.getOutputStream().write(result.getBytes("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
      }// End of method writeOutput

    
    
}
