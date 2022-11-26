/* Project: CS 234 (Group Project)
 * Class name: CalculatorApp
 * Authors: Bradly Budach, Pronob Kumar
 * Date: 11/07/2022
 * Problem: 
 * 		- Create GUI for a Scientific Calculator
 * Goals:
 *      - Have the calculator popup in the GUI
 * 		- Calculator takes input from keyboard or GUI buttons
 * 		- Use the calculator to calculate a mathmatical expression
 * Inputs:
 *      - A mathmatical expression
 * Outputs:
 *      - The result of a given expression
 * Required packages:
 * 		- java.awt.Color; java.awt.Dimension; java.awt.Font; java.awt.GridBagConstraints; java.awt.GridBagLayout;
		- java.awt.Image; java.awt.Toolkit; java.awt.event.ActionEvent; java.awt.event.ActionListener; java.awt.event.KeyEvent;
		- java.awt.event.KeyListener; java.awt.event.WindowAdapter; java.awt.event.WindowEvent; java.util.ArrayList; java.util.Arrays;
		- javax.swing.BorderFactory; javax.swing.JFrame; javax.swing.JTextField; javax.swing.SwingConstants;
 * Test cases:
 *      - If we put 5+5	the result will show 10
 *      - If we put 5-5 the result will show 0
 *      - If we put 5*5	the result will show 25
 *      - If we put 5/5	the result will show 1
 *      - If we put 5%5	the result will show 0
 *      - If we put 5^5	the result will show 3125
 *      - If we put (5+5)*2	the result will show 20
 *      - If we put sin(5) the result will be -0.958924275
 *      - If we put sin(asin(5)) the result will be 0
 *      - The calculator also has functions for abs, cos, tan, log, ln, asin, acos, atan, sqrt, root, sinh, cosh, tanh
 * Pseudo code:
 *     Step 1: Set up all components in GUI
 *     Step 2: Add all components to GUI and make it visible
 *     Step 3: Add action listeners to all buttons.
 */

