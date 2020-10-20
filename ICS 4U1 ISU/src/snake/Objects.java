/*
 * Purpose: class for the objects which Apple and BodyPart inherit
 */

package snake;


public class Objects {
	
	// data members
	protected int xCoor;
	protected int yCoor;
	protected int width;
	protected int height;
	
	
	// constructor
	public Objects (int xCoor, int yCoor, int tileSize) {
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		width=tileSize;
		height=tileSize;
	}
	
	// tick method
	 public void tick() {
	 }
	

	// getters and setters for xCoor and yCoor
	 // xCoor getter
	public int getxCoor() {
		return xCoor;
	}

	// xCoor setter
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	// yCoor getter
	public int getyCoor() {
		return yCoor;
	}

	// yCoor setter
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	 
	 

}

