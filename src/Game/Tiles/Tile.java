package Game.Tiles;

import javafx.scene.layout.Pane;

public class Tile {
    private int position;
    private String type; // parcoure, malus, bonus;
    private String color;
    private Pane p;

    public Pane getP() {
        return p;
    }

    public void setP(Pane p) {
        this.p = p;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tile(int position, String type, String color) {
        this.position = position;
        this.type = type;
        this.color = color;
    }


    @Override
    public String toString() {
        return "Tile{" +
                "position=" + position +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
