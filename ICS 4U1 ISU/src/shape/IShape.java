/*
 * Purpose: Stores the attributes and methods for the IShape
 */
package shape;

import java.util.ArrayList;

public class IShape extends Shape implements SimpleRotate{
	//declare position variable
	private int position;
	
	//constructor
	public IShape(int startingXLocation,int startingYLocation) {
		super(startingXLocation,startingYLocation);	
		position=1;
	}
	
	//getter for position variable
	public int getPosition() {
		return position;
	}
	//rotates the shape to position 1
	public void rotateTo1(ArrayList<Shape> shapes,int c,int numSquares){	
		//set the position
		position=1;
		
		//set the new starting x location of each shape (c represents the number of the square in the shape)
		if(c==0) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(95);
		}
		else if(c==1) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(110);
		}
		else if(c==2) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(125);
		}
		else if(c==3) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(140);
		}
		//all squares have the same starting y location
		shapes.get(numSquares-(c+1)).setStartingYLocation(30);
		
	}
	//rotates the shape to position 2
	public void rotateTo2(ArrayList<Shape> shapes,int c,int numSquares){
		//set the position
		position++;
		
		//set the new starting y location of each shape (c represents the number of the square in the shape)
		if(c==0) {
			shapes.get(numSquares-(c+1)).setStartingYLocation(75);
		}
		else if(c==1) {
			shapes.get(numSquares-(c+1)).setStartingYLocation(60);
		}
		else if (c==2) {
			shapes.get(numSquares-(c+1)).setStartingYLocation(45);
		}
		else if(c==3) {
			shapes.get(numSquares-(c+1)).setStartingYLocation(30);
		}
		shapes.get(numSquares-(c+1)).setStartingXLocation(110);
	}
	
	//check if the rotation is allowed
	public boolean rotateTo1Check(ArrayList<Shape> shapes,int numSquares,String[][] grid) {
		//checks if the shape if too far left or right
		if(shapes.get(numSquares-1).getXLocationLeft()>50&&shapes.get(numSquares-1).getXLocationLeft()<170) {
			//checks if the shape will hit any other shapes if it rotates
			for(int i=0;i<4;i++) {
				int num1=((shapes.get(numSquares-3).getYLocationTop()-30)/15);
				int num2=((shapes.get(numSquares-3).getXLocationLeft()-50)/15)-1+i;
				if(grid[num1][num2].equals("full")) {	
					return false;				
				}
			}
			return true;
		}	
		return false;		
	}
	//check if the rotation is allowed
	public boolean  rotateTo2Check(ArrayList<Shape> shapes,int numSquares,String[][] grid) {
		//checks if the shape if too far down
		if(shapes.get(numSquares-1).getYLocationTop()<270) {
			for(int i=0;i<4;i++) {
				//checks if the shape will hit any other shapes if it rotates
				int num1=((shapes.get(numSquares-3).getYLocationTop()-30)/15)+i;
				int num2=(shapes.get(numSquares-3).getXLocationLeft()-50)/15;
				if(grid[num1][num2].equals("full")) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	//calculate x and y location
	public int getXLocationLeft() {		
		return startingXLocation+x;
	}	
	public int getYLocationTop() {	
		return startingYLocation+y;
	}		
}
