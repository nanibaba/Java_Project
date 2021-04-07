import javax.swing.JFrame;

public class SpeedFrame {

    public SpeedFrame() {

        JFrame frame = new JFrame(); //create new frame
        SpeedScreen screen = new SpeedScreen();	//create an instance of the screen

        //operations that make the game visible
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKE");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}