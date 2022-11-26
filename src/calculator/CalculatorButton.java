/* Class name: CalculatorButton
 * Authors: Bradly Budach, Pronob Kumar
 * Date: 11/07/2022
 * Problem: 
 * 		- Helper class to contain Calculator Buttons
 * Goals:
 *      - Have methods to setup the button
 *      - Have functionalities for inverting the button, adding it to the GUi, and setting custom display settings
 * Required packages:
 * 		- javax.swing.BorderFactory; javax.swing.JButton; java.awt.Color; java.awt.Dimension; java.awt.Font; 
 * 		- java.awt.GridBagConstraints; java.awt.event.MouseEvent; java.awt.event.MouseListener;
 * Pseudo code:
 *     Step 1: Set default values for the button
 *     Step 2: Change any values from default if needed
 *     Step 3: add the button to the GUI with its values
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
	
	/* Constructor method, sets up initial values for the calculator
	 * 
	 * Inputs:
	 * 		- int x for x position
	 * 		- int y for y position
	 * 		- String btnTxt for the text that goes on the button
     * 
     * Pseudocode:
     *     1: Set all the values for the button
	 */
	public CalculatorButton(int x, int y, Color c, String btnTxt) {
		super(btnTxt); // creates the JButton
		insertText = btnTxt; // text that will appear in the IOArea when the button is clicked
		color = c; // sets color
		xPosition = x; // sets X position in grid
		yPosition = y; // sets Y position in grid
		hoverColor = new Color(Math.min(color.getRed()+30, 255), Math.min(color.getGreen()+30, 255), Math.min(color.getBlue()+30, 255)); // Procedurally finds a brighter color that will show when the button is hovered over.
	}
	
	/* sets up a buttons inverse
	 * 
	 * Inputs:
	 * 		- String newButtonText for the text that goes on the button
	 * 		- String newInsertText for the text that will display on click
     * 
     * Pseudocode:
     *     1: sets the button to be invertable
     *     2: sets the inverseText
     *     3: sets the inverseInsertText
	 */
	public void setInvertValues(String newButtonText, String newInsertText) {
		isInvertable = true; // sets the button to switch when the invert button is clicked
		inverseButtonText = newButtonText; // sets the text that the button will display upon inversion
		inverseInsertText = newInsertText; // sets the text for the button to print to the console on inversion
	}
	
	/* toggles the inversion of the button
     * 
     * Pseudocode:
     *     1: displays the inverseButtonText and inverseInsertText and then switches those variables to whatever the button had before
	 */
	public void invert() {
		// Toggles the values that the button displays/holds for insertion:
		String btnTxt = super.getText(); // stores value before it changes
		super.setText(inverseButtonText); // changes to new value
		inverseButtonText = btnTxt; // sets invert value to past value
		String invTxt = inverseInsertText; // stores value before it changes
		inverseInsertText = insertText; // sets inverse insert text value to past value
		insertText = invTxt; // sets insert text to new value
	}
	
	/* Sets a custom width for the button
	 * 
	 * Inputs:
	 * 		- int w for width
     * 
     * Pseudocode:
     *     1: sets the width
	 */
	public void setCustomWidth(int w) {
		width = w;
	}
	
	/* Sets a custom height for the button
	 * 
	 * Inputs:
	 * 		- int h for height
	 * 
     * Pseudocode:
     *     1: sets height
	 */
	public void setCustomHeight(int h) {
		height = h;
	}
	
	/* Sets a custum column height for the column a button is in
	 * 
	 * Inputs:
	 * 		- double height for column height
     * 
     * Pseudocode:
     *     1: sets columnHeight
	 */
	public void setCustomColumnHeight(double height) {
		columnHeight = height;
	}

	/* Sets a custom font 
	 * 
	 * Inputs:
	 * 		- Font f
     * 
     * Pseudocode:
     *     1: sets the font to the given font
	 */
	public void setCustomFont(Font f) {
		font = f;
	}
	
	/* Sets the insert text for a button if it has custom insert text
	 * 
	 * Inputs:
	 * 		- String text for the insert text
     * 
     * Pseudocode:
     *     1: Sets the insert text
	 */
	public void setInsertText(String text) {
		insertText = text;
	}
	
	/* Gets the insertText
	 *
	 * Outputs:
	 * 		- Returns the insert text
     * 
     * Pseudocode:
     *     1: returns the insertText
	 */
	public String getInsertText() {
		return insertText;
	}

	/* Adds the button to the GUI
	 * 
	 * Inputs:
	 * 		- CalculatorApp app : where the button is added to 
     * 
     * Pseudocode:
     *     1: sets the syles for the button
     *     2: sets the grid position for the button
     *     3: adds mouse/action listeners to the button.
     *     4: adds the button to the GUI with the proper grid constraints
	 */
	public void displaySelf(CalculatorApp app) {
		// Sets the styling for all the buttons.
		setBackground(color); // Setting the background color
		setForeground(Color.WHITE); // Setting the foreground color 
		setFont(font); // Setting the font 
		setFocusable(false); // The buttons can not be focused
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // gray border
		super.setPreferredSize(new Dimension(50, 50)); // ensures button try to remain the same proportions.
		
		// adds buttons to their correct position on the grid
		GridBagConstraints c = new GridBagConstraints(); // constraints to describe how the item is displayed
		c.fill = GridBagConstraints.BOTH; // Fills grid in both directions
		c.gridwidth = width; // spaces taken on the x axis by the button
		c.gridheight = height; // spaces taken on the y axis by the button
		c.weightx = 1; // default weight, fills X axis normally
		c.weighty = columnHeight; // custom y, with default will fill normally; with custom y weight, whole column will take up less space
		c.gridx = xPosition; // x position on the grid
		c.gridy = yPosition; // y position on the grid
		
		addActionListener(app); // adds action listener handled by CalculatorApp
		addMouseListener(this); // adds a mouse listener in this class to detect hover
		
		app.add(this, c); // adds this button to the grid in the UI
	}
	
	@Override
	/* This method is for changing color of the button when mouse entered 
	 * 
	 * Inputs:
	 * 		- MouseEvent e
     * 
     * Pseudocode:
     *     1: changes the background color to the new hover color.
	 */
    public void mouseEntered(MouseEvent e) {
        setBackground(hoverColor); // sets color when mouse is over button
    }

	@Override
	/* This method is for changing color of the button when mouse left
	 * 
	 * Inputs:
	 * 		- MouseEvent e
     * 
     * Pseudocode:
     *     1: returns the background color back to its default
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
