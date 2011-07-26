package blank.game;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import blank.shared.Connection;
import blank.shared.Message;

public class ServerConnection extends Connection {

	private String host;
	private int port;
	
	public ServerConnection(String host, int port) throws UnknownHostException, IOException {
		super(new Socket(host,port));
		this.host = host;
		this.port = port;
	}

	@Override
	protected void handleMessage(Message msg) {
		System.out.println("Nachricht empfangen: " + msg.getType());
	}

	@Override
	protected void handleDisconnect() {
		System.out.println("Verbindung verloren...");
	}

}
