package org.example;

public interface IMazeBuilder {
    void buildMaze();
    void buildRoom(int x, int y, int nr);
    void buildDoor(int room_1, int room_2);
    Maze getMaze();    
}