package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorApp extends JFrame implements ActionListener, KeyListener {
	private CalculatorButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSubtract, btnDivide,
			btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnDot, btnBack, btnClear, btnHistory, 
			btnAbs, btnSin, btnCos, btnTan, btnInvert, btnPI, btnSqrt, btnE, btnLog, btnLN; // UI buttons
	ArrayList<CalculatorButton> buttons; // list to hold the buttons so they can be iterated through
	private JTextField IOArea; // Input/Output area
	private String previousAns; // Stores answer of the last expression

	/* Constructor Method
	 * 
     * Outputs: 
     * 		- Creates the GUI
     * 
     * Pseudocode:
     *     1: Sets defaults for the GUI like title and size
     *     2: Initializes all variables.
     *     3: Adds all buttons to the list
     *     4: Adds all buttons to the GUI using their displaySelf method
     *     5: Adds remaining components
     *     6: Adds Action/Key listeners to remaining components that require it
	 */
	public CalculatorApp() {
		setTitle("Calculator"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE); // closes program when X button clicked
		setLayout(new GridBagLayout()); // grid layout with custom rows
		setSize(550, 900); // sets the default size for the frame	
		getContentPane().setBackground(Color.DARK_GRAY); // Frame background color
		Image icon = new ImageIcon(getClass().getResource("../logo.png")).getImage(); // gets the icon image
		setIconImage(icon); // sets the icon
		
		previousAns = ""; // initializes previouAns
		Font smallerFont = new Font("DialogInput", Font.PLAIN, 30); // Font for grid items that need to have smaller text.
		
		// Initializes components:
		btn1 = new CalculatorButton(1, 7, Color.BLACK, "1"); // Button for pressing 1 on the calculator
		btn2 = new CalculatorButton(2, 7, Color.BLACK, "2"); // Button for pressing 2 on the calculator
		btn3 = new CalculatorButton(3, 7, Color.BLACK, "3"); // Button for pressing 3 on the calculator
		btn4 = new CalculatorButton(1, 6, Color.BLACK, "4"); // Button for pressing 4 on the calculator
		btn5 = new CalculatorButton(2, 6, Color.BLACK, "5"); // Button for pressing 5 on the calculator
		btn6 = new CalculatorButton(3, 6, Color.BLACK, "6"); // Button for pressing 6 on the calculator
		btn7 = new CalculatorButton(1, 5, Color.BLACK, "7"); // Button for pressing 7 on the calculator
		btn8 = new CalculatorButton(2, 5, Color.BLACK, "8"); // Button for pressing 8 on the calculator
		btn9 = new CalculatorButton(3, 5, Color.BLACK, "9"); // Button for pressing 9 on the calculator
		btn0 = new CalculatorButton(1, 8, Color.BLACK, "0"); // Button for pressing 0 on the calculator
		btnDot = new CalculatorButton(2, 8, Color.BLACK, "."); // Button for pressing . on the calculator
		btnAdd = new CalculatorButton(4, 7, Color.DARK_GRAY, "+"); // Button for pressing + on the calculator
		btnSubtract = new CalculatorButton(4, 6, Color.DARK_GRAY, "-"); // Button for pressing - on the calculator
		btnE = new CalculatorButton(1, 3, Color.DARK_GRAY, "e"); // Button for the constant e
		
		// Button for pressing / on the calculator
		btnDivide = new CalculatorButton(4, 4, Color.DARK_GRAY, "\u00F7"); 
		btnDivide.setInsertText("/");
		
		// Button for pressing * on the calculator
		btnMultiply = new CalculatorButton(4, 5, Color.DARK_GRAY, "\u00D7"); 
		btnMultiply.setInsertText("*");
		
		// Button for pressing % on the calculator
		btnMod = new CalculatorButton(3, 4, Color.DARK_GRAY, "%");
		
		// Button for pressing ^ on the calculator
		btnPow = new CalculatorButton(4, 3, Color.DARK_GRAY, "x\u00B2"); 
		btnPow.setInsertText("^2"); 
		btnPow.setCustomFont(new Font("", Font.PLAIN, 40)); 
		btnPow.setInvertValues("x\u207F", "^");  
		
		btnParenthesisOpen = new CalculatorButton(1, 4, Color.DARK_GRAY, "("); // Button for pressing ( on the calculator
		btnParenthesisClose = new CalculatorButton(2, 4, Color.DARK_GRAY, ")");	// Button for pressing ) on the calculator
		
		// Button for pressing = on the calculator
		btnEquals = new CalculatorButton(3, 8, new Color(0, 120, 0), "="); 
		btnEquals.setCustomWidth(2); 
		
		// Delete button on the calculator
		btnBack = new CalculatorButton(3, 2, new Color(100, 20, 20), "\u232B"); 
		btnBack.setCustomColumnHeight(0.65); 
		btnBack.setCustomWidth(2);
		
		// Button for pressing "C" on the calculator
		btnClear = new CalculatorButton(1, 2, new Color(100, 20, 20), "C"); 
		btnClear.setCustomColumnHeight(0.65); 
		btnClear.setCustomWidth(2);	
		
		// Space that shows the last answer
		btnHistory = new CalculatorButton(0, 0, Color.DARK_GRAY, ""); 
		btnHistory.setCustomFont(new Font("DialogInput", Font.PLAIN, 20)); 
		btnHistory.setCustomColumnHeight(0.5); 
		btnHistory.setCustomWidth(5);
		
		// Button for Absolute Value function
		btnAbs = new CalculatorButton(0, 3, Color.DARK_GRAY, "|x|");  
		btnAbs.setInsertText("abs()"); 
		btnAbs.setCustomFont(smallerFont); 
		btnAbs.isFunction = true;
		
		// Button for inverting any button that has a secondary function
		btnInvert = new CalculatorButton(0, 2, new Color(0, 0, 120), "inv"); 
		btnInvert.setCustomFont(smallerFont); 
		btnInvert.setCustomColumnHeight(0.65);
		
		// Button for Sine function
		btnSin = new CalculatorButton(0, 4, Color.DARK_GRAY, "sin"); 
		btnSin.setCustomFont(smallerFont); 
		btnSin.setInsertText("sin()"); 
		btnSin.setInvertValues("asin", "asin()"); 
		btnSin.isFunction = true;
		
		// Button for Cosine function
		btnCos = new CalculatorButton(0, 5, Color.DARK_GRAY, "cos"); 
		btnCos.setCustomFont(smallerFont); 
		btnCos.setInsertText("cos()"); 
		btnCos.setInvertValues("acos", "acos()"); 
		btnCos.isFunction = true;
		
		// Button for Tangent function
		btnTan = new CalculatorButton(0, 6, Color.DARK_GRAY, "tan"); 
		btnTan.setCustomFont(smallerFont); 
		btnTan.setInsertText("tan()"); 
		btnTan.setInvertValues("atan", "atan()"); 
		btnTan.isFunction = true;
		
		// Button for Logorithm function
		btnLog = new CalculatorButton(0, 7, Color.DARK_GRAY, "log\u2081\u2080"); 
		btnLog.setCustomFont(smallerFont); 
		btnLog.setInsertText("log()"); 
		btnLog.isFunction = true;
		
		// Button for Natural Log function
		btnLN = new CalculatorButton(0, 8, Color.DARK_GRAY, "ln"); 
		btnLN.setCustomFont(smallerFont); 
		btnLN.setInsertText("ln()"); 
		btnLN.isFunction = true;
		
		// Button for the constant pi
		btnPI = new CalculatorButton(2, 3, Color.DARK_GRAY, "\u03C0"); 
		btnPI.setInsertText("pi");	
		
		// Button for the Square Root function
		btnSqrt = new CalculatorButton(3, 3, Color.DARK_GRAY, "\u221A"); 
		btnSqrt.setInsertText("sqrt()"); 
		btnSqrt.setInvertValues("\u221B", "root3()"); 
		btnSqrt.isFunction = true;
		
		IOArea = new JTextField(); // Input output area

		// adds all buttons to the list of buttons
		buttons = new ArrayList<CalculatorButton>(
				Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnAdd, btnSubtract,
						btnDivide, btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnBack, 
						btnClear, btnHistory, btnAbs, btnSin, btnCos, btnTan, btnInvert, btnPI, btnSqrt, btnE, btnLog, btnLN));
		
		// Adds all buttons to the GUI:
		for (CalculatorButton btn : buttons) {
			btn.displaySelf(this); // Sets up all buttons in the GUI
		}

		// History button style:
		btnHistory.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); 
		btnHistory.setPreferredSize(new Dimension(0, 25));
		btnHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		// Input/Output area styles:
		IOArea.setBackground(Color.BLACK); // Setting Background color to black
		IOArea.setForeground(Color.WHITE); // setting Foreground color to white 
		IOArea.setFont(new Font("DialogInput", Font.PLAIN, 35)); // Setting the font 
		IOArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // empty border to give the area some padding
		IOArea.setEditable(false); // Make the area unable to be edited normally
		IOArea.setCaretColor(Color.WHITE); // Color of the caret in the area

		GridBagConstraints c = new GridBagConstraints(); // constraints to describe how the item is displayed
		c.fill = GridBagConstraints.BOTH; c.weightx = 1; c.weighty = 1; c.gridwidth = 5; c.gridx = 0; c.gridy = 1;
		IOArea.setPreferredSize(new Dimension(0, 70));
		add(IOArea, c);
		
		IOArea.addKeyListener(this); // key listener for the Input/Output Area.

		addWindowFocusListener(new WindowAdapter() { // Makes the caret reappear in the text-box when the window is selected (after selecting a different window)
			public void windowGainedFocus(WindowEvent e) {
				IOArea.getCaret().setVisible(true);
			}
		});
		
		setVisible(true); // sets the GUI to be visible
	}

	/* Main Method
	 * 
     * Pseudocode:
     *     - Calls the constructor to create the GUI
	 */
	public static void main(String[] args) {
		new CalculatorApp(); // Creates instance of the GUI
	}

	@Override
	/* keyTyped override for the KeyListener interface
	 * 
	 * Inputs:
	 * 		- KeyEvent e
	 * 
     * Pseudocode:
     *     1: Gets the character of whatever key was typed
     *     2: if it is a valid character insert it into the IOArea
     *     3: if the = key is typed submit the expression to be solved
	 */
	public void keyTyped(KeyEvent e) {
		Character key = e.getKeyChar(); // gets the character of a the key-press

		if (Character.isDigit(key) || Character.isLetter(key) || key == '+' || key == '-' || key == '/' || 
				key == '*' || key == '%' || key == '^' || key == '(' || key == ')' || key == '.') { // key typed that corresponds with the buttons
			int insertPos = IOArea.getCaretPosition(); // current caret psotion
			IOArea.setText(insertIntoText(IOArea.getText(), key.toString(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			IOArea.setCaretPosition(insertPos + 1); // Updates the caret position to be in the right spot.
		} else if (key == '=' && !IOArea.getText().equals("")) { // equals key typed
			btnEquals.doClick(); // clicks the equals button to get the answer
		}
	}

	@Override
	/* keyPressed override for the KeyListener interface
	 * 
	 * Inputs:
	 * 		- KeyEvent e
     * 
     * Pseudocode:
     *     1: Gets the keycode of the key that was pressed
     *     2: If the key is enter, submit the expression to be solved
     *     3: if the key is backspace delete one character from the IOArea at the position of the caret and update the caret position
     *     4: if it is delete clear the IOArea
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // keycode of the pressed key
		String existingText = IOArea.getText(); // current expression in the IOArea

		if (key == KeyEvent.VK_ENTER && existingText.length() > 0) { // enter key typed
			btnEquals.doClick(); // clicks the equals button to get the answer
		} else if (key == KeyEvent.VK_BACK_SPACE && existingText.length() > 0) { // backspace key clicked
			int removeAt = IOArea.getCaretPosition(); // current caret position

			IOArea.setText(removeFromText(existingText, removeAt)); // updates text

			// Moves caret back unless it is already at the beginning of the string.
			if (removeAt > 0) {
				IOArea.setCaretPosition(removeAt - 1);
			} else {
				IOArea.setCaretPosition(0);
			}
		} else if (key == KeyEvent.VK_DELETE) { // delete key clicked
			IOArea.setText(""); // clears text
		}
	}

	@Override
	/* actionPerformed override for the ActionListener interface
	 * 
	 * Inputs:
	 * 		- ActionEvent e
     * 
     * Pseudocode:
     *     1: gets the souce of the button that was clicked
     *     2: if it is the equals button, use the calculator class to evaluate the expression and update the IOArea with the answer
     *     3: catch any errors from the calculator and display them
     *     4: if the back button was clicked delete a character
     *     5: if the clear button was clicked clear the IOArea
     *     6: if the history button was clicked add the previous answer into the IOArea
     *     7: If the invert button was clicked invert buttons
     *     8: if any other button was clicked add its insertText to the IOArea
	 */
	public void actionPerformed(ActionEvent e) {
		String existingText = IOArea.getText(); // expression in the IOArea
		Object source = e.getSource(); // source object of the event
		
		if (source == btnEquals) { 	// equals button pressed
			if (existingText.length() > 0) { // if there is an expression to solve
				try {
					double answer = Calculator.evaluate(existingText); // solves expression
					String ansString = ""; // string to store the answer
					
					if (answer % 1 == 0) { // if the number is a whole number (decimal is 0)
						ansString = Integer.toString((int) answer); // converts answer to int to remove decimal place
					} else {
						ansString = Double.toString(answer); // turns the answer into a string
					}
					
					IOArea.setText(ansString); // updates the IOArea
					previousAns = ansString; // updates the previous answer
					btnHistory.setText(existingText + " = " + previousAns); // updates the history area
				} catch (IllegalArgumentException ex) {
					IOArea.setText(ex.getMessage()); // Invalid Inputs
				} catch (ArithmeticException ex) {
					IOArea.setText(ex.getMessage()); // Math Errors
				} catch (UnsupportedOperationException ex) {
					IOArea.setText(ex.getMessage()); // Unexpected Error from the calculator trying to do an unsupported operation
					ex.printStackTrace();
				}
			}
		} else if (source == btnBack) { // backspace button
			if (existingText.length() > 0) {
				int removeAt = IOArea.getCaretPosition(); // current caret position

				IOArea.setText(removeFromText(existingText, removeAt)); // removes character

				// Moves caret back unless it is already at the beginning of the string.
				if (removeAt > 0) {
					IOArea.setCaretPosition(removeAt - 1);
				} else {
					IOArea.setCaretPosition(0);
				}
			}
		} else if (source == btnClear) { // clear button
			IOArea.setText(""); // clears text
		} else if (source == btnHistory) { // history button
			int insertPos = IOArea.getCaretPosition(); // current caret position
			if (previousAns.length() > 0) {
				IOArea.setText(insertIntoText(IOArea.getText(), previousAns, insertPos)); // inserts the last answer into the text
			}
		} else if (source == btnInvert) { // invert button
			for (CalculatorButton button : buttons) { // checks all buttons and inverts ones that are invertable
				if (button.isInvertable) {
					button.invert();
				}
			}
			revalidate(); // updates the buttons in the GUI to their new text
		} else { // any other button
			CalculatorButton btn = (CalculatorButton) source; // gets the source of which button is pressed as a CalculatorButton
			int insertPos = IOArea.getCaretPosition(); // current caret position

			IOArea.setText(insertIntoText(IOArea.getText(), btn.getInsertText(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			
			if (btn.isFunction) { // if the button pressed was a function
				IOArea.setCaretPosition(insertPos + btn.getInsertText().length()-1); // Updates the caret position to be in the right spot. Inside the Parenthesis for functions.
			} else {
				IOArea.setCaretPosition(insertPos + btn.getInsertText().length()); // Updates the caret position to be in the right spot.
			}
		}
	}
	
	/* Adds a string/character at a given point in an existing string
	 * 
	 * Inputs:
	 * 		- String existingText : text to insert into
	 * 		- String insertString : string to insert into the text
	 * 		- int insertPos	: position to insert the string
     * Outputs: 
     * 		- updated string
     * 
     * Pseudocode:
     *     1: if the insert position is at the end, append the insert string to the end of the existing one
     *     2: if the insert position is in the string, add the insert string between substrings of the existing one
	 */
	public String insertIntoText(String existingText, String insertString, int insertPos) {
		String newText = new String(); // updated string
		
		if (insertPos == existingText.length()) { // if the insert position is at the end of the existing text.
			newText = existingText + insertString; // appends insertString to the end
		} else {
			newText = existingText.substring(0, insertPos) + insertString + existingText.substring(insertPos); // adds the new string inbetween the old one
		}

		return newText; // returns the new string
	}

	/* Removes a character from a given point in a string
	 * 
	 * Inputs:
	 * 		- String existingText : string to remove character from
	 * 		- int removeAt : position to remove the character
     * Outputs: 
     * 		- updated string
     * 
     * Pseudocode:
     *     1: if the caret is in the text somewhere, remove a character at that position
	 */
	public String removeFromText(String existingText, int removeAt) {
		if (removeAt > 0) { // if the caret is somewhere in the text
			existingText = existingText.substring(0, removeAt - 1) + existingText.substring(removeAt, existingText.length()); // removes a character at the given position
		}

		return existingText;
	}
	
	
	@Override
	// Unused implementation for the keyReleased method of the KeyListener interface.
	public void keyReleased(KeyEvent e) {} 

}
