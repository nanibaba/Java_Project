import java.awt.Color;
import java.awt.Graphics;

public class Apple 
{
	private int xCoo, yCoo, w, h; // Has x and y coordinates, and width and height of apple

    public Apple(int xCoo, int yCoo, int tileSize) { // Constructor to initialize the x and y coordinates and tilesize in according with the height and width parameters
        this.xCoo = xCoo;
        this.yCoo = yCoo;
        w = tileSize;
        h = tileSize;
    }

    public void tick() { // The speed of the snake

    }

    public void draw(Graphics g) { // Graphics class to set the apple to be round and to fill the apple with green color
        g.setColor(new Color(186, 60, 60));
        g.fillOval(xCoo * w, yCoo * h, w, h);
    }
    

    // Getters and setters for x and y coordinates
    public int getxCoo() {
        return xCoo;
    }

    public void setxCoo(int xCoo) {
        this.xCoo = xCoo;
    }

    public int getyCoo() {
        return yCoo;
    }

    public void setyCoo(int yCoo) {
        this.yCoo = yCoo;
    }

}
