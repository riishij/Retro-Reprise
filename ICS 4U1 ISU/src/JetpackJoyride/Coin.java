/* Name: Riishi Jeevakumar
 * Purpose: To implement coins into Jetpack Joyride
 * Licensing info: All of the coin images were created on Adobe Photoshop
 */

package JetpackJoyride;
//importing packages
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin extends MovingObject{
	//importing images into array for animation
	BufferedImage img[]=new BufferedImage[4];{
		try {
			img[0]=ImageIO.read(new File("JetpackImages\\Coin\\1.png"));
			img[1]=ImageIO.read(new File("JetpackImages\\Coin\\2.png"));
			img[2]=ImageIO.read(new File("JetpackImages\\Coin\\3.png"));
			img[3]=ImageIO.read(new File("JetpackImages\\Coin\\4.png"));
		} catch (IOException e) {
			e.printStackTrace();		}	};
	public Coin() {
		//initial position of coin graphically
		x=0;
		y=0;
		sizeX=25;
		sizeY=25;
		velX=0;
		velY=0;
		frame=0;
	}
	//paint method to paint coin
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		//size: 25x25 for coin
		g2d.drawImage(img[frame],x,y,null);
	}
	//move method to move coin in game so long as gameover is false
	public void move(boolean gameOver, int vel) {
		if (!gameOver) {
			velX=vel;
			x+=velX;
			y+=velY;
		}
	}
}