/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author umarmukhtar
 */
public class FileReadWrite {
    
    private String fileName = "";
    
    public FileReadWrite(String fileName) {
        this.fileName = fileName;
    }
    
    public void write(String text, boolean append) {

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(this.fileName, append);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(text);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + this.fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    
    public void write(boolean append) {

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName, append);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    
    public ArrayList<String> read() {

        // This will reference one line at a time
        String line = null;
        ArrayList<String> strOutput = new ArrayList<String>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(this.fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                strOutput.add(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return strOutput;
    }
    
    public static void main(String[] args) {
        
        FileReadWrite frw = new FileReadWrite("test1");
        frw.write("umaq nak makan", false);
        frw.write(true);
        frw.write("ayam goreng", true);
        ArrayList<String> out = frw.read();
        for (int i = 0; i < out.size(); i++) {
            System.out.println("line #"+i+": " + out.get(i));
        }
        
        ArrayList<String> dgss = frw.read();
        dgss.add("ha012" + "|" + "hah ah aha ha");
        for (int j = 0; j < dgss.size(); j++) {
            boolean append = !(j == 0);
            frw.write(dgss.get(j), append);
            frw.write(true);
        }
    }
}
