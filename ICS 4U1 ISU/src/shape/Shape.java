/*
 * Purpose: Stores the attributes and methods for shapes
 */
package shape;

public abstract class Shape {
	//x and y are the amount the shape moves
	protected int x,y;
	
	//where the shapes start on the frame
	protected int startingXLocation;
	protected int startingYLocation;
	
	//getters and setters for starting locations
	public int getStartingXLocation() {
		return startingXLocation;
	}
	public void setStartingXLocation(int startingXLocation) {
		this.startingXLocation = startingXLocation;
	}
	public int getStartingYLocation() {
		return startingYLocation;
	}
	public void setStartingYLocation(int startingYLocation) {
		this.startingYLocation = startingYLocation;
	}

	//getters and setters for x and y
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//constructor
	public Shape(int startingXLocation,int startingYLocation) {
		x=0;
		y=0;
		this.startingXLocation=startingXLocation;
		this.startingYLocation=startingYLocation;		
	}
	
	//calculates the location of the shape
	public abstract int getXLocationLeft();
	public abstract int getYLocationTop();	
}
