package blank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private int port;
	private ServerSocket server;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void listen() {
		try {
			server = new ServerSocket(port);
			
			while (true) {
				Socket client = server.accept();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}