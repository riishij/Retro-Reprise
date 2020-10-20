/* Name: Riishi Jeevakumar
 * Purpose: To generate random x and y values for the zappers
 */
package JetpackJoyride;
import java.awt.image.BufferedImage;
public abstract class Zapper extends MovingObject {
	//variables for hzapper and vzapper
	BufferedImage img[];
	//random y value generator
	public abstract int randomY(int a);
	//random x value generator
	public int randomX() {
		return r.nextInt(4000)+2000;
	}	
}
