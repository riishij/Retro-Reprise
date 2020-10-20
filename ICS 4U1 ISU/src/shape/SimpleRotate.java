/*
 * Purpose: Shapes that can rotate to 2 different positions 
 */
package shape;

import java.util.ArrayList;

public interface SimpleRotate {
	//methods to check if rotation is allowed
	public boolean rotateTo2Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	public boolean rotateTo1Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	//methods to rotate the shape
	public void rotateTo2(ArrayList<Shape> shapes,int c,int numSquares);
	public void rotateTo1(ArrayList<Shape> shapes,int c,int numSquares);
}
