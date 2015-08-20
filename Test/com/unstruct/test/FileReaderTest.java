package com.unstruct.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileReaderTest {

	public static void main(String[] args) {
		StringBuilder result = new StringBuilder();
		try {
			FileReader fr = new FileReader(new File("/home/spark/input/file1.txt"));
			int ch = 0;
			while ((ch = fr.read()) != -1) {
				result.append((char) ch);
			}
			System.out.println(result.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch blockasd
			e.printStackTrace();
		}
		result.delete(0, result.length());

		System.out.println("--------------------");
		System.out.println(result.length());

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("/home/spark/input/file1.txt")));
			String s = null;
			while ((s = br.readLine()) != null) {
				result.append(s + "\n"); // add '\n' ,last line more '\n'
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.toString());
		result.setLength(0);
		System.out.println("--------------------");
		System.out.println(result.length());
		
		

		String s = "你好";
		try {
			FileWriter fw = new FileWriter("/home/spark/input/hello.txt");
			fw.write(s, 0, s.length());

			fw.flush();
			fw.close();

			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/home/spark/input/hello2.txt"));
			osw.write(s, 0, s.length());
			osw.flush();
			osw.close();

			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream("/home/spark/input/hello3.txt")), true);
			pw.println(s);
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}
