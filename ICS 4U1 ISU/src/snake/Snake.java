/*
 * Purpose: class for creating the frame
 */


package snake;

// imports
import java.awt.GridLayout;
import javax.swing.JFrame;

 
public class Snake extends JFrame {

	// serial UID
	private static final long serialVersionUID = 1L;
	
	// constructor 
	public Snake() {
		
		// frame for the snake
		JFrame snake=new JFrame();
		// frame closes when pressed
		snake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set title of frame
		snake.setTitle("Snake");
		// unable to resize the frame
		snake.setResizable(false);
		
		// call the initialization method
		init();
	}
	
	// initialization method 
	public void init() {
		
		// set layout for the snake 
		setLayout(new GridLayout(1, 1, 0, 0));
		// make a new screen
		Screen scr=new Screen(this);
		add(scr);
		
		// pack(): whatever is in the window, it makes it that size
		pack();
		
		// setLocationRelativeTo(): puts the frame in the middle of the screen
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	

}
