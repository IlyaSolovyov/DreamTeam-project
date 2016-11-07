package ua.itstep.haunt.server.registration.model;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiSocketServer {

	public static final int MAX_CLIENTS = 50;
	private ServerSocket serverSocket;

	@SuppressWarnings("static-access")
	public MultiSocketServer(int portNumber) {
		try {
			System.out.println("1!");
			this.serverSocket = new ServerSocket(portNumber);
			System.out.println("Server launched at " + serverSocket.getInetAddress().getLocalHost() + ":" + serverSocket.getLocalPort());
		} catch (IOException e) {
			System.out.println("Unable to launch server!");
		}
	}

	public void start() {
		while (true) {
			try {
				new ClientHandler(serverSocket.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void finish() {
		try {
			System.out.println("Taking server down...");
			System.out.println("Server successfully taken down.");
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Unable to turn off the server (???)");
			e.printStackTrace();
		}
	}

}
