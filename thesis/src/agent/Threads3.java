/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 *
 * @author astha
 */
public class Threads3 implements Callable<String>{
    public Thread thread;
    String serviceName="";
    String serurl="";
    String recievedInput ="empty";
    public Threads3(String serviceName, String url){
        this.serviceName=serviceName;
        this.serurl="http://api.openweathermap.org/data/2.5/weather?q=Dhaka&appid=820740a5e8c9759ce25fbf27676d9876"; //url 
    }

    public synchronized void start() {
        System.out.println("thread started");
        thread = new Thread((Runnable) this);
        thread.start();
    }

    public String sendOutput(){
        return recievedInput;
    }
    
    @Override
    public String call() {
        return serviceName+"--"+httpReq();
        
    }
    
    private String httpReq(){
        HttpURLConnection connection = null;

     try {
        //Create connection
        URL url = new URL(serurl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", 
            "application/x-www-form-urlencoded");


        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        wr.writeBytes("");
        wr.close();

        //Get Response  
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
          response.append(line);
          response.append('\r');
        }
        rd.close();
        return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
              connection.disconnect();
            }
        }
    }
}
