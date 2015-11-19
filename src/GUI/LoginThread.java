/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Helper.Session;

/**
 *
 * @author End User
 */
public class LoginThread implements Runnable {

    public static boolean running = true;
    
    public void run() {
        
        while(running) {
            Login.lf.setVisible(true);
            Login.lf.lbl_wait.setText("<html>Please Wait ..<br />Connecting Database ...</html>");
            break;
        }
        
    }
    
}
