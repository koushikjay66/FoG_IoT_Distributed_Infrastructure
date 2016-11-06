/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder;

import com.google.gson.Gson;

/**
 * This class is a common Builder for all kinds of JSON Objects independent to
 * all forms of servers. One and only static method Compile . Though this is an
 * abstract class the compile method has got implementation details and this
 * class, you don't need to be instantiated
 *
 * @author Koushik 
 */
public  abstract class Builder {

    public static <T> String compile(T p) {
        Gson g = new Gson();
        return g.toJson(p);
    }

}// End of class Builder
