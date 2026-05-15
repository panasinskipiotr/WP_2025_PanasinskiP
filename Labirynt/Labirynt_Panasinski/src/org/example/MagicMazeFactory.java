package org.example;


public class MagicMazeFactory extends StandardMazeFactory {
    @Override
    public Room makeRoom(int x, int y, int nr) {
        return new MagicRoom(x, y, nr);
    }
}