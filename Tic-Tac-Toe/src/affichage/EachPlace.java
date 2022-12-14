package affichage;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

public class EachPlace extends JButton{
	String id_joueur;
	int xpos;
	int ypos;
	
	public int getXpos() {
		return xpos;
	}


	public void setXpos(int xpos) {
		this.xpos = xpos;
	}


	public int getYpos() {
		return ypos;
	}


	public void setYpos(int ypos) {
		this.ypos = ypos;
	}


	public String getId_joueur() {
		return id_joueur;
	}


	public void setId_joueur(String id_joueur) {
		this.id_joueur = id_joueur;
	}


	public EachPlace(int x,int y) {
		this.setId_joueur("");
		this.setXpos(x);
		this.setYpos(y);
		this.setSize(100, 100);
	}
	
	public static Vector<EachPlace> createAllPlace(){
		Vector<EachPlace> allPlaces = new Vector<EachPlace>();
		for(int i = 0; i < 3;i++) {
			for (int j = 0; j < 3; j++) {
				EachPlace provEachPlace = new EachPlace(i, j);
				allPlaces.add(provEachPlace);
			}
		}
		return allPlaces;
	}
	
	public static EachPlace[][] allPlaces(Vector<EachPlace> vectorplaces){
		EachPlace[][] line = new EachPlace[3][3];
		for (int i = 0; i < vectorplaces.size(); i++) {
			line[vectorplaces.get(i).getXpos()][vectorplaces.get(i).getYpos()] = vectorplaces.get(i);
		}
		return line;
	}
}
