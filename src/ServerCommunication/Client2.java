/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerCommunication;

/**
 *
 * @author Heya
 */
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 implements Runnable {


  private static Socket clientSocket = null;
  private static PrintStream os = null;//output stream
  private static DataInputStream is = null;//input stream

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  
  public static void main(String[] args) {
      Scanner reader=new Scanner(System.in);

    System.out.println("Please enter port number");
    int portNumber = reader.nextInt();
    System.out.println("Please enter host name");

    String host = reader.nextLine();


    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {
      System.err.println("err ");
    } catch (IOException e) {
      System.err.println("err");
    }

//for writing data to socket which has been connected
    if (clientSocket != null && os != null && is != null) {
      try {

        new Thread(new Client2()).start();//this thread wil read from the server
        while (!closed) {
          os.println(inputLine.readLine().trim());
        }
        os.close();//closing the output stream
        is.close();//closing input
        clientSocket.close();//closig socket
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }

  public void run() {
//keep on reading lines
    String msgLine;
    try {
      while ((msgLine = is.readLine()) != null) {
        System.out.println(msgLine);
        if (msgLine.indexOf("Goodbye") != -1)
          break;
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}


