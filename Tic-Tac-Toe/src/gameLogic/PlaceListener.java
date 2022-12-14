package gameLogic;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import affichage.*;

public class PlaceListener implements MouseListener{
	GameFrame gameFrame;
	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	
	public PlaceListener(GameFrame gameFrame) {
		this.setGameFrame(gameFrame);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof EachPlace) {
			if(((EachPlace)e.getSource()).getId_joueur().equalsIgnoreCase("") && this.getGameFrame().isState() == true) {
				this.getGameFrame().getClientthread().sendData(this.getGameFrame().getPlayer().getId_player()+":"+((EachPlace)e.getSource()).getXpos()+","+((EachPlace)e.getSource()).getYpos()+"/"+this.getGameFrame().getPlayer().getColorPlace().getRGB());
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
