import java.awt.Color;
import java.awt.Graphics;

public class BodyPart 
{

    private int xCoor, yCoor, width, height; // x and y coordinates, width and height of bodyPart of snake
	private boolean raised;

    public BodyPart(int xCoor, int yCoor, int tileSize) { // constructor to initialize the x and y coordinates and the tile size
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
        raised = true;
    }

    public void tick() { // the speed of the snake

    }

    public void draw(Graphics g) { // Graphics class to set and fill color to the snake
        g.setColor(new Color(168, 102, 8));
        g.fill3DRect(xCoor * width, yCoor * height, width, height, raised);
        //g.setColor(Color.cyan);
        //g.fillRect(xCoor * width + 2, yCoor * height + 2, width -4, height-4);
    }

    // setters and getters for x and y
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
	
