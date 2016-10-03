package json;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Koushik
 */
public class Builder {
    
    /**
     *  public static void main(String args[]) throws NoSuchAlgorithmException{
        Builder b = new Builder(true);
        b.authentication("koushikjay66", "Nopassword01");
        b.token();
        b.service("env");
        System.out.println(b.compile());
        
    }
}
     */

    /**
     * Stores the device ID/ UserID to identify the UNIQUE device set by {@link Builder#authentication(java.lang.String, java.lang.String)
     * }
     */
    private String userID;
    /**
     *Stores the password for the respected USERID to identify the UNIQUE device set by {@link Builder#authentication(java.lang.String, java.lang.String)
     */
    private String password;
    private String token;
    private String serviceName;
    private String basicComponents;
    private String request = "RES";

    public  Builder(boolean requestOrResponse) {
        if (requestOrResponse) {
            this.request = "REQ";
        }
    }//

    /**
     * This method is associated with the JSON to generate the userID and other
     * things
     *
     * @param userID - the USERID of the M2M device, preferably a MAC address
     * @param password - Password set by the device manufacturer to generate
     *
     */
    public void authentication(String userID, String password) {
        if (userID.equals(null) || password.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }
        this.userID = userID;
        this.password = password;
    } // End of function authentication.

    public String token() throws NoSuchAlgorithmException {

        if (userID.equals(null) || password.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((userID + password).getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return (token = sb.toString());
    }

    public void token(String token) {
        this.token = token;
    }// End of method token

    /**
     *
     * @param serviceName Enter the service name you want to request
     */
    public void service(String serviceName) {
        if (userID.equals(null) || password.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }
        if (serviceName.equals(null)) {
            throw new NullPointerException("Service Name null pointer Exception");
        }
        this.serviceName = serviceName;
    }

    public void service(String serviceName, String[] basicComponents) {
        service(serviceName);
        int length = basicComponents.length;
        for (int i = 0; i < length - 1; i++) {
            this.basicComponents += basicComponents[i] + ",";
        }
        this.basicComponents = basicComponents[length - 1];

    }// End of service with two parameters

    /**
     * 
     * @return the Compiled JSON formatted String 
     */
    public String compile() {
        
        String JSON = ""
                +"\""+this.request+"\" : {"
                +"\"authentication\" : {"
                +"\"USERID\": "+"\""+this.userID+"\" , \"password\": \""+this.password+"\"},"
                +"\"token\": "+"\""+this.token+"\", "
                +"\"service\": { "
                +"\"name\": "+"\""+this.serviceName+"\", "
                +"\"components\": "+"\""+this.basicComponents+"\""
                +"}}";
        
        return JSON;
    }

}
