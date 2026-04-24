package org.example;

import java.awt.Graphics;
import java.awt.Image;

public class Door extends MapSite{

    private Room roomOne;
    private Room roomTwo;
    private Direction direction;

    public Door(Room roomOne,Room roomTwo){
        super(-1,-1);
        this.roomOne=roomOne;
        this.roomTwo=roomTwo;
        int x1=roomOne.getX();
        int y1=roomOne.getX();
        int x2=roomTwo.getX();
        int y2=roomTwo.getX();

        if(y1==y2){
            if(x2>x1){
                setX(x2);
                roomOne.setSite(Direction.EAST,this);
                roomTwo.setSite(Direction.WEST,this);
            }
            else{
                setX(x1);
                roomOne.setSite(Direction.WEST,this);
                roomTwo.setSite(Direction.EAST,this);
            }

            setY(y1);
            direction=Direction.WEST;
        }
        else{
            if(y2>y1){
                setY(y2);
                roomOne.setSite(Direction.SOUTH,this);
                roomTwo.setSite(Direction.NORTH,this);
            }
            else{
                setY(y1);
                roomOne.setSite(Direction.NORTH,this);
                roomTwo.setSite(Direction.SOUTH,this);
            }

            setX(x1);
            direction=Direction.NORTH;
        }
    }

    @Override
    public void draw(Image image){
        Graphics g=image.getGraphics();
        
        int x=getX();
        int y=getY();

        if(direction==Direction.NORTH){
            g.drawLine(x,y,x+l/3,y);
            g.drawLine(x+2*l/3,y,x+l,y);
        }
        else{
            g.drawLine(x,y,x,y+l/3);
            g.drawLine(x,y+2*l/3,x,y+l);            
        }
    }
}