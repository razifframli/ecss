/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author End User
 */
public class Test1 {
    public static void main(String[] args) {
        MyKad mykad = new MyKad();
        mykad.start();
        mykad.useJPN();
        mykad.readData();
        System.out.println("ic : " + mykad.ic + " " + mykad.race);
    }
}
