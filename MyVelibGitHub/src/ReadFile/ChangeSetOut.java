package ReadFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ChangeSetOut{

	   public static void change(String path) throws FileNotFoundException {
	    
	      // create file
	      FileOutputStream f = new FileOutputStream(path);

	      System.setOut(new PrintStream(f));
	      
	   }
	} 