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
    String serviceName;
    String serurl;
    String recievedInput ="";
    public Threads(String serviceName, String url){
        this.serviceName=serviceName;
        this.serurl=url;
    }

    @Override
    public synchronized void start() {
        Thread t = new Thread();
        t.start(); 
    }

    @Override
    public void run() {
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
    String urlParameters = "service_name="+serviceName;
    DataOutputStream wr = new DataOutputStream (
        connection.getOutputStream());
    wr.writeBytes(urlParameters);
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
    recievedInput = response.toString();
  } catch (Exception e) {
    e.printStackTrace();
    
  } finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
    }
    
}
