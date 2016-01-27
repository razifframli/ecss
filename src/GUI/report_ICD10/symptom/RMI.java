
import DBConnection.DBConnection;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.RMIConnector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acer
 */
public class RMI {

    public static void main(String[] args) {

        try {
            // call library
//        RMIConnector rc = new RMIConnector();
            String Patient_type;
            
            // declaration host and port
//        String host = "biocore-stag.utem.edu.my";
//        int port = 1099; // for now, stick to this port
            
            // example select query
            int Staft = 5;
            
            Patient_type = Integer.toString(Staft);
            
            //  String sql = " select centre_code, doctor_name,episode_date, person_id_no,symptom_name from LHR_Signs where length(person_id_no) = '"+Patient_type+"'  and centre_code = 'FTMK' ";
            // execute query
            String sql = "select episode_date from lhr_signs where centre_code = 'FTMK'";
            
            // String sql =      " select coalesce(SUM(CASE WHEN PERSON_STATUS = 'L' THEN 1 ELSE 0 END),0) AS Male,coalesce(SUM(CASE WHEN PERSON_STATUS = 'F' THEN 1 ELSE 0 END),0) AS Female,person_id_no, symptom_name ,  count(*) as Bilangan_pelajar from lhr_signs  where (Episode_date between '2014-01-01' AND '2015-12-31') and  length(person_id_no = '"+Patient_type+"' )   group by symptom_name  ORDER BY `symptom_name` ASC"; 
//        ArrayList<ArrayList<String>> data = rc.getQuerySQL(host, port, sql);
            String param[] = {};
            ArrayList<ArrayList<String>> data = DBConnection.getImpl().getQuery(sql, 1, param);
            
            // view all data and results
            for (int i = 0; i < data.size(); i++) {
                System.out.print("Row #" + (i + 1) + ": ");
                for (int j = 0; j < data.get(i).size(); j++) {
                    System.out.print(data.get(i).get(j) + " | ");
                    
                }
                
                System.out.println();
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
