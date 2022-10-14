package calculator;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class CalculatorButton extends JButton {
	protected Color color;
	protected int xPosition;
	protected int yPosition;
	protected int columnWidth = 1;
	protected double columnHeight = 1;
	protected Font font = new Font("DialogInput", Font.PLAIN, 40);
	
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
}
