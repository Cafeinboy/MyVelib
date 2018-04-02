package CLUI;

import java.util.Scanner;

public abstract class CLUIrequest {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String str = "";
		
		while (!str.equals("exit")) {
			System.out.println("Hi, this is the new system Velib");
			System.out.println("You can ask help by tipping help");
			System.out.println("So, what do you want to do ?");
			
			str = scan.nextLine();
			str = FactoryCommandTerminal.command(str);
						
		}
		
		scan.close();
	}

}
