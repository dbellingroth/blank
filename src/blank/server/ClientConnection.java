package blank.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

import blank.shared.Message;
import blank.shared.MessagePriority;
import blank.shared.TestMessage;

public class ClientConnection implements Runnable{

	protected Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Queue<Message> queue;
	private volatile boolean stop = false;
	
	public ClientConnection(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Verbindungsfehler");
		}
		Thread t1 = new Thread(this);
		Thread t2 = new Thread(new sendingThread());
		t1.start();
		t2.start();
	}
	

	private void handleMessage(Message msg) {
		System.out.println("Nachricht empfangen: " + ((TestMessage)msg).getI());
	}

	
	private void handleDisconnect() {
		System.out.println("Client disconnected");
	}
	

	
	public void sendMessage(Message msg) {	
		if (msg.getPriority() == MessagePriority.low)
			try {
				out.writeObject(msg);
			} catch (IOException e) {
			}
		else queue.add(msg);
	}
		
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class sendingThread implements Runnable {

		@Override
		public void run() {
			while(!stop) {
				try {
					if (queue.size() > 0) {
						out.writeObject(queue.element());
						queue.remove();
					}
				} catch (IOException e) {
					
				}
			}
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