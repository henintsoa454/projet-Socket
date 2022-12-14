package frameListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import affichage.Formulaire;
import player.Player;

public class FrameListener implements MouseListener{
    Formulaire formulaire;
    public Formulaire getFormulaire() {
        return formulaire;
    }
    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }
    
    public FrameListener(Formulaire formulaire) {
        this.setFormulaire(formulaire);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof JButton) {
            if(((JButton)e.getSource()).getText().equalsIgnoreCase("enter")) {
                Player player = new Player(this.getFormulaire().getId_joueur().getText(), this.getFormulaire().getChoosenColor());
                this.getFormulaire().getGameFrame().setPlayer(player);
                this.getFormulaire().setVisible(false);
                this.getFormulaire().getGameFrame().setVisible(true);
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
