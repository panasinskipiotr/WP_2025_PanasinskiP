package org.example;
import java.awt.Graphics;
import java.awt.Image;

public class Room extends MapSite{
    public MapSite[] sites = new MapSite[4];
    private int nr;

    public Room(int x, int y, int nr){
        super(x, y);
        this.nr = nr;
    }

    public int getNr(){
        return nr;
    }

    public void setSite(Direction d, MapSite site){
        if(site instanceof Wall) {
            switch (d) {
                case NORTH, WEST:
                    site.setX( getX() );
                    site.setY( getY() );
                    break;
                case SOUTH:
                    site.setX( getX() );
                    site.setY( getY() + l);
                    break;
                case EAST:
                    site.setX( getX() + l);
                    site.setY( getY() );
                    break;
            }

            ((Wall)site).setDirection(d);
        }
        sites[d.ordinal()] = site;
    }

    @Override
    public void draw(Image image){
        for (MapSite site : sites){
            if (site != null){
                site.draw(image);
            }
        }
        Graphics g = image.getGraphics();
        g.drawString("" + nr, getX() + l/2, getY() + l/2);
    }

}
