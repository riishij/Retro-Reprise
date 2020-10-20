/* Name: Riishi Jeevakumar
 * Purpose: To create a frame for Jetpack Joyride
 * 
 */
package JetpackJoyride;
import javax.swing.JFrame;
//refer to GameObject class for header
public class MainMethod {
	public static JFrame frame= new JFrame("Jetpack Joyride");
	public static boolean running;
	public static void main(String[] args) throws InterruptedException{
		//make a jframe object to paint components
		//making a gameobject object to add to jframe
		GameObject game = new GameObject();
		//everything is drawn in gameobject, which is then added to the jframe
	    frame.add(game);
	    //size of jframe
		frame.setSize(1200, 685);
		//jframe is visible
		frame.setVisible(true);
		//put it in the middle
		frame.setLocationRelativeTo(null);
		//close program when you press x
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		running=true;
	}
}
