package blank.server;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(1234);
		server.listen();
	}

}
