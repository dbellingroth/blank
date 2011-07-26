package blank.shared;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public abstract class Connection implements Runnable{

	protected Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	protected abstract void handleMessage(Message msg);
	protected abstract void handleDisconnect();
	
	public Connection(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Verbindungsfehler");
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
	
	
	
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		boolean stop = false;
		while (!stop) {
		try {
			Message msg = (Message)in.readObject();
			handleMessage(msg);
		} catch (IOException e) {
			handleDisconnect();
			stop = true;
		} catch (ClassNotFoundException e) {
			System.err.println("fehlerhafte Nachricht");
		}
		}
	}
}
