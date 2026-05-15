package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeApp extends JFrame implements ActionListener {
    private Image image;
    private MyPanel myPanel = new MyPanel();

    private JButton btnStandard;
    private JButton btnMagic;

    public MazeApp() {
        JPanel panelButtons = new JPanel(new GridLayout(1, 2));

        btnStandard = new JButton("Zwykły Labirynt");
        btnStandard.addActionListener(this);

        btnMagic = new JButton("Magiczny Labirynt");
        btnMagic.addActionListener(this);

        panelButtons.add(btnStandard);
        panelButtons.add(btnMagic);

        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, panelButtons);
        add(BorderLayout.CENTER, myPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);  // Lekko powiększyłem okno
        setVisible(true);
        image = myPanel.getImage();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MazeApp());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (image == null) {
            image = myPanel.getImage();
        }

        Graphics g = image.getGraphics();
        g.setColor(myPanel.getBackground());
        g.fillRect(0, 0, myPanel.getWidth(), myPanel.getHeight());
        g.setColor(Color.BLACK);

        // Wybieramy fabrykę na podstawie tego, który przycisk kliknięto
        MazeFactory factory;
        if (e.getSource() == btnMagic) {
            factory = new MagicMazeFactory();
        } else {
            factory = new StandardMazeFactory();
        }

        // Przekazujemy wybraną fabrykę do Budowniczego!
        drawMazeBuilder(50, 50, new Builder(factory));
    }

    public void drawMazeBuilder(int x, int y, IMazeBuilder mazeBuilder){
        Director director = new Director();
        director.constructMaze(x, y, mazeBuilder);
        Maze maze = mazeBuilder.getMaze();
        maze.drawMaze(image);
        myPanel.repaint();
    }
}