package blank.server;

import java.net.Socket;

import blank.shared.Connection;
import blank.shared.Message;

public class ClientConnection extends Connection {

	public ClientConnection(Socket socket) {
		super(socket);
	}

	@Override
	protected void handleMessage(Message msg) {
		System.out.println("Nachricht empfangen: " + msg.getType());
	}

	@Override
	protected void handleDisconnect() {
		System.out.println("Client disconnected");
	}

}
