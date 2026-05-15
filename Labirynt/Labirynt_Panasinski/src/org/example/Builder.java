package org.example;

public class Builder implements IMazeBuilder {
    private Maze maze;
    private MazeFactory factory;

    // Budowniczy przyjmuje w konstruktorze fabrykę,
    // która dostarczy mu odpowiednie klocki
    public Builder(MazeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void buildMaze() {
        maze = new Maze();
    }

    @Override
    public void buildRoom(int x, int y, int nr) {
        // Zamiast "new Room(...)", korzystamy z fabryki!
        Room room = factory.makeRoom(x, y, nr);
        room.setSite(Direction.NORTH, factory.makeWall(0,0, Direction.NORTH));
        room.setSite(Direction.SOUTH, factory.makeWall(0,0, Direction.SOUTH));
        room.setSite(Direction.EAST, factory.makeWall(0,0, Direction.EAST));
        room.setSite(Direction.WEST, factory.makeWall(0,0, Direction.WEST));
        maze.add(room);
    }

    @Override
    public void buildDoor(int room_1, int room_2) {
        Room r1 = maze.getRooms(room_1);
        Room r2 = maze.getRooms(room_2);
        // Zamiast "new Door(...)", korzystamy z fabryki
        factory.makeDoor(r1, r2);
    }

    @Override
    public Maze getMaze() {
        return maze;
    }
}