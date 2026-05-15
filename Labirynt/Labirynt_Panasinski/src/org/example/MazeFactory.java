package org.example;

public interface MazeFactory {
    Room makeRoom(int x, int y, int nr);
    Wall makeWall(int x, int y, Direction direction);
    Door makeDoor(Room r1, Room r2);
}