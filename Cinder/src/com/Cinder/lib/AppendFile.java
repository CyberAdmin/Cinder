package com.Cinder.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AppendFile {

	public void appendToFile(String text){
		String filePath = "Z:\\Cinder\\database.txt";
		String appendText = text+"\n";

		appendUsingFileWriter(filePath, appendText);

	}
	/**
	 * Use Streams when you are dealing with raw data, binary data
	 * @param data
	 */
	private static void appendUsingOutputStream(String fileName, String data) {
		OutputStream os = null;
		try {
			//below true flag tells OutputStream to append
			os = new FileOutputStream(new File(fileName), true);
			os.write(data.getBytes(), 0, data.length());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Use BufferedWriter when number of write operations are more
	 * @param filePath
	 * @param text
	 * @param noOfLines
	 */
	private static void appendUsingBufferedWriter(String filePath, String text, int noOfLines) {
		File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
			//to append to file, you need to initialize FileWriter using below constructor
			fr = new FileWriter(file,true);
			br = new BufferedWriter(fr);
			for(int i = 0; i<noOfLines;i++){
				br.newLine();
				//you can use write or append method
				br.write(text);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Use FileWriter when number of write operations are less
	 * @param filePath
	 * @param text
	 * @param noOfLines
	 */
	// FIX THIS JSON FILE WRITING. IT'S NOT FORMATTING CORRECTLY.
	private static void appendUsingFileWriter(String filePath, String text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			//Below constructor argument decides whether to append or override
			fr = new FileWriter(file,true);
			if(fileContainsJSON(filePath)){
				// 1. Read the file if there is already JSON in it.
				// 2. Replace the last character of the string, which is a ] with a ,
				// 3. Append the text to that string.
				// 4. Write to file.
				
				String before = readFromFile(filePath);
				String blah = before.substring(0, before.length()-1)+",";
				blah = blah + text + "]";
				PrintWriter pw = new PrintWriter(filePath);
				pw.close();
				fr.write(blah);	
			}else{
				fr.write("["+text+"]");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private static boolean fileContainsJSON(String filePath){
		boolean does = false;
		String content = readFromFile(filePath);

		if(content.length() != 0){
			// There is probably JSON in the file, so we should make an array of all JSON objects.
			does = true;
		}


		return does;
	}

	private static String readFromFile(String filePath){
		String content = null;
		File file = new File(filePath); //for ex foo.txt
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

}