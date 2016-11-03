/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Iniciar;

/**
 *
 * @author Koushik
 */
public class Service<T> {

    private Method m;
    public String name;
    public String[] opValues;

    public Service(String name) {
        this.name = name;
    }

    public Service(String name, String[] optionalValues) {
        this.name = name;
        this.opValues = optionalValues;
    }

    public Service() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param <T>
     */
    public <T>T compile() {
        try {
            Class<?> c = Class.forName(Iniciar.BOOTSTRAP_CLASS_NAME);
            Constructor<?> cons = c.getConstructor(Service.class);
            Object o = cons.newInstance(this);
            Method m = o.getClass().getDeclaredMethod("resultLength");

            if ((int) m.invoke(o) == 0) {
                // It is time to call the agent for service things
                return null;
            } else {
                m = o.getClass().getDeclaredMethod("result");
                return (T) m.invoke(o);
            }
        } catch (Exception e) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
