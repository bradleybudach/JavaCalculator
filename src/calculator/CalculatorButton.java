package calculator;

import javax.swing.JButton;
import java.awt.Color;

public class CalculatorButton extends JButton {
	protected Color color;
	protected int xPosition;
	protected int yPosition;
	protected int columnWidth;
	
	public CalculatorButton(int x, int y, int width, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
		columnWidth = width;
	}
	
	
	public int getXGridPos() {
		return xPosition;
	}
	
	public int getYGridPos() {
		return yPosition;
	}
	
	public Color getBackgroundColor() {
		return color;
	}
	
}
