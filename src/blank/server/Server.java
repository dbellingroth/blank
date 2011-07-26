package blank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
	
	private int port;
	private ServerSocket server;
	private LinkedList<Client> clients;
	
	public Server(int port) {
		this.port = port;
		this.clients = new LinkedList<Client>();
	}
	
	public void listen() {
		try {
			server = new ServerSocket(port);
			
			while (true) {
				Socket socket = server.accept();
				Client c = new Client(socket);
				clients.add(c);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}