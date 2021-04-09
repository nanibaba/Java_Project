import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class SpeedScreen extends JPanel implements Runnable, KeyListener
{
    public static final int WIDTH = 600, HEIGHT = 600; // Setting width and height of window

    private Thread thread; // A thread to be assigned to the application
    private boolean running = false; // Set initial running value to false

    private BodyPart body; // Body Part Object
    private ArrayList<BodyPart> snakey; // A list of BodyPart objects to create the snake

    private Apple apples; // Apple Object
    private int applesEaten = 0; // Number of eaten apples (the game's score)
    private ArrayList<Apple> bunch; // An apple bunch

    private Random rand; // Variable for random calculations

    private int xCoo = 10, yCoo = 10; // Set initial start coordinates for the snake
    private int size = 5; // Set initial amount of body parts of the snake

    private boolean right = true, left = false, up = false, down = false; // Starts to the right only

    private int ticks = 0; // Ticks for the speed of the snake

    public static Timer t = new Timer();

    private int seconds = 60;

    public SpeedScreen() { // Screen Screen constructor
        setFocusable(true); // Making sure the component is focusable

        addKeyListener(this); // Start receiving user input from the keyboard
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Setting the size of the screen

        rand = new Random();
        // Snake and Apple ArrayLists are defined
        snakey = new ArrayList<BodyPart>();
        bunch = new ArrayList<Apple>();

        start();
    }

    public void tick() { // Tick method

        if (snakey.size() == 0) {
            body = new BodyPart(xCoo, yCoo, 22); // Creates snake
            snakey.add(body); // Adds it to snake ArrayList
        }
        if (bunch.size() == 0) {
            int xCoo = rand.nextInt(26); // Random coordinates for x and y
            int yCoo = rand.nextInt(26);

            apples = new Apple(xCoo, yCoo, 22); // Creates apples
            bunch.add(apples); // Adds the apples to apple ArrayList
        }

        for (int i = 0; i < bunch.size(); i++) {
            if (xCoo == bunch.get(i).getxCoo() &&
                    yCoo == bunch.get(i).getyCoo()) {
                // Check if the snake's
                // head coordinates are the same as the apple’s

                size++; // Grow one cube
                bunch.remove(i); // Remove the apple
                i++;
                applesEaten++; // Raise the score
            }
        }

        for (int i = 0; i < snakey.size(); i++) {
            if (xCoo == snakey.get(i).getxCoo() &&
                    yCoo == snakey.get(i).getyCoo()) { // The game is over if the snake intersects itself
                if (i != snakey.size() - 1) {
                    stop();
                }
            }
        }

        if (xCoo < 0 || xCoo > 26 || yCoo < 0 || yCoo > 26) { // If the snake goes outside the borders the game stops
            stop();
        }

        if(seconds == 0) // Stop the game if the time runs out
            stop();

        ticks++;

        if (ticks > 800000) { // Define the speed of the snake and the direction
            if (right) xCoo++;
            if (left) xCoo--;
            if (up) yCoo--;
            if (down) yCoo++;

            ticks = 0;

            body = new BodyPart(xCoo, yCoo, 22);
            snakey.add(body);

            if (snakey.size() > size) {
                snakey.remove(0);
            }
        }

    }

    public void paint(Graphics g) { // Graphic design of the GUI
        if (running) {

            g.clearRect(0, 0, WIDTH, HEIGHT);	// Set the screen graphics and color
            g.setColor(new Color(0, 102, 0));
            g.fillRect(0, 0, WIDTH, HEIGHT);

            for (int i = 0; i < snakey.size(); i++) {	// Set the snake graphics
                snakey.get(i).draw(g);
            }
            for (int i = 0; i < bunch.size(); i++) {	// Set the apple graphics
                bunch.get(i).draw(g);
            }

            g.setColor(new Color(144, 1, 1));	// Add a score and set its color, position, and size
            g.setFont(new Font ("Roboto Thin", Font.BOLD, 13));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: " + applesEaten, (WIDTH - metrics.stringWidth("SCORE: " + applesEaten))/2, 20);
            g.drawString("TIME: " + seconds, (WIDTH - metrics.stringWidth("TIME: " + seconds)), 20);
        }

        else {
            Graphics g2d = (Graphics2D) g;

            g.clearRect(0, 0, WIDTH, HEIGHT);	// Set the screen to be black when the game is over
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.BLACK);

            g.setColor(new Color(144, 1, 1));	// Add text for game over and score for the end of the game and set their colors, size, and position

            g.setFont(new Font ("Times New Roman", Font.BOLD, 75));

            FontMetrics metrics = getFontMetrics(g.getFont());

            g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, 100);

            g.setFont(new Font ("Times New Roman", Font.BOLD, 75));
            g.drawString("CARE TO", (WIDTH - metrics.stringWidth("CARE TO"))/2, 280);
            g.drawString("IMPROVE?", (WIDTH - metrics.stringWidth("IMPROVE?"))/2, 350);

            g.setFont(new Font("Roboto Thin", Font.BOLD,20));
            FontMetrics newMetrics = getFontMetrics(g.getFont());

            g.setColor(new Color(144, 1, 1));
            g.drawString("Press M to leave", (WIDTH - newMetrics.stringWidth("Press M to leave"))/2, 500);
            // Leave the game button text
        }
    }

    public void start() {// Starts the game
        running = true;
        thread = new Thread(this);
        thread.start();
        // Once the game starts, the time begins ticking
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                seconds--;
            }
        }, 0, 1000);
    }

    public void stop() { // Stops the game
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {	// When the game is running the snake is moving
        while (running) {
            tick();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { // Monitors the keys pressed
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) { // Checks for direction
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
        if(key == KeyEvent.VK_M)  // Inserts a key to stop the console from running
            System.exit(0);
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

}