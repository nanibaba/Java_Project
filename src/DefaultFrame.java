import javax.swing.JFrame;

public class DefaultFrame {

    public DefaultFrame() {

        JFrame frame = new JFrame(); //create new frame
        DefaultScreen screen = new DefaultScreen();	//create an instance of the screen

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
