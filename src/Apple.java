
import java.awt.Color;
import java.awt.Graphics;

public class Apple 
{
	private int xCoor, yCoor, width, height; // has x and y coordinates, and width and height of apple

    public Apple(int xCoor, int yCoor, int tileSize) { // constructor to initialize the x and y coordinates and tilesize
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    public void tick() { // the speed of the snake

    }

    public void draw(Graphics g) { // Graphics class to set the apple to be round and to fill the apple with green color
        g.setColor(Color.green);
        g.fillOval(xCoor * width, yCoor * height, width, height);
    }
    

    // getters and setters for x and y coordinates
    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

}
