/*
 * Purpose: Shapes that can rotate to 4 different positions 
 */
package shape;

import java.util.ArrayList;

public interface ComplexRotate {
	//methods to check if rotation is allowed
	public boolean rotateTo2Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	public boolean rotateTo1Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	public boolean rotateTo3Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	public boolean rotateTo4Check(ArrayList<Shape> shapes,int numSquares,String[][] grid);
	//methods to rotate the shape
	public void rotateTo2(ArrayList<Shape> shapes,int c,int numSquares);
	public void rotateTo1(ArrayList<Shape> shapes,int c,int numSquares);
	public void rotateTo3(ArrayList<Shape> shapes,int c,int numSquares);
	public void rotateTo4(ArrayList<Shape> shapes,int c,int numSquares);
}
