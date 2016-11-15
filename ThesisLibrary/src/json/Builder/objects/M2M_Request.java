/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.Builder.objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Koushik
 */
public class M2M_Request {

    public String TYPE;
    public String USERID;
    public String PASSWORD;
    public String TOKEN;
    public String SERVICE_NAME;
    public ArrayList<String> COMPONENTS= new ArrayList<>();

    public M2M_Request(String type) {
        this.TYPE = type;
    }

    private M2M_Request authentication(String userID, String password) {
        if (userID.equals(null) || password.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }
        this.USERID = userID;
        this.PASSWORD = password;
        return this;
    } // End of function authentication.

    private M2M_Request token() throws NoSuchAlgorithmException {

        if (USERID.equals(null) || USERID.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((USERID + PASSWORD).getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        this.TOKEN = sb.toString();
        return this;
    }

    private M2M_Request service(String serviceName) {
        if (USERID.equals(null) || PASSWORD.equals(null)) {
            throw new NullPointerException("USER ID/ Password can't be null");
        }
        if (serviceName.equals(null)) {
            throw new NullPointerException("Service Name null pointer Exception");
        }
        this.SERVICE_NAME = serviceName;
        return this;
    }

    private M2M_Request service(String serviceName, String commaSeperatedValues) {
        service(serviceName);
        this.COMPONENTS = new ArrayList<>(Arrays.asList(commaSeperatedValues.split(",")));
        return this;
    }// End of service with two parameters

    public M2M_Request build(String userID, String password, String serviceName) throws NoSuchAlgorithmException {
        return authentication(userID, password).token().service(serviceName);
    }

    public M2M_Request build(String userID, String password, String serviceName, String commaSeperatedValues) throws NoSuchAlgorithmException {
        return authentication(userID, password).token().service(serviceName, commaSeperatedValues);
    }
}
