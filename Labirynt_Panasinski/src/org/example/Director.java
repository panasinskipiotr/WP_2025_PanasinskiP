package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Director {
    public Maze maze;

    private class RoomInfo {
        int x, y, nr;
        RoomInfo(int x, int y, int nr) {
            this.x = x;
            this.y = y;
            this.nr = nr;
        }
    }

    public void constructMaze(int startX, int startY, IMazeBuilder builder) {
        builder.buildMaze();

        Random random = new Random();
        int totalRooms = random.nextInt(16) + 10;
        int L = MapSite.l;
        int nr = 1;

        ArrayList<RoomInfo> builtRooms = new ArrayList<>();

        builder.buildRoom(startX, startY, nr);
        builtRooms.add(new RoomInfo(startX, startY, nr));
        nr++;

        while (builtRooms.size() < totalRooms) {
            RoomInfo baseRoom = builtRooms.get(random.nextInt(builtRooms.size()));

            int direction = random.nextInt(4);
            int newX = baseRoom.x;
            int newY = baseRoom.y;

            if (direction == 0) newY -= L;
            else if (direction == 1) newX += L;
            else if (direction == 2) newY += L;
            else if (direction == 3) newX -= L;

            if (newX < startX || newY < startY) {
                continue;
            }
            // ------------------------------------------------------------

            boolean isOccupied = false;
            for (RoomInfo r : builtRooms) {
                if (r.x == newX && r.y == newY) {
                    isOccupied = true;
                    break;
                }
            }

            if (!isOccupied) {
                builder.buildRoom(newX, newY, nr);
                builder.buildDoor(baseRoom.nr, nr);
                builtRooms.add(new RoomInfo(newX, newY, nr));
                nr++;
            }
        }

        for(int i = 0; i < builtRooms.size(); i++) {
            for(int j = i + 1; j < builtRooms.size(); j++) {
                RoomInfo r1 = builtRooms.get(i);
                RoomInfo r2 = builtRooms.get(j);

                if (Math.abs(r1.x - r2.x) + Math.abs(r1.y - r2.y) == L) {
                    if (random.nextDouble() < 0.15) {
                        builder.buildDoor(r1.nr, r2.nr);
                    }
                }
            }
        }

        maze = builder.getMaze();
    }
}