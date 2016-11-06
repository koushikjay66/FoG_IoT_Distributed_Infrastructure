/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder;

/**
 *
 * @author Koushik
 * @param <T> Response Builder of specific type 
 */
public class ResponseBuilder<T>{


    private final T p;

    public ResponseBuilder(T p) {
        this.p = p;

    }// End of ResponserBuilder

    public String compile() {
        return Builder.compile(p);
    }//End of compile 

}// End of class Builder
