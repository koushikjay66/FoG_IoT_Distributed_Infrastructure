/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responseResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Tasnia
 */
public class RespResult {
    
    //public static String str = "json.Builder.objects.SOA_server.Simple_Service";
    public static ArrayList<String> replyFormat(String str) throws ClassNotFoundException{ 
    Class connLoader = Class.forName(str);
    
    Field ar [] = connLoader.getFields();
       
      ArrayList<String> aL = new ArrayList<>();
        for (int i = 0; i < ar.length; i++) {
            aL.add(ar[i].getName());
                   
        }
     return aL;   
    }
    public static void main(String []args) throws ClassNotFoundException{
        replyFormat("json.Builder.objects.SOA_server$Simple_Service");
    }
}
