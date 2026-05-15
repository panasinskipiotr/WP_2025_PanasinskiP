package org.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class MagicDoor extends Door {
    public MagicDoor(Room roomOne, Room roomTwo) {
        super(roomOne, roomTwo);
    }

    @Override
    public void draw(Image image) {
        super.draw(image);

        Graphics g = image.getGraphics();
        g.setColor(new Color(150, 0, 255));
        g.setFont(new Font("Arial", Font.BOLD, 20));

        int x = getX();
        int y = getY();
        int L = MapSite.l;

        if (x == -1) return;

        if (direction == Direction.NORTH) {
            g.drawString("*", x + (L / 2) - 6, y + 8);
        } else {
            g.drawString("*", x - 6, y + (L / 2) + 8);
        }

        g.setColor(Color.BLACK);
    }
}