package ua.itstep.haunt.server.registration.main;



import java.util.Scanner;

import ua.itstep.haunt.server.registration.model.MultiSocketServer;

public class ServerEnvironment {
	public static final int PORT = 8383;

	public static void main(String[] args) {
		MultiSocketServer server = null;
		try
		{
			server = new MultiSocketServer(PORT);
			server.start();	
			Scanner sc = new Scanner(System.in);
		}
		finally
		{
			if (server != null ) server.finish();
		}
		
	}

}
