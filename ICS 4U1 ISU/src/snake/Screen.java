/*
 * Purpose: class for the functions of the snake such as moving up/down/left/right; collisions, speed
 */


package snake;

// imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Screen extends JPanel implements Runnable {

	// serial UID
	private static final long serialVersionUID = 1L;
	
	// width and height of the frame
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500; 
	
	// making a thread 
	private Thread thread;
	private boolean running=false;

	private BodyPart b;
	
	// array lists
	private ArrayList <BodyPart> snake;
	private Apple apple;
	private ArrayList <Apple> apples;
	
	// random variable for apple placement
	private Random rand;
	
	// x and y coordinates 
	private int xCoor=10;
	private int yCoor=10;
	
	// size of the snake
	private int size=5;
	
	// apple score
	protected int score=0;
	
	// direction data members for movement
	private boolean right=true;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	
	// ticks for speed of the snake
	private int ticks=0;

	// key 
	private Key key;
	
	// Snake parent 
	private Snake parent;
	
	
	// constructor
	public Screen(Snake parent) {
		
		this.parent=parent;
		
		setFocusable(true);
		
		key = new Key();
		addKeyListener(key);
		
		// sets the size of the frame
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		rand = new Random();
		
		// initializing the array list 
		snake=new ArrayList<BodyPart>();
		apples=new ArrayList <Apple>();
		
		// call the start method to begin the game
		start();
	}
	
	// methods
	// tick method
	public void tick() {
		
		// if there is nothing in the snake except for its head, make a new bodypart on it
		if (snake.size() == 0) {
			b=new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		
		// if there are no apples, then add a new apple in a random location
		if (apples.size() == 0) {
			int xCoor=rand.nextInt(79);
			int yCoor=rand.nextInt(49);
			
			// will add a new apple
			apple=new Apple(xCoor, yCoor, 10);
			// add apple to the array list 
			apples.add(apple);
		}
		
		// for loop to go through apple array
		for (int i=0; i < apples.size(); i++) {
			// if the coordinate of the snake and apple are equal to each other
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) { 
				// adds to the size of the snake
				size++;
				// adds to the score
				score++;
				// remove from the array list 
				apples.remove(i);
				i--; 
				
				// sound
			   
			}
		}
		
		// for loop for collisions
		for (int i=0; i < snake.size(); i++) {
			// if the xCoors and yCoors of the snake are equal, then it has colliding
			if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					// call the stop() method
					stop();
				}
			}
		}
		
		// collision for if it bumps into walls
		if (xCoor < 0 || xCoor > 79 || yCoor < 0 || yCoor > 49) {
			// call the stop() method
			stop();
		}
		
		// add to the ticks
		ticks++;
		
		// ticks for speed
		if (ticks > 500000) {
			// if the user moves right, add to the xCoor
			if (right) {
				xCoor++;
			}
			// if the user moves left, subtract from the xCoor
			if (left) {
				xCoor--;
			}
			// if the user moves up, subtract from the yCoor
			if (up) {
				yCoor--;
			}
			// if the user moves down, add to the yCoor
			if (down) {
				yCoor++;
			}
			
			// resetting of ticks
			ticks=0;
			
			// add to the array 
			b=new BodyPart(xCoor, yCoor, 10);
			// add to the body part
			snake.add(b);
			
			// if the snake size is greater than the size
			if (snake.size()  > size) {
				// remove one of the body parts
				snake.remove(0);
			}
		}
		
	}
	
	// paint method: for displaying the shapes, text
	public void paint(Graphics g ) {
		
		// clearing the rectangles so the snake is a constant length of 5
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		// drawing the grid using a for loop
		g.setColor(Color.GRAY); 
		
		// line for the vertical lines
		for (int i=0; i < WIDTH/10; i++) {
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		// lines for the horizontal lines
		for (int i=0; i < HEIGHT/10; i++) {
			g.drawLine(0, i*10, WIDTH, i*10); 
		}
		// for loop for drawing the snake body part
		for (int i=0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		// for loop 
		for (int i=0; i < apples.size(); i++) {
			apples.get(i).draw(g);
			
		}
		
		// score
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + score, 700, 50);
		
		// game over text
		if (running == false ) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("Game Over!", 100, 250);
			
			// display a message when the game is over
			JOptionPane.showMessageDialog(this, "Game Over");
			parent.dispose();
			
		}
		
		
	}
	
	// method to start the game
	public void start() {
		// if running is true, then the game has started
		running=true;
		thread=new Thread(this, "Game Loop");
		// start the thread
		thread.start(); 
	}
	
	// method to stop the game
	public void stop() {
		// if running is false, then the game has ended
		running=false;
		// try catch for joining the thread
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		// main loop
		while (running) {
			// call the tick() method
			tick();
			// call repaint()
			repaint();
			
		}
		
		// display the dialogue box
		JOptionPane.showMessageDialog(this, "Return to Menu");
		parent.dispose();
	}
	
	// KeyListener for the arrow keys pressed
	private class Key implements KeyListener {
		
		// keyPressed method
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			
			// if the right key is pressed 
			if (key == KeyEvent.VK_RIGHT && !left) { 
				// set right to true
				right=true;
				// set up to false
				up=false;
				// set down to false
				down=false;
			}
			
			// if the left key is pressed
			if (key == KeyEvent.VK_LEFT && !right) {
				// set left to true
				left=true;
				// set up to false
				up=false;
				// set down to false
				down=false;
			}
			
			// if the up arrow key is pressed
			if (key == KeyEvent.VK_UP && !down) {
				// set up to true
				up=true;
				// set right to false
				right=false;
				// set left to false
				left=false;
			}
			
			// if the down arrow key is pressed
			if (key == KeyEvent.VK_DOWN && !up) {
				// set down to true
				down=true;
				// set right to false
				right=false;
				// set left to false
				left=false;
			}
		
		}
		
		// keyTyped method
		public void keyTyped(KeyEvent e) {
			
		}
		
		// keyReleased method
		public void keyReleased(KeyEvent e) {
			
		}
	
	}
 
}

