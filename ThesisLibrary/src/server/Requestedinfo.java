/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import json.Builder.Builder;
import json.Builder.objects.M2M_Request;

/**
 * 0
 *
 * @author Arshad
 */
public class Requestedinfo {

    public String ip;
    public Socket s;
    public String requestedString;
    public boolean possibleOrNot;
    private M2M_Request req;

    public Requestedinfo(Socket s, String ip, String requested) {
        this.ip = ip;
        this.s = s;
        this.requestedString = requested;

        possibleOrNot = analizeRequested();

    }// End of constructor

    /**
     * This method analyzes the requested String. if The request result can be
     * found in this server database or any other medium, Then returns true.
     * Implementation of this method is server Specific . For example , Level -
     * 1/ The main server will be able to give reply for all sorts of problems .
     * But level 2 can not . Another good example can be , suppose this server
     * does not know how to a process a specific service request. It may now can
     * redirect the requested IP to another server. Which may be done by
     * generateResult method.
     *
     * @return true/ false depending on the things .
     */
    public boolean analizeRequested() {
        Gson g = new Gson();
        req = g.fromJson(requestedString, M2M_Request.class);
        return true;
    }// End of method analizeResult

    public String generateResult() throws ClassNotFoundException {
        if (!possibleOrNot) {
            return "Invalid Request";
        }
        try {
            Class<?> c = Class.forName(Iniciar.BOOTSTRAP_CLASS_NAME);
            Constructor<?> cons = c.getConstructor(M2M_Request.class);
            Object o = cons.newInstance(req);
            Method m = o.getClass().getDeclaredMethod("compile");
            System.out.println(Builder.compile(m.invoke(o))+"Paichi");
            return Builder.compile(m.invoke(o));

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            Logger.getLogger(Requestedinfo.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return "Can't Process your Request At This Time";
    }// End of method generateResult
}// End of class 
