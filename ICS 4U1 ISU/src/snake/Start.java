package snake;

// imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;

import MainMenu.MainMenu;

public class Start extends JFrame  implements ActionListener {

	// make a new JPanel
	private JPanel contentPane;

	// main method
	

	// default constructor
	public Start() {
		setResizable(false);
		// frame closes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// size of the frame
		setBounds(100, 100, 800, 500);
		// make a new JPanel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// GridBagLayout
		GridBagLayout gbl_contentPane = new GridBagLayout();
		// setting the column and row width and row heights
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// title of the game
		JLabel lblSnake = new JLabel("Snake");
		GridBagConstraints gbc_lblSnake = new GridBagConstraints();
		gbc_lblSnake.insets = new Insets(0, 0, 5, 0);
		gbc_lblSnake.gridx = 7;
		gbc_lblSnake.gridy = 0;
		// add to the frame
		contentPane.add(lblSnake, gbc_lblSnake);
		
		// make a new button to start the game
		JButton btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 0);
		gbc_btnStart.gridx = 7;
		gbc_btnStart.gridy = 3;
		// add to the frame 
		contentPane.add(btnStart, gbc_btnStart);
		
		// action listener for back btn
        btnStart.addActionListener(new ActionListener() {
            // once this is clicked on, it should call the GUI
            @Override
            public void actionPerformed(ActionEvent e) {
               // make a new Frame of the type Snake
            	new Snake();
            }
        });
		// make a Back button to go back to the main menu
		JButton btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 7;
		gbc_btnBack.gridy = 5;
		// add to the frame
		contentPane.add(btnBack, gbc_btnBack);
		
		 // action listener for back btn
        btnBack.addActionListener(new ActionListener() {
            // once this is clicked on, it should call the GUI
            @Override
            public void actionPerformed(ActionEvent e) {
               // * CHANGE NAME TO THE NAME OF THE MAIN MENU OF RR
            	dispose();
        		MainMenu.main(null);
            }
        });
		
        // instructions for snake
		JTextArea textArea = new JTextArea("\t\t\t        SNAKE INSTRUCTIONS:\t\t\t\n\n\n1) Use the right, left, up, and down arrow keys to move the snake right, left, up, and down respectively.\n\n2) Each apple collected by the snake is a point\n\n3) If the snake collides with the wall or itself the game is over and you lose!\n\nWe hope you enjoy playing this old-school snake throwback!");
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		// set the position
		gbc_textArea.gridx = 7;
		gbc_textArea.gridy = 7;
		// add to the frame
		contentPane.add(textArea, gbc_textArea);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	

}