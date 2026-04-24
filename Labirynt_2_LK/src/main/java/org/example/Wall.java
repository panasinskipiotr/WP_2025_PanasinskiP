package org.example;
import java.awt.Graphics;
import java.awt.Image;

public class Wall extends MapSite{
    private Direction direction;
    public Wall(int x, int y, Direction direction){
        super(x, y);
        this.direction = direction;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }


    @Override
    public void draw(Image image){
        int x = getX();
        int y = getY();
        Graphics g = image.getGraphics();
        switch(direction) {
            case NORTH, SOUTH:
                g.drawLine(x, y, x + l, y);
            case EAST, WEST:
                g.drawLine(x, y, x, y+l);
        }
    }
}
