import javax.swing.JFrame;

public class DefaultFrame {

    public DefaultFrame() {

        JFrame frame = new JFrame(); // Create new frame
        DefaultScreen screen = new DefaultScreen();	// Create an instance of the screen

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
