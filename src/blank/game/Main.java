package blank.game;

import java.io.IOException;
import java.net.UnknownHostException;

import blank.shared.Message;

public class Main {

	public static void main(String[] args) {
		Connection con = new Connection("localhost",1234);
		try {
			con.connect();
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
