package org.example;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    private Image image;

    public Image getImage() {
        return image;
    }

    @Override
    public void paint(Graphics g) {

        if (image == null)
            image = createImage(getWidth(), getHeight());
        else
            g.drawImage(image, 0, 0, this);
    }

}
