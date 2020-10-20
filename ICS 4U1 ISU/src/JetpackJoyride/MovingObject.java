/* Name: Riishi Jeevakumar
 * Purpose: To implement moving objects into Jetpack Joyride using set and get methods
 * 
 */
package JetpackJoyride;
import java.awt.Graphics;
import java.util.Random;

public abstract class MovingObject {
	Random r=new Random();
	//declare variables
	int x,y,velX,velY,sizeX,sizeY,frame;
	public abstract void paint(Graphics g);
	public abstract void move(boolean gameOver,int vel);
	public int getX() {
		return x;
	}
	//set and get methods
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY()  {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public int getFrame() {
		return frame;
	}
	public void setFrame(int frame) {
		this.frame = frame;
	}
}
