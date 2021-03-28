import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable, KeyListener 
{
	private static final long serialVersionUID = 1L; //used during the deserialization of an object to ensure that a loaded class is compatible with the serialized object

    public static final int WIDTH = 600, HEIGHT = 600; // width and height of window

    private Thread thread; // a thread to be assigned to the application
    private boolean running = false; // to check if it's running

    private BodyPart b; // one body part object
    private ArrayList<BodyPart> snake; // a list of BodyPart objects to create the snake

    private Apple apple; // one apple object
    private int applesEaten = 0;	//number of eaten apples
    private ArrayList<Apple> apples; // a bunch of apples

    private Random r; // variable for random calculations

    private int xCoor = 10, yCoor = 10; // setting initial start coordinates for snake
    private int size = 5; // amount of body parts of snake initially

    private boolean right = true, left = false, up = false, down = false; // starts to the right only

    private int ticks = 0; // ticks for the speed of the snake

    public Screen() { // Screen constructor
        setFocusable(true); // making sure the component is focusable

        addKeyListener(this); // to start receiving user input from the keyboard
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // setting the size of the screen

        r = new Random();
        // snake and apple arraylists are defined
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        start();
    }

    public void tick() { // tick method
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 22); // creates snake
            snake.add(b); // adds it to snake arrayList
        }
        if (apples.size() == 0) {
            int xCoor = r.nextInt(26); // random coordinates for x and y
            int yCoor = r.nextInt(26);

            apple = new Apple(xCoor, yCoor, 22); // same as for snake
            apples.add(apple);
        }

        for (int i = 0; i < apples.size(); i++) {
            if (xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) { // check if the snake's head 
// coordinates are the same as the apple’s
                size++; // grow one cube
                apples.remove(i); // remove the apple
                i++;
                applesEaten++; // update the number of eaten apples (score)
            }
        }

        for (int i = 0; i < snake.size(); i++) {
            if (xCoor == snake.get(i).getxCoor() &&
                    yCoor == snake.get(i).getyCoor()) { // if the snake intersects itself, the game is over
                if (i != snake.size() - 1) { 
                    stop();
                }
            }
        }
        
        if (xCoor < 0 || xCoor > 26 || yCoor < 0 || yCoor > 26) { // if the snake goes outside the borders the game stops
            stop();
        }


        ticks++;

        if (ticks > 800000) { // define the speed of the snake and the direction
            if (right) xCoor++;
            if (left) xCoor--;
            if (up) yCoor--;
            if (down) yCoor++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 22);
            snake.add(b);

            if (snake.size() > size) {
                snake.remove(0);
            }
        }
    }

    public void paint(Graphics g) { // graphic design of the GUI
        if (running) {
        	
        	g.clearRect(0, 0, WIDTH, HEIGHT);	//set the screen graphics and color
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, WIDTH, HEIGHT);

        	g.setColor(Color.BLACK);

        	for (int i = 0; i < snake.size(); i++) {	//set the snake graphics
        		snake.get(i).draw(g);
        	}
        	for (int i = 0; i < apples.size(); i++) {	//set the apple graphics
        		apples.get(i).draw(g);
        	}
        	
        	g.setColor(Color.RED);	// add a score and set its color, position and size
        	g.setFont(new Font ("Ink Free", Font.BOLD, 15));
        	FontMetrics metrics = getFontMetrics(g.getFont());
        	g.drawString("SCORE: " + applesEaten, (WIDTH - metrics.stringWidth("SCORE: " + applesEaten))/2, 20);
        }
        
        else if (!running) {
        	
        	g.clearRect(0, 0, WIDTH, HEIGHT);	//set the screen to be black when the game is over
        	g.fillRect(0, 0, WIDTH, HEIGHT);
        	g.setColor(Color.BLACK);
        	
        	g.setColor(Color.RED);	//add text for game over and score for the end of the game and set their colors, size and position
        	g.setFont(new Font ("Ink Free", Font.BOLD, 85));
        	FontMetrics metrics = getFontMetrics(g.getFont());
        	g.drawString("GAME OVER", (WIDTH - metrics.stringWidth("GAME OVER"))/2, 220);
        	g.setFont(new Font ("Ink Free", Font.BOLD, 75));
        	g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, 300);
        	
        }

    }

    public void start() {	//start the game
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {	//stop the game
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    

    public void run() {		//when the game is running the snake is moving 
        while (running) {
            tick();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { // to monitor the keys pressed
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) { // checks for direction and if it's previous position isn't 180 degrees of it
            up = false;
            down = false;
            right = true;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        if (key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }


}
