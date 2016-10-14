/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import serviceCompositionSoa.ServiceBootstrap;

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
       ServiceBootstrap sb = new ServiceBootstrap(this);
       if(sb.serviceResult.length==0){
           return null;
       }
       else{
           return sb.serviceResult;
       }
   }
   
   
   
}
