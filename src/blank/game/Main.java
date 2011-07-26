package blank.game;

import blank.shared.TestMessage;

public class Main {

	public static void main(String[] args) {
		
		try {
			ServerConnection con = new ServerConnection("localhost",1234);
			int i = 0;
			while(true) {
			System.out.println("Versuche Nachricht zu schicken...");
			con.sendMessage(new TestMessage(i++));
			Thread.sleep(1000);
			}
			
		} 
	    catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
