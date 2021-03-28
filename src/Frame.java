import javax.swing.JFrame;

public class Frame {

	public Frame() {

        JFrame frame = new JFrame(); //create new frame
        Screen screen = new Screen();	//create an instance of the screen

        //operations that make the game visible
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

	public static void main(String[] args) {
		new Frame();

	}

}
