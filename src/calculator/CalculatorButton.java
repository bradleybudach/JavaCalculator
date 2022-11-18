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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalculatorButton extends JButton implements MouseListener {
	private Color color; // Variable for the color of the button 
	private Color hoverColor; // Variable for the color of the button when you hover over it
	private int xPosition; // Variable for the x coordinate of the button
	private int yPosition; // Variable for the y coordinate of the button
	private int width = 1; // Variable for the column width of the button which is default to one
	private int height = 1; // Variable for the column width of the button which is default to one
	private double columnHeight = 1; // Variable for the column height of the button which is default to one
	private Font font = new Font("DialogInput", Font.PLAIN, 40); // Variable for font and the type is also selected
	protected boolean isFunction = false; // if the button is used for a function 
	private String insertText; // Text if the button inserts text to the IOArea that is not the name of the button
	protected boolean isInvertable = false; // If the button is invertable
	private String inverseInsertText; // insert text when the button is inverted
	private String inverseButtonText; // button name when it is inverted
	
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
		super(btnTxt); // creates the JButton
		insertText = btnTxt; // text that will appear in the IOArea when the button is clicked
		color = c; // sets color
		xPosition = x; // sets X position in grid
		yPosition = y; // sets Y position in grid
		hoverColor = new Color(Math.min(color.getRed()+30, 255), Math.min(color.getGreen()+30, 255), Math.min(color.getBlue()+30, 255)); // Procedurally finds a brighter color that will show when the button is hovered over.
	}
	
	public void setInvertValues(String newButtonText, String newInsertText) {
		isInvertable = true; // sets the button to switch when the invert button is clicked
		inverseButtonText = newButtonText; // sets the text that the button will display upon inversion
		inverseInsertText = newInsertText; // sets the text for the button to print to the console on inversion
	}
	
	public void invert() {
		// Toggles the values that the button displays/holds for insertion:
		String btnTxt = super.getText(); // stores value before it changes
		super.setText(inverseButtonText); // changes to new value
		inverseButtonText = btnTxt; // sets invert value to past value
		String invTxt = inverseInsertText; // stores value before it changes
		inverseInsertText = insertText; // sets inverse insert text value to past value
		insertText = invTxt; // sets insert text to new value
	}
	
	/* This method is for declaring the variables for the column width variable 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void setCustomWidth(int w) {
		width = w;
	}
	
	/* This method is for declaring the variables for the column width variable 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void setCustomHeight(int h) {
		height = h;
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
	
	public void setInsertText(String text) {
		insertText = text;
	}
	
	public String getInsertText() {
		return insertText;
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
		setBackground(color); // Setting the background color
		setForeground(Color.WHITE); // Setting the foreground color 
		setFont(font); // Setting the font 
		setFocusable(false); // Don't let the screen to focus
		
		// adds buttons to their correct position on the grid
		GridBagConstraints c = new GridBagConstraints(); // constraints to describe how the item is displayed
		c.fill = GridBagConstraints.BOTH; // Fills grid in both directions
		c.gridwidth = width; // spaces taken on the x axis by the button
		c.gridheight = height; // spaces taken on the y axis by the button
		c.weightx = 1; // default weight, fills X axis normally
		c.weighty = columnHeight; // custom y, with default will fill normally; with custom y weight, whole column will take up less space
		c.gridx = xPosition; // x position on the grid
		c.gridy = yPosition; // y position on the grid
		super.setPreferredSize(new Dimension(50, 50)); // ensures button try to remain the same proportions.
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // gray border
		addActionListener(app); // adds action listener handled by CalculatorApp
		addMouseListener(this); // adds a mouse listener in this class to detect hover
		app.add(this, c); // adds this button to the grid in the UI
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
    public void mouseEntered(MouseEvent e) {
        setBackground(hoverColor); // sets color when mouse is over button
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
	public void mouseExited(MouseEvent e) {
	     setBackground(color); // returns color to normal when mouse leaves button
	}
	
	@Override
	/* Unused Implementations from MouseListener */
	public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}
