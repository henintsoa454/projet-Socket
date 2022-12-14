package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread implements Runnable{
	Server server;
	Socket currentPlayer;
	
	Thread serverThread;
	
	BufferedReader in;
	
	// Client propreties
	int clientId;
	
	public ServerThread(Server server, Socket client) {
		this.server = server;
		this.currentPlayer = client;
		
		serverThread = new Thread(this, "Server Thread");
		
		try {
			clientId = this.server.addClient(client);
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			serverThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String message;
		try {
			message = in.readLine();
			while (message != null) {
				server.processMessage(message, clientId);
				message = in.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server.removeClient(clientId);
			try {
				currentPlayer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
