package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeApp extends JFrame implements ActionListener {
    private Image image;
    private MyPanel myPanel = new MyPanel();

    private JButton btnStandard, btnMagic;
    private JButton btnUp, btnDown, btnLeft, btnRight;

    private Maze currentMaze;
    private Player player;

    public MazeApp() {
        JPanel panelButtons = new JPanel(new FlowLayout());

        btnStandard = new JButton("Zwykły");
        btnMagic = new JButton("Magiczny");
        btnUp = new JButton("Góra (N)");
        btnDown = new JButton("Dół (S)");
        btnLeft = new JButton("Lewo (W)");
        btnRight = new JButton("Prawo (E)");

        JButton[] buttons = {btnStandard, btnMagic, btnUp, btnDown, btnLeft, btnRight};
        for (JButton btn : buttons) {
            btn.addActionListener(this);
            panelButtons.add(btn);
        }

        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, panelButtons);
        add(BorderLayout.CENTER, myPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
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

        Object src = e.getSource();

        if (src == btnStandard || src == btnMagic) {
            MazeFactory factory;
            if (src == btnMagic) factory = new MagicMazeFactory();
            else factory = new StandardMazeFactory();

            Builder builder = new Builder(factory);
            Director director = new Director();
            director.constructMaze(50, 50, builder);

            currentMaze = builder.getMaze();
            player = new Player(currentMaze.getRooms(1));
        }
        else if (player != null) {
            if (src == btnUp) player.move(Direction.NORTH);
            else if (src == btnDown) player.move(Direction.SOUTH);
            else if (src == btnLeft) player.move(Direction.WEST);
            else if (src == btnRight) player.move(Direction.EAST);
        }

        redraw();
    }

    private void redraw() {
        Graphics g = image.getGraphics();
        g.setColor(myPanel.getBackground());
        g.fillRect(0, 0, myPanel.getWidth(), myPanel.getHeight());
        g.setColor(Color.BLACK);

        if (currentMaze != null) {
            currentMaze.drawMaze(image);
        }
        if (player != null) {
            player.draw(image);
        }

        myPanel.repaint();
    }
}