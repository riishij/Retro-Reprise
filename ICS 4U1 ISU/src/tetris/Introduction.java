/*
 * Purpose: Shows the instructions and waits for the user to start
 */
package tetris;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Introduction extends JFrame implements ActionListener{ 
	//create panel object 
	private JPanel panel=new JPanel();
	
	//declare the labels that will create the title-separate labels for different colours
	private JLabel label=new JLabel(": A Remake");
	private JLabel labelT=new JLabel("T");
	private JLabel labelE=new JLabel("E");
	private JLabel labelT2=new JLabel("T");
	private JLabel labelR=new JLabel("R");
	private JLabel labelI=new JLabel("I");
	private JLabel labelS=new JLabel("S");
	
	//labels that show the instructions
	private JLabel label2=new JLabel("Inspired by the original game by The Tetris Company LLC");
	private JLabel label3=new JLabel("Left/Right Key: Move the block to the left/right");
	private JLabel label4=new JLabel("Down Key: Accelerate the block downwards");
	private JLabel label5=new JLabel("Space Bar: Rotate the block");
	private JLabel label6=new JLabel("Your goal is to fill the rows so they dont reach the top!");
	private JLabel enterNameLabel=new JLabel("Enter your name: ");
	
	//button that when pressed opens board
	private JButton startButton=new JButton("Start");
	//allows user to enter their name
	private JTextField field=new JTextField();
	//holds the user's name
	static String name;
	
	//default constructor
	public Introduction() {
		//adds panel to frame
		add(panel);		
		panel.setLayout(null);
		
		//sets the colour of the labels
		labelT.setForeground (Color.RED);
		labelE.setForeground (Color.BLUE);
		labelT2.setForeground (Color.MAGENTA);
		labelR.setForeground (Color.ORANGE);
		labelI.setForeground (Color.GREEN);
		labelS.setForeground (Color.CYAN);
		
		//add the title tetris to the panel
		panel.add(labelT);
		panel.add(labelE);
		panel.add(labelT2);
		panel.add(labelR);
		panel.add(labelI);
		panel.add(labelS);
		panel.add(label);
		
		//add the instructions to the panel
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(enterNameLabel);
		
		//add the button and text field to the panel
		panel.add(startButton);
		panel.add(field);
		
		//add actionlistener to the button
		startButton.addActionListener(this);
		
		//set size and location for each gui element
		label.setBounds(170, 50, 150, 30);
		labelT.setBounds(105, 50, 150, 30);
		labelE.setBounds(118, 50, 150, 30);
		labelT2.setBounds(130, 50, 150, 30);
		labelR.setBounds(141, 50, 150, 30);
		labelI.setBounds(152, 50, 150, 30);
		labelS.setBounds(160, 50, 150, 30);
		label2.setBounds(30, 80,340, 30);
		label3.setBounds(40, 120,320, 30);
		label4.setBounds(40, 150,320, 30);
		label5.setBounds(40, 180,320, 30);
		label6.setBounds(40, 210,320, 30);
		startButton.setBounds(250, 240, 70, 30);
		field.setBounds(145,240,100,30);
		enterNameLabel.setBounds(40,240,120,30);
		
		//set font of each label
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelT.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelE.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelT2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelR.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//set background for the button 
		startButton.setBackground(Color.YELLOW);
	}
	
	//when the button is pressed
	public void actionPerformed(ActionEvent e) {
		//sets the name variable to what was typed in the text field
		Introduction.name=field.getText();
		
		//disposes the frame 
		dispose();
		
		//creates a new frame and adds the board panel to it, starting the game
		JFrame frame=new JFrame();
		frame.setSize(400, 400);		
		Board board=new Board();	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.add(board);							
	}	
}
