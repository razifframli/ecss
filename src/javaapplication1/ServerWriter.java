/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class ServerWriter {

    private File createFile = new File("");
    private FileWriter fw;

	/**
	* Constructor to create FilreWriter and allow to append
	* @throws IOException
	*/
	public ServerWriter() throws IOException {
		fw = new FileWriter(createFile,true);
	}

	public ServerWriter(String fileName) throws IOException {
		createFile  = new File(fileName);
		fw = new FileWriter(createFile,true);
	}

	/**
	* To write and flush to a file and ready for the next line
	* @param message
	* @throws IOException
	*/

	public void writeToFile(String message) throws IOException {
		fw.write(message + "\n");
		fw.flush();
	}

	/**
	* To close the filewriter
	* @throws IOException
	*/
	public void closeFile() throws IOException {
		fw.close();
	}


}
