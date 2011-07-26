package blank.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import blank.shared.Message;
import blank.shared.MessagePriority;

public class ServerConnection implements Runnable {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String host;
	private int port;
	private volatile boolean stop = false;
	private Queue<Message> queue;
	
	public ServerConnection(String host, int port) {
		this.host = host;
		this.port = port;
		this.queue = new LinkedList<Message>();
		reconnect();
		Thread t1 = new Thread(this);
		Thread t2 = new Thread(new sendingThread());
		t1.start();
		t2.start();
	}
	
	private void reconnect() {
		//sicherheitshalber nochmal trennen
		try {
			if (socket != null) socket.close();
		} catch (IOException e1) {
		}
		
		//Neue Verbindung
		try {
			socket = new Socket(host,port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			
		} catch (IOException e1) {
			
		}
	}

	private void handleMessage(Message msg) {
		System.out.println("Nachricht empfangen: " + msg.getType());
	}
	
	
	public void sendMessage(Message msg) {
		
		if (msg.getPriority() == MessagePriority.low)
			try {
				out.writeObject(msg);
			} catch (IOException e) {
				reconnect();
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
					reconnect();
				}
			}
		}
		
	}

	
	public void run() {
		while (!stop) {
			
				try {
					Message msg = (Message)in.readObject();
					handleMessage(msg);

				} catch (IOException e) {
					reconnect();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		try {
			socket.close();
		} catch (IOException e) {}
	}

}
