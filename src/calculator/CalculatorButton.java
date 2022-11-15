/*
 * Class name: CS 234(Group Project)
 * Author:Bradly Budach, Pronob Kumar
 * Date: 11/07/2022
 * Problem: 
 * 		- Make a calculator
 * 		- Have the calculator popup in the GUI
 * 		- Calculator should have buttons
 * 		- Calculator should take inputs
 * 		- Use the calculator to calculate values
 * Goals:
 *      - Have the calculator popup in the GUI
 * 		- Calculator should have buttons
 * 		- Calculator should take inputs
 * 		- Use the calculator to calculate values
 * Inputs:
 *      - math problem 
 * Outputs:
 *      - answer of the problem 
 * Required packages:
 * 		- import javax.swing.BorderFactory;
 * 		- import javax.swing.JButton;
 * 		- import java.awt.Color;
 * 		- import java.awt.Font;
 * 		- import java.awt.GridBagConstraints;
 * 		- import java.awt.event.MouseEvent;
 * 		- import java.awt.event.MouseListener;
 * Test cases:
 *      - If we put 5+5	the result will show 10
 *      - If we put 5-5 the result will show 0
 *      - If we put 5*5	the result will show 25
 *      - If we put 5/5	the result will show 1
 *      - If we put 5%5	the result will show 0
 *      - If we put 5^5	the result will show 3125
 *      - If we put (5+5)*2	the result will show 20
 * Pseudo code:
 *     Step 1: It's on you
 *     Step 2: 
 *     Step 3: 
 */


package calculator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalculatorButton extends JButton implements MouseListener {
	private Color color;		// Variable for the color of the button 
	private Color hoverColor;	// Variable for the color of the button when you hover over it
	private int xPosition;		// Variable for the x coordinate of the button
	private int yPosition;		// Variable for the y coordinate of the button
	private int columnWidth = 1;		// Variable for the column width of the button which is default to one
	private double columnHeight = 1;		// Variable for the column height of the button which is default to one
	private Font font = new Font("DialogInput", Font.PLAIN, 40);		// Variable for font and the type is also selected
	
	/* This method is for declaring the variables
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public CalculatorButton(int x, int y, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
		hoverColor = new Color(Math.min(color.getRed()+30, 255), Math.min(color.getGreen()+30, 255), Math.min(color.getBlue()+30, 255));
	}
	
	/* This method is for declaring the variables
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public CalculatorButton(int x, int y, int width, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
		columnWidth = width;
		hoverColor = new Color(Math.min(color.getRed()+30, 255), Math.min(color.getGreen()+30, 255), Math.min(color.getBlue()+30, 255));
	}
	
	/* This method is for declaring the variables for the column height variable 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void setCustomColumnHeight(double height) {
		columnHeight = height;
	}

	/* This method is for setting custom font 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void setCustomFont(Font f) {
		font = f;
	}
	

	/* This method is for displaying the app
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void displaySelf(CalculatorApp app) {
		// Sets the styling for all the buttons.
		setBackground(color);			// Setting the background color
		setForeground(Color.WHITE);		// Setting the foreground color 
		setFont(font);					// Setting the font 
		setFocusable(false);			// Don't let the screen to focus
		
		// adds buttons to their correct position on the grid
		GridBagConstraints c = new GridBagConstraints(); // constraints to describe how the item is displayed
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = columnWidth;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = columnHeight;
		c.gridx = xPosition;
		c.gridy = yPosition;
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));		// Set border with a gray color and border length = 1
		addActionListener(app);
		addMouseListener(this);						// Activate the mouse in the app
		app.add(this, c);
	}
	
	@Override
	/* This method is for changing color of the button when mouse entered 
	 * Inputs:
	 * 		- mouse cursor 
     * Outputs: 
     * 		- change the color of the button 
     * Pseudocode:
     *     - ????
     *     - 
	 */
    public void mouseEntered(MouseEvent e)
    {
        
        setBackground(hoverColor);
    }

	@Override
	/* This method is for changing color of the button when mouse left
	 * Inputs:
	 * 		- mouse cursor 
     * Outputs: 
     * 		- change the color of the button 
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void mouseExited(MouseEvent e){
	     setBackground(color);
	}
	
	@Override
	/* This method is for 		??????	
	 * Inputs:
	 * 		- none
     * Outputs: 
     * 		- none
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void mouseClicked(MouseEvent e){
		
	}

    @Override
    /* This method is for 		??????	
	 * Inputs:
	 * 		- none
     * Outputs: 
     * 		- none
     * Pseudocode:
     *     - ????
     *     - 
	 */
    public void mousePressed(MouseEvent e){
    	
    }

    @Override
    /* This method is for 		??????	
	 * Inputs:
	 * 		- none
     * Outputs: 
     * 		- none
     * Pseudocode:
     *     - ????
     *     - 
	 */
    public void mouseReleased(MouseEvent e){
    	
    }
}
