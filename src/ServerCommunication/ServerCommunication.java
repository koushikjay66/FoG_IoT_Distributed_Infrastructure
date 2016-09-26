/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerCommunication;

/**
 * @aut
 */
import java.io.DataInputStream; 
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerCommunication {

    /**
     * 
     */
  static ServerSocket serverSocket = null;
  static Socket clientSocket = null;
  
  static final int ClientCount = 5;
  static final clientThread[] threads = new clientThread[ClientCount];

  /**
   * 
   * @param args - it takes some sample environment
   * @since  V1.1
   * 
   */
  public static void main(String args[]) {
    int portNumber = 9999;
        if (args.length < 1) {
            System.out.println("Welcome to CSE321 Chat Server");
      System.out.println("ChatServer running on port: " + portNumber);
    }

    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      System.out.println(e);
    }

    
    while (true) {//new client socket is created for each new connection and is passed to the new client thread
      try {
        clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < ClientCount; i++) {
          if (threads[i] == null) {
            (threads[i] = new clientThread(clientSocket, threads)).start();
            break;
          }
        }
        if (i == ClientCount) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Server is busy");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}


class clientThread extends Thread {

  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int ClientCount;

  public clientThread(Socket clientSocket, clientThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    ClientCount = threads.length;
  }

  public void run() {
    int ClientCount = this.ClientCount;
    clientThread[] threads = this.threads;

    try {
      //Create input and output streams for this client.
  
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      os.println("Enter your name.");
      String name = is.readLine().trim();
//      os.println("Welcome to cse321 " + name);
//      os.println("To end session enter bye in a new line");
////      for (int i = 0; i < ClientCount; i++) {
////        if (threads[i] != null && threads[i] != this) {
//            System.out.println(name + " is connected");
////          threads[i].os.println( name + " is connected");
////        }
////      }
      while (true) {
        os.println("please enter text");
        String line = is.readLine();
        if (line.startsWith("bye")) {
          break;
        }
        
        for (int i = 0; i < ClientCount; i++) {
          if (threads[i] != null) {
              
            threads[i].os.println(  name + " : " + line);
          }
        }
        System.out.println("Recieved: "+line+" form " + name);
      }
      for (int i = 0; i < ClientCount; i++) {
        if (threads[i] != null && threads[i] != this) {
            
          threads[i].os.println( name + " Disconnected!!!");
        }
      }
      System.out.println(name + " is Disconnected");
      os.println("End of session");
      os.println("Goodbye");
      for (int i = 0; i < ClientCount; i++) {
        if (threads[i] == this) {
          threads[i] = null;
        }
      }

      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }
}


