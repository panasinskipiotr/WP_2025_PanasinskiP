package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Player {
    private Room currentRoom;

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public void move(Direction d) {
        if (currentRoom != null) {
            MapSite site = currentRoom.getSite(d);

            if (site instanceof Door) {
                Door door = (Door) site;
                currentRoom = door.getOtherRoom(currentRoom);
            }
        }
    }

    public void draw(Image image) {
        if (currentRoom != null) {
            Graphics g = image.getGraphics();
            g.setColor(Color.RED);

            int offset = MapSite.l / 4;
            int size = MapSite.l / 2;
            g.fillOval(currentRoom.getX() + offset, currentRoom.getY() + offset, size, size);

            g.setColor(Color.BLACK);
        }
    }
}