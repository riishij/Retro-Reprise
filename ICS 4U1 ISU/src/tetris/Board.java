/*
 * Purpose: The game board where the game is played
 * Source: Sound effects from https://www.findsounds.com/
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import shape.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener,ActionListener{
	//a timer that refreshes the board every 800 milliseconds
	private Timer timer=new Timer(800,this);
	
	//generates a number to create a random shape
	private Random rand=new Random();
	
	//holds the random number created by rand
	private int numSquares,numOfShape,numOfNextShape;	
	
	//getter and setters 
	public int getNumOfNextShape() {
		return numOfNextShape;
	}

	public void setNumOfNextShape(int numOfNextShape) {
		this.numOfNextShape = numOfNextShape;
	}
	
	//holds the shapes
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	
	//determines if a space in the grid is filled or empty
	private String grid[][]=new String[20][10];
	
	//hold the starting positions of the ishape and oshape
	private int startingPosition[][][]=new int[2][4][2];
	
	//buttons and labels for gui
	private JButton exitButton=new JButton("Exit");
	private JButton restartButton=new JButton("Restart");
	private JLabel scoreLabel=new JLabel("Score: ");
	private JLabel nextShapeLabel=new JLabel("Next Shape ");
	
	//holds the score
	static int score;
	//default constructor
	public Board()  {
		score=0;
		//sets every box in the grid empty
		for (int row=0;row<grid.length;row++) {
			for (int col=0;col<grid[row].length;col++) {
				grid[row][col]="Empty";
			}
		}
		
		//generating random shape
		numSquares=4;				
		numOfShape=rand.nextInt(7)+1;	
		setNumOfNextShape(rand.nextInt(7)+1);
		
		//set the values of the starting positions 
		int c=95;
		for(int j=0;j<startingPosition.length;j++) {
			for (int i=0;i<startingPosition[j].length;i++) {
				//for ishape
				if(j==0) {
					startingPosition[j][i][0]=c;	
					startingPosition[j][i][1]=30;
					c+=15;
				}
				//for oshape
				else {
					if(i==0) {
						startingPosition[j][i][0]=95;
						startingPosition[j][i][1]=30;
					}
					else if(i==1) {
						startingPosition[j][i][0]=110;
						startingPosition[j][i][1]=30;
					}
					else if(i==2) {
						startingPosition[j][i][0]=95;
						startingPosition[j][i][1]=45;
					}
					else if(i==3) {
						startingPosition[j][i][0]=110;
						startingPosition[j][i][1]=45;
					}				
				}				
			}
		}		
		//creating ishape
		if (numOfShape==1) {
			createIShape();			
		}
		//creating oshape
		else if(numOfShape==2) {
			createOShape();		
		}
		//creating lshape
		else if(numOfShape==3) {
			createLShape();
		}
		//creating jshape
		else if(numOfShape==4) {
			createJShape();
		}
		//creating sshape
		else if(numOfShape==5) {
			createSShape();
		}
		//creating zhape
		else if(numOfShape==6) {
			createZShape();
		}
		//creating tshape
		else if(numOfShape==7) {
			createTShape();
		}	
		
		//for animation
		addKeyListener(this);
		setFocusable(true);		
		
		//setting size and layout
		setLayout(null);		
		setBounds(0, 0, 400,400);	
		//adding buttons and labels
		add(exitButton);
		add(restartButton);	
		add(scoreLabel);
		add(nextShapeLabel);	
		//setting their size and location
		exitButton.setBounds(230, 200, 100, 30);
		restartButton.setBounds(230, 250, 100, 30);
		scoreLabel.setBounds(230,150,100,30);
		nextShapeLabel.setBounds(230,50,100,30);		
		//setting the background
		exitButton.setBackground(Color.LIGHT_GRAY);
		restartButton.setBackground(Color.LIGHT_GRAY);	
		
		//action listener for exit button
		exitButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//close the frame
				((Window) getRootPane().getParent()).dispose();
				timer.stop();
				
				//create the gameover frame
				GameOver gameOver=new GameOver();
				gameOver.pack();
				gameOver.setVisible(true);
				gameOver.setSize(400, 400);				
			} }); 			
		//action listener for restart button
		restartButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				timer.stop();
				//close thr frame
				((Window) getRootPane().getParent()).dispose();
				
				//create a new frame and add the panel to it
				JFrame frame=new JFrame();
				frame.setSize(400, 400);
				Board board=new Board();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
				frame.add(board);		
				//reset score
				Board.score=0;
			} }); 	
		
		//start the animation
		timer.start();	
	}
	
	//paints the panel
	public void paintComponent (Graphics g) { //learned how to use Graphics 2D class
		//repaints the panel blank before new animation
		super.paintComponent(g);		
		//creating graphics object
		Graphics2D g2d = (Graphics2D) g;		
		//setting the background of my board
		g2d.setBackground(Color.BLACK);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(50, 30, 150, 300);	
		
		//next shape is ishape
		if(getNumOfNextShape()==1) {			
			drawIShape(g2d);
		}
		//next shape is oshape
		else if(getNumOfNextShape()==2) {			
			drawOShape(g2d);
		}
		//next shape is lshape
		else if(getNumOfNextShape()==3) {			
			drawLShape(g2d);
		}
		//next shape is jshape
		else if(getNumOfNextShape()==4) {			
			drawJShape(g2d);
		}
		//next shape is sshape
		else if(getNumOfNextShape()==5) {	
			drawSShape(g2d);
		}
		//next shape is zshape
		else if(getNumOfNextShape()==6) {		
			drawZShape(g2d);
		}
		//next shape is tshape
		else if(getNumOfNextShape()==7) {		
			drawTShape(g2d);
		}		
		
		//prints each square
		for (int i=0;i<numSquares;i++) {	
			//square is part of ishape 
			if(shapes.get(i).getClass()==IShape.class) {
				g2d.setColor(Color.BLUE);				
			}
			//square is part of oshape
			else if(shapes.get(i).getClass()==OShape.class) {
				g2d.setColor(Color.ORANGE);	
			}
			//square is part of lshape
			else if(shapes.get(i).getClass()==LShape.class) {
				g2d.setColor(Color.CYAN);	
			}
			//square is part of jshape
			else if(shapes.get(i).getClass()==JShape.class) {
				g2d.setColor(Color.MAGENTA);	
			}
			//square is part of sshape
			else if(shapes.get(i).getClass()==SShape.class) {
				g2d.setColor(Color.RED);	
			}
			//square is part of zshape
			else if(shapes.get(i).getClass()==ZShape.class) {
				g2d.setColor(Color.GREEN);	
			}
			//square is part of tshape
			else if(shapes.get(i).getClass()==TShape.class) {
				g2d.setColor(Color.YELLOW);	
			}			
			//paints the square
			g2d.fillRect(shapes.get(i).getXLocationLeft(),shapes.get(i).getYLocationTop(),15,15);	
		}		
		//paints the grid lines vertically
		g2d.setColor(Color.GRAY);	
		for (int i=50;i<=200;i+=15) {			
			g2d.drawLine(i, 30, i, 330);
		}
		//paints the grid lines horizontally
		for (int i=30;i<=330;i+=15) {
			g2d.drawLine(50, i,200, i);
		}	
	}
					
	//for keyboard input-doesn't react when key is pressed or typed
	public void keyPressed(KeyEvent e) {}		
	public void keyTyped(KeyEvent e) {}
	
	//when key is released
	public void keyReleased(KeyEvent e) {
		//stores the key code
		int code=e.getKeyCode();	
		//user entered right key
		if (code==KeyEvent.VK_RIGHT) {				
			for(int i=0;i<4;i++) {
				
				//tests if each square in the shape is at the right boundary of the board
				if (shapes.get(numSquares-(i+1)).getXLocationLeft()>=185) { 
					playSound("sound/norotate.wav");
					break;
				}				
				//find the location in the grid the square is in and if there is a square to the right of it
				int num1=(shapes.get(numSquares-(i+1)).getYLocationTop()-30)/15;
				int num2=((shapes.get(numSquares-(i+1)).getXLocationLeft()-50)/15)+1;
				if (grid[num1][num2].equals("full")) {
					playSound("sound/norotate.wav");
					break;
				}				
				//if none of the previous conditions have caused the loop to break for the previous squares
				if(i==3) {
					moveRight();					
					repaint();
				}					
			}									
		}
		//user entered left key
		else if (code==KeyEvent.VK_LEFT) {			
			for(int i=0;i<4;i++) {
				//tests if each square in the shape is at the left boundary of the board
				if (shapes.get(numSquares-(i+1)).getXLocationLeft()<=50) {
					playSound("sound/norotate.wav");
					break;
				}
				//find the location in the grid the square is in and if there is a square to the left of it
				int num1=(shapes.get(numSquares-(i+1)).getYLocationTop()-30)/15;
				int num2=((shapes.get(numSquares-(i+1)).getXLocationLeft()-50)/15)-1;								
				if (grid[num1][num2].equals("full")) {
					playSound("sound/norotate.wav");
					break;
				}
				//if none of the previous conditions have caused the loop to break for the previous squares
				if(i==3) {
					moveLeft();
					repaint();
				}	
			}				
		}
		
		//user entered down key
		else if (code==KeyEvent.VK_DOWN) {			
				speedUp();
		}
		
		//user released space bar
		else if (code==KeyEvent.VK_SPACE) {
			//current shape is ishape
			if(shapes.get(numSquares-1).getClass()==IShape.class) {
				rotateIShape();				
			}
			//current shape is lshape
			else if(shapes.get(numSquares-1).getClass()==LShape.class) {
				rotateLShape();
			}
			//current shape is jshape
			else if(shapes.get(numSquares-1).getClass()==JShape.class) {
				rotateJShape();
			}
			//current shape is sshape
			else if(shapes.get(numSquares-1).getClass()==SShape.class) {
				rotateSShape();
			}
			//current shape is zshape
			else if(shapes.get(numSquares-1).getClass()==ZShape.class) {
				rotateZShape();
			}
			//current shape is tshape
			else if(shapes.get(numSquares-1).getClass()==TShape.class) {
				rotateTShape();
			}
			//repaint shape in the new position 
			repaint();
		}		
	}

	public void actionPerformed(ActionEvent e) { 
		//update score on gui
		scoreLabel.setText("Score: "+Board.score );
	
		boolean test=false;
		for (int i=0;i<4;i++) {
			//test if the row beneath the current shape is empty
			int num1=((shapes.get(numSquares-(i+1)).getYLocationTop()-30)/15)+1;
			int num2=(shapes.get(numSquares-(i+1)).getXLocationLeft()-50)/15;
			if (shapes.get(numSquares-1).getYLocationTop()<315&&grid[num1][num2].equals("Empty")) {
				test=true;			
			}
			//breaks the whole loops if one shape has a shape under it
			else {
				test=false;
				break;
			}
		}
		//shape is allowed to fall
		if (test==true) {		
			for(int i=1;i<5;i++) {
				//moves each square down one
				int num=shapes.get(numSquares-i).getY(); 
				shapes.get(numSquares-i).setY(num+=15);			
				repaint();
			}			
		}
		//shape is not allowed to fall	
		else {		
			//shape has not moved at all-game ends
			if(shapes.get(numSquares-1).getY()==0) {
				//plays a sound and learned exception handling
				playSound("sound/gameover.wav");			
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				//deletes this frame and opens the gameover frame
				((Window) getRootPane().getParent()).dispose();
				GameOver gameOver=new GameOver();
				gameOver.pack();
				gameOver.setVisible(true);
				gameOver.setSize(400, 400);
				//stops the timer
				((Timer)e.getSource()).stop();					
			}
			//sets grid where shape is as full
			for (int i=0;i<4;i++) {
				int num1=(shapes.get(numSquares-(i+1)).getYLocationTop()-30)/15;
				int num2=(shapes.get(numSquares-(i+1)).getXLocationLeft()-50)/15;
				grid[num1][num2]="full";			
			}
			//plays sound effect
			playSound("sound/fallen.wav");
			
			//checks if the row needs to be cleared
			for(int row=0;row<grid.length;row++) {
				int counter=0;
				for(int col=0;col<grid[row].length;col++) {
					//test if each column in the row is full
					if(grid[row][col].equals("full")) { 
						counter++;		
						//hold the shapes that need to be removed
						Shape temp[]=new Shape[10];
						//row is full
						if(counter==10) {
							//plays sound effect, adds to score, and resets counter
							playSound("sound/clear.wav");					
							Board.score+=100;
							counter=0;
							
							//adds the shapes to a temp array
							for(int i=0;i<shapes.size();i++) {		
								if(shapes.get(i).getYLocationTop()==row*15+30) {
									temp[counter]=shapes.get(i);
									counter++;							
								}														
							}
							//removes the shapes that are equal to the temp array
							for (int i=0;i<shapes.size();i++) {
								for(int c=0;c<temp.length;c++) {
									if(shapes.get(i).equals(temp[c])) {
										shapes.remove(i);							
									}
								}					
							}
							
							//set those grid spaces to be empty and decrease the number of shapes
							for(int i=0;i<10;i++) {
								grid[row][i]="Empty";
								numSquares--;
							}
							
							//moved arraylist holds the shapes that were above the row that was just cleared
							ArrayList<Shape> moved=new ArrayList<Shape>();
							for (int i=0;i<numSquares;i++) { 
								//only looks at shapes above the rows that was cleared
								if ((shapes.get(i).getYLocationTop()-30)/15<row) {
									int num1=((shapes.get(i).getYLocationTop()-30)/15);
									int num2=(shapes.get(i).getXLocationLeft()-50)/15;
									//set those grid spots empty
									grid[num1][num2]="Empty";
									
									//moves the square down one
									int num3=shapes.get(i).getY();
									shapes.get(i).setY(num3+=15);
									moved.add(shapes.get(i));
								}								
							}
							
							//make the grid spaces with the shapes equal to the shapes in the moved array equal to full
							for (int i=0;i<moved.size();i++) {
								for (int c=0;c<shapes.size();c++) {
									if(shapes.get(c).equals(moved.get(i))) {
										int num1=(shapes.get(c).getYLocationTop()-30)/15;
										int num2=(shapes.get(c).getXLocationLeft()-50)/15;
										grid[num1][num2]="full";									
									}
								}											
							}
							//repaint the board
							repaint();
						}		
					}
				}			
			}
			
			//create the next ishape
			if (numOfNextShape==1) {
				createIShape();
			}
			//create the next oshape
			else if(numOfNextShape==2) {
				createOShape();		
			}
			//create the next lshape
			else if(numOfNextShape==3) {
				createLShape();
			}
			//create the next jshape
			else if(numOfNextShape==4) {
				createJShape();
			}
			//create the next sshape
			else if(numOfNextShape==5) {
				createSShape();
			}
			//create the next zshape
			else if(numOfNextShape==6) {
				createZShape();			
			}
			//create the next tshape
			else if(numOfNextShape==7) {
				createTShape();
			}
			
			//determine the next shape
			setNumOfNextShape(rand.nextInt(7)+1);
			
			//update the number of squares and game score
			numSquares+=4;
			Board.score+=10;		
			slowDown();
			
			//exception handling
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			repaint();			
		}	
	}
	//plays sound from a .wav file
	public void playSound(String path) {
		try { //exception handling
			//creates audioinputstream object
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
			Clip clip = AudioSystem.getClip(); 
	          
	        // open audioInputStream to the clip and starts it
	        clip.open(audioInputStream); 
	        clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		} 
	}
	//causes the shape to fall faster
	private void speedUp() {
		timer.setDelay(100);
	}
	//causes the shape to fall slower
	private void slowDown() {
		timer.setDelay(800);	
	}
	//moves the shape to the right
	private void moveRight() {
		for(int c=0;c<4;c++) {			
			int num=shapes.get(numSquares-(c+1)).getX();
			shapes.get(numSquares-(c+1)).setX(num+=15);
		}	
	}
	//moves the shape to the left
	private void moveLeft() {
		for(int c=0;c<4;c++) {		
			int num=shapes.get(numSquares-(c+1)).getX();
			shapes.get(numSquares-(c+1)).setX(num-=15);
		}					
	}
	//creates the shapes based on the randomly generated number (applies to the 7 methods below)
	private void createIShape() {
		for(int i=0;i<4;i++) {
			shapes.add(new IShape(startingPosition[0][i][0],startingPosition[0][i][1]));
		}			
	}
	private void createOShape() {
		for(int i=0;i<4;i++) {
			shapes.add(new OShape(startingPosition[1][i][0],startingPosition[1][i][1]));
		}		
	}
	private void createLShape() {
		shapes.add(new LShape(110,30)); 
		shapes.add(new LShape(110,45));
		shapes.add(new LShape(110,60));
		shapes.add(new LShape(125,60));
	}
	private void createJShape() {
		shapes.add(new JShape(125,30)); 
		shapes.add(new JShape(125,45));
		shapes.add(new JShape(125,60));
		shapes.add(new JShape(110,60));
	}
	private void createSShape() {
		shapes.add(new SShape(140,30)); 
		shapes.add(new SShape(125,30));
		shapes.add(new SShape(125,45));
		shapes.add(new SShape(110,45));
	}
	private void createZShape() {
		shapes.add(new ZShape(110,30)); 
		shapes.add(new ZShape(125,30));
		shapes.add(new ZShape(125,45));
		shapes.add(new ZShape(140,45));
	}
	private void createTShape() {
		shapes.add(new TShape(110,30)); 
		shapes.add(new TShape(125,30));
		shapes.add(new TShape(140,30));
		shapes.add(new TShape(125,45));
	}
	//draws the shapes in to signify the next shape (applies to the 7 methods below)
	private void drawIShape(Graphics2D g2d) {
		g2d.setColor(Color.BLUE);
		g2d.fillRect(250,100,60,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(250,100,60,15);
		g2d.drawLine(265, 100, 265, 115);
		g2d.drawLine(280, 100, 280, 115);
		g2d.drawLine(295,100,295,115);
	}
	private void drawOShape(Graphics2D g2d) {
		g2d.setColor(Color.ORANGE);
		g2d.fillRect(265,90,30,30);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(265,90,30,30);
		g2d.drawLine(280, 90, 280, 120);
		g2d.drawLine(265, 105, 295, 105);			
	}
	private void drawLShape(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.fillRect(265,90,15,45);
		g2d.fillRect(265,120,30,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(265,90,15,45);
		g2d.drawRect(265,120,30,15);						
		g2d.drawLine(265, 105, 280, 105);				
	}
	private void drawJShape(Graphics2D g2d) {
		g2d.setColor(Color.MAGENTA);
		g2d.fillRect(280,90,15,45);
		g2d.fillRect(265,120,30,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(280,90,15,45);
		g2d.drawRect(265,120,30,15);						
		g2d.drawLine(280, 105, 295, 105);		
	}
	private void drawSShape(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillRect(270,90,30,15);
		g2d.fillRect(255,105,30,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(270,90,30,15);
		g2d.drawRect(255,105,30,15);						
		g2d.drawLine(285, 90, 285, 105);
		g2d.drawLine(270, 105, 270, 120);			
	}
	private void drawZShape(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(270,105,30,15);
		g2d.fillRect(255,90,30,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(270,105,30,15);
		g2d.drawRect(255,90,30,15);						
		g2d.drawLine(285, 105, 285, 120);
		g2d.drawLine(270, 90, 270, 105);
	}
	private void drawTShape(Graphics2D g2d) {
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(270,90,45,15);
		g2d.fillRect(285,105,15,15);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(270,90,45,15);
		g2d.drawRect(285,105,15,15);						
		g2d.drawLine(285, 90, 285, 105);
		g2d.drawLine(300, 90, 300, 105);
	}
	//tests and then rotates the shapes when the space bar is pressed (applies to the 6 methods below)
	private void rotateIShape() {
		//shape is in position 1
		if(((IShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//checks if the rotation is allowed
			if(((IShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((IShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);	
				}
			}	
			else {
				playSound("sound/norotate.wav");
			}				
		}
		//shape is in position 2
		else if(((IShape) shapes.get(numSquares-1)).getPosition()==2) { 			
			//checks if the rotation is allowed
			if(((IShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {	
				//rotate the shape
				for(int c=0;c<4;c++) {
					((IShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}							
		}			
	}
	private void rotateLShape() {
		//shape is in position 1
		if(((LShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//check if rotation is allowed
			if(((LShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((LShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}				
		}
		//shape is in position 2
		else if(((LShape) shapes.get(numSquares-1)).getPosition()==2) { 
			//check if rotation is allowed
			if(((LShape) shapes.get(numSquares-1)).rotateTo3Check(shapes, numSquares, grid)==true) {			
				//rotate the shape
				for(int c=0;c<4;c++) {
					((LShape) shapes.get(numSquares-(c+1))).rotateTo3(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is in position 3
		else if(((LShape) shapes.get(numSquares-1)).getPosition()==3) { 
			//check if rotation is allowed
			if(((LShape) shapes.get(numSquares-1)).rotateTo4Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((LShape) shapes.get(numSquares-(c+1))).rotateTo4(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is in position 4
		else if(((LShape) shapes.get(numSquares-1)).getPosition()==4) { 
			//check if rotation is allowed
			if(((LShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((LShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}		
			}
		}					
	}
	private void rotateJShape() {
		//shape is in position 1
		if(((JShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//check if rotation is allowed
			if(((JShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {			
				//rotate the shape
				for(int c=0;c<4;c++) {
					((JShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}				
		}
		//shape is in position 2
		else if(((JShape) shapes.get(numSquares-1)).getPosition()==2) { 
			//check if rotation is allowed
			if(((JShape) shapes.get(numSquares-1)).rotateTo3Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((JShape) shapes.get(numSquares-(c+1))).rotateTo3(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is in position 3
		else if(((JShape) shapes.get(numSquares-1)).getPosition()==3) { 
			//check if rotation is allowed
			if(((JShape) shapes.get(numSquares-1)).rotateTo4Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((JShape) shapes.get(numSquares-(c+1))).rotateTo4(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is position 4
		else if(((JShape) shapes.get(numSquares-1)).getPosition()==4) { 			
			//check if rotation is allowed
			if(((JShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((JShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}							
		}									
	}
	private void rotateSShape() {
		//shape is in position 1
		if(((SShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//check if rotation is allowed
			if(((SShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((SShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}				
		}
		//shape is in position 2
		else if(((SShape) shapes.get(numSquares-1)).getPosition()==2) { 							
			//check if rotation is allowed
			if(((SShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {	
				//rotate the shape
				for(int c=0;c<4;c++) {
					((SShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}							
		}			
	}
	private void rotateZShape() {
		//shape is in position 1
		if(((ZShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//check if rotation is allowed
			if(((ZShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {			
				//rotate the shape
				for(int c=0;c<4;c++) {
					((ZShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}				
		}
		//shape is in position 2
		else if(((ZShape) shapes.get(numSquares-1)).getPosition()==2) { 		
			//check if rotation is allowed
			if(((ZShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {			
				//rotate the shape
				for(int c=0;c<4;c++) {
					((ZShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}							
		}			
	}
	private void rotateTShape() {
		//shape is in position 1
		if(((TShape) shapes.get(numSquares-1)).getPosition()==1) { 	
			//check if rotation is allowed
			if(((TShape) shapes.get(numSquares-1)).rotateTo2Check(shapes, numSquares, grid)==true) {	
				//rotate the shape
				for(int c=0;c<4;c++) {
					((TShape) shapes.get(numSquares-(c+1))).rotateTo2(shapes,c,numSquares);		
				}	
			}
			else {
				playSound("sound/norotate.wav");
			}
		}
		//shape is in position 2
		else if(((TShape) shapes.get(numSquares-1)).getPosition()==2) { 
			//check if rotation is allowed
			if(((TShape) shapes.get(numSquares-1)).rotateTo3Check(shapes, numSquares, grid)==true) {		
				//rotate the shape
				for(int c=0;c<4;c++) {
					((TShape) shapes.get(numSquares-(c+1))).rotateTo3(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is in position 3
		else if(((TShape) shapes.get(numSquares-1)).getPosition()==3) { 
			//check if rotation is allowed
			if(((TShape) shapes.get(numSquares-1)).rotateTo4Check(shapes, numSquares, grid)==true) {				
				//rotate the shape
				for(int c=0;c<4;c++) {
					((TShape) shapes.get(numSquares-(c+1))).rotateTo4(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}			
		}
		//shape is in position 4
		else if(((TShape) shapes.get(numSquares-1)).getPosition()==4) { 		
			//check if rotation is allowed
			if(((TShape) shapes.get(numSquares-1)).rotateTo1Check(shapes, numSquares, grid)==true) {				
				//rotate the shape
				for(int c=0;c<4;c++) {
					((TShape) shapes.get(numSquares-(c+1))).rotateTo1(shapes,c,numSquares);		
				}
			}					
			else {
				playSound("sound/norotate.wav");
			}							
		}									
	}
}

