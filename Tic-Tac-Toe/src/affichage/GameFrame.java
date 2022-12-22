package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
	JLabel winJLabel;
	JLabel looseJLabel;
	JLabel drawJLabel;
	int win;
    	int loose;
    	int draw;
    
	public int getWin() {
        return win;
    }
    public void setWin(int win) {
        this.win = win;
    }
    public int getLoose() {
        return loose;
    }
    public void setLoose(int loose) {
        this.loose = loose;
    }
    public int getDraw() {
        return draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }
    public JLabel getWinJLabel() {
        return winJLabel;
    }
    public void setWinJLabel(JLabel winJLabel) {
        this.winJLabel = winJLabel;
    }
    public JLabel getLooseJLabel() {
        return looseJLabel;
    }
    public void setLooseJLabel(JLabel looseJLabel) {
        this.looseJLabel = looseJLabel;
    }
    public JLabel getDrawJLabel() {
        return drawJLabel;
    }
    public void setDrawJLabel(JLabel drawJLabel) {
        this.drawJLabel = drawJLabel;
    }
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
	    this.setWin(0);
	    this.setLoose(0);
	    this.setDraw(0);
	    PlaceListener placeListener = new PlaceListener(this);
	    this.setWinJLabel(new JLabel());
	    this.getWinJLabel().setText("Victoire: " + this.getWin());
	    this.setLooseJLabel(new JLabel());
	    this.getLooseJLabel().setText("Defaite: " + this.getLoose());
	    this.setDrawJLabel(new JLabel());
	    this.getDrawJLabel().setText("Match nul: " + this.getDraw());
	    JPanel gamePanel = new JPanel();
	    this.setAllPlaces(EachPlace.createAllPlace());
	    gamePanel.setLayout(new GridLayout(3,3));
	    for (int i = 0; i < this.getAllPlaces().size(); i++) {
	        this.getAllPlaces().get(i).addMouseListener(placeListener);
	        gamePanel.add(this.getAllPlaces().get(i));
	    }
	    JPanel infoPanel = new JPanel();
	    infoPanel.add(winJLabel);
	    infoPanel.add(looseJLabel);
	    infoPanel.add(drawJLabel);
	    this.setWinner("");
	    this.setClientthread(new ClientThread(this));
		this.setState(true);
		this.setTitle("Morpion");
		this.setLayout(new BorderLayout());
		this.setSize(500, 300);
		this.setResizable(false);
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(infoPanel,BorderLayout.EAST);
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
		if(!allLine[0][0].getId_joueur().equals("") && allLine[0][0].getId_joueur().equals(allLine[1][1].getId_joueur()) && allLine[0][0].getId_joueur().equals(allLine[2][2].getId_joueur())) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[0][2].getId_joueur().equals("") && allLine[0][2].getId_joueur().equals(allLine[1][1].getId_joueur()) && allLine[0][2].getId_joueur().equals(allLine[2][0].getId_joueur())) {
			winner = allLine[0][2].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[0][0].getId_joueur().equals("") && allLine[0][0].getId_joueur().equals(allLine[0][1].getId_joueur()) && allLine[0][0].getId_joueur().equals(allLine[0][2].getId_joueur())) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[1][0].getId_joueur().equals("") && allLine[1][0].getId_joueur().equals(allLine[1][1].getId_joueur()) && allLine[1][0].getId_joueur().equals(allLine[1][2].getId_joueur())) {
			winner = allLine[1][0].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[2][0].getId_joueur().equals("") && allLine[2][0].getId_joueur().equals(allLine[2][1].getId_joueur()) && allLine[2][0].getId_joueur().equals(allLine[2][2].getId_joueur())) {
			winner = allLine[2][0].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[0][0].getId_joueur().equals("") && allLine[0][0].getId_joueur().equals(allLine[1][0].getId_joueur()) && allLine[0][0].getId_joueur().equals(allLine[2][0].getId_joueur())) {
			winner = allLine[0][0].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[0][1].getId_joueur().equals("") && allLine[0][1].getId_joueur().equals(allLine[1][1].getId_joueur()) && allLine[0][1].getId_joueur().equals(allLine[2][1].getId_joueur())) {
			winner = allLine[0][1].getId_joueur();
			this.setState(false);
		}
		else if(!allLine[0][2].getId_joueur().equals("") && allLine[0][2].getId_joueur().equals(allLine[1][2].getId_joueur()) && allLine[0][2].getId_joueur().equals(allLine[2][2].getId_joueur())) {
			winner = allLine[0][2].getId_joueur();
			this.setState(false);
		}
	}
	
	public void canContinue() {
		this.checkWinner();
		if(this.isState() == true) {
			this.checkPlace();
		}
	}
	
	public void reset() {
	    this.setWinner("");
	    for (int i = 0; i < this.getAllPlaces().size(); i++) {
            this.getAllPlaces().get(i).setBackground(null);
            this.getAllPlaces().get(i).setId_joueur("");
            this.setState(true);
        }
	}
	
	public void allOperation() {
		canContinue();
		if(this.isState() == false){
			String message = "";
			if(this.getWinner().equalsIgnoreCase("")){
				message = "Match nul";
				this.setDraw(this.getDraw()+1);
			}
			else{
			    if(this.getWinner().equals(this.getPlayer().getId_player())){
			        message = "Victoire";
			        this.setWin(this.getWin()+1);
			    }
			    else {
			        message = "Defaite";
			        this.setLoose(this.getLoose()+1);
			    } 
			}
			JOptionPane.showMessageDialog(this, message);
			this.getWinJLabel().setText("Victoire: " + this.getWin());
			this.getLooseJLabel().setText("Defaite: " + this.getLoose());
			this.getDrawJLabel().setText("Match nul: " + this.getDraw());
			reset();
		}
	}
	
	public void processMessage(String message) {
	   System.out.println("traitement");
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
	           this.getAllPlaces().get(i).setId_joueur(id_player);
	       }
	   }
	}
}
