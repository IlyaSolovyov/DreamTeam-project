package ua.itstep.haunt.server.registration.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;

import ua.itstep.haunt.server.registration.utils.HibernateUtil;
import ua.itstep.haunt.server.registration.utils.ServerMethod;
import ua.itstep.haunt.server.registration.utils.ServerResponse;

public class ClientHandler extends Thread {
	private static Session session;
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private HashSet<User> userList;
	private FileWriter file;

	public ClientHandler(Socket socket) {
		clientSocket = socket;
		try {
			file = new FileWriter(ClientHandler.class.getClassLoader().getResource("Files/account_database.txt").getPath());
		} catch (IOException e) {
			System.out.println("Unable to open file");
			e.printStackTrace();
		}
		// session = HibernateUtil.getSessionFactory().openSession();
	}

	public void run() {
		try {
			setStreams();
			while (true) {
				JSONObject request = receiveRequest();
				respondToRequest(request);
			}
		} finally {
			try {
				System.out.println("Closing socket " + clientSocket.toString());
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("Unable to disconnect from client (???)");
				e.printStackTrace();
			}
		}
	}

	private void respondToRequest(JSONObject request) {
		if (request.get("method").toString() == ServerMethod.LOGIN.name()) {
			processLoginRequest(request);
		} else if (request.get("method").toString() == ServerMethod.REGISTER.name()) {
			processRegisterRequest(request);
		} else if (request.get("method").toString() == ServerMethod.REQUEST_SALT.name()) {
			processSaltRequest(request);
		} else {
			System.out.println("Unknown server method " + request.get("method").toString());
		}

	}

	private void processSaltRequest(JSONObject request) {
		JSONObject response = new JSONObject();

		sendResponse(response);
	}

	private void processRegisterRequest(JSONObject request) {
		JSONObject response = new JSONObject();
		sendResponse(response);
	}

	private void processLoginRequest(JSONObject request) {
		JSONObject response = new JSONObject();
		sendResponse(response);
	}

	private void sendResponse(JSONObject response) {
		try {
			outputStream.writeObject(response);
		} catch (IOException e) {
			System.out.println("Unable to send response to socket " + clientSocket.toString());
			e.printStackTrace();
		}
	}

	public void setStreams() {
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Unable to get streWams from socket " + clientSocket.toString());
			e.printStackTrace();
		}
	}

	public JSONObject receiveRequest() {
		try {
			return (JSONObject) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Unknown object has been sent.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to receive object.");
			e.printStackTrace();
		}
		return null;
	}

	public void appendUserToFile(User user)
	{
	//	generateJSON
	}
	
	private JSONObject generateJSONUserModel(User user)
	{
		JSONObject userModel = new JSONObject();
		userModel.put("username", user.getUsername());
		userModel.put("password", user.getPass());
		userModel.put("email", user.getEmail());
		userModel.put("salt", user.getSaltString());
		return userModel;
	}
}
