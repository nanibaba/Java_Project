import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class SpeedScreen<BufferedImageLoader> extends JPanel implements Runnable, KeyListener
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

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


    private int xCoor = 10, yCoor = 10; // setting initial start coordinates for snake
    private int size = 5; // amount of body parts of snake initially

    private boolean right = true, left = false, up = false, down = false; // starts to the right only

    private int ticks = 0; // ticks for the speed of the snake

    public static Timer t = new Timer();

    private int seconds = 60;

    public SpeedScreen() { // Screen constructor
        setFocusable(true); // making sure the component is focusable

        try {
            image = ImageIO.read(new File("snakeBground.jpg"));
        }
        catch(IOException e)
        {
        }

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

        if(seconds == 0)
            stop();

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
            g.setColor(new Color(0, 102, 0));
            g.fillRect(0, 0, WIDTH, HEIGHT);

            for (int i = 0; i < snake.size(); i++) {	//set the snake graphics
                snake.get(i).draw(g);
            }
            for (int i = 0; i < apples.size(); i++) {	//set the apple graphics
                apples.get(i).draw(g);
            }

            g.setColor(new Color(144, 1, 1));	// add a score and set its color, position and size
            g.setFont(new Font ("Roboto Thin", Font.BOLD, 13));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: " + applesEaten, (WIDTH - metrics.stringWidth("SCORE: " + applesEaten))/2, 20);
            g.drawString("TIME: " + seconds, (WIDTH - metrics.stringWidth("TIME: " + seconds)), 20);
        }

        else {
            Graphics g2d = (Graphics2D) g;

            g.clearRect(0, 0, WIDTH, HEIGHT);	//set the screen to be black when the game is over
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.BLACK);

            g.setColor(new Color(144, 1, 1));	//add text for game over and score for the end of the game and set their colors, size and position

            g.setFont(new Font ("Times New Roman", Font.BOLD, 75));

            FontMetrics metrics = getFontMetrics(g.getFont());

            g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, 100);

            g.setFont(new Font ("Times New Roman", Font.BOLD, 75));
            g.drawString("CARE TO", (WIDTH - metrics.stringWidth("CARE TO"))/2, 280);
            g.drawString("IMPROVE?", (WIDTH - metrics.stringWidth("IMPROVE?"))/2, 350);


            Rectangle play = new Rectangle(WIDTH/4, 400, 290, 50);

            g.setFont(new Font("Roboto Thin", Font.BOLD,20));
            FontMetrics newMetrics = getFontMetrics(g.getFont());

            g.setColor(new Color(0, 102, 0));
            g.drawString("Play Again", (WIDTH - newMetrics.stringWidth("Play Again"))/2, play.y + 30);
            ((Graphics2D) g2d).draw(play);

            g.setFont(new Font("Roboto Thin", Font.BOLD,20));

            g.setColor(new Color(144, 1, 1));
            g.drawString("Press M to leave", (WIDTH - newMetrics.stringWidth("Press M to leave"))/2, 500);
        }
    }

    public void start() {	//start the game
        running = true;
        thread = new Thread(this);
        thread.start();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                seconds--;
            }
        }, 0, 1000);
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
        if(key == KeyEvent.VK_M)  // inserting a key to stop the console from running
        {
            System.exit(0);
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}