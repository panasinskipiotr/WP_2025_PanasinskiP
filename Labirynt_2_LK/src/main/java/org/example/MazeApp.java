package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MazeApp extends JFrame implements ActionListener {
    private Image image;  // obraz, na którym rysujemy labirynt
    private Graphics g;   // konktekst graficzny - rysowanie

    private MyPanel myPanel = new MyPanel();

    public MazeApp() {
        JPanel panelButtons = new JPanel(new GridLayout(1, 1)); // Panel z przyciskami
        JButton buttonDraw = new JButton("Draw maze");
        buttonDraw.addActionListener(this);
        panelButtons.add(buttonDraw);


        setLayout(new BorderLayout());  // rozkład dla obszaru roboczego aplikacji
        add(BorderLayout.NORTH, panelButtons);

        add(BorderLayout.CENTER, myPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // domyślny sposób zamknięcia aplikacji
        setSize(500, 300);  // rozmiar okna aplikacji
        setVisible(true);               // wyświetlenie aplikacji na ekranie
        image = myPanel.getImage();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MazeApp();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (image == null)
            image = myPanel.getImage();
        drawMaze(50, 75);
        myPanel.repaint();

    }

/* Rysuje labirynt
x i y - wspolrzedne poczatkowe labiryntu
*/

    public void drawMaze(int x, int y){
        Wall wall1 = new Wall(x,y, Direction.NORTH);
        Wall wall2 = new Wall(x+MapSite.l/2,y, Direction.WEST);
        Wall wall3 = new Wall(x, y + MapSite.l , Direction.EAST);
        Wall wall4 = new Wall(x,y + MapSite.l/2, Direction.SOUTH);
        int nr = 1;
        Room room1 = new Room(x + 100, y, nr++);
        room1.setSite(Direction.WEST, wall1);
        room1.setSite(Direction.NORTH, wall2);
        room1.setSite(Direction.SOUTH, wall3);
        room1.setSite(Direction.EAST, wall4);

        Room room2 = new Room(x + MapSite.l, y, nr++);
        room2.setSite(Direction.WEST, new Wall(x, y, Direction.NORTH));
        room2.setSite(Direction.NORTH, new Wall(x, y, Direction.NORTH));
        room2.setSite(Direction.SOUTH, new Wall(x, y , Direction.NORTH));
        room2.setSite(Direction.EAST, new Wall(x, y, Direction.NORTH));


       //Door door12=new Door(room1,room2);
        
        room1.draw(image);
        room2.draw(image);
        myPanel.repaint();
    }
}