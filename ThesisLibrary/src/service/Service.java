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
 * @param <T>
 */
public class Service<T>{

    private Method m;
    public T RequestedObject;
    public Service(T RequestedObject) {
        this.RequestedObject=RequestedObject;
    }

    /**
     *
     * @param <T>
     * @return 
     */
    public <T> T compile() {
        try {
            Class<?> c = Class.forName(Iniciar.BOOTSTRAP_CLASS_NAME);
            Constructor<?> cons = c.getConstructor(Service.class);
            Object o = cons.newInstance(this);
            Method m = o.getClass().getDeclaredMethod("result");
            return (T) m.invoke(o);
           
        } catch (Exception e) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

}
