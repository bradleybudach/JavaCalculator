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
 * 		- import java.awt.*;
 * 		- import java.awt.event.*;
 * 		- import javax.swing.*;
 * 		- import java.util.ArrayList;
 * 		- import java.util.Arrays;
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

//import java.util.Scanner; console support
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorApp extends JFrame implements ActionListener, KeyListener {
	// TODO: have history be a drop-down
	private CalculatorButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSubtract, btnDivide,
			btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnDot, btnBack, btnClear, btnHistory, 
			btnAbs, btnSin, btnCos, btnTan, btnInvert, btnPI, btnSqrt, btnE, btnLog, btnLN, btnArcsin, btnArccos, btnArctan; // UI buttons
	private JTextField IOArea; // Input/Output area
	private String previousAns; // Answer of the last expression.

	/* This method is for building the GUI and activate all the buttons 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public CalculatorApp() {
		setTitle("Calculator"); // sets title
		setDefaultCloseOperation(EXIT_ON_CLOSE); // closes program when X button clicked
		setLayout(new GridBagLayout()); // grid layout with custom rows
		setSize(550, 900); // sets the default size for the frame	
		getContentPane().setBackground(Color.DARK_GRAY); // Frame background color
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\wb8295nb\\eclipse-workspace\\GroupProject\\src\\logo.png"); // gets the icon image
		setIconImage(icon); // sets the icon
		
		previousAns = ""; // initializes previouAns
		Font historyFont = new Font("DialogInput", Font.PLAIN, 20); // Custom font for the History bar
		Font smallerFont = new Font("DialogInput", Font.PLAIN, 30); // Font for grid items that need to have smaller text.
		
		// Initializes components:
		btn1 = new CalculatorButton(1, 7, Color.BLACK, "1");	// Button for pressing 1 on the calculator
		btn2 = new CalculatorButton(2, 7, Color.BLACK, "2");	// Button for pressing 2 on the calculator
		btn3 = new CalculatorButton(3, 7, Color.BLACK, "3");	// Button for pressing 3 on the calculator
		btn4 = new CalculatorButton(1, 6, Color.BLACK, "4");	// Button for pressing 4 on the calculator
		btn5 = new CalculatorButton(2, 6, Color.BLACK, "5");	// Button for pressing 5 on the calculator
		btn6 = new CalculatorButton(3, 6, Color.BLACK, "6");	// Button for pressing 6 on the calculator
		btn7 = new CalculatorButton(1, 5, Color.BLACK, "7");	// Button for pressing 7 on the calculator
		btn8 = new CalculatorButton(2, 5, Color.BLACK, "8");	// Button for pressing 8 on the calculator
		btn9 = new CalculatorButton(3, 5, Color.BLACK, "9");	// Button for pressing 9 on the calculator
		btn0 = new CalculatorButton(1, 8, Color.BLACK, "0");	// Button for pressing 0 on the calculator
		btnDot = new CalculatorButton(2, 8, Color.BLACK, ".");		// Button for pressing . on the calculator
		btnAdd = new CalculatorButton(4, 7, Color.DARK_GRAY, "+");	// Button for pressing + on the calculator
		btnSubtract = new CalculatorButton(4, 6, Color.DARK_GRAY, "-");		// Button for pressing - on the calculator
		btnDivide = new CalculatorButton(4, 4, Color.DARK_GRAY, "\u00F7"); btnDivide.setInsertText("/");	// Button for pressing / on the calculator
		btnMultiply = new CalculatorButton(4, 5, Color.DARK_GRAY, "\u00D7"); btnMultiply.setInsertText("*");	// Button for pressing * on the calculator
		btnMod = new CalculatorButton(3, 4, Color.DARK_GRAY, "%");			// Button for pressing % on the calculator
		btnPow = new CalculatorButton(4, 3, Color.DARK_GRAY, "x\u207F"); btnPow.setInsertText("^"); btnPow.setCustomFont(new Font("", Font.PLAIN, 40)); // Button for pressing ^ on the calculator
		btnParenthesisOpen = new CalculatorButton(1, 4, Color.DARK_GRAY, "(");		// Button for pressing ( on the calculator
		btnParenthesisClose = new CalculatorButton(2, 4, Color.DARK_GRAY, ")");		// Button for pressing ) on the calculator
		btnEquals = new CalculatorButton(3, 8, new Color(0, 120, 0), "="); btnEquals.setCustomColumnWidth(2);			// Button for pressing = on the calculator
		btnBack = new CalculatorButton(3, 2, new Color(100, 20, 20), "\u232B"); /*btnBack.setCustomColumnHeight(0.5);*/ btnBack.setCustomColumnWidth(2);
		btnClear = new CalculatorButton(1, 2, new Color(100, 20, 20), "C"); /*btnBack.setCustomColumnHeight(0.5);*/ btnClear.setCustomColumnWidth(2);	// Button for pressing "C" on the calculator
		btnHistory = new CalculatorButton(0, 0, Color.DARK_GRAY, ""); btnHistory.setCustomFont(historyFont); btnHistory.setCustomColumnHeight(0.5); btnHistory.setCustomColumnWidth(5);	// Space for showing the last answer 
		btnAbs = new CalculatorButton(0, 2, Color.DARK_GRAY, "|");  btnAbs.setInsertText("abs()");
		btnInvert = new CalculatorButton(0, 3, Color.DARK_GRAY, "inv"); btnInvert.setCustomFont(smallerFont); 
		btnSin = new CalculatorButton(0, 4, Color.DARK_GRAY, "sin"); btnSin.setCustomFont(smallerFont); btnSin.setInsertText("sin()");
		btnCos = new CalculatorButton(0, 5, Color.DARK_GRAY, "cos"); btnCos.setCustomFont(smallerFont); btnCos.setInsertText("cos()");
		btnTan = new CalculatorButton(0, 6, Color.DARK_GRAY, "tan"); btnTan.setCustomFont(smallerFont); btnTan.setInsertText("tan()");
		btnLog = new CalculatorButton(0, 7, Color.DARK_GRAY, "log"); btnLog.setCustomFont(smallerFont); btnLog.setInsertText("log()");
		btnLN = new CalculatorButton(0, 8, Color.DARK_GRAY, "ln"); btnLN.setCustomFont(smallerFont); btnLN.setInsertText("ln()");
		btnE = new CalculatorButton(1, 3, Color.DARK_GRAY, "e");
		btnPI = new CalculatorButton(2, 3, Color.DARK_GRAY, "\u03C0"); btnPI.setCustomFont(smallerFont); btnPI.setInsertText("pi");
		btnSqrt= new CalculatorButton(3, 3, Color.DARK_GRAY, "\u221A"); btnSqrt.setCustomFont(smallerFont); btnSqrt.setInsertText("sqrt()");
		IOArea = new JTextField();
		
		// adds all buttons to the list of buttons.
		ArrayList<CalculatorButton> buttons = new ArrayList<CalculatorButton>(
				Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnAdd, btnSubtract,
						btnDivide, btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnBack, 
						btnClear, btnHistory, btnAbs, btnSin, btnCos, btnTan, btnInvert, btnPI, btnSqrt, btnE, btnLog, btnLN));

		// Sets Styles:
		for (CalculatorButton btn : buttons) {
			btn.displaySelf(this);		//All the buttons in a self display and activate with the mouse button
		}

		// History button style:
		btnHistory.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));		//Keep the 
		btnHistory.setPreferredSize(new Dimension(0, 25));
		btnHistory.setHorizontalAlignment(SwingConstants.LEFT);
		
		// Input/Output area styles:
		IOArea.setBackground(Color.BLACK);		// Setting Background color to black
		IOArea.setForeground(Color.WHITE);		// setting Foreground color to white 
		IOArea.setFont(new Font("DialogInput", Font.PLAIN, 40));			// Setting the font 
		IOArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		IOArea.setEditable(false);				// Make the area code unable to edit 
		IOArea.setCaretColor(Color.WHITE);
		IOArea.getCaret().setVisible(true);		// Make it visible to the user 

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
	}

