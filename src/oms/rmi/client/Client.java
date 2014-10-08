/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import oms.rmi.server.Message;

public class Client {
    
    private void doTest(){
        try {
			// fire to localhost port 1099
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 8083);
			
			// search for myMessage service
            Message impl = (Message) myRegistry.lookup("myMessage");
			
			// call server's method			
            impl.sayHello("..Friza ");
			
            System.out.println("Message Sent");
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    public static void main(String[] args) {
        Client main = new Client();
        main.doTest();
    }
}


