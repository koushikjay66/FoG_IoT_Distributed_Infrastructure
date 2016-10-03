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
public class Requestinfo {

    public Socket s;
    private String ip;
    private int port = 1140;

    /**
     *
     * @param ip - the IP address of the server you want to get info from . The
     * servers are always running
     */
    public Requestinfo(String ip) {
        this.ip = ip;
        try {
            s = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Requestinfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// End of constructor requestinfo

    /**
     *
     * @param ip - the IP address of the server you want to get info from . The
     * servers are always running
     * @param port - All the default port is 1140. Don't put any port if you
     * don't know explicitely
     */
    public Requestinfo(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            s = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Requestinfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param JSONData - The data of the JSON format you want to send to that
     * specific server
     * @return true/false Depending on the states of sending status
     */
    public boolean sendData(String JSONData) {
        try {
            s.getOutputStream().write(JSONData.getBytes("UTF-8"));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Requestinfo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param nonJSONData - Incase if you want to want this method to encode
     * data in JSON format
     * @param t Option Boolean value to give to the parameters
     * @return returns status
     */
    public boolean sendData(String nonJSONData, boolean t) {

        return false;
    }

    /**
     * 
     * @return null or the JSON ENCODED data after you have requested the thing 
     */
    public String getReply() {
        String reply = "";
        while (true) {
            try {
                reply = reply + (char) s.getInputStream().read();
                if (s.getInputStream().available() == 0) {
                    return reply;
                }
            } catch (IOException ex) {
                Logger.getLogger(Requestinfo.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    }// End of method reply
}
