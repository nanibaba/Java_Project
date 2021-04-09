import java.awt.Color;
import java.awt.Graphics;

public class BodyPart 
{

    private int xCoo, yCoo, w, h; // X and Y coordinates, width and height of bodyPart of snake
	private boolean raised; // Adding another parameter to make it so that the snak appears kind of 3D

    public BodyPart(int xCoo, int yCoo, int tileSize) { // Constructor to initialize the x and y coordinates, the tile size and the raised boolean value
        this.xCoo = xCoo;
        this.yCoo = yCoo;
        w = tileSize;
        h = tileSize;
        raised = true;
    }

    public void tick() { // The speed of the snake

    }

    public void draw(Graphics g) { // Graphics class to set and fill color to the snake
        g.setColor(new Color(168, 102, 8));
        g.fill3DRect(xCoo * w, yCoo * h, w, h, raised);
    }

    // Setters and getters for x and y
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
	
