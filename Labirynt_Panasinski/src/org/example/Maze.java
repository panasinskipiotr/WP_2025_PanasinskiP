package org.example;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class Maze {
    // Brakowało nazwy zmiennej 'rooms'
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public void add(Room room){
        rooms.add(room);
    }

    public Room getRooms(int nr) {
        Iterator<Room> it = rooms.iterator();
        Room room = null;
        while(it.hasNext()){
            room = it.next();
            if(room.getNr() == nr){
                break;
            }
        }
        return room;
    }

    public void drawMaze(Image image){
        // Brakowało nazwy zmiennej 'g'
        Graphics g = image.getGraphics();
        Room room = null;
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()){
            room = it.next();
            room.draw(image);
        }
    }
}