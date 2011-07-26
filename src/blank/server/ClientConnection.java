package blank.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
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
	private volatile boolean inactive = false;
	
	public ClientConnection(Socket socket) {
		this.socket = socket;
		this.queue = new LinkedList<Message>();
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
		//System.out.println("Nachricht empfangen: " + ((TestMessage)msg).getI());
		sendMessage(msg);
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
		stop = true;
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
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void reset(Socket socket) { //Client hat sich neu verbunden
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.socket = socket;
		inactive = false;
	}
	
	
	@Override
	public void run() {
		boolean stop = false;
		while (!stop) {
		try {
			if (!inactive) {
				Message msg = (Message)in.readObject();
				handleMessage(msg);
			}
		} catch (IOException e) {
			//Client getrennt Verbindung wird auf inaktiv gesetzt aber nicht gelšscht...
			if (!socket.isConnected()) {
				inactive = true;
			}
		} catch (ClassNotFoundException e) {
			System.err.println("fehlerhafte Nachricht");
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}