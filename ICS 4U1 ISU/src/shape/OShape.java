/*
 * Purpose: Stores the attributes and methods for the OShape
 */
package shape;

public class OShape extends Shape{
	//constructor
	public OShape(int startingXLocation,int startingYLocation) {
		super(startingXLocation,startingYLocation);
	}
	//calculate x and y location
	public int getXLocationLeft() {		
		return startingXLocation+x;
	}	
	public int getYLocationTop() {	
		return startingYLocation+y;
	}		
}
