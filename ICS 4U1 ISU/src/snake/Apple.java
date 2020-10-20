/*
 * Purpose: class for making the apple, inherits the Object class
 */


package snake;

// imports
import java.awt.Color;
import java.awt.Graphics;

public class Apple extends Objects {

	// constructor
	public Apple(int xCoor, int yCoor, int tileSize) {
		// inherits the Objects
		super(xCoor, yCoor, tileSize);
	}
	
	// draw method 
	 public void draw(Graphics g) {
		 // setting the apple colour
		 g.setColor(Color.RED);
		 // snake
		 g.fillRect(xCoor * width, yCoor * height, width, height);
	 }

}
