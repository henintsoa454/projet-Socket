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
	
	String lastPlayer;
	
	
	
	public String getLastPlayer() {
        return lastPlayer;
    }

    public void setLastPlayer(String lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

    public Server() {
		initServer();
		this.setLastPlayer("");
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
	
	public void changeLastPlayer(String message) {
	    String player = message.split(":")[0];
	    this.setLastPlayer(player);
	}
	
	public void processMessage(String message) {
	    if(this.getLastPlayer() == null) {
	        sendToAllClients(message);	
	        changeLastPlayer(message); 
	    }
	    else {
            if (!this.getLastPlayer().equals(message.split(":")[0])) {
                sendToAllClients(message);
                changeLastPlayer(message);
            }
        }
	    
    }
	
	public static void main(String[] args) {
		new Server();
	}
}
