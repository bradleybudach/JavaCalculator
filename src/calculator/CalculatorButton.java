package calculator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalculatorButton extends JButton implements MouseListener {
	private Color color;
	private Color hoverColor;
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
		hoverColor = new Color(color.getRed()+30, color.getGreen()+30, color.getBlue()+30);
	}
	
	public CalculatorButton(int x, int y, int width, Color c, String btnTxt) {
		super(btnTxt);
		color = c;
		xPosition = x;
		yPosition = y;
		columnWidth = width;
		hoverColor = new Color(color.getRed()+30, color.getGreen()+30, color.getBlue()+30);
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
		addMouseListener(this);
		app.add(this, c);
	}
	
	@Override
    public void mouseEntered(MouseEvent e)
    {
        
        setBackground(hoverColor);
    }

	@Override
	public void mouseExited(MouseEvent e)
	{
	     setBackground(color);
	}
	
	@Override
	public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}
}
