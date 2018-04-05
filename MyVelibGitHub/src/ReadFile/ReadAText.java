package ReadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import CLUI.FactoryCommandTerminal;

public abstract class ReadAText {
	
	public static void readAText(String name) {
		FileReader file = null;
		BufferedReader reader = null;
		String line = "";
		
		try {
			file = new FileReader(name);
			reader = new BufferedReader(file);
			while ((line = reader.readLine()) != null) {
				FactoryCommandTerminal.command(line);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("This file does not exist retry the path.\n");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
			if (file != null) {
				try {
					file.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
			
		}
		
	}

}
