package calculator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;

public class CalculatorButton extends JButton {
	private Color color;
	private int xPosition;
	private int yPosition;
	private int columnWidth = 1;
	private double columnHeight = 1;
	private Font font = new Font("DialogInput", Font.PLAIN, 40);
	
	public CalculatorButton(int x, int y, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
	}
	
	public CalculatorButton(int x, int y, int width, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
		columnWidth = width;
	}
	
	public void setCustomColumnHeight(double height) {
		columnHeight = height;
	}
	
	public void setCustomFont(Font f) {
		font = f;
	}
	
	public void displaySelf(CalculatorApp app) {
		// Sets the styling for all the buttons.
		setBackground(color);
		setForeground(Color.WHITE);
		setFont(font);
		setFocusable(false);
		
		// adds buttons to their correct position on the grid
		GridBagConstraints c = new GridBagConstraints(); // constraints to describe how the item is displayed
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = columnWidth;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = columnHeight;
		c.gridx = xPosition;
		c.gridy = yPosition;
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		addActionListener(app);
		app.add(this, c);
	}
}
