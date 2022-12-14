package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	PrintWriter out;
	BufferedReader in;
	
	int port = 5000;
	
	ArrayList<Socket> players = new ArrayList<Socket>();
	int playerNumbers = 0;
	
	public Server() {
		initServer();
	}
	
	public void initServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				if (playerNumbers < 2) {
				    new ServerThread(this, serverSocket.accept());                    
                }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int addClient(Socket n_client) {
		System.out.println("Client connecter");
		
		playerNumbers++;
		
		players.add(n_client);
		
		PrintWriter out;
		try {
			out = new PrintWriter(n_client.getOutputStream());
			//out.println("00:" + Integer.toString(players.size() - 1));
			
			if (players.size() > 0) {
				for (int i = 0; i < players.size(); i++) {
					if (i == players.size() - 1) continue;
					
					PrintWriter out2 = new PrintWriter(players.get(i).getOutputStream());
//					out2.println("01:" + Integer.toString(players.size() - 1));
//					out.println("01:" + i);
					
					out2.flush();
				}
			}
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return players.size();
	}
	
	public void removeClient(int clientIndex) {
		playerNumbers--;
		
		if (players.get(clientIndex) != null) players.remove(clientIndex);
	}
	
	public void sendToAllClients(String message) {
		for (int i = 0; i < players.size(); i++) {
			try {
				PrintWriter out = new PrintWriter(players.get(i).getOutputStream());
				
				out.println(message);
				
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendToClient(int clientId, String message) {
		try {
			PrintWriter out = new PrintWriter(players.get(clientId).getOutputStream());
			out.println(message);
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void processMessage(String message, int clientId) {
		if (message.startsWith("10") || message.startsWith("11") || message.startsWith("12") || message.startsWith("13")) {
			// Movement
			
			String[] movementPacket = message.split(":");
			String[] currentPosition = movementPacket[1].split(",");
			
			int x = Integer.parseInt(currentPosition[0]);
			int y = Integer.parseInt(currentPosition[1]);
			
			int movementSpeed = Integer.parseInt(movementPacket[2]);
			
			if (movementPacket[0].contentEquals("10")) {
				y -= movementSpeed;
			}
			if (movementPacket[0].contentEquals("11")) {
				y += movementSpeed;
			}
			if (movementPacket[0].contentEquals("12")) {
				x -= movementSpeed;
			}
			if (movementPacket[0].contentEquals("13")) {
				x += movementSpeed;
			}
			
			PrintWriter out;
			try {
				out = new PrintWriter(players.get(clientId).getOutputStream());
				out.println("22:" + x + "," + y);
				
				for (int i = 0; i < players.size(); i++) {
					if (i == clientId) continue;
					
					PrintWriter out2 = new PrintWriter(players.get(i).getOutputStream());
					out2.println("23:" + x + "," + y + ":" + clientId);
					
					out2.flush();
				}
				
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (message.startsWith("02:")) {
			String[] packet = message.split(":");
			int otherPlayerId = Integer.parseInt(packet[1]);
			
			try {
				PrintWriter out = new PrintWriter(players.get(otherPlayerId).getOutputStream());
				
				out.println("02:" + packet[2]);
				
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (message.startsWith("03:")) {
			System.out.println(message);
			String[] packet = message.split(":");
			
			int otherId = Integer.parseInt(packet[2]);
				
				PrintWriter out;
				try {
					out = new PrintWriter(players.get(Integer.parseInt(packet[3])).getOutputStream());
					out.println("04:" + packet[1] + ":" + otherId);
					out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		
		System.out.println(message + " From " + clientId);
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
