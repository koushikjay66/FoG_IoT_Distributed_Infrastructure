/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import mainServer.mainServer;


/**
 *
 * @author Koushik
 */
public class Service {
    public String name;
    public String [] opValues;
    
   public Service (String name){
       this.name = name;
   }
   
   public Service(String name, String []optionalValues){
       this.name = name;
       this.opValues = optionalValues;
   }

    public Service() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   
   public Object[] compile(){
       mainServer sb = new mainServer(this);
       if(sb.serviceResult.length==0){
           // It is time to call the main server for service things
           return null;
       }
       else{
           return sb.serviceResult;
       }
   }
   
   
   
}
