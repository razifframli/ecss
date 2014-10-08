/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import library.Func;
import oms.rmi.server.Message;

/**
 *
 * @author End User
 */
public class Test3 {
    public static void main(String[] args) {
        try {
            ArrayList<String> listOnline = Func.readXML("online");
            Registry myRegistry = LocateRegistry.getRegistry("210.48.157.104", 1099);
//            Registry myRegistry = LocateRegistry.getRegistry("10.73.32.201", 1099);

            // search for myMessage service
            Message impl = (Message) myRegistry.lookup("myMessage");
            
            impl.sayHello("umar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
