package calculator;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorApp extends JFrame implements ActionListener, KeyListener {
	private CalculatorButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnSubtract, btnDivide,
			btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnDot, btnBack, btnClear;
	private ArrayList<CalculatorButton> buttons;
	private JTextField IOArea;

	public CalculatorApp() {
		setTitle("Calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setSize(450, 820);
		setBackground(Color.BLACK);
		getContentPane().setBackground(Color.DARK_GRAY);

		// Initializes components:
		btn1 = new CalculatorButton(0, 3, 1, Color.BLACK, "1");
		btn2 = new CalculatorButton(1, 3, 1, Color.BLACK, "2");
		btn3 = new CalculatorButton(2, 3, 1, Color.BLACK, "3");
		btn4 = new CalculatorButton(0, 4, 1, Color.BLACK, "4");
		btn5 = new CalculatorButton(1, 4, 1, Color.BLACK, "5");
		btn6 = new CalculatorButton(2, 4, 1, Color.BLACK, "6");
		btn7 = new CalculatorButton(0, 5, 1, Color.BLACK, "7");
		btn8 = new CalculatorButton(1, 5, 1, Color.BLACK, "8");
		btn9 = new CalculatorButton(2, 5, 1, Color.BLACK, "9");
		btn0 = new CalculatorButton(0, 6, 1, Color.BLACK, "0");
		btnDot = new CalculatorButton(1, 6, 1, Color.BLACK, ".");
		btnAdd = new CalculatorButton(3, 2, 1, Color.DARK_GRAY, "+");
		btnSubtract = new CalculatorButton(3, 3, 1, Color.DARK_GRAY, "-");
		btnDivide = new CalculatorButton(3, 4, 1, Color.DARK_GRAY, "/");
		btnMultiply = new CalculatorButton(3, 5, 1, Color.DARK_GRAY, "*");
		btnMod = new CalculatorButton(2, 2, 1, Color.DARK_GRAY, "%");
		btnPow = new CalculatorButton(3, 6, 1, Color.DARK_GRAY, "^");
		btnParenthesisOpen = new CalculatorButton(0, 2, 1, Color.DARK_GRAY, "(");
		btnParenthesisClose = new CalculatorButton(1, 2, 1, Color.DARK_GRAY, ")");
		btnEquals = new CalculatorButton(2, 6, 1, new Color(0, 120, 0), "=");
		btnBack = new CalculatorButton(2, 1, 2, new Color(100, 20, 20), "\u232B");
		btnClear = new CalculatorButton(0, 1, 2, new Color(100, 20, 20), "C");
		IOArea = new JTextField();

		// adds all buttons to the list of buttons.
		buttons = new ArrayList<CalculatorButton>(
				Arrays.asList(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnAdd, btnSubtract,
						btnDivide, btnMultiply, btnMod, btnPow, btnParenthesisOpen, btnParenthesisClose, btnEquals, btnBack, btnClear));

		// Sets Styles:
		Font textFont = new Font("DialogInput", Font.PLAIN, 40); // Calculator font

		// Grid Defaults:
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		for (int i = 0; i < buttons.size(); i++) {
			CalculatorButton calcBtn = buttons.get(i);

			// Sets the styling for all the buttons.
			calcBtn.setBackground(calcBtn.color);
			calcBtn.setForeground(Color.WHITE);
			calcBtn.setFont(textFont);
			calcBtn.setFocusable(false);

			// adds buttons to their correct position on the grid
			c.gridwidth = calcBtn.columnWidth;
			c.gridx = calcBtn.xPosition;
			c.gridy = calcBtn.yPosition;
			add(calcBtn, c);

			calcBtn.addActionListener(this); // adds listener to all buttons
		}

		// Sets styles and displays the Input/Output text-box in the GUI:
		IOArea.setBackground(Color.BLACK);
		IOArea.setForeground(Color.WHITE);
		IOArea.setFont(textFont);
		IOArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		IOArea.setEditable(false);
		IOArea.setCaretColor(Color.WHITE);
		IOArea.getCaret().setVisible(true);

		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		add(IOArea, c);

		IOArea.addKeyListener(this);

		addWindowFocusListener(new WindowAdapter() { // Makes the caret reappear in the text-box when the window is selected (after selecting a different window)
			public void windowGainedFocus(WindowEvent e) {
				IOArea.getCaret().setVisible(true);
			}
		});
	}

	public String readInputFromConsole() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter epxression: ");
		return in.nextLine();
	}

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
	public void keyTyped(KeyEvent e) {
		Character key = e.getKeyChar(); // gets the character of a the key-press

		if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8'
				|| key == '9' || key == '0' || key == '+' || key == '-' || key == '/' || key == '*' || key == '%'
				|| key == '^' || key == '(' || key == ')' || key == '.') { // key typed that corresponds with the buttons
			int insertPos = IOArea.getCaretPosition();

			IOArea.setText(insertIntoText(IOArea.getText(), key.toString(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			IOArea.setCaretPosition(insertPos + 1); // Updates the caret position to be in the right spot.
		} else if (key == '=' && !IOArea.getText().equals("")) { // equals key typed
			btnEquals.doClick(); // clicks the equals button to get the answer
		}
	}

	@Override
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEquals) { // equals button pressed
			double answer = Calculator.evaluateExpression(IOArea.getText());
			if (answer % 1 == 0) { // if the number is a whole number (decimal is 0)
				IOArea.setText(Integer.toString((int) answer));
			} else {
				IOArea.setText(Double.toString(answer));
			}
		} else if (e.getSource() == btnBack) { // backspace button
			String existingText = IOArea.getText();
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
		} else if (e.getSource() == btnClear) { // clear button
			IOArea.setText(""); // clears text
		} else { // any other button
			CalculatorButton btn = (CalculatorButton) e.getSource(); // gets the source of which button is pressed as a CalculatorButton
			int insertPos = IOArea.getCaretPosition();

			IOArea.setText(insertIntoText(IOArea.getText(), btn.getText(), insertPos)); // Inserts the character at the correct position in the text and updates the IOArea text-box.
			IOArea.setCaretPosition(insertPos + 1); // Updates the caret position to be in the right spot.
		}
	}

	// Unused implementation for the keyReleased method of the KeyListener interface.
	@Override
	public void keyReleased(KeyEvent e) {
	}

	// Adds a character at a given point in a string.
	public String insertIntoText(String existingText, String insertString, int insertPos) {
		String newText = new String();

		if (insertPos == existingText.length()) { // if the insert position is at the end of the existing text.
			newText = existingText + insertString;
		} else {
			for (int i = 0; i < existingText.length(); i++) {
				if (i == insertPos) {
					newText += insertString;
				}

				newText += existingText.charAt(i);
			}
		}

		return newText;
	}

	// Removes a character from a given point in a string.
	public String removeFromText(String existingText, int removeAt) {
		if (removeAt == existingText.length()) {
			existingText = existingText.substring(0, existingText.length() - 1);
		} else if (removeAt > 0) {
			existingText = existingText.substring(0, removeAt - 1)
					+ existingText.substring(removeAt, existingText.length());
		}

		return existingText;
	}

}
