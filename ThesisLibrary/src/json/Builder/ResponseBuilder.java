/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder;

/**
 *
 * @author Koushik
 */
public class ResponseBuilder<T>{

    private String token;
    private T p;

    public ResponseBuilder(T p, String token) {
        this.p = p;
        this.token = token;

    }// End of ResponserBuilder

    public String compile() {
        return Builder.compile(p);
    }//End of compile 

}
