import javax.swing.JFrame;

public class SpeedFrame {

    public SpeedFrame() {

        JFrame frame = new JFrame(); // Create new frame
        SpeedScreen screen = new SpeedScreen();	// Create an instance of the screen

        // Operations that make the game visible
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Serpentine Dream");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}