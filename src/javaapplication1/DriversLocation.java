/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import Helper.S;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class DriversLocation {

   ServerWriter sw;

   //Insert the clob data into thumb drive
   public void insertToDrive(int status, String header, String patientInfo, 
           String msgs[]) {
       
       
       // Get all the drives
       File drives[] = File.listRoots();

       System.out.println("..Drives length ..:" + drives.length);

       // Loop through the drive list and store the data to the drives.
       for (int index = 3; index < drives.length; index++) {
           try {
               System.out.println("...drives..:" + drives[index]);

               String fileName = drives[index] + "HealthRecord.txt";
               File file = new File(fileName);
               System.out.println(fileName);
               String data = "\r\n" + header + "\r\n" + patientInfo;
               for(int i = 0; i < msgs.length; i++) {
                   if (msgs[i].length() > 0) {
                       data += "\r\n" + msgs[i];
                   }
               }
               System.out.println(data + " THIS IS FROM DIRVER LOCATION ");

               boolean exists = file.exists();
               if (exists == false) {
                   //Create new File
                   FileWriter newFile = new FileWriter(fileName);
                   System.out.println(fileName);

                   //Save data into File
                   file = new File(fileName);
                   sw = new ServerWriter(fileName);
                   sw.writeToFile(data);
                   sw.closeFile();

                   System.out.println("success");
               } else {
                   //Save data into existing File
                   file = new File(fileName);
                   sw = new ServerWriter(fileName);
                   sw.writeToFile(data);
                   sw.closeFile();
                   System.out.println("Save into existing file");
               }
           } catch (Exception e) {
               S.oln(e.getMessage());
           }
       }
    

  }

}
