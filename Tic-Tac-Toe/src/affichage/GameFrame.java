package affichage;

import java.awt.Color;
import java.awt.GridLayout;
import java.lang.reflect.InaccessibleObjectException;
import java.util.Vector;
import client.*;
import javax.swing.*;
import gameLogic.PlaceListener;
import player.Player;

public class GameFrame extends JFrame{
	Vector<EachPlace> allPlaces;
	boolean state;
	Player player;
	ClientThread clientthread;
	String winner;
	
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public ClientThread getClientthread() {
        return clientthread;
    }
    public void setClientthread(ClientThread clientthread) {
        this.clientthread = clientthread;
    }
	public Vector<EachPlace> getAllPlaces() {
		return allPlaces;
	}
	public void setAllPlaces(Vector<EachPlace> allPlaces) {
		this.allPlaces = allPlaces;
	}
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public GameFrame() {
	    this.setWinner("");
	    this.setClientthread(new ClientThread(this));
		this.setState(true);
		PlaceListener placeListener = new PlaceListener(this);
		this.setAllPlaces(EachPlace.createAllPlace());
		this.setTitle("Morpion");
		this.setLayout(new GridLayout(3, 3));
		this.setSize(600, 600);
		this.setResizable(false);
		for (int i = 0; i < this.getAllPlaces().size(); i++) {
			this.getAllPlaces().get(i).addMouseListener(placeListener);
			this.add(this.getAllPlaces().get(i));
		}
		this.addMouseListener(placeListener);
		this.setVisible(true);
	}
	
	public void checkPlace() {
		this.setState(false);
		for (int i = 0; i < this.getAllPlaces().size(); i++) {
			if (this.getAllPlaces().get(i).getId_joueur().equalsIgnoreCase("")) {
				this.setState(true);
				break;
			}
		}
	}
	
	public void checkWinner() {
		EachPlace[][] allLine = EachPlace.allPlaces(allPlaces);
		if(allLine[0][0].getId_joueur().isEmpty() && allLine[0][0].getId_joueur() == allLine[1][1].getId_joueur() && allLine[0][0].getId_joueur() == allLine[2][2].getId_joueur()) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(allLine[0][2].getId_joueur().isEmpty() && allLine[0][2].getId_joueur() == allLine[1][1].getId_joueur() && allLine[0][2].getId_joueur() == allLine[2][0].getId_joueur()) {
			winner = allLine[0][2].getId_joueur();
			this.setState(false);
		}
		else if(allLine[0][0].getId_joueur().isEmpty() && allLine[0][0].getId_joueur() == allLine[0][1].getId_joueur() && allLine[0][0].getId_joueur() == allLine[0][2].getId_joueur()) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(allLine[1][0].getId_joueur().isEmpty() && allLine[1][0].getId_joueur() == allLine[1][1].getId_joueur() && allLine[1][0].getId_joueur() == allLine[2][1].getId_joueur()) {
			winner = allLine[1][0].getId_joueur();
			this.setState(false);
		}
		else if(allLine[2][0].getId_joueur().isEmpty() && allLine[2][0].getId_joueur() == allLine[2][1].getId_joueur() && allLine[2][0].getId_joueur() == allLine[2][2].getId_joueur()) {
			winner = allLine[2][0].getId_joueur();
			this.setState(false);
		}
		else if(allLine[0][0].getId_joueur().isEmpty() && allLine[0][0].getId_joueur() == allLine[1][0].getId_joueur() && allLine[0][0].getId_joueur() == allLine[2][0].getId_joueur()) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(allLine[0][1].getId_joueur().isEmpty() && allLine[0][1].getId_joueur() == allLine[1][1].getId_joueur() && allLine[0][1].getId_joueur() == allLine[2][1].getId_joueur()) {
			winner = allLine[0][1].getId_joueur();
			this.setState(false);
		}
		else if(allLine[0][2].getId_joueur().isEmpty() && allLine[0][2].getId_joueur() == allLine[1][2].getId_joueur() && allLine[0][2].getId_joueur() == allLine[2][2].getId_joueur()) {
			winner = allLine[0][2].getId_joueur();
			this.setState(false);
		}
	}
//	
//	public void canContinue() {
//		this.checkWinner();
//		if(this.isState() == true) {
//			this.checkPlace();
//		}
//	}
//	
//	public void reset() {
//	    this.setWinner(0);
//	    for (int i = 0; i < this.getAllPlaces().size(); i++) {
//            this.getAllPlaces().get(i).setBackground(null);
//            this.getAllPlaces().get(i).setId_joueur(0);
//            this.setState(true);
//        }
//	}
//	
//	public void allOperation() {
//		canContinue();
//		if(this.isState() == false){
//			String message = "";
//			if(this.getWinner() == 0){
//				message = "Match nul";
//			}
//			else{
//				message = "Victoire du joueur " + this.getWinner();
//			}
//			JOptionPane.showMessageDialog(this, message);
//			reset();
//		}
//	}
	
	public void processMessage(String message) {
	   String[] firstPart = message.split(":");
	   String id_player = firstPart[0];
	   String[] secondPart = firstPart[1].split("/");
	   int RGB = Integer.valueOf(secondPart[1]);
	   String[] coordonnee = secondPart[0].split(",");
	   int x = Integer.valueOf(coordonnee[0]);
	   int y = Integer.valueOf(coordonnee[1]);	   
	   for (int i = 0; i < this.getAllPlaces().size(); i++) {
	       if (this.getAllPlaces().get(i).getXpos() == x && this.getAllPlaces().get(i).getYpos() == y) {
	           Color color = new Color(RGB);
	           this.getAllPlaces().get(i).setBackground(color);
	           this.getAllPlaces().get(i).se