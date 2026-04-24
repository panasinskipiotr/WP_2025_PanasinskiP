package org.example;
import java.awt.Image;

enum Direction {
    NORTH,
    EAST,
    WEST,
    SOUTH
}

//Reprezentuje element mapy labiryntu

public abstract class MapSite {
    private int x;
    private int y;
    public static final int l = 50;

    public MapSite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    public abstract void draw(Image image);
}
