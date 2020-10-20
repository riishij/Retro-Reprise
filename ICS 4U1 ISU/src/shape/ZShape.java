/*
 * Purpose: Stores the attributes and methods for the ZShape
 */
package shape;

import java.util.ArrayList;

public class ZShape extends Shape implements SimpleRotate{
	//declare position variable
	int position;
	//constructor
	public ZShape(int startingXLocation,int startingYLocation) {
		super(startingXLocation,startingYLocation);
		position=1;
	}
	//getter for position variable
	public int getPosition() {
		return position;
	}
	//check if the rotation is allowed
	public boolean rotateTo2Check(ArrayList<Shape> shapes,int numSquares,String[][] grid) {
		//checks if the shape will rotate out of the board
		if(shapes.get(numSquares-2).getYLocationTop()<300) { 
			//checks if the shape will hit any other shapes if it rotates
			int num1=((shapes.get(numSquares-1).getYLocationTop()-30)/15)-1;
			int num2=((shapes.get(numSquares-1).getXLocationLeft()-50)/15);
			int num3=((shapes.get(numSquares-2).getYLocationTop()-30)/15)+1;
			int num4=((shapes.get(numSquares-2).getXLocationLeft()-50)/15);
			if(grid[num1][num2].equals("full")) {	
				return false;		
			}
			else if(grid[num3][num4].equals("full")) {	
				return false;		
			}
			else {
				return true;
			}	
		}	
		return false;
	}
	//check if the rotation is allowed
	public boolean rotateTo1Check(ArrayList<Shape> shapes,int numSquares,String[][] grid) {
		//checks if the shape will rotate out of the board
		if(shapes.get(numSquares-1).getXLocationLeft()>50) { 
			//checks if the shape will hit any other shapes if it rotates
			int num1=((shapes.get(numSquares-2).getYLocationTop()-30)/15)-1;
			int num2=((shapes.get(numSquares-2).getXLocationLeft()-50)/15);
			int num3=((shapes.get(numSquares-4).getYLocationTop()-30)/15);
			int num4=((shapes.get(numSquares-4).getXLocationLeft()-50)/15)-2;
			if(grid[num1][num2].equals("full")) {	
				return false;		
			}
			else if(grid[num3][num4].equals("full")) {	
				return false;	
			}	
			else {
				return true;
			}	
		}	
		return false;
	}
	//rotates the shape to position 2
	public void rotateTo2(ArrayList<Shape> shapes,int c,int numSquares){
		//set the position
		this.position++;	
		//set the new starting x and y location of each shape (c represents the number of the square in the shape)
		if(c==0) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(125);
			shapes.get(numSquares-(c+1)).setStartingYLocation(60);
		}
		else if(c==1) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(125);
			shapes.get(numSquares-(c+1)).setStartingYLocation(45);
		}
		else if(c==2) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(140);
			shapes.get(numSquares-(c+1)).setStartingYLocation(45);
		}
		else if(c==3) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(140);
			shapes.get(numSquares-(c+1)).setStartingYLocation(30);
		}
	}
	//rotates the shape to position 1
	public void rotateTo1(ArrayList<Shape> shapes,int c,int numSquares){
		//set the position
		this.position=1;
		//set the new starting x and y location of each shape (c represents the number of the square in the shape)
		if(c==0) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(140);
			shapes.get(numSquares-(c+1)).setStartingYLocation(45);
		}
		else if(c==1) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(125);
			shapes.get(numSquares-(c+1)).setStartingYLocation(45);
		}
		else if(c==2) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(125);
			shapes.get(numSquares-(c+1)).setStartingYLocation(30);
		}
		else if(c==3) {
			shapes.get(numSquares-(c+1)).setStartingXLocation(110);
			shapes.get(numSquares-(c+1)).setStartingYLocation(30);
		}
	}
	//calculate x and y location
	public int getXLocationLeft() {		
		return startingXLocation+x;
	}	
	public int getYLocationTop() {	
		return startingYLocation+y;
	}		
}
