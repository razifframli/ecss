/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author End User
 */
public class Test2 {
    public static void main(String[] args) {
        try {
            InetAddress addr
                    = InetAddress.getByName("210.48.157.104");
            Socket theSocket = new Socket(addr, 1);
            System.out.println("Connected to "
                    + theSocket.getInetAddress()
                    + " on port " + theSocket.getPort() + " from port "
                    + theSocket.getLocalPort() + " of "
                    + theSocket.getLocalAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
