package blank.game;

import java.io.IOException;

import blank.shared.Message;

public class Main {

	public static void main(String[] args) {
		
		try {
			ServerConnection con = new ServerConnection("localhost",1234);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.sendMessage(new Message());
			Thread.sleep(1000);
			con.disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
