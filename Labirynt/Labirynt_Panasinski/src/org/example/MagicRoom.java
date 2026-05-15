package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class MagicRoom extends Room {
    public MagicRoom(int x, int y, int nr) {
        super(x, y, nr);
    }

    @Override
    public void draw(Image image) {
        Graphics g = image.getGraphics();

        g.setColor(new Color(230, 200, 255));
        g.fillRect(getX(), getY(), l, l);
        g.setColor(Color.BLACK);

        super.draw(image);
    }
}