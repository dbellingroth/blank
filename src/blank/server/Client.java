package blank.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import blank.shared.Message;

public class Client implements Runnable{

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public Client(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Verbindung zu Client fehlgeschlagen");
		}
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void sendMessage(Message msg) {
		try {
			out.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleMessage(Message msg) {
		System.out.println("Nachricht empfangen: "+msg.getType());
	}
	
	@Override
	public void run() {
		boolean stop = false;
		while (!stop) {
		try {
			Message msg = (Message)in.readObject();
			handleMessage(msg);
		} catch (IOException e) {
			System.out.println("Client getrennt...");
			stop = true;
		} catch (ClassNotFoundException e) {
			System.err.println("fehlerhafte Nachricht");
		}
		}
	}
}
