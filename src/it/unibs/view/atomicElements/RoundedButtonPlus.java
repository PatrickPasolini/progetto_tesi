package it.unibs.view.atomicElements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RoundedButtonPlus extends RoundedButton {

	public RoundedButtonPlus(Color defaultColor) {
		super("", defaultColor);
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g.create();
         // Disegna il simbolo + al centro
         int w = getWidth();
         int h = getHeight();
         g2.setColor(Color.WHITE);
         g2.setStroke(new BasicStroke(2));
         g2.drawLine(w/2, h/3, w/2, 2*h/3);
         g2.drawLine(w/3, h/2, 2*w/3, h/2);
         g2.dispose();
	}
	

}
