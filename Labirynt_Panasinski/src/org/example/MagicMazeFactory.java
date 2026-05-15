package org.example;


public class MagicMazeFactory extends StandardMazeFactory {
    @Override
    public Room makeRoom(int x, int y, int nr) {
        return new MagicRoom(x, y, nr);
    }

    @Override
    public Door makeDoor(Room r1, Room r2){
        return new MagicDoor(r1,r2);
    }
}