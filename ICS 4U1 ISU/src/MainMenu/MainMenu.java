/*	Main Menu of RETRO REPRISE (Est.2019)
 *  Name: Riishi Jeevakumar
 *  Purpose: This is the main menu and connects to the other games in the arcade
 *  Licensing info: The music used for the main menu was purchased from the iOS App Store for $0.99
 *                  and the image used for the background was created on Adobe Photoshop (black background with stars)
 */

package MainMenu;
//importing all the packages
//importing all the requirements for the Frame (swing class)
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
//importing all of the different game packages
import JetpackJoyride.*;
import tetris.*;
import snake.*;
public class MainMenu {
	//declaring static variable for the sound effect
	static int soundEffect=1;
	static JFrame frame; 
	static ContinuousAudioDataStream loop=null;
	static AudioPlayer AP=AudioPlayer.player;
	static AudioPlayer AP2=AudioPlayer.player;
	//Sets up and displays the frame
	public static void main(String[] args) {
		music();
		Font font=new Font("Arcade Classic",Font.PLAIN,0);
		//Declare Main JFrame
		frame = new JFrame("Main Menu");
		frame.setBackground(UIManager.getColor("Button.focus"));
		frame.setSize(852,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//import image
		ImageIcon background=new ImageIcon("src\\mainMenuBackground\\gameBackground.png");
		frame.setLayout(null);
		//Set image into background
		JLabel back=new JLabel(background);
		back.setBounds(0, 0, 846, 480);
		back.setForeground(Color.RED);
		back.setFont(new Font("Century Schoolbook", Font.PLAIN, 98));
		back.setText("");
		back.setLayout(new BoxLayout(back, BoxLayout.Y_AXIS));
		frame.add(back);
		
		//Adding label
		JLabel label=new JLabel("RETRO REPRISE");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(new Color(148, 50, 251));
		label.setFont(new Font("Times New Roman", Font.BOLD, 72));
		back.add(label);
		
		//Creating a panel for the label
		JLabel space=new JLabel("  ");
		back.add(space);
		space.setAlignmentX(Component.CENTER_ALIGNMENT);
		space.setFont (font.deriveFont (25.0f));
		
		//Creating another panel for a different label
		JLabel space1=new JLabel("  ");
		back.add(space1);
		space1.setAlignmentX(Component.CENTER_ALIGNMENT);
		space1.setFont (font.deriveFont (25.0f));
		
		//Add button #1
		JButton button1=new JButton("Option 1: Jetpack Joyride");
		button1.setToolTipText("This button is for Jetpack Joyride");
		back.add(button1);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button1.setFont (font.deriveFont (25.0f));
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					JetpackJoyride();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Add button #2
		JButton button2=new JButton("        Option 2: Tetris        ");
		button2.setToolTipText("This button is for Tetris");
		back.add(button2);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button size
		button2.setFont (font.deriveFont (25.0f));
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				tetris();
			}
		});
		
		
		//Add button #3
		JButton button3=new JButton("       Option 3: Snake       ");
		button3.setToolTipText("This option is for Snake");
		back.add(button3);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button3.setFont (font.deriveFont (25.0f));
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				snake();
			}
		});
		
		//Add button #4
		JButton button4=new JButton("                 Exit                 ");
		button4.setToolTipText("This is to exit the program"); 
		back.add(button4);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button4.setFont (font.deriveFont (25.0f));
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.out.println("Thanks for coming!");
				AP.stop(loop);exit();
				
			}
		});
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	//Music starts to play
	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e) {
			music();
		}
	}
	//Adds sound to the menu
	public static void music() {
		AudioStream AS;
		AudioData AD;
		try {
			AS=new AudioStream(new FileInputStream("tSound.wav"));
			AD=AS.getData();
			loop=new ContinuousAudioDataStream(AD);
		}catch(IOException error) {
			System.out.print("No sound for you.");
		}
		
		AP.start(loop);
		if(soundEffect==0) {
			AP.stop(loop);}
	}
	//Opens Jetpack Joyride menu
	public static void JetpackJoyride() throws InterruptedException {
		if (MainMethod.running) {
			MainMethod.frame.setVisible(true); 
		}
		else {
			MainMethod.main(null);
		}
		AP.stop(loop);
	}
	//Opens Tetris menu
	public static void tetris(){
		AP.stop(loop);
		Introduction menu=new Introduction();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		menu.setSize(400,400);
		menu.setVisible(true);
		}
	//Opens Snake menu
	public static void snake() {
		AP.stop(loop);
		EventQueue.invokeLater(new Runnable() {
			// run method
			public void run() {
				//try catch
				try {
					// make a new frame of Start
					Start frame = new Start();
					// set the frame to visible
					frame.setVisible(true);
					// center the frame
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Exits the program
	public static void exit(){
	}
}
