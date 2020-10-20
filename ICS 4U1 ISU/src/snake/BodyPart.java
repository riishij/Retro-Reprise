/*
 * Purpose: class for making the bodypart, extends the objects
 */


package snake;

// imports
import java.awt.Color;
import java.awt.Graphics;

public class BodyPart extends Objects {
	
	// constructor
	public BodyPart(int xCoor, int yCoor, int tileSize) {
		// inherits the Objects
		super(xCoor, yCoor, tileSize);
	}

	// draw method
	public void draw(Graphics g) {
		// setting the color of the outline of the snake bodypart
		g.setColor(Color.BLACK);
		
		// making the snake
		g.fillRect(xCoor * width, yCoor * height, width, height);
		
		// setting the snake's colour to green
		g.setColor(Color.GREEN);
		
		// filling the snake rectangle
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 4, height-4);

	}
	

}
