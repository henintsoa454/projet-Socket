package player;

import java.awt.Color;

public class Player {
    String id_player;
    Color colorPlace;
    public String getId_player() {
        return id_player;
    }
    public void setId_player(String id_player) {
        this.id_player = id_player;
    }
    public Color getColorPlace() {
        return colorPlace;
    }
    public void setColorPlace(Color colorPlace) {
        this.colorPlace = colorPlace;
    }
    
    public Player(String id_player, Color colorPlace) {
        this.setId_player(id_player);
        this.setColorPlace(colorPlace);
    }
}
