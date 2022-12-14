package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import affichage.GameFrame;

public class ClientThread implements Runnable{
	Socket socket;
	
	GameFrame gameframe;
	
	Thread clientThread;
	
	BufferedReader in;
	public ClientThread(GameFrame gameframe) {
		try {
			socket = new Socket("localhost", 5000);
			
			this.gameframe = gameframe;
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			clientThread = new Thread(this, "Player Thread");
			clientThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			String message = in.readLine();
			
			while(message != null) {
			    System.out.println(message);
				gameframe.processMessage(message);
				message = in.readLine();
			}
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendData(String data) {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			out.println(data);
			
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
