package org.example;

public class StandardMazeFactory implements MazeFactory {
    @Override
    public Room makeRoom(int x, int y, int nr) {
        return new Room(x, y, nr);
    }

    @Override
    public Wall makeWall(int x, int y, Direction direction) {
        return new Wall(x, y, direction);
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }
}