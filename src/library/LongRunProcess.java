package library;

import Helper.S;
import Helper.Session;
import config.Config;
import javaapplication1.Consultation;
import javax.swing.SwingWorker;

public class LongRunProcess extends SwingWorker {

    protected Object doInBackground() throws Exception {
        
        int i = 0;
        while(i == 0) {
            LongRunProcess.change_status_network();
            Thread.sleep(2000);
        }
        Integer result = new Integer(0);
        return result;
    }
    
    public static void change_status_network() {
        check_network();
        if (Session.getPrev_stat()) {
            Consultation.showOnline();
        } else {
            Consultation.showOffline();
        }
    }
    
    public static void change_status_network2() {
        check_network2();
        if (Session.getPrev_stat()) {
            Consultation.showOnline();
        } else {
            Consultation.showOffline();
        }
    }
    
    public static void check_network() {
        S.oln("Prev Stat: " + Session.getPrev_stat());
        Session.setCurr_stat(NetworkStatus.DoPing(Config.getIpServer(), 2000));
        S.oln("Curr Stat: " + Session.getCurr_stat());
        if (Session.getCurr_stat() != Session.getPrev_stat()) {
            Session.lineMessage();
            Session.setPrev_stat(Session.getCurr_stat());
            Session.setCon_x(); 
        }
    }
    
    public static void check_network2() {
        S.oln("Prev Stat: " + Session.getPrev_stat());
        Session.setCurr_stat(NetworkStatus.DoPing(Config.getIpServer(), 2000));
        S.oln("Curr Stat: " + Session.getCurr_stat());
        if (Session.getCurr_stat() != Session.getPrev_stat()) {
            Session.setPrev_stat(Session.getCurr_stat());
        }
    }
}