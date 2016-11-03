/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import agent.ReplyAgent;
import service.Service;

/**
 *
 * @author Tasnia
 */
public class testReply {
     public static void main(String [] args) throws Exception{
        Service t = new Service("env");
        ReplyAgent ra = new ReplyAgent(t);
        
    }
}
