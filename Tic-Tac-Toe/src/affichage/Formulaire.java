package affichage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.ChangedCharSetException;

import frameListener.FrameListener;

public class Formulaire extends JFrame{
    JTextField id_joueur;
    Color choosenColor;
    GameFrame gameFrame;
    
    
    public JTextField getId_joueur() {
        return id_joueur;
    }


    public void setId_joueur(JTextField id_joueur) {
        this.id_joueur = id_joueur;
    }


    public Color getChoosenColor() {
        return choosenColor;
    }


    public void setChoosenColor(Color choosenColor) {
        this.choosenColor = choosenColor;
    }


    public GameFrame getGameFrame() {
        return gameFrame;
    }


    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }


    public Formulaire(){
        FrameListener frameListener = new FrameListener(this);
        this.setChoosenColor(Color.black);
        this.setGameFrame(new GameFrame());
        this.getGameFrame().setVisible(false);
        this.setId_joueur(new JTextField());
        this.setSize(300,150);
        this.setResizable(false);
        this.setTitle("User's informations");
        JLabel titre1 = new JLabel("UserName: ");
        JLabel titre2 = new JLabel("Color: ");
        JPanel selectedColor = new JPanel();
        JButton chooseColorButton = new JButton("Change color");
        JButton accept = new JButton("Enter"); 
        accept.addMouseListener(frameListener);
        titre1.setBounds(20, 20, 70, 30);
        titre2.setBounds(20, 50, 70, 30);
        selectedColor.setBounds(120,60,15,15);
        chooseColorButton.setBounds(150, 50, 118, 30);
        accept.setBounds(20, 80, 100, 30);
        selectedColor.setBackground(this.getChoosenColor());
        chooseColorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null,"Choose color",choosenColor); 
                choosenColor = color;
                selectedColor.setBackground(choosenColor);
            }
        });
        this.getId_joueur().setBounds(120, 20, 150, 30);
        this.setLayout(null);
        this.add(titre1);
        this.add(titre2);
        this.add(this.getId_joueur());
        this.add(selectedColor);
        this.add(chooseColorButton);
        this.add(accept);
        this.setVisible(true);
    }
}
