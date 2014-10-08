package library;

import GUI.Console;
import Helper.S;
import Helper.Session;
import config.Config;
import javaapplication1.Consultation;
import javax.swing.SwingWorker;

public class ConsoleLog extends SwingWorker {

    protected Object doInBackground() throws Exception {
        
        int i = 0;
        ConsoleLog.showMsg();
        Integer result = new Integer(0);
        return result;
    }
    
    public static void showMsg() {
        
    }
}