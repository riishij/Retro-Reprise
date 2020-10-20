/*
 * Purpose: Shows the results of the game options to play again or return to main menu
 */
package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainMenu.MainMenu;

public class GameOver extends JFrame { 
	//create the labels that will create the gui
	private JLabel gameOverLabel=new JLabel("Game Over");
	private JLabel playerLabel=new JLabel("Player:");
	private JLabel scoreLabel=new JLabel("Score:");
	
	//create panel object
	private JPanel panel=new JPanel();
	
	//create buttons that play again or return to main menu
	private JButton playAgainButton=new JButton("Play Again");
	private JButton returnButton=new JButton("Return to Main Menu");
	
	//hold names for leaderboard
	private JLabel players[]= new JLabel[5];
	
	//hold scores to sort
	private int scoresToSort[]= {5320,1025,4589,7256,Board.score};
	
	//hold scores to print 
	private JLabel scoresToPrint[]= new JLabel[5];
	
	//default constructor 
	public GameOver() {
		//adds names to the leaderboard
		players[0]=new JLabel("Patricia");
		players[1]=new JLabel("Riishi");
		players[2]=new JLabel("Anonymous");
		players[3]=new JLabel("Dhwani");
		players[4]=new JLabel(Introduction.name);
		
		//add panel to the frame
		add(panel);
		panel.setLayout(null);
		
		//add the buttons and labels to the panel
		panel.add(gameOverLabel);	
		panel.add(playAgainButton);
		panel.add(returnButton);
		panel.add(playerLabel);
		panel.add(scoreLabel);
		
		//set the location and size of gui elements
		gameOverLabel.setBounds(145, 30, 100, 30);
		playAgainButton.setBounds(110, 70, 160, 30);
		returnButton.setBounds(110, 110, 160, 30);
		playerLabel.setBounds(110,160,50,30);
		scoreLabel.setBounds(220,160,50,30);
		
		//set font and background of gui elements
		gameOverLabel.setFont(new Font("Tahoma",Font.PLAIN , 18));			
		returnButton.setBackground(Color.ORANGE);
		playAgainButton.setBackground(Color.YELLOW);
		
		//add action listener to play again button
		playAgainButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//close this frame and reset score, run the main method again
				dispose();
				Board.score=0;
				Introduction menu=new Introduction();
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
				menu.pack();
				menu.setVisible(true);
			} }); 
		returnButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//close this frame and reset score, run the main method again
				dispose();
				Board.score=0;
				MainMenu.main(null);
			} }); 	
		
		//sort the scores
		sort();
	
		//formatting the leaderboard onto the panel
		int c=190;
		for(int x=0;x<5;x++) {
			scoresToPrint[x]=new JLabel(String.valueOf(scoresToSort[x]));
			//adding to the panel
			panel.add(players[x]);
			panel.add(scoresToPrint[x]);
			//setting the size and location
			players[x].setBounds(110,c,80,30);
			scoresToPrint[x].setBounds(220,c,50,30);
			c+=30;
		}
	}
	
	//sorts the scores and rearranges to players' names to match
	private void sort() {
		//declare variables
		int temp,passes,compareNum;
		JLabel temp2;
		passes=players.length -1;
		compareNum=passes;
		
		for(int i=0; i < passes; i++) {
			//sorts in descending order
		    for (int j=0; j < compareNum;j++) {
		    	if(scoresToSort[j] < scoresToSort[j+1]) {
		    		temp=scoresToSort[j];
		    		temp2=players[j];
		    		scoresToSort[j]=scoresToSort[j+1];
		    		players[j]=players[j+1];
		    		scoresToSort[j+1]=temp;
		    		players[j+1]=temp2;
		    	}
		    }		  
		   compareNum--;	    
		}
	}	
}