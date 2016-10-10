/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceCompositionSoa;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author astha
 */
public class Threads extends Thread{
    public Thread thread;
    String serviceName="";
    String serurl="";
    String recievedInput ="empty";
    public Threads(String serviceName, String url){
        this.serviceName=serviceName;
        this.serurl="http://api.openweathermap.org/data/2.5/weather?q=Dhaka&appid=820740a5e8c9759ce25fbf27676d9876"; //url 
    }

    @Override
    public synchronized void start() {
        System.out.println("thread started");
        thread = new Thread();
        thread.start();
        this.run(); //why is it not working runnable class
    }

    public String sendOutput(){
        return recievedInput;
    }
    
    @Override
    public void run() {
        System.out.println("sf"+httpReq());
        
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
