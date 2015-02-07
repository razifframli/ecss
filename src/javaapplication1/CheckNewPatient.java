/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import Helper.VoiceOutput;
import api.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author End User
 */
public class CheckNewPatient implements Runnable {

    public static boolean active = true;
    private Consultation cons;
    
    public CheckNewPatient(Consultation cons) {
        this.cons = cons;
    }
    
    public void run() {
        
        int i = 0;

        while (active) {
            
//            if (i == 2) {
//                cons.setAlwaysOnTop(true);
//            } else {
//                cons.setAlwaysOnTop(false);
//            }
            
            // thread activity.
            if (isNewPatient()) {
                i++;
                if (i % 2 == 0) {
                    cons.lbl_new_patient.setVisible(false);
                } else {
                    cons.lbl_new_patient.setVisible(true);
                }
                if (i % 20 == 0 && !cons.checkPatient2()) { // x panggil patient
                    if (i % 200 == 0) {
                        cons.setAlwaysOnTop(true);
                    } else {
                        cons.setAlwaysOnTop(false);
                    }
                    cons.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    VoiceOutput.getSound("Doctor, you've got new patient");
                } else {
                    cons.setAlwaysOnTop(false);
                }
                //cons.setAlwaysOnTop(true);
            } else {
                i = 0;
                cons.setAlwaysOnTop(false);
                cons.lbl_new_patient.setVisible(false);
                //cons.setAlwaysOnTop(false);
            }
            
            try {
                // rest 1 second.
                Thread.sleep(300);
            } catch (InterruptedException ex) {
            }
        }
    }

    private boolean isNewPatient() {
        try {
            Queue que = new Queue();
            Vector vec = que.getQueueNameList("");
            if (vec.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    } 
}