//	Console App Support:
//	public String readInputFromConsole() {
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter epxression: ");
//		return in.nextLine();
//	}

	/* This method is the main method 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - set the app visible
	 */
	public static void main(String[] args) {
		CalculatorApp app = new CalculatorApp();
		app.setVisible(true);

		// Console app support:
		// while (true) {
		// String input = app.readInputFromConsole();
		// System.out.println("The answer is: " + Calculator.evaluateExpression(input) +
		// "\n");
		// }
	}

	@Override
	/* This method is for taking the key buttons pressed 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void keyTyped(KeyEvent e) {
		Character key = e.getKeyChar(); // gets the character of a the key-press

		if (Character.isDigit(key) || Character.isAlphabetic(key) || key == '+' || key == '-' || key == '/' || 
				key == '*' || key == '%' || key == '^' || key == '(' || key == ')' || key == '.' || key == '|') { // key typed that corresponds with the buttons
			int insertPos = IOArea.getCaretPosition();
			
			IOArea.setText(insertIntoText(IOArea.getText(), key.toString(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			IOArea.setCaretPosition(insertPos + 1); // Updates the caret position to be in the right spot.
		} else if (key == '=' && !IOArea.getText().equals("")) { // equals key typed
			btnEquals.doClick(); // clicks the equals button to get the answer
		}
	}

	@Override
	/* This method is for taking the codes in a variable 
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		String existingText = IOArea.getText();

		if (key == KeyEvent.VK_ENTER && existingText.length() > 0) { // enter key typed
			btnEquals.doClick(); // clicks the equals button to get the answer
		} else if (key == KeyEvent.VK_BACK_SPACE && existingText.length() > 0) { // backspace key clicked
			int removeAt = IOArea.getCaretPosition();

			IOArea.setText(removeFromText(existingText, removeAt));

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
	/* This method is for taking the codes in a variable
	 * Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void actionPerformed(ActionEvent e) {
		String existingText = IOArea.getText();
		Object source = e.getSource();
		
		if (source == btnEquals) { 	// equals button pressed
			if (existingText.length() > 0) {
				double answer = Calculator.evaluateExpression(existingText);
				if (answer % 1 == 0) { 		// if the number is a whole number (decimal is 0)
					String ansString = Integer.toString((int) answer);
					IOArea.setText(ansString);
					previousAns = ansString;
				} else {
					String ansString = Double.toString(answer);
					IOArea.setText(ansString);
					previousAns = ansString;
				}
				
				btnHistory.setText(existingText + " = " + previousAns);
			}
		} else if (source == btnBack) { // backspace button
			if (existingText.length() > 0) {
				int removeAt = IOArea.getCaretPosition();

				IOArea.setText(removeFromText(existingText, removeAt));

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
			if (previousAns.length() > 0) {
				IOArea.setText(IOArea.getText() + previousAns); // appends the answer to the I/O text-box
			}
		}  else { // any other button
			CalculatorButton btn = (CalculatorButton) source; // gets the source of which button is pressed as a CalculatorButton
			int insertPos = IOArea.getCaretPosition();

			IOArea.setText(insertIntoText(IOArea.getText(), btn.getInsertText(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			IOArea.setCaretPosition(insertPos + btn.getInsertText().length()); // Updates the caret position to be in the right spot.
		}
	}
	
	// Adds a character at a given point in a string.
	/* Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public String insertIntoText(String existingText, String insertString, int insertPos) {
		String newText = new String();

		if (insertPos == existingText.length()) { 	// if the insert position is at the end of the existing text.
			newText = existingText + insertString;
		} else {
			for (int i = 0; i < existingText.length(); i++) {		// works until "i" is smaller than existing text length 
				if (i == insertPos) {
					newText += insertString;
				}

				newText += existingText.charAt(i);
			}
		}

		return newText;
	}

	// Removes a character from a given point in a string.
	/* Inputs:
	 * 		- math problems 
     * Outputs: 
     * 		- answer of the math problem
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public String removeFromText(String existingText, int removeAt) {
		if (removeAt == existingText.length()) { // if the caret is at the end of the text
			existingText = existingText.substring(0, existingText.length() - 1); // removes the last character
		} else if (removeAt > 0) { // if the caret is somewhere in the text
			existingText = existingText.substring(0, removeAt - 1) + existingText.substring(removeAt, existingText.length()); // removes a character at the given position
		}

		return existingText;
	}
	
	
	@Override
	/* This method is for ???
	 * Inputs:
	 * 		- none 
     * Outputs: 
     * 		- none
     * Pseudocode:
     *     - ????
     *     - 
	 */
	public void keyReleased(KeyEvent e) {
	} // Unused implementation for the keyReleased method of the KeyListener
		// interface.

}
